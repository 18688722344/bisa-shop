package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.admin.model.GuestBookRemark;

@Repository
public class AdminGuestBookRemarkDaoImpl extends BaseDao<GuestBookRemark> implements IAdminGuestBookRemarkDao {

    @Override
    public Pager<GuestBookRemark> getGuestBookRemarkList(Integer guestbookId) {
        String sql = "SELECT * FROM s_guestbook_remark where guestbook_id = ?";
        return super.findBySql(sql, new Object[]{guestbookId}, GuestBookRemark.class, true);
    }

    @Override
    public int leaveMsgRemark(GuestBookRemark guestBookRemark) {
        String sql = "INSERT INTO s_guestbook_remark( guestbook_id, remark_name, remark_content, create_time ) VALUES(?,?,?,?)";
        return super.addBySql(sql, new Object[]{guestBookRemark.getGuestbook_id(), guestBookRemark.getRemark_name(),
                guestBookRemark.getRemark_content(), guestBookRemark.getCreate_time()});
    }

}
