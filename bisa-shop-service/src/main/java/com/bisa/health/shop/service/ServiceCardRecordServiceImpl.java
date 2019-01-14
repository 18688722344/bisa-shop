package com.bisa.health.shop.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.dao.IServiceCardRecordDao;
import com.bisa.health.shop.model.ServiceCardRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "ServiceCardRecordServiceImpl#${redis.exp1}")
public class ServiceCardRecordServiceImpl implements IServiceCardRecordService {

    @Autowired
    private IServiceCardRecordDao serviceCardRecordDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString()+#limit.toString()+#user_guid+#status.toString()")
    public Pager<ServiceCardRecord> getPagerServiceCardRecordByUserGuid(Integer page, Integer limit, int user_guid, Integer status) {
        SystemContext.setPageOffset(page);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按购买时间倒序
        SystemContext.setOrder("desc");

        Pager<ServiceCardRecord> serviceCardRecordPager = null;
        if (status == -1) {
            serviceCardRecordPager = serviceCardRecordDao.getPagerServiceCardRecordByUserGuid(user_guid);
        } else {
            serviceCardRecordPager = serviceCardRecordDao.getPagerServiceCardRecordByUserGuid(user_guid, status);
        }
        return serviceCardRecordPager;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#user_guid")
    public List<ServiceCardRecord> getListServiceCardRecordByUserGuid(int user_guid) {
        return serviceCardRecordDao.getListServiceCardRecordByUserGuid(user_guid);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#user_guid+#status")
    public List<ServiceCardRecord> getListServiceCardRecordByGuidAndStatus(int user_guid, int status) {
        if (status == -1) {
            //全部激活卡
            return serviceCardRecordDao.getListServiceCardRecordByUserGuid(user_guid);
        } else {
            //未使用、已使用
            return serviceCardRecordDao.getListServiceCardRecordByGuidAndStatus(user_guid, status);
        }
    }

    @Override
    @CacheEvict(value = "ServiceCardRecordServiceImpl", allEntries = true)
    public boolean addServiceCardRecord(ServiceCardRecord serviceCardRecord) {
        int result = serviceCardRecordDao.addServiceCardRecord(serviceCardRecord);
        return result >= 1 ? true : false;
    }

    @Override
    @CacheEvict(value = "ServiceCardRecordServiceImpl", allEntries = true)
    public boolean updateServiceCardRecord(ServiceCardRecord serviceCardRecord) {
        int result = serviceCardRecordDao.updateServiceCardRecord(serviceCardRecord);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#cardNumber")
    public ServiceCardRecord selectServiceCardRecordByCardNumber(String cardNumber) {
        return serviceCardRecordDao.selectServiceCardRecordByCardNumber(cardNumber);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#userGuid+#orderGoodsId.toString()")
    public List<ServiceCardRecord> getLisRecordByUserGuidAndOrderId(int userGuid, Integer orderGoodsId) {
        return serviceCardRecordDao.getLisRecordByUserGuidAndOrderId(userGuid, orderGoodsId);
    }

}
