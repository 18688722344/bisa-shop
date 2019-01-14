package com.bisa.health.shop.admin.enums;

/**
 * 留言类型
 * @author Administrator
 *
 */

public enum LeaveMsgTpye {
	// 1-商品咨询；2-服务咨询；3-售后；4-账户异常；5-无关项(其他)
	/**
	 * 处理人 还没有 标注 留言类型
	 */
	none(0, "NONE"),
	/**
	 * 商品咨询
	 */
	product_consult(1, "PRODUCT_CONSULT"),
	/**
	 * 服务咨询
	 */
	service_consult(2, "SERVICE_CONSULT"),
	/**
	 * 售后咨询
	 */
	after_sale(3, "AFTER_SALE"),
	/**
	 * 账户异常
	 */
	account_abnormal(4, "ACCOUNT_ABNORMAL"),
	/**
	 * 其他
	 */
	other(5, "OTHER");

	private int value;
	private String name;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private LeaveMsgTpye(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static LeaveMsgTpye getByValue(int value) {
		for (LeaveMsgTpye status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
