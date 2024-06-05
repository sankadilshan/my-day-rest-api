package com.sankadilshan.myday.service.serviceImpl;

import com.sankadilshan.myday.dao.ExpenseDao;
import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.service.ExpenseService;
import com.sankadilshan.myday.utils.MapUtil;
import com.sankadilshan.myday.utils.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDao expenseDao;
    private MapUtil mapUtil;

    @Autowired
    public ExpenseServiceImpl(ExpenseDao expenseDao, MapUtil mapUtil){
        this.expenseDao= expenseDao;
        this.mapUtil= mapUtil;
    }

    @Override
    public List<ExpenseResponse> queryAllExpenses() {
        log.info("Expense Service :: query all expenses :: service level");
        return expenseDao.queryAllExpenses();
    }

    @Override
    public List<MydayUserResponse> queryAllMydayUsers() {
        log.info("Expense Service :: query all mydayuser :: service level");
        return expenseDao.queryAllMydayUsers();
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
                        List<Map<String, Object>> expenseMapById = queryMapResponse.stream().filter(qm -> qm.get("mId") == id).collect(Collectors.toList());
                        Map<String, Object> responseMap = null;
                        if (mapById != null) {
                            responseMap = mapUtil.cloneMap(mapById,
                                    PersistenceUtil.MydayUser.MID,
                                    PersistenceUtil.MydayUser.EMAIL,
                                    PersistenceUtil.MydayUser.FIRST_NAME,
                                    PersistenceUtil.MydayUser.LAST_NAME,
                                    PersistenceUtil.MydayUser.MCREATED_DATE,
                                    PersistenceUtil.MydayUser.MMODIFIED_DATE
                            );
                            
                            List<Map<String, Object>> expenses = new ArrayList<>();
                            expenseMapById.forEach(ex -> {
                                Map<String, Object> data = mapUtil.cloneMap(ex,
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

}
