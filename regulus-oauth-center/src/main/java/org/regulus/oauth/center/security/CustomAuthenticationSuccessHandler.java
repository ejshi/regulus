/**
 * Project Name:regulus-oauth-center
 * File Name:CustomAuthenticationSuccessHandler.java
 * Package Name:org.regulus.oauth.center.security
 * Date:2018年3月19日下午3:32:08
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.ResponseJson;
import org.regulus.oauth.center.model.usercenter.User;
import org.regulus.oauth.center.utils.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * ClassName:CustomAuthenticationSuccessHandler <br/>
 * Function: security认证成功处理类 <br/>
 * Date:     2018年3月19日 下午3:32:08 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see 	 
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        WebUtil.send(response, new ResponseJson<User>(true,ResponseCodeEnum.REQUEST_SUCCESS,WebUtil.getCurrentUser()));
    }

}

