package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 打印 订单集合的对象 下面的商品明细
 * @author Administrator
 */
public class PrintOrderGoodsListDto implements Serializable {

    private String product_name;// 商品的名称
    private String ascription_guid;// 商品的编号
    private Integer count;// 购买的数量
    private BigDecimal price;// 单项单价

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getAscription_guid() {
        return ascription_guid;
    }

    public void setAscription_guid(String ascription_guid) {
        this.ascription_guid = ascription_guid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
