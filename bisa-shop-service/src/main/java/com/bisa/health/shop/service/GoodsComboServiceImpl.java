package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IGoodsComboDao;
import com.bisa.health.shop.model.GoodsCombo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "GoodsComboServiceImpl#${redis.exp1}")
public class GoodsComboServiceImpl implements IGoodsComboService {

    @Autowired
    private IGoodsComboDao goodsComboDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public List<GoodsCombo> getGoodsComboByGoodsNumber(String goodsNumber) {
        return goodsComboDao.getGoodsComboByGoodsNumber(goodsNumber);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#comboGuid")
    public GoodsCombo getGoodsComboByGuid(String comboGuid) {
        return goodsComboDao.getGoodsComboByGuid(comboGuid);
    }

}
