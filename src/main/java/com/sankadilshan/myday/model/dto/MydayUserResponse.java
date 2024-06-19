package com.sankadilshan.myday.model.dto;

import com.sankadilshan.myday.model.Roles;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class MydayUserResponse {

    private String email;
    private String firstName;
    private String lastName;
    private int roleId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
