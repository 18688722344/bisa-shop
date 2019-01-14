package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.admin.model.Property;
import com.bisa.health.shop.admin.model.PropertyValue;


@Repository
public class AdminPropertyDaoImpl extends BaseDao<Property> implements IAdminPropertyDao {

	@Override
	public int insertProperty(String property, Integer classifyId) {
		String sql = "INSERT INTO s_property( name, classify_id ) VALUES(?,?)";
		return super.addBySql(sql, new Object[] { property, classifyId });
	}

	@Override
	public List<Property> getListPropertyByClassifyId(Integer classifyId) {
		String sql = "SELECT * FROM s_property WHERE classify_id = ?";
		return super.listBySql(sql, new Object[] {classifyId}, Property.class);
	}

	@Override
	public int deleteProperty(int id) {
		String sql = "DELETE FROM s_property WHERE ID = ?";
		return super.deleteBySql(sql, new Object[] {id});
	}

	@Override
	public Property selectPropertyById(int id) {
		String sql = "SELECT * FROM s_property WHERE ID = ?";
		return super.queryObjectBySql(sql, new Object[] {id}, Property.class);
	}

	@Override
	public int updateProperty(Property property) {
		String sql = "UPDATE s_property SET NAME = ? WHERE ID = ?";
		return super.updateBySql(sql, new Object[] {property.getName(), property.getId()});
	}

	@Override
	public List<PropertyValue> selectPropertyValues(Integer property_id) {
		String sql = "SELECT * FROM s_property_value WHERE property_id = ?";
		return super.listBySql(sql, new Object[] {property_id}, PropertyValue.class);
	}

}
