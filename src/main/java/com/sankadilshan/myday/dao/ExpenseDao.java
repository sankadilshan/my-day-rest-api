package com.sankadilshan.myday.dao;

import com.sankadilshan.myday.model.dto.ExpenseInput;
import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;

import java.util.List;
import java.util.Map;

public interface ExpenseDao {

    List<ExpenseResponse> queryAllExpenses();

    List<MydayUserResponse> queryAllMydayUsers();

    List<Map<String, Object>> queryAllUsersWithExpenses();

    List<ExpenseResponse> queryExpenseByUserId(Long id);

    void inserExpense(ExpenseInput expenseInput) ;
}
