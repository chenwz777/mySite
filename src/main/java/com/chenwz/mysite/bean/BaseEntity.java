package com.chenwz.mysite.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：bsh
 * @date ： 2022/8/29 18:31
 * @description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

}
