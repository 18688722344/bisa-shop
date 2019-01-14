package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IGoodsImgDao;
import com.bisa.health.shop.enumerate.IndexImgEnum;
import com.bisa.health.shop.enumerate.InternationalizationEnum;
import com.bisa.health.shop.model.GoodsImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "GoodsImgServiceImpl#${redis.exp1}")
public class GoodsImgServiceImpl implements IGoodsImgService {

    @Autowired
    private IGoodsImgDao goodsImgDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber+#lang")
    public List<GoodsImg> selectMainImgByGoodsNumber(String goodsNumber, String lang) {

        List<GoodsImg> goodsImgs = null;
        if (InternationalizationEnum.zh_CN.getName().equals(lang)) {
            //简体图片
            goodsImgs = goodsImgDao.selectMainImgByGoodsNumber(goodsNumber, InternationalizationEnum.zh_CN.getValue());
        } else if (InternationalizationEnum.en_US.getName().equals(lang)) {
            //英文图片
            goodsImgs = goodsImgDao.selectMainImgByGoodsNumber(goodsNumber, InternationalizationEnum.en_US.getValue());
        } else {
            //繁体图片
            goodsImgs = goodsImgDao.selectMainImgByGoodsNumber(goodsNumber, InternationalizationEnum.zh_HK.getValue());
        }

        //要判断一下图片 有没有图片 因为图片可能国际化都是同一个 这样我们就 简体的图片
        if (goodsImgs.size() == 0) {
            goodsImgs = goodsImgDao.selectMainImgByGoodsNumber(goodsNumber, InternationalizationEnum.zh_CN.getValue());
        }
        return goodsImgs;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber+#lang")
    public String loadMainPicByGoodNumber(String goodsNumber, String lang) {

        GoodsImg goodsImg = null;
        if (InternationalizationEnum.zh_CN.getName().equals(lang)) {
            //简体图片
            goodsImg = goodsImgDao.loadMainPicByGoodNumber(goodsNumber, InternationalizationEnum.zh_CN.getValue());
        } else if (InternationalizationEnum.en_US.getName().equals(lang)) {
            //英文图片
            goodsImg = goodsImgDao.loadMainPicByGoodNumber(goodsNumber, InternationalizationEnum.en_US.getValue());
        } else {
            //繁体图片
            goodsImg = goodsImgDao.loadMainPicByGoodNumber(goodsNumber, InternationalizationEnum.zh_HK.getValue());
        }

        //要判断一下图片 有没有图片 因为图片可能国际化都是同一个 这样我们就 简体的图片
        if (goodsImg == null) {
            goodsImg = goodsImgDao.loadMainPicByGoodNumber(goodsNumber, InternationalizationEnum.zh_CN.getValue());
        }
        return goodsImg.getImgUrl();
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#lang")
    public List<GoodsImg> getIndexImg(String lang) {

        List<GoodsImg> list = null;
        if (InternationalizationEnum.zh_CN.getName().equals(lang)) {
            list = goodsImgDao.getIndexImg(String.valueOf(IndexImgEnum.index_img.getValue()), InternationalizationEnum.zh_CN.getValue());
        } else if (InternationalizationEnum.en_US.getName().equals(lang)) {
            list = goodsImgDao.getIndexImg(String.valueOf(IndexImgEnum.index_img.getValue()), InternationalizationEnum.en_US.getValue());
        } else {
            list = goodsImgDao.getIndexImg(String.valueOf(IndexImgEnum.index_img.getValue()), InternationalizationEnum.zh_HK.getValue());
        }
        return list;
    }

}
