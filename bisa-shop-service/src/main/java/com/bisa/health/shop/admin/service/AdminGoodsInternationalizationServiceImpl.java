package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminGoodsDao;
import com.bisa.health.shop.admin.dao.IAdminGoodsInternationalizationDao;
import com.bisa.health.shop.model.GoodsInternationalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "GoodsInternationalizationServiceImpl#${redis.exp1}")
public class AdminGoodsInternationalizationServiceImpl implements IAdminGoodsInternationalizationService {

    @Autowired
    private IAdminGoodsInternationalizationDao goodsInternationalizationDao;
    @Autowired
    private IAdminGoodsDao goodsDao;

    @Override
    @Cacheable(value = "GoodsInternationalizationServiceImpl#${redis.exp1}",key = "targetClass.name+methodName+#goodsNumber+#internationalization")
    public GoodsInternationalization selectInsternationalzationByInternationalization(String goodsNumber, int internationalization) {
        return goodsInternationalizationDao.selectInsternationalzationByInternationalization(goodsNumber, internationalization);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl#${redis.exp1}", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl#${redis.exp1}", allEntries = true),
    })
    public void insertGoodsInsternationalzation(GoodsInternationalization goodsInter) {
        goodsInternationalizationDao.insertGoodsInsternationalzation(goodsInter);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl#${redis.exp1}", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl#${redis.exp1}", allEntries = true),
    })
    public void updateInternational(GoodsInternationalization goodsInternationalization) {
        goodsInternationalizationDao.updateInternational(goodsInternationalization);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl#${redis.exp1}", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl#${redis.exp1}", allEntries = true),
    })
    public void updateImgUrl(String url, String goodsNumber, Integer internationalization) {
        goodsInternationalizationDao.updateImgUrl(url, goodsNumber, internationalization);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl#${redis.exp1}", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl#${redis.exp1}", allEntries = true),
    })
    public boolean deleteGoodsInternationByGoosNumber(String goods_number) {
        int result = goodsInternationalizationDao.deleteGoodsInternationByGoosNumber(goods_number);
        return result >= 1 ? true : false;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl", allEntries = true),
    })
    public boolean deleteGoodsByGoosNumber(String goods_number) {
        int result = goodsDao.deleteGoodsByGoosNumber(goods_number);
        return result >= 1 ? true : false;
    }
}
