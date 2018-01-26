/**
 * Project Name:regulus-account-provider
 * File Name:UserService.java
 * Package Name:org.regulus.account.provider.service
 * Date:2018年1月26日上午11:30:25
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.provider.service;

import java.util.List;

import javax.annotation.Resource;

import org.regulus.account.api.model.usercenter.User;
import org.regulus.account.provider.common.BaseService;
import org.regulus.account.provider.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserService <br/>
 * Date:     2018年1月26日 上午11:30:25 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @see 	 
 */
@Service
public class UserService extends BaseService<User, String>{
    
    @Resource
    private UserMapper userMapper;
    
    public List<User> selectUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return userMapper.selectUserByExample(user);
    }
}

