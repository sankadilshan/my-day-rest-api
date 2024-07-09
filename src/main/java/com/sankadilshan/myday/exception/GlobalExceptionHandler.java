package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.enums.CustomHttpStatus;
import com.sankadilshan.myday.model.dto.ErrorResponse;
import com.sankadilshan.myday.utils.ErrorResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<Object> handleAuthenticationException(Exception e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return errorResponseBuilder(null, "Authentication Failed at controller advice");
    }

    @ExceptionHandler(RowMapperException.class)
    public ResponseEntity<Object> handleRowMapperException(RowMapperException rowMapperException){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return errorResponseBuilder(null, rowMapperException.getMessage());
    }

    @ExceptionHandler(value = {UserNameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UserNameNotFoundException userNameNotFoundException) {
        HttpStatus status= HttpStatus.NOT_FOUND;
        return errorResponseBuilder(null, userNameNotFoundException.getMessage());
    }

    @ExceptionHandler(UserSignUpFailedException.class)
    public ResponseEntity<Object> handleSignupFaildException(UserSignUpFailedException userSignUpFailedException) {
        HttpStatus status= HttpStatus.INTERNAL_SERVER_ERROR;
        return errorResponseBuilder(null, userSignUpFailedException.getMessage());
    }

    @ExceptionHandler(UserValidationFailedException.class)
    public ResponseEntity<Object> handleInvalidFirstNameException(UserValidationFailedException userValidationFailedException) {
        CustomHttpStatus status = userValidationFailedException.getCustomHttpStatus();
        return errorResponseBuilder(status, userValidationFailedException.getMessage());
    }
    @ExceptionHandler(InvalidUserNameException.class)
    public ResponseEntity<Object> handleInvalidUsernameException(InvalidUserNameException invalidUserNameException){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return errorResponseBuilder(null, invalidUserNameException.getMessage());
    }
    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<Object> handleIBadCredentailException(BadCredentialException badCredentialException){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return errorResponseBuilder(null, badCredentialException.getMessage());
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<Object> handleDataAccessException(DataAccessException dataAccessException, WebRequest webRequest){
        CustomHttpStatus status = CustomHttpStatus.DB_EXPENSE_DATA_FETCH_ERROR;
        log.error("Occurred data access exception: {}", dataAccessException.getMessage(), dataAccessException);
        return errorResponseBuilder(status, dataAccessException.getMessage());
    }

    @ExceptionHandler(value = {UserTokenExpiredException.class})
    public ResponseEntity<Object>  handleUsertokenExpiredException( UserTokenExpiredException userTokenExpiredException) {
        log.error("Occurred data access exception: {}", userTokenExpiredException.getMessage(), userTokenExpiredException);
        CustomHttpStatus status = CustomHttpStatus.USER_UNAUTHORIZED;
        return errorResponseBuilder( status, userTokenExpiredException.getMessage());
    }

    @ExceptionHandler(value = {ConvertObjectToMapException.class})
    public ResponseEntity<Object> handleDataAccessException(ConvertObjectToMapException convertObjectToMapException, WebRequest webRequest){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Occurred data access exception: {}", convertObjectToMapException.getMessage(), convertObjectToMapException);
        return handleExceptionInternal(convertObjectToMapException, convertObjectToMapException.getMessage(), new HttpHeaders(), status, webRequest);
//        return errorResponseBuilder(null, dataAccessException.getMessage());
    }
    private ResponseEntity<Object> errorResponseBuilder(CustomHttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(status,status.value(), message, LocalDateTime.now());
        return ErrorResponseBuilder.build(errorResponse);
    }
}
