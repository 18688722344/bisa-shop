package com.bisa.health.shop.dto;

import java.io.Serializable;

import com.bisa.health.shop.model.Combo;
import com.bisa.health.shop.model.Goods;

/**
 * 购物车model
 * @author Administrator
 */

public class GoodsShopcar implements Serializable {

    private static final long serialVersionUID = 7053947934564599440L;

    private Integer id;
    private Integer goodsType; //商品/套餐
    private String goodsGuid; //商品编号/套餐编号
    private Integer count; // 商品的数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsGuid() {
        return goodsGuid;
    }

    public void setGoodsGuid(String goodsGuid) {
        this.goodsGuid = goodsGuid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public GoodsShopcar() {
        super();
    }

    public GoodsShopcar(Goods goods) {
        super();
        this.id = 0;
        this.goodsType = 0;
        this.goodsGuid = goods.getGoodsNumber();
        this.count = 1;
    }

    public GoodsShopcar(Combo combo) {
        super();
        this.id = 0;
        this.goodsType = 1;
        this.goodsGuid = combo.getGoodsNumber();
        this.count = combo.getCount();
    }

    public GoodsShopcar(Integer id, Integer goodsType, String goodsGuid, Integer count) {
        super();
        this.id = id;
        this.goodsType = goodsType;
        this.goodsGuid = goodsGuid;
        this.count = count;
    }

    @Override
    public String toString() {
        return "GoodsShopcar [id=" + id + ", goodsType=" + goodsType + ", goodsGuid=" + goodsGuid + ", count=" + count + "]";
    }

}