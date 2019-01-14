package com.bisa.health.shop.admin.enums;

/**
 * 留言  处理状态
 *
 * @author Administrator
 */

public enum HandleStatus {
    /**
     * 待处理
     */
    pending(0, "PENDING"),
    /**
     * 处理完毕
     */
    figured_out(1, "FIGURED_OUT");

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

    private HandleStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static HandleStatus getByValue(int value) {
        for (HandleStatus status : values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
