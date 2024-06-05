package com.sankadilshan.myday.service;

import com.sankadilshan.myday.model.dto.MydayUserResponse;

import java.util.List;

public interface UserService {
    List<MydayUserResponse> queryAllMydayUsers();
}
