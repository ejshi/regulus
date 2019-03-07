/**
 * Project Name:regulus-account-provider
 * File Name:UserMapper.java
 * Package Name:org.regulus.account.provider.mapper
 * Date:2018年1月26日上午11:29:03
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.demo.web.mapper.usercenter;

import org.apache.ibatis.annotations.Mapper;
import org.regulus.common.plugin.mapper.BaseMapper;
import org.regulus.demo.web.model.usercenter.User;

import java.util.List;

/**
 * ClassName:UserMapper <br/>
 * Date:     2018年1月26日 上午11:29:03 <br/>
 * @author   shijun@richinfo.cn
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
    
    List<User> selectUserByExample(User user);
}

