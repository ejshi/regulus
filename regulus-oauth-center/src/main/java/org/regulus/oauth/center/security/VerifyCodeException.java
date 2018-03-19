/**
 * Project Name:regulus-oauth-center
 * File Name:VerifyCodeException.java
 * Package Name:org.regulus.oauth.center.security
 * Date:2018年3月19日下午4:22:31
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.security;

import org.springframework.security.core.AuthenticationException;

/**
 * ClassName:VerifyCodeException <br/>
 * Function: security异常之验证码错误 <br/>
 * Date:     2018年3月19日 下午4:22:31 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see AuthenticationException	 
 */
public class VerifyCodeException extends AuthenticationException{
    
    private static final long serialVersionUID = 3737605507520495288L;

    public VerifyCodeException(String msg) {
        
        super(msg);
    }

}

