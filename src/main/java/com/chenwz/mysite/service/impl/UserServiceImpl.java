package com.chenwz.mysite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenwz.mysite.bean.User;
import com.chenwz.mysite.dao.UserMapper;
import com.chenwz.mysite.service.UserService;
import com.chenwz.mysite.vo.user.UserQueryVO;
import com.chenwz.mysite.vo.user.UserReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Override
    public Page<UserReturnDTO> listPage(Integer pageSize, Integer pageNum, UserQueryVO userQueryVO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(userQueryVO.getName())){
            queryWrapper.like(User::getName, userQueryVO.getName());
        }
        queryWrapper.orderByDesc(User::getId);
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        Page<UserReturnDTO> userReturnDTOPage = new Page<>();
        BeanUtils.copyProperties(userPage, userReturnDTOPage);
        if(Objects.nonNull(userPage) && !CollectionUtils.isEmpty(userPage.getRecords())){
            List<UserReturnDTO> userReturnDTOS = userPage.getRecords().stream().map(
                    User::transfer
            ).collect(Collectors.toList());
            userReturnDTOPage.setRecords(userReturnDTOS);
        }
        return userReturnDTOPage;
    }
}
