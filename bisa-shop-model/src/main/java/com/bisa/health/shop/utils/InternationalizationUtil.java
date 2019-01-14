package com.bisa.health.shop.utils;

import com.bisa.health.shop.enumerate.InternationalizationEnum;

import javax.servlet.http.HttpSession;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

/**
 * 判断处理国际化语言 工具类
 */

public class InternationalizationUtil {

    /**
     * 根据springmvc的session 获取当前语言的类型
     * @param session
     * @return
     */
    public static String getLang(HttpSession session) {

        //获取springmvc中的国际化语言
        Object lang = session.getAttribute(LOCALE_SESSION_ATTRIBUTE_NAME);
        String langstr = null;
        if (lang == null) {
            langstr = InternationalizationEnum.zh_HK.getName();
        } else {
            langstr = lang.toString();
        }
        return langstr;
    }
}
