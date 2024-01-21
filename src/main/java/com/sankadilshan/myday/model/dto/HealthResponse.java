package com.sankadilshan.myday.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

public record HealthResponse(HttpStatus code, String status, String message, Date timeStamp) {
}
