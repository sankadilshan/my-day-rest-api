package com.sankadilshan.myday.dao.sql;

import com.sankadilshan.myday.utils.PersistenceUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaoSql {

    public static final String GET_ALL_EXPENSES= "SELECT " +
            "id," +
            "userId," +
            "amount," +
            "type," +
            "expenseDate," +
            "to_char(createdDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as  createdDate," +
            "to_char(modifiedDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as modifiedDate" +
            " FROM " +
            PersistenceUtil.Expense.EXPENSE;

    public static final String GET_EXPENSES_BY_USERID= "SELECT " +
            "userId," +
            "amount," +
            "type," +
            "expenseDate," +
            "to_char(createdDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as  createdDate," +
            "to_char(modifiedDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as modifiedDate" +
            " FROM " +
            PersistenceUtil.Expense.EXPENSE +
            " WHERE " +
            "userId = :userId";

    public static final String GET_EXPENSES_BY_TYPE_FILTER= "SELECT " +
            "id," +
            "userId," +
            "amount," +
            "type," +
            "expenseDate," +
            "to_char(createdDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as  createdDate," +
            "to_char(modifiedDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as modifiedDate" +
            " FROM " +
            PersistenceUtil.Expense.EXPENSE +
            " WHERE " +
            "userId = :userId " +
            "and " +
            "type in (:filters)";

    public static final String GET_ALL_MY_DAY_USERS= "SELECT " +
            "id," +
            "email," +
            "firstName," +
            "lastName," +
            "to_char(createdDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as createdDate," +
            "to_char(modifiedDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as modifiedDate" +
            " FROM "+
            PersistenceUtil.MydayUser.MYDAYUSER;
    public static final String GET_ROLE_BY_ID = "SELECT "+
            "r.id, " +
            "r.roles " +
            "FROM "+
            PersistenceUtil.Roles.ROLES + " r " +
            "WHERE " +
            "r.id = :id";


    public static final String GET_ALL_MY_DAY_USERS_WITH_EXPENSES = "SELECT " +
            "mu.id as mId," +
            "mu.email," +
            "mu.firstName," +
            "mu.lastName," +
            "to_char(mu.createdDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as mCreatedDate," +
            "to_char(mu.modifiedDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as mModifiedDate," +
            "ex.amount," +
            "ex.type," +
            "ex.expenseDate," +
            "to_char(ex.createdDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as  eCreatedDate," +
            "to_char(ex.modifiedDate :: timestamp , 'yyyy-mm-dd HH:mm:ss') as eModifiedDate" +
            " FROM " +
            PersistenceUtil.MydayUser.MYDAYUSER  + " mu " +
            " JOIN " +
            PersistenceUtil.Expense.EXPENSE + " ex " +
            " ON " +
            "mu.id = ex.userId";

    public static final String MYDAYUSER_FIND_BY_USERNAME = "SELECT "+
            "mu.id, " +
            "mu.email," +
            "mu.firstName," +
            "mu.lastName," +
            "mu.password, " +
            "r.roles " +
            "FROM "+
            PersistenceUtil.MydayUser.MYDAYUSER + " mu " +
            "JOIN " +
            PersistenceUtil.Roles.ROLES +" r "+
            "ON " +
            "mu.roleId = r.id " +
            "WHERE " +
            "mu.email = :username";

    public static final String INSERT_MYDAYUSER = "INSERT INTO " +
            PersistenceUtil.MydayUser.MYDAYUSER  +
            "(email, firstname, lastname, password, roleId) " +
            "VALUES (:username, :firstName, :lastName, :password, :roleId)";

    public static final String INSERT_EXPENSE = "INSERT INTO " +
            PersistenceUtil.Expense.EXPENSE +
            "(userId, amount, type) " +
            "VALUES (:userId, :amount, :type)";
}
