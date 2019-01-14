package com.bisa.health.shop.admin.dao;


import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCard;

public interface IAdminServiceCardDao {
	/**
	 * 添加服务卡
	 * @param serviceCard
	 * @return
	 */
	int addServiceCard(ServiceCard serviceCard);
	/**
	 * 获取所有的服务卡
	 * @return
	 */
	Pager<ServiceCard> loadServiceCardList();
	/**
	 * 根据激活卡ID查询
	 */
	 Pager<ServiceCard> selecServiceCardByCardNumber(String incontent);
	 /**
	  * 已激活、未激活
	  * @param value
	  * @return
	  */
	 Pager<ServiceCard> getGoodsByGoodsStatus(int value);// 1 销售中 2 下架中
}
