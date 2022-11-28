package com.chenwz.mysite.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenwz.mysite.vo.base.BaseResponse;
import com.chenwz.mysite.bean.base.PageResult;
import com.chenwz.mysite.enums.Code;

public class BaseController {


    /**
     * 成功
     *
     * @return
     */
    protected BaseResponse success() {
        return BaseResponse.with(Code.SUCCESS);
    }

    /**
     * 成功 (携带返回值)
     *
     * @param data
     * @param <T>
     * @return
     */
    protected <T> BaseResponse<T> success(T data) {
        return BaseResponse.with(Code.SUCCESS, data);
    }


    /**
     * 失败
     *
     * @param code
     * @return
     */
    protected BaseResponse error(Code code) {
        return BaseResponse.with(code);
    }

    /**
     * 失败 (携带自定义的错误信息)
     *
     * @param code
     * @param customErrorMsg
     * @return
     */
    protected BaseResponse<?> error(Code code, String customErrorMsg) {
        return BaseResponse.with(code.getCode(), customErrorMsg);
    }

    /**
     * 分页查询的结果处理
     *
     * @param iPage
     * @return
     */
    protected <T> BaseResponse<PageResult<T>> getPageResult(IPage<T> iPage) {
        PageResult<T> pageResult = new PageResult<>(iPage);
        return BaseResponse.with(Code.SUCCESS.getCode(), pageResult);
    }

}
