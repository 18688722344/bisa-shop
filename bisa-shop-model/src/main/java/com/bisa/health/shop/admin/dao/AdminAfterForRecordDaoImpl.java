package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.admin.model.SAfterForRecord;

@Repository
public class AdminAfterForRecordDaoImpl extends BaseDao<SAfterForRecord> implements IAdminAfterForRecordDao {

	@Override
	public SAfterForRecord selectApplyForListByOrderNo(String orderNo) {
		String sql = "SELECT * FROM s_after_for_record WHERE order_no = ? order by create_time desc limit 0,1";
		return super.queryObjectBySql(sql, new Object[] { orderNo }, SAfterForRecord.class);
	}

}
