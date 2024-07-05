package com.sankadilshan.myday.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Schema(description = "Authentication user input details")
public class AuthInput {
    @Schema(description = "email address", example = "mydayuser20240605@yopmail.com")
    private String email;
    @Schema(description = "user password", example = "mydayuser20240605")
    private String password;
}
