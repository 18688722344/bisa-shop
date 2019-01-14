package com.bisa.health.shop.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 基础请求对象
 * @author Ben.
 */

@SuppressWarnings("serial")
public class BaseRequest implements Serializable {
	/**
	 * 签名信息
	 */
	@NotEmpty
	protected String accessKey;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	@Override
	public String toString() {
		return "BaseRequest [accessKey=" + accessKey + "]";
	}

}
