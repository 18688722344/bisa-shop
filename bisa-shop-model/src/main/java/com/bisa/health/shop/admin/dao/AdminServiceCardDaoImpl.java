package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCard;

@Repository
public class AdminServiceCardDaoImpl extends BaseDao<ServiceCard> implements IAdminServiceCardDao {
	
	@Override
	  public int addServiceCard(ServiceCard serviceCard) {
		ServiceCard object = super.add(serviceCard);
		return object!=null?1:0;
	}

	@Override
	public Pager<ServiceCard> loadServiceCardList() {
		String sql="SELECT * FROM s_service_card";
		return super.findBySql(sql, ServiceCard.class, true);
	}

	@Override
	public Pager<ServiceCard> selecServiceCardByCardNumber(String incontent) {
		String sql = "SELECT * FROM s_service_card where card_number = ?";
		return super.findBySql(sql, new Object[] { incontent }, ServiceCard.class, true);
	}

	@Override
	public Pager<ServiceCard> getGoodsByGoodsStatus(int value) {
		String sql = "SELECT * FROM s_service_card where card_status = ?";
		return super.findBySql(sql, new Object[] { value }, ServiceCard.class, true);
	}

}
