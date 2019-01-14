package com.bisa.health.shop.admin.service;

import java.util.List;

import com.bisa.health.shop.admin.model.PropertyValue;

/**
 * 商品的标签表
 * @author Administrator
 */

public interface IAdminPropertyValueService {

    /**
     * 获取标签表的value_id 的最大值
     * @return
     */
    Integer getValueIdMax();

    /**
     * 新增 商品标签
     */
    boolean insertPropertyValue(int property_id, String value_id, String name);

    /**
     * 查询属性表
     * @param propertyId
     * @return
     */
    List<PropertyValue> selectPropertyValues(Integer propertyId);

    /**
     * 根据ID删除对应的标签
     * @param id
     * @return
     */
    boolean deletePropertyValues(int id);

    /**
     * 更改/编辑属性值
     * @param name
     * @param id
     * @return
     */
    boolean updataPropertyValue(String name, int id);
}
