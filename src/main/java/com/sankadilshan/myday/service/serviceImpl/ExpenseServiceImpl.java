package com.sankadilshan.myday.service.serviceImpl;

import com.sankadilshan.myday.dao.ExpenseDao;
import com.sankadilshan.myday.model.dto.ExpenseInput;
import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.service.ExpenseService;
import com.sankadilshan.myday.utils.MapUtil;
import com.sankadilshan.myday.utils.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDao expenseDao;
    @Autowired
    public ExpenseServiceImpl(ExpenseDao expenseDao) {
        this.expenseDao= expenseDao;
    }

    @Override
    public List<ExpenseResponse> queryAllExpenses() throws RuntimeException {
        log.info("Expense Service :: query all expenses :: service level");
        return expenseDao.queryAllExpenses();
    }


    @Override
    public List<Map<String, Object>> queryAllUsersWithExpense() {
        log.info("Expense Service :: query all mydayuser, expense :: service level");

        List<Map<String, Object>> queryMapResponse = expenseDao.queryAllUsersWithExpenses();
        int size = queryMapResponse.size();
        List<Map<String, Object>> response= new ArrayList<>();

        if (size > 0) {
            List<Long> ids = queryMapResponse.stream().map(qr-> (long)qr.get("mid")).toList().stream().distinct().toList();
            ids.forEach(
                    id-> {
                        Map<String, Object> mapById = queryMapResponse.stream().filter(qm -> qm.get("mId") == id).findFirst().orElseGet(null);
                        List<Map<String, Object>> expenseMapById = queryMapResponse.stream().filter(qm -> qm.get("mId") == id).toList();
                        Map<String, Object> responseMap = null;
                        if (mapById != null) {
                            responseMap = MapUtil.cloneMap(mapById,
                                    PersistenceUtil.MydayUser.MID,
                                    PersistenceUtil.MydayUser.EMAIL,
                                    PersistenceUtil.MydayUser.FIRST_NAME,
                                    PersistenceUtil.MydayUser.LAST_NAME,
                                    PersistenceUtil.MydayUser.MCREATED_DATE,
                                    PersistenceUtil.MydayUser.MMODIFIED_DATE
                            );
                            
                            List<Map<String, Object>> expenses = new ArrayList<>();
                            expenseMapById.forEach(ex -> {
                                Map<String, Object> data = MapUtil.cloneMap(ex,
                                        PersistenceUtil.Expense.AMOUNT,
                                        PersistenceUtil.Expense.TYPE,
                                        PersistenceUtil.Expense.EXPENSE_DATE,
                                        PersistenceUtil.Expense.ECREATED_DATE,
                                        PersistenceUtil.Expense.EMODIFIED_DATE
                                );
                                expenses.add(data);
                            });
                            responseMap.put(PersistenceUtil.Expense.EXPENSE, expenses);
                        }
                        response.add(responseMap);
                    }
            );
        }
        return response.stream().sorted((m1, m2) -> Long.parseLong(m1.get(PersistenceUtil.MydayUser.MID).toString()) >= Long.parseLong(m2.get(PersistenceUtil.MydayUser.MID).toString()) ? 1 : -1
        ).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseResponse> queryExpenseByUserId(Long id) {
        log.info("Expense Service :: query expense by userId :: service level");
        return expenseDao.queryExpenseByUserId(id);
    }

    @Override
    public ExpenseResponse insertExpense(ExpenseInput expense) {
        log.info("Expense Service :: insert an expense :: service level");
        return expenseDao.insertExpense(expense);
    }

    @Override
    public List<Map<String, Object>> getSummary(Map<String, Object> input) throws Exception {
        log.info("Expense Service :: get expense summary :: service level");
        try {
            List<ExpenseResponse> response = expenseDao.getSummary(input);
            if (!response.isEmpty()) {

                List<String> expenseTypes = response.stream().map(ExpenseResponse::getType).distinct().toList();
                List<Map<String, Object>> mappedResponse = new ArrayList<>();

                for (String type : expenseTypes) {

                    Map<String, Object> summeryMap = new HashMap<>();
                    Map<String, Object> totalMap = new HashMap<>();

                    List<ExpenseResponse> expenses = response.stream().filter(res -> res.getType().equals(type)).toList();
                    Double totalAmount = expenses.stream().map(ExpenseResponse::getAmount).reduce(0D, Double::sum);
                    long totalDate = expenses.stream().map(ExpenseResponse::getExpenseDate).distinct().toList().size();

                    totalMap.put("results", expenses.size());
                    totalMap.put("amount", totalAmount);
                    totalMap.put("numberOfExpenseDates", totalDate);

                    summeryMap.put("expenseType", type);
                    summeryMap.put("total", totalMap);
                    summeryMap.put("result", expenses);

                    mappedResponse.add(summeryMap);

                }
                return mappedResponse;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
