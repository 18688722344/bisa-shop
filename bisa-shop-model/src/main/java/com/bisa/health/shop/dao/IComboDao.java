package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.Combo;

import java.util.List;

/**
 * 套餐表
 */
public interface IComboDao {

    /**
     * 根据comboGuid，获取套餐集合
     * @param comboGuid
     * @return
     */
    List<Combo> selectComboByGuid(String comboGuid);

}
