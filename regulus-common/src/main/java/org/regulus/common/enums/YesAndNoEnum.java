/**
 * Project Name:regulus-common
 * File Name:YesAndNoEnum.java
 * Package Name:org.regulus.common.enums
 * Date:2018年1月26日下午1:45:58
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.common.enums;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName:YesAndNoEnum <br/>
 * Function: 是否枚举<br/>
 * Date:     2018年1月26日 下午1:45:58 <br/>
 * @author   shijun@richinfo.cn
 */
public enum YesAndNoEnum {
    YES(1, true, "1", "是"),
    NO(0, false,  "0", "否")
    ;
    
    private static Map<String,YesAndNoEnum> map = null;
    
    static{
        map = EnumSet.allOf(YesAndNoEnum.class).stream().collect(Collectors.toMap(YesAndNoEnum::getCode, y -> y));
    }
    
    public static YesAndNoEnum valueof(String code) {
        return StringUtils.isBlank(code) ? null : map.get(code);
    }
    
    private int num;
    private boolean yes;
    private String code;
    private String name;
    
    YesAndNoEnum(int num, boolean yes, String code, String name) {
        this.num = num;
        this.yes = yes;
        this.code = code;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public boolean isYes() {
        return yes;
    }

    public void setYes(boolean yes) {
        this.yes = yes;
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

