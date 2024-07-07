package com.sankadilshan.myday.service;

import com.sankadilshan.myday.model.dto.ExpenseInput;
import com.sankadilshan.myday.model.dto.ExpenseResponse;

import java.util.List;
import java.util.Map;

public interface ExpenseService {
    List<ExpenseResponse> queryAllExpenses();

    List<Map<String, Object>> queryAllUsersWithExpense();

    List<ExpenseResponse> queryExpenseByUserId(Long id);

    ExpenseResponse insertExpense(ExpenseInput expense);

    List<Map<String, Object>> getSummary(Map<String, Object> input) throws Exception;
}
