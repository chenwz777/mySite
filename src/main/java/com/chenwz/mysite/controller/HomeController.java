package com.chenwz.mysite.controller;

import com.chenwz.mysite.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/home")
@Api(value = "首页接口",tags = "首页接口")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ApiOperation("用户列表")
    public String test(){
        userService.getList();
        Map map = new HashMap<>();
        map.put("hello", "world");
        return map.toString();
    }
}
