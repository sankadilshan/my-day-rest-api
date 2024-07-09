package com.sankadilshan.myday.model.dto;

import com.sankadilshan.myday.enums.CustomHttpStatus;
import lombok.*;

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
