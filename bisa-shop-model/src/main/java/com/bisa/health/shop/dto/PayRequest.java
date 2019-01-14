package com.bisa.health.shop.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.bisa.health.shop.pay.annotation.Signature;

/**
 * 支付接口请求对象
 * @author Ben.
 *
 */
@SuppressWarnings("serial")
public class PayRequest extends BaseRequest {

	/**
	 * PIN碼
	 */
	@NotEmpty
	@Signature
	private String pin;
	/**
	 * SecondPin碼
	 */
	@NotEmpty
	@Signature
	private String secPin;

	/**
	 * 交易金額
	 */
	@NotEmpty
	@Signature
	private String amount;

	/**
	 * 訂單生成日期
	 */
	@NotEmpty
	@Signature
	private String orderCreateTime;
	/**
	 * 商戶訂單編號
	 */
	@NotEmpty
	@Signature
	private String orderId;

	/**
	 * 通知URL
	 */
	@NotEmpty
	@Signature
	private String callbackUrl;

	/**
	 * 返回URL
	 */
	@NotEmpty
	@Signature
	private String frontendUrl;
	/**
	 * 渠道編號
	 */
	@NotEmpty
	@Signature
	private String channel;
	/**
	 * 持卡人IP
	 */
	@NotEmpty
	@Signature
	private String customerIp;

	/**
	 * 持卡人卡號
	 */
	private String merchantCardNumber;
	/**
	 * 銀行代碼
	 */
	private String bankId;
	/**
	 * 商戶保留域
	 */
	private String merReserved;
	/**
	 * 手续费扣费类型
	 */
	private String feeDeductWay;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getSecPin() {
		return secPin;
	}

	public void setSecPin(String secPin) {
		this.secPin = secPin;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getFrontendUrl() {
		return frontendUrl;
	}

	public void setFrontendUrl(String frontendUrl) {
		this.frontendUrl = frontendUrl;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getMerchantCardNumber() {
		return merchantCardNumber;
	}

	public void setMerchantCardNumber(String merchantCardNumber) {
		this.merchantCardNumber = merchantCardNumber;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getMerReserved() {
		return merReserved;
	}

	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}

	public String getFeeDeductWay() {
		return feeDeductWay;
	}

	public void setFeeDeductWay(String feeDeductWay) {
		this.feeDeductWay = feeDeductWay;
	}

	@Override
	public String toString() {
		return "PayRequest [pin=" + pin + ", secPin=" + secPin + ", amount=" + amount + ", orderCreateTime=" + orderCreateTime
				+ ", orderId=" + orderId + ", callbackUrl=" + callbackUrl + ", frontendUrl=" + frontendUrl + ", channel=" + channel
				+ ", customerIp=" + customerIp + ", merchantCardNumber=" + merchantCardNumber + ", bankId=" + bankId + ", merReserved="
				+ merReserved + ", feeDeductWay=" + feeDeductWay + "]";
	}

}
