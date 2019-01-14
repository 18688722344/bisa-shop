package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.model.Guestbook;

/**
 * 留言表
 * @author Administrator
 */
public interface IAdminGuestBookService {

    /**
     * 查询  (留言)  guestBook 分页集合
     * @param page
     * @param limit
     * @param val
     * @param searchabout
     * @param incontent
     * @return
     */
    OrderListPageDto<Guestbook> getLeaveMsgList(Integer page, Integer limit, String incontent, String searchabout, String val);

    /**
     * 根据留言表的id 去查询留言对象
     * @param id
     * @return
     */
    Guestbook getLeaveMsg(Integer id);

    /**
     * 处理留言
     * @param guestbook
     */
    void updateGuestbook(Guestbook guestbook);

}
