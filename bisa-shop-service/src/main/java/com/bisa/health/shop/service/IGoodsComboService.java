package com.bisa.health.shop.service;

import com.bisa.health.shop.model.GoodsCombo;

import java.util.List;

/**
 * 商品套餐中间表
 */

public interface IGoodsComboService {

    /**
     * 查询某个商品下所有套餐
     * @param goodsNumber
     * @return
     */
    List<GoodsCombo> getGoodsComboByGoodsNumber(String goodsNumber);

    /**
     * 根据comboGuid，获取套餐的中间表
     * @param comboGuid
     * @return
     */
    GoodsCombo getGoodsComboByGuid(String comboGuid);

}
