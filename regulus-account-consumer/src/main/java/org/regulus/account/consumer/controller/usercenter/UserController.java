/**
 * Project Name:regulus-account-consumer
 * File Name:UserController.java
 * Package Name:org.regulus.account.consumer.controller.usercenter
 * Date:2018年1月26日下午5:57:53
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.consumer.controller.usercenter;

import org.regulus.account.api.dubbo.usercenter.UserProvider;
import org.regulus.account.api.model.usercenter.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
public class UserController {
    
    @Reference
    private UserProvider userProvider;
    
    @GetMapping("/test/{id}")
    public User test(@PathVariable("id") String id){
        User user = userProvider.findByPrimaryKey(id);
        return user;
    }
}

