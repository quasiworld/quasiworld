package com.qwplus.quasiworld.business.user.controller;

import com.qwplus.quasiworld.business.user.service.UserService;
import com.qwplus.quasiworld.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    private Boolean register(@RequestBody User user) {
        return userService.register(user);
    }

}
