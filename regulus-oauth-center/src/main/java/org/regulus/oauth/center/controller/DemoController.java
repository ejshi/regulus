/**
 * Project Name:regulus-account-consumer
 * Date:2018年1月26日下午5:57:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */

package org.regulus.oauth.center.controller;

import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value= "DemoController",tags ="测试用例" ,description="用户管理相关功能API")
@RestController
public class DemoController {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @ApiOperation(value = "登录" ,notes="用户登录")
    @PostMapping(path = "/guest/redisTest")
    public ResponseJson<Object> redisTest(){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username", "admin");
        String username = opsForValue.get("username");
        System.out.println("==================="+username);
        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS,username);
    }
    
    
}

