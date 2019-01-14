package com.bisa.health.shop.admin.enums;
/**
 * 激活卡状态
 * @author Administrator
 *
 */
public enum AdminCard {
	
	/**
	 * 未激活
	 */
	unactivated(0, "unactivated"), 
	
	/**
	 * 已激活
	 */
	activated(1, "activated");

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

	private AdminCard(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static AdminCard getByValue(int value) {
		for (AdminCard status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
