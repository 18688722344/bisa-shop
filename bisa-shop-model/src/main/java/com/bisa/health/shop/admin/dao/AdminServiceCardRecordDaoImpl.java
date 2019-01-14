package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCardRecord;

@Repository
public class AdminServiceCardRecordDaoImpl extends BaseDao<ServiceCardRecord> implements IAdminServiceCardRecordDao {

	@Override
	public Pager<ServiceCardRecord> selectAllCard() {
		String sql = "SELECT * FROM s_service_card_record";
		return super.findBySql(sql, ServiceCardRecord.class, true);
	}

	@Override
	public Pager<ServiceCardRecord> selectCardByCardNumber(String incontent) {
		String sql = "SELECT * FROM  s_service_card_record WHERE card_number= ?";
		return super.findBySql(sql, new Object[] { incontent }, ServiceCardRecord.class, true);
	}

}
