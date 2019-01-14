package com.bisa.health.shop.dto;

import java.io.Serializable;

/**
 * 购物车去结算
 * @author Administrator
 */
public class ShopCartCommitDto implements Serializable {

    private GoodsComboDto goodsComboDto;
    private int count;    //商品数量
    private String cartGuid;    //商品/套餐编号
    private int cartIndex;    //购物车索引，用于修改删除

    public GoodsComboDto getGoodsComboDto() {
        return goodsComboDto;
    }

    public void setGoodsComboDto(GoodsComboDto goodsComboDto) {
        this.goodsComboDto = goodsComboDto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCartGuid() {
        return cartGuid;
    }

    public void setCartGuid(String cartGuid) {
        this.cartGuid = cartGuid;
    }

    public int getCartIndex() {
        return cartIndex;
    }

    public void setCartIndex(int cartIndex) {
        this.cartIndex = cartIndex;
    }

    public ShopCartCommitDto() {
        super();
    }

    public ShopCartCommitDto(GoodsComboDto goodsComboDto, int count, String cartGuid, int cartIndex) {
        super();
        this.goodsComboDto = goodsComboDto;
        this.count = count;
        this.cartGuid = cartGuid;
        this.cartIndex = cartIndex;
    }

    @Override
    public String toString() {
        return "ShopCartCommitDto [goodsComboDto=" + goodsComboDto + ", count=" + count + ", cartGuid=" + cartGuid
                + ", cartIndex=" + cartIndex + "]";
    }

}
