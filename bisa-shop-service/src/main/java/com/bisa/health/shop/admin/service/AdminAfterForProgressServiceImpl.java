package com.bisa.health.shop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.bisa.health.shop.admin.dao.IAdminAfterForProgressDao;
import com.bisa.health.shop.admin.model.SAfterForProgress;

@Service
public class AdminAfterForProgressServiceImpl implements IAdminAfterForProgressService {

    @Autowired
    private IAdminAfterForProgressDao iAfterForProgressDao;

    @Override
    public void insert(SAfterForProgress progress) {
        iAfterForProgressDao.insert(progress);
    }

}
