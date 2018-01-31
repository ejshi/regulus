/**
 * Project Name:regulus-account-consumer
 * File Name:UserController.java
 * Package Name:org.regulus.account.consumer.controller.usercenter
 * Date:2018年1月26日下午5:57:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.consumer.controller.usercenter;

import org.regulus.account.api.dubbo.usercenter.UserProvider;
import org.regulus.account.api.model.usercenter.User;
import org.regulus.account.consumer.model.reqest.UserRequest;
import org.regulus.account.consumer.model.response.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value= "用户管理",description="用户管理详情描述")
@RestController
public class UserController {
    
    @Reference
    private UserProvider userProvider;
    
    @ApiOperation(value = "测试" ,notes="查询数据")
    @GetMapping(path = "/test/{id}")
    public User testGet(@ApiParam("用户id") @PathVariable("id") String id){
        User user = userProvider.findByPrimaryKey(id);
        return user;
    }
    
    @ApiOperation(value = "测试" ,notes="新增数据")
    @PostMapping(path = "/test/add")
    public UserResponse testAdd(
    		@ApiParam("请求参数") @RequestBody @Validated(UserRequest.Add.class) UserRequest  userRequest){
    	UserResponse userResponse = new UserResponse();
    	userResponse.setNickname(userRequest.getUsername());
    	return userResponse;
    }
}

