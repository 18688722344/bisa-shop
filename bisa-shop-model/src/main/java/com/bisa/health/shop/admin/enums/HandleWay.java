package com.bisa.health.shop.admin.enums;

/**
 * 留言   处理方式
 * @author Administrator
 *
 */

public enum HandleWay {
	//（1-邮箱；2-短信；3-通话）
	/**
	 * 处理人 还没有 处理
	 */
	none(0, "NONE"),
	/**
	 * 邮箱
	 */
	email(1, "EMAIL"),
	/**
	 * 短信
	 */
	note(2, "NOTE"),
	/**
	 * 通话
	 */
	call(3, "CALL");
	

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

	private HandleWay(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static HandleWay getByValue(int value) {
		for (HandleWay status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
