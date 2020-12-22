package com.qwplus.quasiworld.business.user.controller;

import com.qwplus.quasiworld.business.user.service.UserService;
import com.qwplus.quasiworld.common.response.QWResult;
import com.qwplus.quasiworld.common.response.ResponseResult;
import com.qwplus.quasiworld.model.User;
import com.qwplus.quasiworld.model.bo.UserLoginBO;
import com.qwplus.quasiworld.model.bo.UserRegisterBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户接口管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    private ResponseResult register(@RequestBody UserRegisterBO userRegisterBO) {
        userService.register(userRegisterBO);
        return QWResult.ok();
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    private ResponseResult login(@RequestBody UserLoginBO userLoginBO) {
        userService.login(userLoginBO.getUserName(), userLoginBO.getPassword());
        return QWResult.ok();
    }

    @ApiOperation("/用户信息")
    @GetMapping("/info")
    private ResponseResult getUserInfo(){
        return QWResult.ok(userService.getUserInfo());
    }

    @ApiOperation("/注销")
    @DeleteMapping("/logout")
    private ResponseResult logout(){
        userService.logout();
        return QWResult.ok();
    }
}
