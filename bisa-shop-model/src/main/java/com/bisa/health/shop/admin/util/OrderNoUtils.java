package com.bisa.health.shop.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成订单编号的工具类
 * 
 * @author Administrator
 *
 */
public class OrderNoUtils {

	/**
	 * 根据时间生成商品的编号
	 * @return
	 */
	public static String getOrderIdByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			result += random.nextInt(10);
		}
		return "BISA" + newDate + result;
	}

	public static void main(String[] args) {
		String string = getOrderIdByTime();
		System.out.println(string);
	}
}
