/**
 * Project Name:regulus-account-provider
 * File Name:UserMapper.java
 * Package Name:org.regulus.account.provider.mapper
 * Date:2018年1月26日上午11:29:03
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.provider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.regulus.account.api.model.usercenter.User;
import org.regulus.account.provider.common.BaseMapper;

/**
 * ClassName:UserMapper <br/>
 * Date:     2018年1月26日 上午11:29:03 <br/>
 * @author   shijun@richinfo.cn
 * @see org.regulus.account.provider.common.mapper.CommonMapper<User>
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
    
    public List<User> selectUserByExample(User user);
}

