package com.sankadilshan.myday.controller;

import com.sankadilshan.myday.model.dto.ExpenseInput;
import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.GeneralResponse;
import com.sankadilshan.myday.service.ExpenseService;
import com.sankadilshan.myday.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    
    private ExpenseService expenseService;

    @Autowired
    private ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping("/")
    public GeneralResponse queryAllExpenses() {
        log.info("Expense Service :: query all expense :: controller level");
        List<ExpenseResponse> expenseResponses = expenseService.queryAllExpenses();
//        throw new InvalidFirstNameException("sanka");
        return ResponseUtil.getGeneralResponse(expenseResponses);
    }

    @GetMapping("/{userId}")
    public GeneralResponse queryAllExpensesByUserId(@PathVariable(name = "userId") Long userId) {
        log.info("Expense Service :: query all expenses by user id :: controller level");
        List<ExpenseResponse> expenseResponses = expenseService.queryExpenseByUserId(userId);
        return ResponseUtil.getGeneralResponse(expenseResponses);
    }

    @Operation(summary = "insert Expense",
            security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully insert Expense", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseResponse.class))}),
            @ApiResponse(responseCode = "401", description = "",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "end point not found",
                    content = @Content) })
   @PostMapping("/")
   public GeneralResponse insertExpense(@RequestBody ExpenseInput expense) {
       log.info("Expense Service :: insert an expense :: controller level");
       ExpenseResponse expenseResponse = expenseService.insertExpense(expense);
       return ResponseUtil.getGeneralResponse(expenseResponse);
   }

    @GetMapping("/user")
    public GeneralResponse queryExpenseByUserId(@RequestParam("userId") Long id) {
        log.info("Expense Service :: query expense by userId: {} :: controller level", id);
        List<ExpenseResponse> expenseResponses = expenseService.queryExpenseByUserId(id);
        return ResponseUtil.getGeneralResponse(expenseResponses);
    }

    @GetMapping("/user/expenses")
    public GeneralResponse queryAllMydayUaersWithExpense() {
        log.info("Expense Service :: query all mydayuser, expense :: controller level");
        List<Map<String, Object>> queryMapResponse = expenseService.queryAllUsersWithExpense();
        return ResponseUtil.getGeneralResponse(queryMapResponse);

    }
}
