package com.chenwz.mysite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenwz.mysite.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {


    List selectName();

    @Select("select * from t_user")
    List<User> selectByAnnotation();
}
