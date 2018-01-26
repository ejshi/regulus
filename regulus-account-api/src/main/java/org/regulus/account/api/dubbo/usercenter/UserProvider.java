/**
 * Project Name:regulus-account-api
 * File Name:UserServiceProvider.java
 * Package Name:org.regulus.account.api.dubbo.usercenter
 * Date:2018年1月26日下午4:53:09
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.api.dubbo.usercenter;

import java.util.List;

import org.regulus.account.api.model.usercenter.User;

/**
 * ClassName:UserServiceProvider <br/>
 * Function: 用户服务API接口 <br/>
 * Date:     2018年1月26日 下午4:53:09 <br/>
 * @author   shijun@richinfo.cn
 */
public interface UserProvider {
    
    User findByPrimaryKey(String id);
    
    List<User> selectUserByUsername(String username);
}

