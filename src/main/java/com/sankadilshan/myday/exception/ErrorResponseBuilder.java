package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.model.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class ErrorResponseBuilder {

    public static ResponseEntity<Object> build(ErrorResponse error){
        return new ResponseEntity<>(error, error.getStatus());
    }
}
