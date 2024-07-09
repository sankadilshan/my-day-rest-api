package com.sankadilshan.myday.controller;

import com.sankadilshan.myday.Constants;
import com.sankadilshan.myday.model.dto.AuthInput;
import com.sankadilshan.myday.model.dto.AuthResponse;
import com.sankadilshan.myday.model.dto.GeneralResponse;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import com.sankadilshan.myday.service.AuthService;
import com.sankadilshan.myday.utils.ResponseUtil;
import com.sankadilshan.myday.validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth Controller", description = "all the auth related request")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Sign in",
    security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sign in", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "401", description = "",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @PostMapping("/sign")
    public ResponseEntity<GeneralResponse> sign(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "user sign in", required = true, content = @Content(schema = @Schema(implementation = AuthInput.class))) @RequestBody Map<String, Object> user) {
        AuthResponse authResponse = authService.sign(user);
        return ResponseUtil.getGeneralResponse(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody MyDayUserInput userInput) {
        log.info("Auth Service :: signup new user :: controller level");
        Map<String, Object> validatorInput = new HashMap<>();
        validatorInput.put("operation", "SIGN_UP");
        validatorInput.put("body",userInput);
        UserValidator.validator(validatorInput);
        AuthResponse authResponse = authService.signup(userInput);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody Map<String, Object> user) {
        log.info("Auth Service :: generate jwt token :: controller level");
        String token = authService.generateToken(user);
        AuthResponse authResponse = AuthResponse.builder().token(token).message(Constants.TOKEN_GENERATION_SUCCESS).status(HttpStatus.OK).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
