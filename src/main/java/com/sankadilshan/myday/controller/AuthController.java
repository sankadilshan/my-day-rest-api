package com.sankadilshan.myday.controller;

import com.sankadilshan.myday.Constants;
import com.sankadilshan.myday.model.dto.AuthResponse;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import com.sankadilshan.myday.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService= authService;
    }

    @PostMapping("/sign")
    public ResponseEntity<Object> sign(@RequestBody Map<String, Object> user) {
        AuthResponse authResponse = authService.sign(user);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody MyDayUserInput userInput) {
        logger.info("Auth Service :: signup new user :: controller level");
        AuthResponse authResponse = authService.signup(userInput);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody Map<String, Object> user) {
        logger.info("Auth Service :: generate jwt token :: controller level");
        String token= authService.generateToken(user);
        AuthResponse authResponse= AuthResponse.builder()
                .token(token)
                .message(Constants.TOKEN_GENERATION_SUCCESS)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
