package com.chenwz.mysite.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User extends BaseEntity {

    private String name;

}
