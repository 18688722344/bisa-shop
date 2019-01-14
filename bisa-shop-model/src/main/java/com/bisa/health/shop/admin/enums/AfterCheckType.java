package com.bisa.health.shop.admin.enums;

/**
 * 售后处理状态
 * @author Administrator
 *
 */
public enum AfterCheckType {

	/**
	 * 和用户描述的问题  相符合
	 */
	conform(1, "CONFORM"),
	/**
	 * 和用户描述的问题  不相符合
	 */
	unconform(2, "UNCONFORM");

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

	private AfterCheckType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static AfterCheckType getByValue(int value) {
		for (AfterCheckType status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
