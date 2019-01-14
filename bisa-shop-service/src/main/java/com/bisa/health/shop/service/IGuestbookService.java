package com.bisa.health.shop.service;

import com.bisa.health.shop.model.Guestbook;

/**
 * 留言表
 */

public interface IGuestbookService {

    /**
     * 新增一条留言信箱
     * @param guestbook
     * @return
     */
    public Guestbook addGuestbook(Guestbook guestbook);

}
