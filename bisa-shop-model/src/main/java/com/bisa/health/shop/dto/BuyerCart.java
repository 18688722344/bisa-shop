package com.bisa.health.shop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bisa.health.shop.enumerate.GoodsTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 购物车数据，添加修改，删除
 * @author Administrator
 */

public class BuyerCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品结果集
     */
    private List<GoodsShopcar> items = new ArrayList<GoodsShopcar>();

    public List<GoodsShopcar> getItems() {
        return items;
    }

    public void setItems(List<GoodsShopcar> items) {
        this.items = items;
    }

    /**
     * 购物车id从0开始
     * @return
     */
    public int getMaxId() {
        if (items.size() == 0) {
            return -1;
        }
        return items.get(items.size() - 1).getId();
    }

    /**
     * 添加购物车
     * @param item
     */
    public void addItem(GoodsShopcar item) {

        if (items.size() > 0) {
            for (GoodsShopcar buyerItem : items) {
                //单品增加数量
                if (buyerItem.getGoodsType() != GoodsTypeEnum.combo.getValue() && buyerItem.getGoodsGuid().equals(item.getGoodsGuid())) {
                    buyerItem.setCount(buyerItem.getCount() + item.getCount());
                    return;
                }
            }
        }
        //单品无同款或套餐，直接添加
        items.add(item);
    }

    /**
     * 修改购物车数量
     */
    public void updateItem(GoodsShopcar item) {
        for (GoodsShopcar buyerItem : items) {
            //单品修改数量
            if (buyerItem.getGoodsType() != GoodsTypeEnum.combo.getValue() && buyerItem.getGoodsGuid().equals(item.getGoodsGuid())) {
                buyerItem.setCount( item.getCount() );//buyerItem.getCount()+
                return;
            }
        }
    }

    /**
     * 计算商品数量
     * @return
     */
    @JsonIgnore
    public Integer getProductAmount() {
        Integer result = 0;
        for (GoodsShopcar buyerItem : items) {
            result += buyerItem.getCount();
        }
        return result;
    }

    /**
     * 删除购物车单项
     */
    public void delItem(int index) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == index) {
                items.remove(i);
            }
        }
    }

}
