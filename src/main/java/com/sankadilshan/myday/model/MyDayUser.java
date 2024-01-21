package com.sankadilshan.myday.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyDayUser {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String roles;
}
