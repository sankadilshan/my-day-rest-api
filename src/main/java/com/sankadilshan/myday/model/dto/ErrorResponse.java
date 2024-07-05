package com.sankadilshan.myday.model.dto;

import com.sankadilshan.myday.utils.CustomHttpStatus;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ErrorResponse {

    private CustomHttpStatus status;
    private int code;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
