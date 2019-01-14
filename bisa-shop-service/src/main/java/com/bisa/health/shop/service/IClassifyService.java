package com.bisa.health.shop.service;

import com.bisa.health.shop.model.Classify;

import java.util.List;

/**
 * 分类表
 */

public interface IClassifyService {

    /**
     * 根据分类表的id，获取分类对象
     * @param id
     * @return
     */
    Classify selectClassifyById(Integer id);
    /**
     * 获取分类表的 最高级的信息
     * @return
     */
    List<Classify> getClassifyListByHighest();
    /**
     * 根据superId去查询 分类的集合
     * @param id
     * @return
     */
    List<Classify> getClassifyListBySuperId(Integer id);
}
