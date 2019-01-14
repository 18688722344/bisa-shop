package com.bisa.health.shop.admin.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理商品标签的工具类
 * @author Administrator
 */

public class GoodsTagUtils {

	public static void main(String[] args) {
		String goodsTag = GoodsTagUtils.getGoodsTag("10");
		System.out.println(goodsTag);
	}

	/**
	 * 把商品标签表的数据 封装成list集合的数据
	 * @param content
	 * @return 
	 */
	public static List<String> getGoodsShowTag(String content) {
		List<String> list = new ArrayList<>();
		String string = content + ",";
		int x = string.indexOf(",");

		int y = 0;
		while (x != -1) {
			String string2 = string.substring(y, x);
			list.add(string2);

			y = x + 1;
			x = string.indexOf(",", y);
		}
		return list;
	}

	/**
	 * 保存商品的时候 组合成要的数据    1003,1005
	 * @param string   5/10
	 * @return
	 */
	public static String getGoodsTag(String string) {
		if (string.indexOf("/") == -1) {
			return string;
		}
		string += ",";
		String string2 = "";
		int x = string.indexOf("/");
		int y = string.indexOf(",");

		while (x != -1) {
			string2 += string.substring(x + 1, y) + ",";

			x = string.indexOf("/", x + 1);
			y = string.indexOf(",", x + 1);
		}
		string2 = string2.substring(0, string2.length() - 1);
		return string2;
	}

}
