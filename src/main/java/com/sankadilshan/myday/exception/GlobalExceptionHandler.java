package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(RowMapperException.class)
    public ResponseEntity<Object> handleRowMapperException(RowMapperException rowMapperException){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return errorResponseBuilder(status, rowMapperException.getMessage());
    }

    @ExceptionHandler(value = {UserNameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UserNameNotFoundException userNameNotFoundException) {
        HttpStatus status= HttpStatus.NOT_FOUND;
        return errorResponseBuilder(status, userNameNotFoundException.getMessage());
    }

    @ExceptionHandler(UserSignUpFailedException.class)
    public ResponseEntity<Object> handleSignupFaildException(UserSignUpFailedException userSignUpFailedException) {
        HttpStatus status= HttpStatus.INTERNAL_SERVER_ERROR;
        return errorResponseBuilder(status, userSignUpFailedException.getMessage());
    }

    @ExceptionHandler(InvalidUserNameException.class)
    public ResponseEntity<Object> handleInvalidUsernameException(InvalidUserNameException invalidUserNameException){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return errorResponseBuilder(status, invalidUserNameException.getMessage());
    }
    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<Object> handleIBadCredentailException(BadCredentialException badCredentialException){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return errorResponseBuilder(status, badCredentialException.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException dataAccessException){
        HttpStatus status = HttpStatus.NO_CONTENT;
        return errorResponseBuilder(status, dataAccessException.getMessage());
    }
    private ResponseEntity<Object> errorResponseBuilder(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(status,status.value(), message, LocalDateTime.now());
        return ErrorResponseBuilder.build(errorResponse);
    }
}
