package com.bisa.health.shop.dao;

import java.util.List;

import com.bisa.health.shop.model.GoodsCombo;

/**
 * 套餐中间表
 */
public interface IGoodsComboDao {
    /**
     * 查询某个商品下所有套餐
     * @param goodsNumber
     * @return
     */
    List<GoodsCombo> getGoodsComboByGoodsNumber(String goodsNumber);

    /**
     * 获取套餐的中间表
     * @param comboGuid
     * @return
     */
    GoodsCombo getGoodsComboByGuid(String comboGuid);
}
