package com.bisa.health.shop.controller;

import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.client.entity.User;
import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.shop.component.ShopCartComponent;
import com.bisa.health.shop.dto.BuyerCart;
import com.bisa.health.shop.dto.GoodsShopcar;
import com.bisa.health.shop.enumerate.GoodsTypeEnum;
import com.bisa.health.shop.utils.ShopCartCookieUtils;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 购物车
 * 第一版本：游客模式cookie + 登录用户数据存数据库
 * 第二版本： 游客模式cookie+redis + 登录用户数据存数据库
 * 第三版本（当前）：游客模式cookie + 登录用户数据存redis
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/web/call")
public class BuyCartController {

    private Logger logger = LogManager.getLogger(BuyCartController.class);

    private static final String BUYER_CART = "cart";

    @Autowired
    private ShopCartComponent shopCartComponent;
    @Value("${max_cart_count}")
    private String MAX_CART_COUNT; //购物车数量上限

    /**
     * 查看购物车页面
     * @return
     */
    @RequestMapping(value = "/toCart", method = RequestMethod.GET)
    public String toCart() {
        return "shopping/buyerCart";
    }

    /**
     * 加载 用户购物车的数量
     */
    @RequestMapping(value = "/getCartNumber", method = RequestMethod.GET)
    @ResponseBody
    public Integer getCartNumber(HttpServletRequest request, @CurrentUser User user) {
        // 判断是否登录状态
        Subject subject = SecurityUtils.getSubject();
        Integer shopCartNumber = 0;
        if (!subject.isAuthenticated()) {//未登录
            // 获取Cookie中的购物车
            shopCartNumber = ShopCartCookieUtils.getBuyerCartCountCookies(request.getCookies(), BUYER_CART);
        } else {//已登录
            String redisKey = BUYER_CART + ":" + user.getUser_guid();
            shopCartNumber = shopCartComponent.getCartCountFromRedis(redisKey);
        }
        return shopCartNumber;
    }

    /**
     * 从cookie中，加载商品信息
     */
    @RequestMapping(value = "/loadBuyerCartJson", method = RequestMethod.GET)
    @ResponseBody
    public String loadBuyerCartJson(HttpServletRequest request, @CurrentUser User user, HttpSession session) {

        Subject subject = SecurityUtils.getSubject();
        List<Object> allShopCartDetail = null;
        JSONObject jsonObject = new JSONObject();
        try {
            // 游客状态-从cookie获取数据
            if (!subject.isAuthenticated()) {
                // 获取Cookie中的购物车
                List<GoodsShopcar> listGoodsShopCar = ShopCartCookieUtils.getBuyerCartCookies(request.getCookies(), BUYER_CART);
                Collections.reverse(listGoodsShopCar); //倒序

                if (listGoodsShopCar != null) {
                    logger.debug("Cookie中的购物车有数据");
                    // 获取购物车所有商品信息
                    allShopCartDetail = shopCartComponent.getShopCartsDetailsByCartItems(listGoodsShopCar, session);
                }
            } else {
                // 登录状态-从redis获取购物车数据
                String redisKey = BUYER_CART + ":" + user.getUser_guid();
                allShopCartDetail = shopCartComponent.getShopCartsFromRedis(redisKey, session);
            }
            jsonObject.put("shopCarts", allShopCartDetail);
            jsonObject.put("code", 200);
            String jsonstr = jsonObject.toString();
            return jsonstr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("code", 400);
        String jsonstr = jsonObject.toString();
        return jsonstr;
    }

    /**
     * 加入购物车
     * @param goodsType 商品的类型 (单品还是套餐)
     * @param goodsGuid 商品编码/套餐编号
     * @return
     */
    @RequestMapping(value = "/addBuyerCart", method = RequestMethod.POST)
    @ResponseBody
    public String addBuyerCart(HttpServletRequest request, HttpServletResponse response, @CurrentUser User sysUser,
                              int goodsType,
                              String goodsGuid) {

        Subject subject = SecurityUtils.getSubject();
        try {
            List<GoodsShopcar> items = ShopCartCookieUtils.getBuyerCartCookies(request.getCookies(), BUYER_CART);
            // cookie中没有购物车，创建购物车对象
            if (items == null) {
                items = new ArrayList<GoodsShopcar>();
            }
            //购物车数量达到上限
            if (items.size() == Integer.parseInt(MAX_CART_COUNT)) {
                return ExceptionCodeEnum.PAGE_EXPIRED.getValue();
            }
            BuyerCart buyCart = new BuyerCart();
            buyCart.setItems(items);

            if (goodsType == GoodsTypeEnum.single.getValue()) {// 单品
                // 添加数量
                GoodsShopcar shopCart = new GoodsShopcar(buyCart.getMaxId() + 1, goodsType, goodsGuid, 1);
                buyCart.addItem(shopCart);

                // 登录用户，添加数据到redis
                if (subject.isAuthenticated()) {
                    String redisKey = BUYER_CART + ":" + sysUser.getUser_guid();
                    shopCartComponent.addUpdateShopCartsInRedis(redisKey, goodsType, goodsGuid, shopCart.getCount());
                }

            } else {// 套餐或虚拟商品
                // 添加数量
                GoodsShopcar shopCart = new GoodsShopcar(buyCart.getMaxId() + 1, goodsType, goodsGuid, 1);
                buyCart.addItem(shopCart);
                // 登录用户，添加数据到redis
                if (subject.isAuthenticated()) {
                    String redisKey = BUYER_CART + ":" + sysUser.getUser_guid();
                    shopCartComponent.addUpdateShopCartsInRedis(redisKey, goodsType, goodsGuid, shopCart.getCount());
                }
            }
            if (!subject.isAuthenticated()) {
                // 游客模式，加入数据到cookie
                Gson gson = new Gson();
                String itemsJson = gson.toJson(buyCart.getItems());
                Cookie cookie = ShopCartCookieUtils.resetCartCookie(itemsJson, BUYER_CART);
                response.addCookie(cookie);
            }
            return ExceptionCodeEnum.SUCCESS.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("加入购物车失败---", e);
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 修改购物车数量
     * @param index     购物车索引
     * @param goodsGuid 商品编号/套餐编号
     * @param count     数量
     * @return
     */
    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    @ResponseBody
    public String updateCart(HttpServletRequest request, HttpServletResponse response, @CurrentUser User sysUser,
                             Integer index,
                             String goodsGuid,
                             Integer count) {

        Subject subject = SecurityUtils.getSubject();
        try {
            // 登录用户，添加数据到数据库
            if (subject.isAuthenticated()) {
                String redisKey = BUYER_CART + ":" + sysUser.getUser_guid();
                shopCartComponent.addUpdateShopCartsInRedis(redisKey, 0, goodsGuid, count, index);

                return ExceptionCodeEnum.SUCCESS.getValue();
            } else {
                List<GoodsShopcar> items = ShopCartCookieUtils.getBuyerCartCookies(request.getCookies(), BUYER_CART);
                // cookie中没有购物车，创建购物车对象
                if (items == null) {
                    items = new ArrayList<GoodsShopcar>();
                }
                BuyerCart buyCart = new BuyerCart();
                buyCart.setItems(items);
                // 修改数量
                GoodsShopcar shopCart = new GoodsShopcar(0, 0, goodsGuid, count);
                buyCart.updateItem(shopCart);
                // 游客模式，加入数据到cookie
                Gson gson = new Gson();
                //Collections.reverse(buyCart.getItems()); // 倒序
                String itemsJson = gson.toJson(buyCart.getItems());
                Cookie cookie = ShopCartCookieUtils.resetCartCookie(itemsJson, BUYER_CART);
                response.addCookie(cookie);

                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("购物车更新失败---", e);
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 删除单个或多个商品
     * @param index     id,购物车索引   用于删除redis中数据
     * @param goodsType 商品的类型 (单品还是套餐)
     * @param goodsGuid 商品编号/套餐编号
     * @return
     */
    @RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
    @ResponseBody
    public String deleteBuyerCart(HttpServletRequest request, HttpServletResponse response, @CurrentUser User sysUser,
                                  Integer index,
                                  Integer goodsType,
                                  String goodsGuid) {

        Subject subject = SecurityUtils.getSubject();
        try {

            if (subject.isAuthenticated()) { //登录 用户，删除redis中购物车项
                String redisKey = BUYER_CART + ":" + sysUser.getUser_guid();
                shopCartComponent.deleteShopCartsInRedis(redisKey, index);

                return ExceptionCodeEnum.SUCCESS.getValue();
            } else {
                List<GoodsShopcar> items = ShopCartCookieUtils.getBuyerCartCookies(request.getCookies(), BUYER_CART);
                // cookie中没有购物车，创建购物车对象
                if (items == null) {
                    items = new ArrayList<GoodsShopcar>();
                }
                BuyerCart buyCart = new BuyerCart();
                buyCart.setItems(items);
                // 删除购物车项
                buyCart.delItem(index);
                // 游客模式，加入数据到cookie
                Gson gson = new Gson();
                String itemsJson = gson.toJson(buyCart.getItems());
                Cookie cookie = ShopCartCookieUtils.resetCartCookie(itemsJson, BUYER_CART);
                response.addCookie(cookie);
                return ExceptionCodeEnum.SUCCESS.getValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("购物车删除失败---", e);
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

}
