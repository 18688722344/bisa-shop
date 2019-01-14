package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.admin.model.PropertyValue;

@Repository
public class AdminPropertyValueDaoImpl extends BaseDao<PropertyValue> implements IAdminPropertyValueDao {

	@Override
	public List<PropertyValue> getPropertyValueByPropertyId(Integer propertyId) {
		String sql = "SELECT * FROM s_property_value WHERE property_id = ?";
		return super.listBySql(sql, new Object[] { propertyId }, PropertyValue.class);
	}

	@Override
	public PropertyValue getPropertyValueByValueId(int valueId) {
		String sql = "SELECT * FROM s_property_value WHERE value_id = ?";
		return super.queryObjectBySql(sql, new Object[] { valueId }, PropertyValue.class);
	}

	@Override
	public Integer getValueIdMax() {
		String sql = "SELECT max(value_id) FROM s_property_value";
		return (int) super.queryIntegerTotal(sql, null);
	}
	/**
	 * 新增商品标签
	 */
	@Override
	public int insertPropertyValue(int property_id,String value_id,String name) {
		String sql = "INSERT INTO s_property_value( property_id, value_id, name) VALUES(?,?,?)";
		return super.addBySql(sql, new Object[] {property_id,value_id ,name});
	}

	@Override
	public List<PropertyValue> selectPropertyValues(Integer propertyId) {
		String sql = "SELECT * FROM s_property_value WHERE property_id = ?";
		return super.listBySql(sql, new Object[] {propertyId}, PropertyValue.class);
	}

	@Override
	public int deletePropertyValues(int id) {
		String sql="delete from s_property_value where id= ?";
		return super.deleteBySql(sql, new Object[] {id});
	}

	@Override
	public int updataPropertyValue(String name, int id) {
		String sql="UPDATE s_property_value SET name = ? WHERE id = ?";
		return super.updateBySql(sql,new Object[] {name, id});
	}
	
}
