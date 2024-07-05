package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.model.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ErrorResponseBuilder {

    public static ResponseEntity<Object> build(ErrorResponse error){
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, error.getCode());
    }
}
