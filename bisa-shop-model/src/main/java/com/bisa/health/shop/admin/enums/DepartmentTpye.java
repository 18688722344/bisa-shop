package com.bisa.health.shop.admin.enums;

/**
 * 售后服务类型
 * @author Administrator
 *
 */

public enum DepartmentTpye {
	/**
	 * 客服
	 */
	service(1, "SERVICE"),
	/**
	 * 物流
	 */
	logistics(2, "LOGISTICS"),
	/**
	 * 财务
	 */
	finance(3, "FINANCE");

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

	private DepartmentTpye(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static DepartmentTpye getByValue(int value) {
		for (DepartmentTpye status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
