package com.sankadilshan.myday.dao;

import com.sankadilshan.myday.exception.DataAccessException;
import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.model.Roles;
import com.sankadilshan.myday.model.dto.AuthResponse;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import com.sankadilshan.myday.model.dto.MydayUserResponse;

import java.util.List;

public interface MyDayUserDao {

    public MyDayUser queryFindByUsername(String username);

    void insertMydayUser(MyDayUserInput myDayUser0);

    AuthResponse signUp(MyDayUserInput userInput);

    List<MydayUserResponse> queryAllMydayUsers() throws Exception;

    Roles fetchRoleById(Long id);
}
