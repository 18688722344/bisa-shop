package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.admin.model.Property;
import com.bisa.health.shop.admin.model.PropertyValue;

/**
 * 属性表
 * @author Administrator
 *
 */
public interface IAdminPropertyDao {

	int insertProperty(String property, Integer classifyId);//新增 一条属性

	List<Property> getListPropertyByClassifyId(Integer classifyId);	//获取某个类别的所有属性

	int deleteProperty(int id);

	Property selectPropertyById(int id);

	int updateProperty(Property property);
	
	List<PropertyValue> selectPropertyValues(Integer property_id);//查询属性表
	
	
}
