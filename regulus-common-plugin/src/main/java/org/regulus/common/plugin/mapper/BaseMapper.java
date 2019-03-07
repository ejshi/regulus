/**
 * Project Name:regulus-account-provider
 * File Name:BaseMapper.java
 * Package Name:org.regulus.account.provider.common.mapper
 * Date:2018年1月26日上午11:04:46
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.common.plugin.mapper;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;

/**
 * ClassName:BaseMapper <br/>
 * Function: 基类mapper <br/>
 * Date:     2018年1月26日 上午11:04:46 <br/>
 * @author   shijun@richinfo.cn
 * @see https://mapperhelper.github.io/all/
 */
public interface BaseMapper<T> extends 
            tk.mybatis.mapper.common.BaseMapper<T>, ExampleMapper<T>,IdsMapper<T>{
  //TODO IdsMapper的用法在tk.mybatis mapper-spring-boot-starter 1.2.0以上的版本有问题
}

