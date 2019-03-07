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
import org.regulus.common.plugin.model.PageResultModel;
import org.regulus.demo.web.model.usercenter.Role;
import org.regulus.demo.web.provider.RoleProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value= "RoleController",tags ="角色" ,description="角色管理相关功能API")
@RestController
public class RoleController {

//    @Resource
//    private RoleService roleService;

    @Resource
    private RoleProvider roleProvider;
    
    @ApiOperation(value = "测试用例" ,notes="测试用例")
    @GetMapping(path = "/role/list")
    public ResponseJson<PageResultModel> list(){
        System.out.println("===================role-list=================");
//        List<Role> roleList = roleService.selectByExample(new Example(Role.class));
        PageResultModel<Role> pageResultModel = roleProvider.selectWithPage(new Role(), 1, 10);
        return new ResponseJson<PageResultModel>(true, ResponseCodeEnum.REQUEST_SUCCESS,pageResultModel);
    }
}

