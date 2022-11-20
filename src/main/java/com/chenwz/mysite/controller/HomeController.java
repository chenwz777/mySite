package com.chenwz.mysite.controller;

import com.chenwz.mysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        userService.getList();
        Map map = new HashMap<>();
        map.put("hello", "world");
        return map.toString();
    }
}
