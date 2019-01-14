package com.bisa.health.shop.utils;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.bisa.health.common.utils.HmacSHA256Utils;

public class TokenCreateUtils {

    /**
     * 生成一个令牌
     * @return String
     */
    public static String generateToken(String tokenStr) {
        String random = System.currentTimeMillis() + new Random().nextInt() + "";
        String token = HmacSHA256Utils.digest(tokenStr, random);
        return token;
    }

    /**
     * 管理令牌
     * @param request
     * @param tokenStr 用于生成token的字符串
     * @param mType    管理类型 p:存放 d:删除
     * @return
     */
    public static String managerToken(HttpServletRequest request, String tokenStr, String mType) {
        if ("p".equals(mType)) {
            //产生token
            String token = generateToken(tokenStr);
            request.getSession().setAttribute("token", token);
            return token;
        } else if ("d".equals(mType)) {
            request.getSession().removeAttribute("token");
        }
        return null;
    }

    /**
     * 令牌是否验证通过
     * @param request
     * @return boolean
     */
    public static boolean isTokenValid(HttpServletRequest request) {
        String client_token = request.getParameter("token");
        if (client_token == null) {
            return false;
        }
        String server_token = (String) request.getSession().getAttribute("token");
        if (server_token == null) {
            return false;
        }
        if (!client_token.equals(server_token)) {
            return false;
        }
        return true;
    }
}
