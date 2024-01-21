package com.sankadilshan.myday.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExpenseResponse {
    private Long userId;
    private double amount;
    private String type;
    private LocalDate expenseDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
