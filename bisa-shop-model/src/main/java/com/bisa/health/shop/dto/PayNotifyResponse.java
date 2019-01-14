package com.bisa.health.shop.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.bisa.health.shop.pay.annotation.Signature;

@SuppressWarnings("serial")
public class PayNotifyResponse extends BaseRequest {

	/**
	 * 賬單編號
	 */
	@NotEmpty
	@Signature
	private String paymentId;

	/**
	 * 交易狀態
	 */
	@NotEmpty
	@Signature
	private String status;

	/**
	 * 響應碼
	 */
	@NotEmpty
	@Signature
	private String respCode;
	/**
	 * 響應碼描述
	 */
	@NotEmpty
	@Signature
	private String respMsg;

	/**
	 * 清算匯率
	 */
	@Signature
	private String exchangeRate;

	/**
	 * 清算日期
	 */
	@Signature
	private String settleDate;

	/**
	 * 清算幣種
	 */
	@Signature
	private String settleCurrency;

	/**
	 * 清算金額
	 */
	@Signature
	private String settleAmount;

	/**
	 * 訂單幣種
	 */
	@NotEmpty
	@Signature
	private String orderCurrency;

	/**
	 * 訂單金額
	 */
	@NotEmpty
	@Signature
	private String orderAmount;

	/**
	 * 商戶保留域
	 */
	@Signature
	private String reserved;

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

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	public String getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	@Override
	public String toString() {
		return "PayNotifyResponse [paymentId=" + paymentId + ", status=" + status + ", respCode=" + respCode + ", respMsg=" + respMsg
				+ ", exchangeRate=" + exchangeRate + ", settleDate=" + settleDate + ", settleCurrency=" + settleCurrency + ", settleAmount="
				+ settleAmount + ", orderCurrency=" + orderCurrency + ", orderAmount=" + orderAmount + ", reserved=" + reserved + "]";
	}

}
