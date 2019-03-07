/**
 * Project Name:regulus-account-consumer
 * Date:2018年1月26日下午5:57:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */

package org.regulus.demo.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.ResponseJson;
import org.regulus.common.util.BeanCopierUtil;
import org.regulus.demo.web.model.usercenter.User;
import org.regulus.demo.web.model.usercenter.UserResponse;
import org.regulus.demo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value= "UserController",tags ="用户管理" ,description="用户管理相关功能API")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
//    @ApiOperation(value = "新增" ,notes="新增数据记录")
//    @PostMapping(path = "/user/add")
//    public ResponseJson<Object> addUser(
//    		@ApiParam("请求参数") @RequestBody @Validated(UserRequest.Add.class) UserRequest  userRequest){
//        
//        User user = BeanCopierUtil.invoke(userRequest, User.class);
//        user.setId(StringUtil.uuid());
//        
//        userProvider.insertSelective(user);
//        
//    	return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS);
//    }
//    
//    @ApiOperation(value = "修改" ,notes="修改数据记录")
//    @PutMapping(path = "/user/update/{id}")
//    public ResponseJson<Object> updateUser(
//            @ApiParam("记录id") @PathVariable("id") String id ,
//            @ApiParam("请求参数") @RequestBody @Validated(UserRequest.Edit.class) UserRequest  userRequest){
//        
//        User user = BeanCopierUtil.invoke(userRequest, User.class);
//        user.setId(id);
//        
//        userProvider.updateByPrimaryKeySelective(user);
//        
//        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS);
//    }
//    
//    @ApiOperation(value = "删除" ,notes="删除数据记录")
//    @DeleteMapping(path = "/user/delete/{id}")
//    public ResponseJson<Object> deleteUser(@ApiParam("记录id") @PathVariable("id") String id) {
//        
//        userProvider.deleteByPrimaryKey(id);
//        
//        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS);
//    }
    
    @ApiOperation(value = "查询" ,notes="根据id，查询相应数据记录")
    @GetMapping(path = "/user/get/{id}")
    public ResponseJson<UserResponse> getUser(@ApiParam("记录id") @PathVariable("id") String id){
        
        User user = userService.findByPrimaryKey(id);
        
        UserResponse userResponse = BeanCopierUtil.invoke(user, UserResponse.class);
        
        return new ResponseJson<UserResponse>(true, ResponseCodeEnum.REQUEST_SUCCESS, userResponse);
    }
    
    @ApiOperation(value = "查询" ,notes="根据id，查询相应数据记录")
    @GetMapping(path = "/guest/user/get/{id}")
    public ResponseJson<UserResponse> getGuestUser(@ApiParam("记录id") @PathVariable("id") String id){
        
        User user = userService.findByPrimaryKey(id);
        
        UserResponse userResponse = BeanCopierUtil.invoke(user, UserResponse.class);
        
        return new ResponseJson<UserResponse>(true, ResponseCodeEnum.REQUEST_SUCCESS, userResponse);
    }
    
    @ApiOperation(value = "登录" ,notes="用户登录")
    @PostMapping(path = "/login")
    public ResponseJson<UserResponse> login(
            @ApiParam("用户名") @RequestParam("username") String username,
            @ApiParam("密码") @RequestParam("password") String password,
            @ApiParam("验证码") @RequestParam("verifyCode") String verifyCode){
        
        return null;
    }
    
    
}

