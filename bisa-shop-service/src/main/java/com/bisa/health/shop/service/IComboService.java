package com.bisa.health.shop.service;

import com.bisa.health.shop.model.Combo;

import java.util.List;

/**
 * 套餐表
 */
public interface IComboService {

    /**
     * 根据combo_guid，获得套餐集合
     * @param comboGuid 套餐表的combo_guid
     * @return
     */
    List<Combo> selectComboByGuid(String comboGuid);

}
