package com.sankadilshan.myday.service;

import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.model.Roles;
import com.sankadilshan.myday.model.dto.MydayUserResponse;

import java.util.List;

public interface UserService {
    List<MydayUserResponse> queryAllMydayUsers() throws Exception;

    Roles fetchRoleById(Long id);

    MyDayUser fetchUserByUserName(String username);
}
