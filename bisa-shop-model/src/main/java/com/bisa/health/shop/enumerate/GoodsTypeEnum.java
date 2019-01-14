package com.bisa.health.shop.enumerate;

/**
 * 商品的类型(这里定义的是前台自定义的类型，主要用途是商品的详情页面下单 或者加入购物车的时候要用到)
 * @author Administrator
 */

public enum GoodsTypeEnum {

    /**
     * 单品
     */
    single(0, "single"),
    /**
     * 套餐
     */
    combo(1, "combo"),
    /**
     * 虚拟商品
     */
    virtual(2, "virtual");


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

    private GoodsTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GoodsTypeEnum getByValue(int value) {
        for (GoodsTypeEnum status : values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
