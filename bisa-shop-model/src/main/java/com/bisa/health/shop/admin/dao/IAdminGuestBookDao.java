package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.Guestbook;

/**
 * 留言 表
 *
 * @author Administrator
 */

public interface IAdminGuestBookDao {

    /**
     * 查询全部的 留言
     *
     * @return
     */
    Pager<Guestbook> getAllLeaveMsg();

    /**
     * 1 根据 姓名 查询留言
     *
     * @param incontent
     * @return
     */
    Pager<Guestbook> selectLeaveMsgListByName(String incontent);

    /**
     * 2 根据 电话 查询留言
     *
     * @param incontent
     * @return
     */
    Pager<Guestbook> selectLeaveMsgListByPhone(String incontent);

    /**
     * 3 根据 邮箱 查询留言
     *
     * @param incontent
     * @return
     */
    Pager<Guestbook> selectLeaveMsgListByEmail(String incontent);

    /**
     * 1 待处理 2 已处理
     *
     * @param value
     * @return
     */
    Pager<Guestbook> selectLeaveMsgListByHandStatus(int value);

    /**
     * 根据留言表的id 去查询留言对象
     *
     * @param id
     * @return
     */
    Guestbook getLeaveMsgById(Integer id);

    /**
     * 处理留言
     *
     * @param guestbook
     * @return
     */
    int updateGuestbook(Guestbook guestbook);

}
