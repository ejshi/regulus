/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */

package org.regulus.account.consumer.controller.usercenter;

import java.util.List;

import org.regulus.account.api.dubbo.usercenter.RoleProvider;
import org.regulus.account.api.model.usercenter.Role;

import org.regulus.account.consumer.model.query.usercenter.RoleQuery;
import org.regulus.account.consumer.model.request.usercenter.RoleRequest;
import org.regulus.account.consumer.model.response.usercenter.RoleResponse;

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


@Api(value= "RoleController",tags ="Role" ,description="Role相关功能接口API")
@RestController
public class RoleController {
    
    @Reference
    private RoleProvider roleProvider;
    
    @ApiOperation(value = "新增" ,notes="新增数据记录")
    @PostMapping(path = "/role/add")
    public ResponseJson<Object> addRole(
            @ApiParam("请求参数") @RequestBody @Validated(RoleRequest.Add.class) RoleRequest  roleRequest){
        
        Role role = BeanCopierUtil.invoke(roleRequest, Role.class);
        role.setId(StringUtil.uuid());
        roleProvider.insertSelective(role);
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS);
    }
    
    @ApiOperation(value = "修改" ,notes="修改数据记录")
    @PutMapping(path = "/role/update/{id}")
    public ResponseJson<Object> updateRole(
            @ApiParam("记录id") @PathVariable("id") String id ,
            @ApiParam("请求参数") @RequestBody @Validated(RoleRequest.Edit.class) RoleRequest  roleRequest){
        
        Role role = BeanCopierUtil.invoke(roleRequest, Role.class);
        role.setId(id);
        
        roleProvider.updateByPrimaryKeySelective(role);
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS);
    }
    
    @ApiOperation(value = "删除" ,notes="删除数据记录")
    @DeleteMapping(path = "/role/delete/{id}")
    public ResponseJson<Object> deleteRole(@ApiParam("记录id") @PathVariable("id") String id) {
        
        roleProvider.deleteByPrimaryKey(id);
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.REQUEST_SUCCESS);
    }
    
    @ApiOperation(value = "查询" ,notes="根据id，查询相应数据记录")
    @GetMapping(path = "/role/get/{id}")
    public ResponseJson<RoleResponse> getRole(@ApiParam("记录id") @PathVariable("id") String id){
        
        Role role = roleProvider.findByPrimaryKey(id);
        
        RoleResponse roleResponse = BeanCopierUtil.invoke(role, RoleResponse.class);
        
        return new ResponseJson<RoleResponse>(true, ResponseCodeEnum.REQUEST_SUCCESS, roleResponse);
    }
    
    @ApiOperation(value = "查询" ,notes="查询相应数据记录，分页显示")
    @GetMapping(path = "/role/listPage")
    public ResponseJson<List<RoleResponse>> listPageRole(@ApiParam("请求数据") RoleQuery roleQuery){
        Role role = BeanCopierUtil.invoke(roleQuery, Role.class);
        
        PageResultModel<Role> pageResult = roleProvider.selectWithPage(role, roleQuery.getPageNum(), roleQuery.getPageSize());
        
        List<RoleResponse> roleResponses = BeanCopierUtil.invokeList(pageResult.getDataList(), RoleResponse.class);
        
        return new ResponseJson<List<RoleResponse>>(true, ResponseCodeEnum.REQUEST_SUCCESS, roleResponses,pageResult.getTotal());
    }
}
