package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.admin.model.GoodsProperty;

@Repository
public class AdminGoodsPropertyDaoImpl extends BaseDao<GoodsProperty> implements IAdminGoodsPropertyDao {

	@Override
	public int addGoodsProperty(GoodsProperty goodsProperty) {
		String sql = "INSERT INTO s_goods_property(goods_number, content) VALUES(?,?)";
		return super.addBySql(sql, new Object[] { goodsProperty.getGoods_number(), goodsProperty.getContent() });
	}

	@Override
	public GoodsProperty selectGoodsPropertyListByGoodsNumber(String goodsNumber) {
		String sql = "SELECT * FROM s_goods_property WHERE goods_number = ?";
		return super.queryObjectBySql(sql, new Object[] { goodsNumber }, GoodsProperty.class);
	}

	@Override
	public int updateGoodsProperty(GoodsProperty goodsProperty) {
		String sql = "UPDATE s_goods_property SET content = ? WHERE goods_number = ?";
		return super.updateBySql(sql, new Object[] { goodsProperty.getContent(), goodsProperty.getGoods_number() });
	}

}
