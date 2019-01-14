package com.bisa.health.shop.admin.enums;

/**
 * 用户评价 的状态
 * @author Administrator
 *
 */
public enum AppraiseStatus {

	/**
	 * 失望
	 */
	veryBad(1, "VERYBAD"),
	/**
	 * 一般
	 */
	bad(2, "BAD"),
	/**
	 * 满意
	 */
	general(3, "GENERAL"),
	/**
	 * 喜欢
	 */
	good(4, "GOOD"),
	/**
	 * 超爱
	 */
	veryGood(5, "VERYGOOD");

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

	private AppraiseStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static AppraiseStatus getByValue(int value) {
		for (AppraiseStatus status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
