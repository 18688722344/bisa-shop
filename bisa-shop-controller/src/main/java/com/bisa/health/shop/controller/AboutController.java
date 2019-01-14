package com.bisa.health.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 关于碧沙
 * @author Administrator
 */

@Controller
public class AboutController {

	/**
	 * 关于
	 * @return
	 */
	@RequestMapping(value = "/aboutBis", method = RequestMethod.GET)
	public String index() {
		return "other/About";
	}

	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	public  String menuList(){
		return "menuList/menuList";
	}

}
