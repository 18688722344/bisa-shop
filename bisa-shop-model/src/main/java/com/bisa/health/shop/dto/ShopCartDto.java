package com.bisa.health.shop.dto;

import java.io.Serializable;

/**
 * 用于 购物车显示 的封装DTO
 * @author Administrator
 */

public class ShopCartDto implements Serializable {

    private GoodsComboDto goodsComboDto;
    private GoodsShopcar goodsShopCart;

    public GoodsComboDto getGoodsComboDto() {
        return goodsComboDto;
    }

    public void setGoodsComboDto(GoodsComboDto goodsComboDto) {
        this.goodsComboDto = goodsComboDto;
    }

    public GoodsShopcar getGoodsShopCart() {
        return goodsShopCart;
    }

    public void setGoodsShopCart(GoodsShopcar goodsShopCart) {
        this.goodsShopCart = goodsShopCart;
    }

    public ShopCartDto(GoodsComboDto goodsComboDto, GoodsShopcar goodsShopCart) {
        super();
        this.goodsComboDto = goodsComboDto;
        this.goodsShopCart = goodsShopCart;
    }

    public ShopCartDto() {
        super();
    }

}
