package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.admin.model.PropertyValue;

/**
 * 属性项目(值)  表
 * @author Administrator
 */

public interface IAdminPropertyValueDao {

    /**
     * 根据属性表的id去查询 对应的属性 项(值)表
     * @param id
     */
    List<PropertyValue> getPropertyValueByPropertyId(Integer id);

    /**
     * 根据商品的标签去查询对象
     * @param i
     * @return
     */
    PropertyValue getPropertyValueByValueId(int i);

    /**
     * 获取标签表的value_id 的最大值
     * @return
     */
    Integer getValueIdMax();

    /**
     * 新增 商品标签
     * @return
     */
    int insertPropertyValue(int property_id, String value_id, String name);

    /**
     * @param propertyId
     * @return
     */
    List<PropertyValue> selectPropertyValues(Integer propertyId);

    /**
     * 根据ID删除对应的标签
     * @param id
     * @return
     */
    int deletePropertyValues(int id);

    /**
     * 更改/编辑属性值
     */
    int updataPropertyValue(String name, int id);
}
