package com.sankadilshan.myday.controller;

import com.sankadilshan.myday.model.Roles;
import com.sankadilshan.myday.model.dto.GeneralResponse;
import com.sankadilshan.myday.model.dto.MydayUserResponse;
import com.sankadilshan.myday.service.UserService;
import com.sankadilshan.myday.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GeneralResponse> fetchAllMydayUsers() throws Exception {
        log.info("User Service :: query all mydayuser :: controller level");
        List<MydayUserResponse> mydayUserResponses = userService.queryAllMydayUsers();
        return ResponseUtil.getGeneralResponse(mydayUserResponses);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<GeneralResponse> fetchRolesById(@PathVariable(name = "id") Long id) {
        log.info("User service :: query roles by user id :: controller level");
        Roles roles = userService.fetchRoleById(id);
        return ResponseUtil.getGeneralResponse(roles);
    }
}
