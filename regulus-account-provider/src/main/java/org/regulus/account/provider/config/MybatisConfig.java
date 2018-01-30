/**
 * Project Name:regulus-account-provider
 * File Name:MybatisConfig.java
 * Package Name:org.regulus.account.provider.config
 * Date:2018年1月29日上午9:47:31
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.provider.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

/**
 * ClassName:MybatisConfig <br/>
 * Function: mybatis配置<br/>
 * Date:     2018年1月29日 上午9:47:31 <br/>
 * @author   shijun@richinfo.cn
 * @see 	 
 */
@SpringBootConfiguration
@MapperScan("org.regulus.account.provider.mapper")
public class MybatisConfig {

}

