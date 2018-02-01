/**
 * Project Name:regulus-account-provider
 * File Name:UserServiceProvider.java
 * Package Name:org.regulus.account.provider.impl.usercenter
 * Date:2018年1月26日下午4:50:15
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.provider.dubbo.usercenter;

import org.regulus.account.api.dubbo.usercenter.UserProvider;
import org.regulus.account.api.model.usercenter.User;
import org.regulus.account.provider.common.BaseProviderImpl;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * ClassName:UserServiceProvider <br/>
 * Function: 用户服务实现 <br/>
 * Date:     2018年1月26日 下午4:50:15 <br/>
 * @author   shijun@richinfo.cn
 */
@Service
public class UserProviderImpl extends BaseProviderImpl<User, String> implements UserProvider{

//    @Autowired
//    private UserService userService;
    
    
}

