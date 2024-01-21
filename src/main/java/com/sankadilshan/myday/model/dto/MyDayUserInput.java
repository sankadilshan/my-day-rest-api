package com.sankadilshan.myday.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MyDayUserInput {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int roleId;
}
