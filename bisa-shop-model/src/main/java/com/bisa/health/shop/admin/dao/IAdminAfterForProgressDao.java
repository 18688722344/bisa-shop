package com.bisa.health.shop.admin.dao;

import com.bisa.health.shop.admin.model.SAfterForProgress;

/**
 * 售后进度表
 * @author Administrator
 *
 */
public interface IAdminAfterForProgressDao {
	
	/**
	 * insert售后进度表对象
	 * @param progress
	 */
	boolean insert(SAfterForProgress progress);

}
