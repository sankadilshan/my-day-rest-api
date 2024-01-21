package com.sankadilshan.myday.model;

import com.sankadilshan.myday.enums.ExpenseType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Expense {
    private Long id;
    private Long userId;
    private double amount;
    private ExpenseType type;
    private Date expenseDate;
    private Date createdDate;
    private Date modifiedDate;
}
