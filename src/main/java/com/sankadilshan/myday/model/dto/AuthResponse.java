package com.sankadilshan.myday.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthResponse {

    private String token;
    private String message;
    private HttpStatus status;

}
