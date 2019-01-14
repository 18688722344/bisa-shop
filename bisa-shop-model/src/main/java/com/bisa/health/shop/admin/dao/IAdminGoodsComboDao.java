package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.model.GoodsCombo;

/**
 * 商品套餐中间表
 * @author Administrator
 */
public interface IAdminGoodsComboDao {

    /**
     * 根据商品的编号去 查询商品套餐的中间表
     * @param goodsNumber
     */
    List<GoodsCombo> getGoodsComboGuidByGoodsNumber(String goodsNumber);

    /**
     * 新增goodsCombo 对象
     * @param goodsCombo
     * @return
     */
    int insertGoodsCombo(GoodsCombo goodsCombo);

    /**
     * 删除套餐商品关系表
     */
    int delGoodsCombo(String comboGuid);


}
