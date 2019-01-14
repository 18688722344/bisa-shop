package com.bisa.health.shop.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付响应参数
 * @author Ben.
 */

public class PayResponse  {

	/**
	 * 賬單編號
	 */
	private String paymentId;

	/**
	 * 交易狀態
	 */
	private String status;
	/**
	 * 響應碼
	 */
	private String respCode;

	/**
	 * 響應碼描述
	 */
	private String respMsg;
	/**
	 * 支付渠道URL
	 */
	private String url;
	/**
	 * 支付渠道信息
	 */
	private Map<String, String> formData;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public void setFormData(Map<String, String> formData) {
		this.formData = formData;
	}

	public Map<String, String> getFormData() {
		if (this.formData == null) {
			this.formData = new HashMap<String, String>();
		}
		return formData;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
