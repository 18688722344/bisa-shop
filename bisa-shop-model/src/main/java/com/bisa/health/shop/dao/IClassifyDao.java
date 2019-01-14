package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.Classify;

import java.util.List;

/**
 * 分类表
 */
public interface IClassifyDao {

    /**
     * 获得指定类别
     * @param id 表id
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
