package com.chenwz.mysite.vo.base;

import com.chenwz.mysite.enums.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel("返回基础实体")
public class BaseResponse<T> implements Serializable {
    @ApiModelProperty("返回码，0-成功，其他为失败")
    private int code;

    @ApiModelProperty("描述内容，code不为0时有意义")
    private String message;

    @ApiModelProperty("数据内容，code不为0时，一般为null")
    private T data;

    public BaseResponse() {
        //set defualt code
        this.code = Code.SYSTEM_ERROR.getCode();
        this.message = Code.SYSTEM_ERROR.getMsg();
    }

    public BaseResponse(int code) {
        this(code, null, null);
    }

    public BaseResponse(int code, String message) {
        this(code, message, null);
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Code code) {
        this(code, null);
    }

    public BaseResponse(Code code, T data) {
        this(code.getCode(), code.getMsg(), data);
    }

    public static BaseResponse with(Code code) {
        return with(code, null);
    }

    public static <T> BaseResponse<T> with(Code code, T data) {
        return new BaseResponse<>(code, data);
    }

    public static BaseResponse with(int code) {
        return with(code, null, null);
    }

    public static BaseResponse with(int code, String msg) {
        return with(code, msg, null);
    }

    public static <T> BaseResponse<T> with(int code, T data) {
        return with(code, null, data);
    }

    public static <T> BaseResponse<T> with(int code, String msg, T data) {
        return new BaseResponse<>(code, msg, data);
    }


}
