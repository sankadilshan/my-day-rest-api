package com.sankadilshan.myday.utils;

import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.model.dto.ExpenseResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class PersistenceUtil {

    private final static Logger logger = LoggerFactory.getLogger(PersistenceUtil.class);

    public static class Expense {
        public final static String EXPENSE= "expense";
        public final static  String AMOUNT= "amount";
        public final static  String TYPE= "type";
        public final static  String EXPENSE_DATE= "expenseDate";
        public final static  String ECREATED_DATE= "eCreatedDate";
        public final static  String EMODIFIED_DATE= "eModifiedDate";
        public final static String USERID= "userId";

        private Expense(){
        }

        public static class ExpenseResponseMapper implements RowMapper<ExpenseResponse> {

            @Override
            public ExpenseResponse mapRow(ResultSet rs, int rowNum) {
                try {

                    ExpenseResponse expenseResponse = new ExpenseResponse();
                    expenseResponse.setUserId(rs.getLong("userId"));
                    expenseResponse.setType(rs.getString("type"));
                    expenseResponse.setAmount(rs.getDouble("amount"));
                    expenseResponse.setExpenseDate(DateUtil.dateFormatter(rs.getString("expenseDate"), DateUtil.DateConstant.YYYYMMDD_FORMAT));
                    expenseResponse.setCreatedDate(DateUtil.dateTimeFormatter(rs.getString("createdDate"), DateUtil.DateConstant.YYYYMMDDHHMMSS_FORMAT));
                    expenseResponse.setModifiedDate(DateUtil.dateTimeFormatter(rs.getString("modifiedDate"), DateUtil.DateConstant.YYYYMMDDHHMMSS_FORMAT));

                    logger.info("Expense Response: {}", expenseResponse.toString());
                    return expenseResponse;

                } catch (Exception e){
                    logger.error(e.getMessage());
                    return new ExpenseResponse();
                }
            }
        }

    }

    public static class MydayUser {
        public final static String  MYDAYUSER= "mydayuser";
        public final static String MID = "mId";
        public final static String EMAIL = "email";
        public final static String PASSWORD = "password";
        public final static String FIRST_NAME = "firstName";
        public final static String LAST_NAME = "lastName";
        public final static String MCREATED_DATE = "mCreatedDate";
        public final static String MMODIFIED_DATE = "mModifiedDate";
        public final static String USERNAME = "username";
        public final static String ROLEID = "roleId";

        public static class MydayUserRowMapper implements RowMapper<MydayUserResponse> {

            @Override
            public MydayUserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                MydayUserResponse mydayUserResponse = MydayUserResponse
                        .builder()
                        .email(rs.getString("email"))
                        .firstName(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .createdDate(DateUtil.dateTimeFormatter(rs.getString("createdDate"),DateUtil.DateConstant.YYYYMMDDHHMMSS_FORMAT))
                        .modifiedDate(DateUtil.dateTimeFormatter(rs.getString("modifiedDate"),DateUtil.DateConstant.YYYYMMDDHHMMSS_FORMAT))
                        .build();

                logger.info("MydayUser Response: {}", mydayUserResponse.toString());
                return mydayUserResponse;

            }
        }

        public static class MyDayUser1RowMapper implements RowMapper<MyDayUser> {

            @Override
            public MyDayUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                MyDayUser myDayUser = MyDayUser
                        .builder()
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .roles(rs.getString("roles"))
                        .build();

                logger.info("MydayUser1 Response: {}", myDayUser.toString());
                return myDayUser;
            }
        }
    }

    public static class Roles {
        public static final String ROLES = "roles";

        public static class RolesRowMapper implements RowMapper<com.sankadilshan.myday.model.Roles> {

            @Override
            public com.sankadilshan.myday.model.Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
                com.sankadilshan.myday.model.Roles roles = com.sankadilshan.myday.model.Roles
                        .builder()
                        .id(rs.getInt("id"))
                        .roles(rs.getString("roles"))
                        .build();

                logger.info("Roles Response: {}", roles.toString());
                return roles;
            }
        }
    }
}
