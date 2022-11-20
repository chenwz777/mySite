package com.chenwz.mysite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenwz.mysite.bean.User;
import com.chenwz.mysite.dao.UserMapper;
import com.chenwz.mysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void getList() {
        List<User> list = this.list();  //使用框架封装的查询
        List nameList = userMapper.selectName();   //使用自己写的
        List<User> userList = userMapper.selectByAnnotation();

        System.out.println(list);

    }
}
