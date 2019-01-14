package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IGuestbookDao;
import com.bisa.health.shop.model.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "GuestbookServiceImpl#${redis.exp1}")
public class GuestbookServiceImpl implements IGuestbookService {

    @Autowired
    private IGuestbookDao iGuestbookDao;

    @Override
    @CacheEvict(value = "GuestbookServiceImpl", allEntries = true)
    public Guestbook addGuestbook(Guestbook guestbook) {
        return iGuestbookDao.addGuestbook(guestbook);
    }

}
