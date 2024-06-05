package com.sankadilshan.myday.controller;

import com.sankadilshan.myday.model.dto.GeneralResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.service.UserService;
import com.sankadilshan.myday.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public GeneralResponse queryAllMydayUsers() {
        log.info("Expense Service :: query all mydayuser :: controller level");
        List<MydayUserResponse> mydayUserResponses = userService.queryAllMydayUsers();
        return ResponseUtil.getGeneralResponse(mydayUserResponses);
    }
}
