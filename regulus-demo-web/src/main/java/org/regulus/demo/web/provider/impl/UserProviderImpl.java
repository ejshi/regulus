/**
 * Project Name:regulus-account-provider
 * File Name:UserServiceProvider.java
 * Package Name:org.regulus.account.provider.impl.usercenter
 * Date:2018年1月26日下午4:50:15
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.demo.web.provider.impl;

import org.regulus.common.plugin.provider.BaseProviderImpl;
import org.regulus.demo.web.model.usercenter.User;
import org.regulus.demo.web.provider.UserProvider;
import org.springframework.stereotype.Component;

/**
 * ClassName:UserServiceProvider <br/>
 * Function: 用户服务实现 <br/>
 * Date:     2018年1月26日 下午4:50:15 <br/>
 * @author   shijun@richinfo.cn
 */
@Component
public class UserProviderImpl extends BaseProviderImpl<User, String> implements UserProvider {
    
//    @Autowired
//    private UserService userService;


}

