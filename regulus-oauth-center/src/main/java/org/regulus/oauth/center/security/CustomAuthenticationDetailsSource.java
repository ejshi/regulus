/**
 * Project Name:regulus-oauth-center
 * File Name:CustomAuthenticationDetailsSource.java
 * Package Name:org.regulus.oauth.center.security
 * Date:2018年3月19日下午3:36:52
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * ClassName:CustomAuthenticationDetailsSource <br/>
 * Function: 自定义用户登录附带的请求数据，如验证码<br/>
 * Date:     2018年3月19日 下午3:36:52 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see 	 
 */
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>{

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomWebAuthenticationDetails(request);
    }
}

