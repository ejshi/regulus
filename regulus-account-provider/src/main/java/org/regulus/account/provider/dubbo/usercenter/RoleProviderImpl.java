/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */

package org.regulus.account.provider.dubbo.usercenter;

import com.alibaba.dubbo.config.annotation.Service;

import org.regulus.account.provider.common.BaseProviderImpl;
import org.regulus.account.provider.common.BaseService;
import org.regulus.account.provider.service.usercenter.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.regulus.account.api.model.usercenter.Role;
import org.regulus.account.api.dubbo.usercenter.RoleProvider;


/**
 * dubbo服务实现
 * Date:  2018年1月26日
 * @author ejshi
 */
@Service
public class RoleProviderImpl extends BaseProviderImpl<Role, String> implements RoleProvider{

    @Autowired
    private RoleService roleService;
    
    @Override
    public BaseService<Role, String> getBaseService() {
        
        return roleService;
    }
    
    
}

