package com.sankadilshan.myday.controller;

import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.GeneralResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.service.ExpenseService;
import com.sankadilshan.myday.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ExpenseController {
    
    private ExpenseService expenseService;

    @Autowired
    private ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping("/expense")
    public GeneralResponse queryAllExpenses() {
        log.info("Expense Service :: query all expense :: controller level");
        List<ExpenseResponse> expenseResponses = expenseService.queryAllExpenses();
        return ResponseUtil.getGeneralResponse(expenseResponses);
    }

    @GetMapping("/expense/user")
    public GeneralResponse queryExpenseByUserId(@RequestParam("userId") Long id) {
        log.info("Expense Service :: query expense by userId: {} :: controller level", id);
        List<ExpenseResponse> expenseResponses = expenseService.queryExpenseByUserId(id);
        return ResponseUtil.getGeneralResponse(expenseResponses);
    }

    @GetMapping("/mydayuser")
    public GeneralResponse queryAllMydayUsers() {
        log.info("Expense Service :: query all mydayuser :: controller level");
        List<MydayUserResponse> mydayUserResponses = expenseService.queryAllMydayUsers();
        return ResponseUtil.getGeneralResponse(mydayUserResponses);
    }

    @GetMapping("/")
    public GeneralResponse queryAllMydayUaersWithExpense() {
        log.info("Expense Service :: query all mydayuser, expense :: controller level");
        List<Map<String, Object>> queryMapResponse = expenseService.queryAllUsersWithExpense();
        return ResponseUtil.getGeneralResponse(queryMapResponse);

    }
}
