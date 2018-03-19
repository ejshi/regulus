/**
 * Project Name:regulus-oauth-center
 * File Name:UserService.java
 * Package Name:org.regulus.oauth.center.service
 * Date:2018年3月19日下午5:08:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.service;

import javax.annotation.Resource;

import org.regulus.oauth.center.mapper.usercenter.UserMapper;
import org.regulus.oauth.center.model.usercenter.User;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    
    public User getUserByUsername(String username){
        Example example = new Example(User.class);
        example.and().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }

    public User findByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

