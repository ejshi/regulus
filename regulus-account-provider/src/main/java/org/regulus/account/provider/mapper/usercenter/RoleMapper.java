/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */
package org.regulus.account.provider.mapper.usercenter;

import org.apache.ibatis.annotations.Mapper;

import org.regulus.account.provider.common.BaseMapper;
import org.regulus.account.api.model.usercenter.Role;

/**
 * ClassName:RoleMapper <br/>
 * Function: mapper接口 <br/>
 * Date:     2018年1月26日 下午4:53:09 <br/>
 * @author   ejshi
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    //支持自定义mapper接口，用法同mybatis一致
}

