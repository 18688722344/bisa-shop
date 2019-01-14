package com.bisa.health.shop.utils;

public class PhoneTypeUtil {

    /**
     * 根据USER-AGENT判断访问客户端类型
     * @param userAgent
     * @return
     */
    public static String phoneType(String userAgent) {
        if (null == userAgent) {
            userAgent = "";
        } else {
            String keys[] = {"android", "iphone", "ipod", "windows phone", "mqqbrowser", "ucbrowser", "micromessenger"};
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("windows nt") < 0 && userAgent.indexOf("macintosh") < 0) {
                for (int i = 0; i < keys.length; i++) {
                    if (userAgent.indexOf(keys[i]) > -1) {    // 移动端
                        if (userAgent.indexOf("iphone") < 0) {    //	安卓
                            return "android";
                        } else if (userAgent.indexOf("iphone") > 0) {    //	苹果
                            return "iphone";
                        }
                    }
                }
            } else {
                return "computer";
            }
        }
        return null;
    }
}
