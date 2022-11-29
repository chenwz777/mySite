package com.chenwz.mysite.controller;

import com.chenwz.mysite.controller.base.BaseController;
import com.chenwz.mysite.service.DoubleColorBallService;
import com.chenwz.mysite.service.UserService;
import com.chenwz.mysite.vo.BallReturnDTO;
import com.chenwz.mysite.vo.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/home")
@Api(value = "首页接口",tags = "首页接口")
public class HomeController extends BaseController {

    @Autowired
    private DoubleColorBallService doubleColorBallService;

    @GetMapping("/getBall")
    @ApiOperation("获取")
    public BaseResponse<List<BallReturnDTO>> getBall(@ApiParam("多少组") @RequestParam(required = false,defaultValue = "5")
                                                                 Integer number){
        List<BallReturnDTO> list = doubleColorBallService.getBall(number);
        return success(list);
    }
}
