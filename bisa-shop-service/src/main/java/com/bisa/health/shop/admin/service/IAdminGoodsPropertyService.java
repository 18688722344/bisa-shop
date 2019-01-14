package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.model.GoodsProperty;

/**
 * 商品的标签表
 * @author Administrator
 */

public interface IAdminGoodsPropertyService {

    /**
     * 给商品关联标签
     * @param goodsProperty
     */
    void addGoodsProperty(GoodsProperty goodsProperty);

    /**
     * 更新商品标签表
     * @param goodsProperty
     */
    void updateGoodsProperty(GoodsProperty goodsProperty);

}
