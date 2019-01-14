package com.bisa.health.shop.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 处理银联支付回调的签名
 * @author Administrator
 */

public class EasylinkSignatureUtil {

	@SuppressWarnings("rawtypes")
	public static Map<String, String> buildRequestParameters(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		Map parameters = request.getParameterMap();
		Map<String, String> map = new HashMap<String, String>();
		if (parameters != null && parameters.size() > 0) {
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String[] values = (String[]) parameters.get(key);
				if (values.length == 1) {
					map.put(key, values[0]);
				}
			}
		}
		return map;
	}

	/**
	 * 生成签名信息
	 * @param request 基础请求对象
	 * @return 签名信息
	 */
	public static String generateSignature(Object obj, String secretKey) {
		// 获取验签映射集
		Map<String, String> map = SignatureUtil.getSignatureMap(obj);
		List<String> encodeList = SignatureUtil.mapToListBySort(map);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < encodeList.size(); i++) {
			sb.append(encodeList.get(i)).append("&");
		}
		sb.append(secretKey);
		String signature = DigestUtils.sha256Hex(sb.toString().trim());
		return signature;
	}
}
