package com.chenwz.mysite.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chenwz.mysite.bean.base.BaseEntity;
import com.chenwz.mysite.vo.user.UserReturnDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_user")
public class User extends BaseEntity {

    private String name;

    private LocalDateTime createTime;

    public static UserReturnDTO transfer(User p) {
        assert p != null;
        return UserReturnDTO.builder().id(p.getId()).name(p.getName()).createTime(p.getCreateTime()).build();
    }
}
