/**
 * Project Name:regulus-account-consumer
 * File Name:DubboConfig.java
 * Package org.regulus.account.consumer.config
 * Date:2018年1月26日下午5:00:58
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.consumer.config;

import org.springframework.boot.SpringBootConfiguration;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;

/**
 * ClassName:DubboConfig <br/>
 * Function: dubbo配置 <br/>
 * Date:     2018年1月26日 下午5:00:58 <br/>
 * @author   shijun@richinfo.cn
 * @since    V1.0
 * @see https://github.com/alibaba/dubbo-spring-boot-starter/blob/master/README_zh.md	 
 */
@SpringBootConfiguration
@EnableDubboConfig
@DubboComponentScan(value="org.regulus.account.consumer.controller")
public class DubboConfig {

}

