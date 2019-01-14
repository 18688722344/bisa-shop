package com.bisa.health.shop.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import com.bisa.health.shop.dto.GoodsShopcar;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

public class ShopCartCookieUtils {

    /**
     * 从cookie中获取数据， 封装购物车对象
     * @param cookies
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static List<GoodsShopcar> getBuyerCartCookies(Cookie[] cookies, String cookie_key)
            throws JsonParseException, JsonMappingException, IOException {

        List<GoodsShopcar> buyerCartItemList = null;
        GoodsShopcar shopCart = null;
        if (cookies != null && cookies.length > 0) {
            buyerCartItemList = new ArrayList<GoodsShopcar>();
            for (Cookie cookie : cookies) {
                if (cookie_key.equals(cookie.getName())) {
                    // [{"id":0,"user_id":214,"classify":11,"count":2}]
                    String cookiesValue = URLDecoder.decode(cookie.getValue(), "utf-8");
                    org.json.JSONArray jsonArray = new org.json.JSONArray(cookiesValue);
                    Gson gson = new Gson();
                    for (Integer i = 0; i < jsonArray.length(); i++) {
                        shopCart = gson.fromJson(jsonArray.getJSONObject(i).toString(), GoodsShopcar.class);
                        buyerCartItemList.add(shopCart);
                    }
                }
            }
        }
        return buyerCartItemList;
    }

    /**
     * 设置cookie
     * @param buyerjson
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Cookie resetCartCookie(String buyerJson, String cookie_key) throws UnsupportedEncodingException {
        // 设置中文编码
        buyerJson = URLEncoder.encode(buyerJson, "utf-8");

        Cookie cookie = new Cookie(cookie_key, buyerJson);
        // 设置path是可以共享cookie
        cookie.setPath("/");
        // 设置Cookie存活时间
        cookie.setMaxAge(24 * 60 * 60);// 一天

        return cookie;
    }

    /**
     * 销毁浏览器中的cookie_ss
     * @param cookie_ss
     * @return
     */
    public static Cookie destroyCookie(String cookie_key) {

        Cookie cookie = new Cookie(cookie_key, null);
        cookie.setPath("/");// 删除cookie时，path必须一致，否则不会删除。
        cookie.setMaxAge(0); // 0表示立即失效
        return cookie;
    }

    /**
     * 计算cookie中购物车项目数量
     * @param cookies
     * @param buyerCart
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Integer getBuyerCartCountCookies(Cookie[] cookies, String cookie_key) {
        int cartCount = 0;

        try {
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie_key.equals(cookie.getName())) {
                        // [{"id":0,"user_id":214,"classify":11,"count":2}]
                        String cookiesValue = URLDecoder.decode(cookie.getValue(), "utf-8");
                        org.json.JSONArray jsonArray = new org.json.JSONArray(cookiesValue);
                        cartCount = jsonArray.length();
                    }
                }
            }
        } catch (Exception e) {
        }

        return cartCount;
    }

    /**
     * 返回cookie中保存的sid
     * @param cookies
     * @param cookie_key
     * @return
     */
    public static String getCartRedisKeyFromCookie(Cookie[] cookies, String cookie_key) {
        try {
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie_key.equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
