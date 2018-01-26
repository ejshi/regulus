/**
 * Project Name:regulus-account-provider
 * File Name:UserServiceProvider.java
 * Package Name:org.regulus.account.provider.impl.usercenter
 * Date:2018年1月26日下午4:50:15
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.provider.dubbo.usercenter;

import java.util.List;

import org.regulus.account.api.dubbo.usercenter.UserProvider;
import org.regulus.account.api.model.usercenter.User;
import org.regulus.account.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * ClassName:UserServiceProvider <br/>
 * Function: 用户服务实现 <br/>
 * Date:     2018年1月26日 下午4:50:15 <br/>
 * @author   shijun@richinfo.cn
 */
@Service
public class UserProviderImpl implements UserProvider{

    @Autowired
    private UserService userService;
    
    @Override
    public User findByPrimaryKey(String id) {
        return userService.findByPrimaryKey(id);
    }

    @Override
    public List<User> selectUserByUsername(String username) {
        return userService.selectUserByUsername(username);
    }
}

