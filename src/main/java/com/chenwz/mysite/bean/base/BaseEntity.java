package com.chenwz.mysite.bean.base;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：chenwz
 * @date ： 2022/11/28 18:31
 * @description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

}
