package com.bisa.health.shop.admin.dao;

import com.bisa.health.shop.admin.model.GoodsProperty;

/**
 * 商品的标签表
 * @author Administrator
 */

public interface IAdminGoodsPropertyDao {

    /**
     * 商品添加标签
     * @param goodsProperty
     * @return
     */
    int addGoodsProperty(GoodsProperty goodsProperty);

    /**
     * 根据goodsNumber 去查询商品下面的所有的标签
     * @param goodsNumber
     */
    GoodsProperty selectGoodsPropertyListByGoodsNumber(String goodsNumber);

    /**
     * 更新商品标签表
     * @param goodsProperty
     */
    int updateGoodsProperty(GoodsProperty goodsProperty);

}
