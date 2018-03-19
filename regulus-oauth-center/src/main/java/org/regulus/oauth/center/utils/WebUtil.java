package org.regulus.oauth.center.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.regulus.oauth.center.model.usercenter.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
/**
 * 
 * ClassName: WebUtil <br/>
 * Function: 常用web方法 <br/>
 * date: 2018年3月19日 下午2:32:04 <br/>
 *
 * @author shijun@richinfo.cn
 * @version 
 * @since V1.0
 */
public class WebUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);
    
    /**
     * 获取系统用户
     * @return
     */
    public static User getCurrentUser(){
//        SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return principal.getUser();
        
        return null;
    }
    
    public static void send(HttpServletResponse response,Object result) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("utf-8");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(JSON.toJSONString(result));
        } catch (IOException e) {
            LOGGER.error("IO流操作异常", e);
        }
    }
    
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    private static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }
    /**
     * 获取请求ip地址
     * @return
     */
    public static String getRequestIpAddr() {
        HttpServletRequest request = getRequest();
        for (String header : new String[]{"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP"}) {
            String ip = request.getHeader(header);
            if (isValidIp(ip)) {
                return ip.split(",")[0];
            }
        }
        String realIp = request.getHeader("X-Real-IP");
        if (isValidIp(realIp)) {
            return realIp;
        }
        return request.getRemoteAddr();
    }
    
    private static Boolean isValidIp(String ip) {
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            return false;
        }
        return true;
    }
}
