/**
 * Project Name:regulus-common
 * File Name:StringUtil.java
 * Package Name:org.regulus.common.util
 * Date:2018年2月1日下午3:16:26
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.common.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 字符串处理工具类
 * Date:     2018年2月1日 下午3:16:26 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 */
public class StringUtil {
    private static final String RANDOM_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String RANDOM_NUMBER = "0123456789";

    /**
     * md5加密
     * @param text
     * @return
     */
    public static String MD5(String text) {
        return DigestUtils.md5Hex(text).toLowerCase();
    }

    /**
     * md5 盐加密
     *
     * @param text
     * @param salt
     * @return
     */
    public static String MD5WithSalt(String salt, String text) {
        return DigestUtils.md5Hex(salt+text).toLowerCase();
    }

    /**
     * UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 随机生成字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_STRING.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
    /**
     * 随机生成数字
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_NUMBER.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }
}

