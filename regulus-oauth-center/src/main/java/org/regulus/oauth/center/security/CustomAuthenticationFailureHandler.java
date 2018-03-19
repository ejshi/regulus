/**
 * Project Name:regulus-oauth-center
 * File Name:CustomAuthenticationFailureHandler.java
 * Package Name:org.regulus.oauth.center.security
 * Date:2018年3月19日下午3:28:10
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
import org.regulus.oauth.center.utils.WebUtil;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * ClassName:CustomAuthenticationFailureHandler <br/>
 * Function: security认证失败处理类 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月19日 下午3:28:10 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see 	 
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        ResponseCodeEnum repCode = ResponseCodeEnum.REQUEST_FAIL;
        if(exception instanceof UsernameNotFoundException 
                || exception instanceof BadCredentialsException
                || exception instanceof AuthenticationCredentialsNotFoundException)
        {
            repCode = ResponseCodeEnum.OAUTH_ACCOUNT_UNKNOWN;
        }else if(exception instanceof LockedException || exception instanceof DisabledException){
            repCode = ResponseCodeEnum.OAUTH_ACCOUNT_LOCKED;
        }else if(exception instanceof VerifyCodeException){
            repCode = ResponseCodeEnum.OAUTH_VERIFY_CODE_NOT_CORRECT;
        }
        WebUtil.send(response, new ResponseJson<String>(false,repCode));
    }
}

