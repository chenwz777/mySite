package com.chenwz.mysite.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenwz.mysite.constants.BaseConstants;
import com.chenwz.mysite.controller.base.BaseController;
import com.chenwz.mysite.vo.base.BaseResponse;
import com.chenwz.mysite.bean.base.PageResult;
import com.chenwz.mysite.service.UserService;
import com.chenwz.mysite.vo.user.UserQueryVO;
import com.chenwz.mysite.vo.user.UserReturnDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@Api(value = "用户接口",tags = "用户接口")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/listPage")
    @ApiOperation("用户列表")
    public BaseResponse<PageResult<UserReturnDTO>> listPage(@ApiParam("行数") @RequestParam(value = "pageSize", required = false, defaultValue = BaseConstants.DEFAULT_PAGE_SIZE_STR) Integer pageSize,
                                                            @ApiParam("页码") @RequestParam(value = "pageNum", required = false, defaultValue = BaseConstants.DEFAULT_PAGE_NUM_STR) Integer pageNum,
                                                            @Valid UserQueryVO userQueryVO){
        Page<UserReturnDTO> page = userService.listPage(pageSize, pageNum, userQueryVO);
        return getPageResult(page);
    }
}
