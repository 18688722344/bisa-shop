package com.bisa.health.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 关于碧沙
 * @author Administrator
 */

@Controller
public class CertificateController {

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "/certificate", method = RequestMethod.GET)
	public String index() {
		return "certificate/certificate";
	}


}
