package com.bisa.health.shop.admin.service;

import java.util.List;

import com.bisa.health.shop.admin.model.Property;

/**
 * 商品属性表
 * @author Administrator
 */

public interface IAdminPropertyService {

    /**
     * 新增 一条属性
     * @param property
     * @param valueOf
     */
    void insertProperty(String property, Integer valueOf);

    /**
     * 获取某个类别的所有属性
     * @param classifyId
     * @return
     */
    List<Property> selectPropertyByClassifyId(Integer classifyId);

    /**
     * 删除属性
     * @param id
     * @return
     */
    boolean deleteProperty(int id);

    /**
     * 获取属性对象
     * @param id
     * @return
     */
    Property selectPropertyById(int id);

    /**
     * 更新属性
     * @param property
     * @return
     */
    boolean updateProperty(Property property);

}
