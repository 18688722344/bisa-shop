package com.bisa.health.shop.admin.dto;

import java.io.Serializable;

/**
 * 订单下面商品的所有信息
 * @author Administrator
 */
public class OrderGoodsDto implements Serializable {

    private String goods_name; // 商品名字
    private String goods_number_id; // 商品编号
    private Integer count; // 购买的商品数量
    private String goods_price; // (下单时间的价格，不会根据变价而改动)单价 1000
    private String preferential_price; // (下单时间的价格，不会根据变价而改动)优惠价格 800

    public String getGoods_number_id() {
        return goods_number_id;
    }

    public void setGoods_number_id(String goods_number_id) {
        this.goods_number_id = goods_number_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getPreferential_price() {
        return preferential_price;
    }

    public void setPreferential_price(String preferential_price) {
        this.preferential_price = preferential_price;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

}
