package org.regulus.oauth.center.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * ClassName: CustomWebAuthenticationDetails <br/>
 * Function: 自定义请求参数获取 <br/>
 * date: 2018年3月19日 下午3:51:32 <br/>
 *
 * @author shijun@richinfo.cn
 * @version CustomAuthenticationDetailsSource
 * @since V1.0
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
    
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

