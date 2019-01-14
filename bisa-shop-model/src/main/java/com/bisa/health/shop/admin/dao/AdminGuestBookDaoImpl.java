package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.Guestbook;

@Repository
public class AdminGuestBookDaoImpl extends BaseDao<Guestbook> implements IAdminGuestBookDao {

    @Override
    public Pager<Guestbook> getAllLeaveMsg() {
        String sql = "SELECT * FROM s_guestbook";
        return super.findBySql(sql, null, Guestbook.class, true);
    }

    @Override
    public Pager<Guestbook> selectLeaveMsgListByName(String incontent) {
        String sql = "SELECT * FROM s_guestbook where name = ?";
        return super.findBySql(sql, new Object[]{incontent}, Guestbook.class, true);
    }

    @Override
    public Pager<Guestbook> selectLeaveMsgListByPhone(String incontent) {
        String sql = "SELECT * FROM s_guestbook where phone = ?";
        return super.findBySql(sql, new Object[]{incontent}, Guestbook.class, true);
    }

    @Override
    public Pager<Guestbook> selectLeaveMsgListByEmail(String incontent) {
        String sql = "SELECT * FROM s_guestbook where mail = ?";
        return super.findBySql(sql, new Object[]{incontent}, Guestbook.class, true);
    }

    @Override
    public Pager<Guestbook> selectLeaveMsgListByHandStatus(int value) {
        String sql = "SELECT * FROM s_guestbook where handle_status = ?";
        return super.findBySql(sql, new Object[]{value}, Guestbook.class, true);
    }

    @Override
    public Guestbook getLeaveMsgById(Integer id) {
        String sql = "SELECT * FROM s_guestbook WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{id}, Guestbook.class);
    }

    @Override
    public int updateGuestbook(Guestbook guestbook) {
        String sql = "UPDATE s_guestbook SET message_type = ?,handle_way = ?,handle_time = ?,handle_status = ?,reply_content = ?,reply_name = ? WHERE id = ?";
        return super.updateBySql(sql, new Object[]{guestbook.getMessage_type(), guestbook.getHandle_way(), guestbook.getHandle_time(),
                guestbook.getHandle_status(), guestbook.getReply_content(), guestbook.getReply_name(), guestbook.getId()});
    }

}
