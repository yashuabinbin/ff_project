package com.lbb.utils;

import org.springframework.util.DigestUtils;

public class Md5Utils {

    /**
     * @description: md5加密
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 6:47 AM
     **/
    public static String md5(String text, String key) {
        String encodeStr = new String(DigestUtils.md5Digest((text + key).getBytes()));
        return encodeStr;
    }

}
