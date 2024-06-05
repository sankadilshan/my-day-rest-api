package com.sankadilshan.myday.dao.impl;

import com.sankadilshan.myday.dao.ExpenseDao;
import com.sankadilshan.myday.dao.MyDayUserDao;
import com.sankadilshan.myday.dao.sql.DaoSql;
import com.sankadilshan.myday.exception.UserNameNotFoundException;
import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.model.dto.ExpenseInput;
import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.utils.PersistenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Repository
public class ExpenseDaoImpl implements ExpenseDao {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseDaoImpl.class);

    private NamedParameterJdbcTemplate namedTemplate;
    private MyDayUserDao myDayUserDao;

    @Autowired
    public ExpenseDaoImpl(NamedParameterJdbcTemplate namedTemplate, MyDayUserDao myDayUserDao){
        this.namedTemplate= namedTemplate;
        this.myDayUserDao= myDayUserDao;
    }

    @Override
    public List<ExpenseResponse> queryAllExpenses() {
        logger.info("Expense Dao :: query all expense :: repository level");

        List<ExpenseResponse> queryResponse = namedTemplate.query(DaoSql.GET_ALL_EXPENSES, new PersistenceUtil.Expense.ExpenseResponseMapper());
        Stream.of(Arrays.asList(queryResponse)).forEach( q-> logger.info(q.toString()));
        return queryResponse;
    }

    @Override
    public List<MydayUserResponse> queryAllMydayUsers() {
        logger.info("Expense Dao :: query all mydayuser :: repository level");

        List<MydayUserResponse> queryResponse = namedTemplate.query(DaoSql.GET_ALL_MY_DAY_USERS, new PersistenceUtil.MydayUser.MydayUserRowMapper());
        Stream.of(Arrays.asList(queryResponse)).forEach( q-> logger.info(q.toString()));
        return queryResponse;
    }

    @Override
    public List<Map<String, Object>> queryAllUsersWithExpenses() {
        logger.info("Expense service :: query all mydayuser, expense :: repository level");

        try {
            return namedTemplate.queryForList(DaoSql.GET_ALL_MY_DAY_USERS_WITH_EXPNSES, (SqlParameterSource) null);
        }catch (Exception e){
            logger.error(e.getMessage(),e.getCause());
            throw new RuntimeException();
        }
    }

    @Override
    public List<ExpenseResponse> queryExpenseByUserId(Long id) {
        logger.info("Expense Service :: query expense by userId :: repository level");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(PersistenceUtil.Expense.USERID, id);

        return namedTemplate.query(DaoSql.GET_EXPENSES_BY_USERID, parameters, new PersistenceUtil.Expense.ExpenseResponseMapper());
    }

    @Override
    public void inserExpense(ExpenseInput expenseInput) {
        logger.info("Expense Service :: insert expense :: repository level");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
            String currentUserName = getCurrentUserName();
            MyDayUser myDayUser = myDayUserDao.queryFindByUsername(currentUserName);
            if (myDayUser == null) {
                throw new UserNameNotFoundException(currentUserName);
            }

            parameters.addValue(PersistenceUtil.Expense.USERID, myDayUser.getId());
            parameters.addValue(PersistenceUtil.Expense.AMOUNT, expenseInput.getAmount());
            parameters.addValue(PersistenceUtil.Expense.TYPE, expenseInput.getType());

            namedTemplate.update(DaoSql.INSERT_EXPENSE, parameters);
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
