package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IServiceCardDao;
import com.bisa.health.shop.model.ServiceCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "ServiceCardServiceImpl#${redis.exp1}")
public class ServiceCardServiceImpl implements IServiceCardService {

    @Autowired
    private IServiceCardDao serviceCardDao;

    @Override
    @CacheEvict(value = "ServiceCardServiceImpl", allEntries = true)
    public void updateServiceCard(ServiceCard serviceCard) {
        serviceCardDao.updateServiceCard(serviceCard);
    }

    @Override
    @CacheEvict(value = "ServiceCardServiceImpl", allEntries = true)
    public void addServiceCard(ServiceCard serviceCard) {
        serviceCardDao.addServiceCard(serviceCard);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber+#status")
    public ServiceCard getServiceCardServiceByGoodsNumber(String goodsNumber, int status) {
        return serviceCardDao.getServiceCardServiceByGoodsNumber(goodsNumber, status);
    }

}
