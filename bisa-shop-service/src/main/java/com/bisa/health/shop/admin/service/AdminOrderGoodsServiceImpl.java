package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminOrderGoodsDao;
import com.bisa.health.shop.model.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "OrderGoodsServiceImpl#${redis.exp1}")
public class AdminOrderGoodsServiceImpl implements IAdminOrderGoodsService {

    @Autowired
    private IAdminOrderGoodsDao orderGoodsDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#orderId.toString()")
    public List<OrderGoods> getOrderGoodsByOrderId(Integer orderId) {
        return orderGoodsDao.selectOrderGoodsByOrderId(orderId);
    }
}
