/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */

package org.regulus.account.provider.service.usercenter;

import org.springframework.stereotype.Service;
import org.regulus.account.provider.common.BaseMapper;
import org.regulus.account.provider.common.BaseService;
import org.regulus.account.provider.mapper.usercenter.RoleMapper;

import javax.annotation.Resource;

import org.regulus.account.api.model.usercenter.Role;

/**
 * ClassName:RoleService <br/>
 * Function: service层,事务在service层统一控制 <br/>
 * Date:     2018年1月26日 下午4:53:09 <br/>
 * @author   ejshi
 */
@Service
public class RoleService extends BaseService<Role,String> {

    @Resource
    private RoleMapper roleMapper;
    
    @Override
    public BaseMapper<Role> getBaseMapper() {
        
        return roleMapper;
    }
    
}

