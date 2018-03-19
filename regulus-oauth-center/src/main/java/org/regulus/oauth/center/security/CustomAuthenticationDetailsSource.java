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
    
    /**
     * ClassName: CustomWebAuthenticationDetails <br/>
     * Function: 自定义请求参数获取 <br/>
     * date: 2018年3月19日 下午3:51:32 <br/>
     *
     * @author shijun@richinfo.cn
     * @version CustomAuthenticationDetailsSource
     * @since V1.0
     */
    class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
        
		private static final long serialVersionUID = 6867591032451279860L;
		private String verifyCode;
        
        public CustomWebAuthenticationDetails(HttpServletRequest request) {
            super(request);
            this.verifyCode = request.getParameter("verifyCode");
            //TODO 从session中获取生成的验证码，获取成功后删除session中的验证码
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }
    }
}

