/**
 * Project Name:regulus-account-consumer
 * Date:2018年1月26日下午5:57:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */

package org.regulus.demo.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.ResponseJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value= "DemoController",tags ="测试用例" ,description="用户管理相关功能API")
@RestController
public class DemoController {
    
    @ApiOperation(value = "测试用例" ,notes="测试用例")
    @GetMapping(path = "/guest/hello")
    public ResponseJson<Object> redisTest(){

        System.out.println("===================regulus-common-plugin=================");

        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS,"regulus-common-plugin");
    }
    
    
}

