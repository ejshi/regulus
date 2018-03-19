/**
 * Project Name:regulus-oauth-center
 * File Name:UserStatusEnum.java
 * Package Name:org.regulus.oauth.center
 * Date:2018年3月19日下午5:21:48
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.enums;

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * ClassName:UserStatusEnum <br/>
 * Function: 用户状态枚举 <br/>
 * Date:     2018年3月19日 下午5:21:48 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see 	 
 */
public enum UserStatusEnum {
    NORMAL("01","正常"),
    LOCK("02","锁定"),
    ;
    
    private static final Map<String,UserStatusEnum> map;
    
    static {
        EnumSet<UserStatusEnum> allOf = EnumSet.allOf(UserStatusEnum.class);
        map = Maps.newHashMapWithExpectedSize(allOf.size());
        allOf.stream().forEach(u -> {
            map.put(u.getCode(), u);
        });
    }
    
    public UserStatusEnum valueof(String code){
        return map.get(code);
    }
    
    private String code;
    private String name;
    
    private UserStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

