package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Guestbook;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookDaoImpl extends BaseDao<Guestbook> implements IGuestbookDao {

    @Override
    public Guestbook addGuestbook(Guestbook guestbook) {
        String sql = "INSERT INTO s_guestbook(HANDLE_WAY,HANDLE_STATUS,HANDLE_TIME,MAIL,MESSAGE,MESSAGE_TIME,MESSAGE_TYPE,NAME,REPLY_CONTENT,PHONE,TITLE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        int result = super.addBySql(sql, new Object[]{guestbook.getHandle_way(), guestbook.getHandle_status(),
                guestbook.getHandle_time(), guestbook.getMail(), guestbook.getMessage(), guestbook.getMessage_time(),
                guestbook.getMessage_type(), guestbook.getName(), guestbook.getReply_content(), guestbook.getPhone(),
                guestbook.getTitle()});
        return result >= 1 ? guestbook : null;
    }

}
