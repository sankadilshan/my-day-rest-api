package com.sankadilshan.myday.service.serviceImpl;

import com.sankadilshan.myday.dao.MyDayUserDao;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    MyDayUserDao myDayUserDao;

    UserServiceImpl(MyDayUserDao dao) {
        this.myDayUserDao = dao;
    }
    @Override
    public List<MydayUserResponse> queryAllMydayUsers() {
        log.info("User Service :: query all mydayuser :: service level");
        return myDayUserDao.queryAllMydayUsers();
    }
}
