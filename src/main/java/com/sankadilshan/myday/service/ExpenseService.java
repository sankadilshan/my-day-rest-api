package com.sankadilshan.myday.service;

import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;

import java.util.List;
import java.util.Map;

public interface ExpenseService {
    List<ExpenseResponse> queryAllExpenses();

    List<Map<String, Object>> queryAllUsersWithExpense();

    List<ExpenseResponse> queryExpenseByUserId(Long id);
}
