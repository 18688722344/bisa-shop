package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminGoodsComboDao;
import com.bisa.health.shop.model.GoodsCombo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "GoodsComboServiceImpl#${redis.exp1}")
public class AdminGoodsComboServiceImpl implements IAdminGoodsComboService {

    @Autowired
    private IAdminGoodsComboDao goodsComboDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public List<GoodsCombo> getGoodsComboGuidByGoodsNumber(String goodsNumber) {
        List<GoodsCombo> list = goodsComboDao.getGoodsComboGuidByGoodsNumber(goodsNumber);
        return list;
    }

    @Override
    @CacheEvict(value = "GoodsComboServiceImpl", allEntries = true)
    public void insertGoodsCombo(GoodsCombo goodsCombo) {
        goodsComboDao.insertGoodsCombo(goodsCombo);
    }

    @Override
    @CacheEvict(value = "GoodsComboServiceImpl", allEntries = true)
    public boolean delGoodsCombo(String comboGuid) {
        int result = goodsComboDao.delGoodsCombo(comboGuid);
        return result >= 1 ? true : false;
    }
}
