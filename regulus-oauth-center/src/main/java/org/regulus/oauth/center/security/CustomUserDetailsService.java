/**
 * Project Name:regulus-oauth-center
 * File Name:CustomUserDetailsService.java
 * Package Name:org.regulus.oauth.center.security
 * Date:2018年3月19日下午4:12:34
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.security;

import org.regulus.oauth.center.model.usercenter.SecurityUser;
import org.regulus.oauth.center.model.usercenter.User;
import org.regulus.oauth.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.fastjson.JSON;

/**
 * ClassName:CustomUserDetailsService <br/>
 * Function: 用户认证，获取用户信息
 * Date:     2018年3月19日 下午4:12:34 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see org.regulus.oauth.center.security.CustomDaoAuthenticationProvider
 */
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户权限
        SecurityUser securityUser = (SecurityUser) user;
        System.out.println(JSON.toJSONString(securityUser));
        return securityUser;
    }
    
}

