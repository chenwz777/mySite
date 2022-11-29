package com.chenwz.mysite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("球返回实体")
public class BallReturnDTO {

    @ApiModelProperty("蓝球")
    private Integer blue;

    @ApiModelProperty("红球列表")
    private List<Integer> redList;

    private String message;

}
