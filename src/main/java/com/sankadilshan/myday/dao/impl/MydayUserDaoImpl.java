package com.sankadilshan.myday.dao.impl;

import com.sankadilshan.myday.Constants;
import com.sankadilshan.myday.dao.MyDayUserDao;
import com.sankadilshan.myday.dao.sql.DaoSql;
import com.sankadilshan.myday.exception.UserSignUpFailedException;
import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.model.dto.AuthResponse;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.security.JwtService;
import com.sankadilshan.myday.utils.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Repository
@Transactional
public class MydayUserDaoImpl implements MyDayUserDao {

    private NamedParameterJdbcTemplate namedTemplate;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Autowired
    public MydayUserDaoImpl(NamedParameterJdbcTemplate namedTemplate,
                            PasswordEncoder passwordEncoder,
                            JwtService jwtService) {
        this.namedTemplate= namedTemplate;
        this.passwordEncoder= passwordEncoder;
        this.jwtService= jwtService;
    }
    @Override
    public MyDayUser queryFindByUsername(String username) {
        log.info("MydayUser Service :: query user by username :: repository level");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(PersistenceUtil.MydayUser.USERNAME, username);
        return namedTemplate.query(DaoSql.MYDAYUSER_FIND_BY_USERNAME, parameters, new PersistenceUtil.MydayUser.MyDayUser1RowMapper()).get(0);
    }

    @Override
    public void insertMydayUser(MyDayUserInput myDayUser) {
        log.info("MydayUser Service :: insert new user :: repository level");

        MapSqlParameterSource parameters=  new MapSqlParameterSource();
        parameters.addValue(PersistenceUtil.MydayUser.USERNAME, myDayUser.getEmail());
        parameters.addValue(PersistenceUtil.MydayUser.PASSWORD, myDayUser.getPassword());
        parameters.addValue(PersistenceUtil.MydayUser.FIRST_NAME, myDayUser.getFirstName());
        parameters.addValue(PersistenceUtil.MydayUser.LAST_NAME, myDayUser.getLastName());
        parameters.addValue(PersistenceUtil.MydayUser.ROLEID, myDayUser.getRoleId());

        int res = namedTemplate.update(DaoSql.INSERT_MYDAYUSER, parameters);
    }

    @Override
    public AuthResponse signUp(MyDayUserInput myDayUser) {
        log.info("Auth Service :: signup new user :: repository level");

        MapSqlParameterSource parameters= new MapSqlParameterSource();
        parameters.addValue(PersistenceUtil.MydayUser.USERNAME, myDayUser.getEmail());
        parameters.addValue(PersistenceUtil.MydayUser.PASSWORD, encodePassword(myDayUser.getPassword()));
        parameters.addValue(PersistenceUtil.MydayUser.FIRST_NAME, myDayUser.getFirstName());
        parameters.addValue(PersistenceUtil.MydayUser.LAST_NAME, myDayUser.getLastName());
        parameters.addValue(PersistenceUtil.MydayUser.ROLEID, myDayUser.getRoleId());

        try {
            int res = namedTemplate.update(DaoSql.INSERT_MYDAYUSER, parameters);
            if (res > 0) {
                return AuthResponse.builder()
                        .message(Constants.AUTH_MESSAGE)
                        .status(HttpStatus.OK)
                        .build();
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new UserSignUpFailedException(myDayUser.getEmail());
        }
        return null;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public List<MydayUserResponse> queryAllMydayUsers() {
        log.info("Expense Dao :: query all mydayuser :: repository level");

        List<MydayUserResponse> queryResponse = namedTemplate.query(DaoSql.GET_ALL_MY_DAY_USERS, new PersistenceUtil.MydayUser.MydayUserRowMapper());
        Stream.of(Arrays.asList(queryResponse)).forEach(q-> log.info(q.toString()));
        return queryResponse;
    }

}
