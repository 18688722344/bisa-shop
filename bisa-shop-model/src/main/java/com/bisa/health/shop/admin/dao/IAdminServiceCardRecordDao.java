package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCardRecord;

public interface IAdminServiceCardRecordDao {
	/**
	 * 查询所有的激活卡
	 * 
	 * @return
	 */
	Pager<ServiceCardRecord> selectAllCard();

	/**
	 * 根据激活卡号查询激活卡
	 * 
	 * @param card_number
	 * @return
	 */
	Pager<ServiceCardRecord> selectCardByCardNumber(String incontent);
}
