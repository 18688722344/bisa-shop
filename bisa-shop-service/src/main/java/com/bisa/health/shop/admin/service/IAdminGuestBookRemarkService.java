package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.model.GuestBookRemark;

/**
 * 留言 备注表
 * @author Administrator
 */

public interface IAdminGuestBookRemarkService {

    /**
     * 根据guestbookId查询,对应的 (留言备注)  GuestBookRemark 分页集合
     * @param page
     * @param limit
     * @param guestbookId 留言表的id
     * @return
     */
    OrderListPageDto<GuestBookRemark> getGuestBookRemarkList(Integer page, Integer limit, Integer guestbookId);

    /**
     * 留言备注
     * @param guestBookRemark
     */
    void leaveMsgRemark(GuestBookRemark guestBookRemark);

}
