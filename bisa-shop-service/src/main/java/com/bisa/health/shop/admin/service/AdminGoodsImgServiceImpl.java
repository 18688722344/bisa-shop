package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminGoodsImgDao;
import com.bisa.health.shop.model.GoodsImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "GoodsImgServiceImpl#${redis.exp1}")
public class AdminGoodsImgServiceImpl implements IAdminGoodsImgService {

    @Autowired
    private IAdminGoodsImgDao goodsImgDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public List<GoodsImg> selectMainImgByGoodsNumber(String goodsNumber) {
        return goodsImgDao.selectMainImgByGoodsNumber(goodsNumber);
    }
    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public List<GoodsImg> selectImgByInternationalization(String goodsNumber,int internationalization) {
        return goodsImgDao.selectImgByInternationalization(goodsNumber,internationalization);
    }
    @Override
    @CacheEvict(value = "GoodsImgServiceImpl", allEntries = true)
    public boolean addGoodsImg(GoodsImg goodsImg) {
        int result = goodsImgDao.addGoodsImg(goodsImg);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber +#position.toString() +#internationalization.toString()")
    public GoodsImg getImgByPosition(String goodsNumber, Integer position, Integer internationalization) {
        return goodsImgDao.getImgByPosition(goodsNumber, position, internationalization);
    }

    @Override
    @CacheEvict(value = "GoodsImgServiceImpl", allEntries = true)
    public void updateGoodsImg(String url, String goodsNumber, Integer position, Integer internationalization) {
        goodsImgDao.updateGoodsImg(url, goodsNumber, position, internationalization);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber+#lang.toString()")
    public List<GoodsImg> getIndexImgsByLang(String goodsNumber, Integer lang) {
        return goodsImgDao.getIndexImgsByLang(goodsNumber, lang);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber+#internationalization.toString()")
    public List<GoodsImg> selectGoodsImgs(String goodsNumber, Integer internationalization) {
        return goodsImgDao.selectGoodsImgs(goodsNumber, internationalization);
    }
    @Override
    @CacheEvict(value = "GoodsImgServiceImpl", allEntries = true)
    public boolean delImg(String goods_number){
        int result=goodsImgDao.delImg(goods_number);
       return result >= 1 ? true : false;
    }
}
