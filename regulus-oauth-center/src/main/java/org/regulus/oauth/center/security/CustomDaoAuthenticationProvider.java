/**
 * Project Name:regulus-oauth-center
 * File Name:CustomUserDetailsService.java
 * Package Name:org.regulus.oauth.center.security
 * Date:2018年3月19日下午4:02:31
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * ClassName:CustomDaoAuthenticationProvider <br/>
 * Function:  用户认证实现<br/>
 * Date:     2018年3月19日 下午4:02:31 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see 	 
 */
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider{

    //TODO 自定义验证码，便于测试
    private static final String VERIFY_CODE = "123456";
    
    /**
     * 登录附加参数校验，验证不通过，抛出相应异常信息
     * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object details = authentication.getDetails();
        if(details instanceof CustomWebAuthenticationDetails){
            CustomWebAuthenticationDetails webAuthenticationDetails = (CustomWebAuthenticationDetails) details;
            //获取验证码
            String verifyCode = webAuthenticationDetails.getVerifyCode();
            if(StringUtils.isBlank(verifyCode)){
                throw new VerifyCodeException("验证码不能为空");
            }
            if(!VERIFY_CODE.equalsIgnoreCase(verifyCode)){
                throw new VerifyCodeException("验证码不正确");
            }
        }
        return super.authenticate(authentication);
    }
}

