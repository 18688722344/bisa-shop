package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.admin.model.GuestBookRemark;

/**
 * 留言 备注表
 * @author Administrator
 *
 */
public interface IAdminGuestBookRemarkDao {

	/**
	 * 根据guestbookId 查询 对应的 (留言备注) GuestBookRemark 分页集合
	 * @param guestbookId
	 * @return
	 */
	Pager<GuestBookRemark> getGuestBookRemarkList(Integer guestbookId);

	/**
	 * 留言备注
	 * @param guestBookRemark
	 */
	int leaveMsgRemark(GuestBookRemark guestBookRemark);

}
