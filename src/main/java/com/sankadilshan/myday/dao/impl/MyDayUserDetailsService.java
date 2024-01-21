package com.sankadilshan.myday.dao.impl;

import com.sankadilshan.myday.dao.MyDayUserDao;
import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.model.MyDayUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDayUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyDayUserDetailsService.class);

    @Autowired
    private MyDayUserDao myDayUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyDayUser myDayUser = myDayUserDao.queryFindByUsername(username);
        return new MyDayUserInfo(myDayUser);
    }
}

