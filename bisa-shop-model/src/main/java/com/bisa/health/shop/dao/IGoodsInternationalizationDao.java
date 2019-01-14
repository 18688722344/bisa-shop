package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.GoodsInternationalization;

/**
 * 商品 国际化语言表
 */

public interface IGoodsInternationalizationDao {

    /**
     * 根据商品表的id 获取商品国际化商品对象
     * @param id    商品表id
     * @param value
     * @return
     */
    GoodsInternationalization selectGoodsInternationalizationById(Integer id, int value);

    /**
     * 根据商品编号 获取商品国际化商品对象
     * @param goodsNumber
     * @param value
     * @return
     */
    GoodsInternationalization selectGoodsInternationalizationByGoodsNumber(String goodsNumber, int value);
}
