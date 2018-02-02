/**
 * Project Name:regulus-account-consumer
 * Date:2018年1月26日下午5:57:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */

package org.regulus.account.consumer.controller.usercenter;

import java.util.List;

import org.regulus.account.api.dubbo.usercenter.UserProvider;
import org.regulus.account.api.model.usercenter.User;
import org.regulus.account.consumer.model.query.usercenter.UserQuery;
import org.regulus.account.consumer.model.request.usercenter.UserRequest;
import org.regulus.account.consumer.model.response.usercenter.UserResponse;
import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.PageResultModel;
import org.regulus.common.model.ResponseJson;
import org.regulus.common.util.BeanCopierUtil;
import org.regulus.common.util.StringUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value= "UserController",tags ="用户管理" ,description="用户管理相关功能API")
@RestController
public class UserController {
    
    @Reference
    private UserProvider userProvider;
    
    @ApiOperation(value = "新增" ,notes="新增数据记录")
    @PostMapping(path = "/user/add")
    public ResponseJson<Object> addUser(
    		@ApiParam("请求参数") @RequestBody @Validated(UserRequest.Add.class) UserRequest  userRequest){
        
        User user = BeanCopierUtil.invoke(userRequest, User.class);
        user.setId(StringUtil.uuid());
        
        userProvider.insertSelective(user);
        
    	return new ResponseJson<Object>(true, ResponseCodeEnum.SUCCESS);
    }
    
    @ApiOperation(value = "修改" ,notes="修改数据记录")
    @PutMapping(path = "/user/update/{id}")
    public ResponseJson<Object> updateUser(
            @ApiParam("记录id") @PathVariable("id") String id ,
            @ApiParam("请求参数") @RequestBody @Validated(UserRequest.Edit.class) UserRequest  userRequest){
        
        User user = BeanCopierUtil.invoke(userRequest, User.class);
        user.setId(id);
        
        userProvider.updateByPrimaryKeySelective(user);
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.SUCCESS);
    }
    
    @ApiOperation(value = "删除" ,notes="删除数据记录")
    @DeleteMapping(path = "/user/delete/{id}")
    public ResponseJson<Object> deleteUser(@ApiParam("记录id") @PathVariable("id") String id) {
        
        userProvider.deleteByPrimaryKey(id);
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.SUCCESS);
    }
    
    @ApiOperation(value = "查询" ,notes="根据id，查询相应数据记录")
    @GetMapping(path = "/user/get/{id}")
    public ResponseJson<UserResponse> getUser(@ApiParam("记录id") @PathVariable("id") String id){
        
        User user = userProvider.findByPrimaryKey(id);
        
        UserResponse userResponse = BeanCopierUtil.invoke(user, UserResponse.class);
        
        return new ResponseJson<UserResponse>(true, ResponseCodeEnum.SUCCESS, userResponse);
    }
    
    @ApiOperation(value = "查询" ,notes="查询相应数据记录，分页显示")
    @GetMapping(path = "/user/listPage")
    public ResponseJson<List<UserResponse>> listPageUser(@ApiParam("请求数据") UserQuery userQuery){
        User user = BeanCopierUtil.invoke(userQuery, User.class);
        
        PageResultModel<User> pageResult = userProvider.selectWithPage(user, userQuery.getPageNum(), userQuery.getPageSize());
        
        List<UserResponse> userResponses = BeanCopierUtil.invokeList(pageResult.getDataList(), UserResponse.class);
        
        return new ResponseJson<List<UserResponse>>(true, ResponseCodeEnum.SUCCESS, userResponses,pageResult.getTotal());
    }
}

