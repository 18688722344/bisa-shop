package com.bisa.health.shop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bisa.health.shop.admin.dao.IAdminAfterForRecordDao;
import com.bisa.health.shop.admin.model.SAfterForRecord;

@Service
@CacheConfig(cacheNames = "AfterForRecordServiceImpl#${redis.exp1}")
public class AdminAfterForRecordServiceImpl implements IAdminAfterForRecordService {

    @Autowired
    private IAdminAfterForRecordDao iAfterForRecordDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#orderNo")
    public SAfterForRecord selectApplyForListByOrderNo(String orderNo) {
        return iAfterForRecordDao.selectApplyForListByOrderNo(orderNo);
    }

}
