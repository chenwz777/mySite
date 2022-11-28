package com.chenwz.mysite.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户查询VO")
public class UserQueryVO {

    @ApiModelProperty("姓名")
    private String name;
}
