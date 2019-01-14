package com.bisa.health.shop.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bisa.health.client.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.bisa.health.cache.kit.RedisKit;
import com.bisa.health.shop.component.ShopCartComponent;
import com.bisa.health.shop.dto.GoodsShopcar;
import com.bisa.health.shop.utils.ShopCartCookieUtils;

/**
 * 登陆成功之后， 跳转页面之前过滤，
 * 对cookie中的购物车数据进行处理，
 * 与当前登录用户的购物车数据进行合并
 * @author Administrator
 *
 */
public class ShopCartFilter extends AccessControlFilter {
	
	@Autowired
	private ShopCartComponent shopCartComponent;
	
	@Autowired
	private RedisKit redisKit;
	
	private static final String BUYER_CART = "cart";
	
	private static final Logger log = LogManager.getLogger(ShopCartFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest req, ServletResponse res, Object mappedValue)
			throws Exception {
		
		Subject subject = getSubject(req, res);
		
		if (null != subject && subject.isAuthenticated()) {// 判断是否登入

			HttpServletRequest request = WebUtils.toHttp(req);
			HttpServletResponse response = WebUtils.toHttp(res);
			User user=(User) subject.getSession().getAttribute(User.class.getName());

			String redisKey = BUYER_CART + ":" + user.getUser_guid();
			// 从cookie获取购物车数据
			List<GoodsShopcar> listGoodsShopCar = ShopCartCookieUtils.getBuyerCartCookies(request.getCookies(), BUYER_CART);
				
			if (listGoodsShopCar == null) { // cookie无购物车数据
				return false;
			} else {
				log.info("items size:" + listGoodsShopCar.size());
				for (int i = 0; i < listGoodsShopCar.size(); i ++) {
					GoodsShopcar goodShopcart = listGoodsShopCar.get(i);
					shopCartComponent.addUpdateShopCartsInRedis(redisKey,goodShopcart.getGoodsType(),goodShopcart.getGoodsGuid(), goodShopcart.getCount());
				}
				// 清空cookie中的购物车数据
				Cookie cookie = ShopCartCookieUtils.destroyCookie(BUYER_CART);
				response.addCookie(cookie);

				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		log.info("未登录");  
		return true;
	}
}
