package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.OrderGoods;

@Repository
public class AdminOrderGoodsDaoImpl extends BaseDao<OrderGoods> implements IAdminOrderGoodsDao {

	@Override
	public List<OrderGoods> selectOrderGoodsByOrderId(Integer orderId) {
		String sql = "SELECT * FROM s_order_goods WHERE order_id = ?";
		return super.listBySql(sql, new Object[] { orderId }, OrderGoods.class);
	}

}
