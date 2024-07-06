package com.sankadilshan.myday.model.dto;

import com.sankadilshan.myday.enums.ExpenseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseInput {
    private double amount;
    private ExpenseType type;
}
