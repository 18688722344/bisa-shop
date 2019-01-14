package com.bisa.health.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 购物指南
 * @author Administrator
 */

@Controller
public class PayGuideController {
	/**
	 * 购物指南
	 * @return
	 */
	@RequestMapping(value = "/shoppingGuide", method = RequestMethod.GET)
	public String shoppingGuide(HttpServletRequest request) {
		request.setAttribute("request_type", "shoppingGuide");
		return "other/HowToShop";
	}

	/**
	 * 购物流程
	 * @return
	 */
	@RequestMapping(value = "/shoppingProcess", method = RequestMethod.GET)
	public String shoppingProcess(HttpServletRequest request) {
		request.setAttribute("request_type", "shoppingProcess");
		return "other/HowToShop";
	}

	/**
	 * 包邮政策
	 * @return
	 */
	@RequestMapping(value = "/deliveryDetail", method = RequestMethod.GET)
	public String deliveryDetail(HttpServletRequest request) {
		request.setAttribute("request_type", "deliveryDetail");
		return "other/HowToShop";
	}

	/**
	 * 下单及支付时效
	 * @return
	 */
	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
	public String placeOrder(HttpServletRequest request) {
		request.setAttribute("request_type", "placeOrder");
		return "other/HowToShop";
	}

	/**
	 * 发货时效
	 * @return
	 */
	@RequestMapping(value = "/deliveryTime", method = RequestMethod.GET)
	public String deliveryTime(HttpServletRequest request) {
		request.setAttribute("request_type", "deliveryTime");
		return "other/HowToShop";
	}

	/**
	 * 支付方式
	 * paymentMethod
	 */
	@RequestMapping(value = "/paymentMethod", method = RequestMethod.GET)
	public String paymentMethod(HttpServletRequest request) {
		request.setAttribute("request_type", "paymentMethod");
		return "other/HowToShop";
	}

	/**
	 * 售后政策
	 * @return
	 */
	@RequestMapping(value = "/afterSales", method = RequestMethod.GET)
	public String afterSales(HttpServletRequest request) {
		request.setAttribute("request_type", "afterSales");
		return "other/HowToShop";
	}

}
