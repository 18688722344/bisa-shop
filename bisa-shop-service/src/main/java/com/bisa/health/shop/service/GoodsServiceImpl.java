package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IGoodsDao;
import com.bisa.health.shop.dao.IGoodsInternationalizationDao;
import com.bisa.health.shop.dto.GoodsComboDto;
import com.bisa.health.shop.enumerate.InternationalizationEnum;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.GoodsInternationalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@CacheConfig(cacheNames = "GoodsInternationalizationServiceImpl#${redis.exp1}")
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;
    @Autowired
    private IGoodsInternationalizationDao goodsInternationalizationDao;

    @Override
    //@Cacheable(value = "GoodsInternationalizationServiceImpl}",key = "targetClass.name+methodName+#goodsNumber+#lang")
    public Goods selectGoodsByGoodsNumber(String goodsNumber, String lang) {

        Goods goods = goodsDao.selectGoodsByGoodsNumber(goodsNumber);
        //商品国际化语言
        Goods newGoods = goodsLangStyle(lang, goods);
        return newGoods;
    }

    //商品国际化语言类型
    private Goods goodsLangStyle(String lang, Goods goods) {

        Goods newGoods = null;
        if (InternationalizationEnum.zh_CN.getName().equals(lang)) {
            //简体
            newGoods = getGoodsInternationalization(goods, InternationalizationEnum.zh_CN);
        } else if (InternationalizationEnum.en_US.getName().equals(lang)) {
            //英文
            newGoods = getGoodsInternationalization(goods, InternationalizationEnum.en_US);
        } else {
            //繁体
            newGoods = getGoodsInternationalization(goods, InternationalizationEnum.zh_HK);
        }
        return newGoods;
    }

   //获取商品的语言版本

    private Goods getGoodsInternationalization(Goods goods, InternationalizationEnum lang) {
        GoodsInternationalization goodsInternationalization = goodsInternationalizationDao.selectGoodsInternationalizationById(goods.getId(), lang.getValue());

        if (goodsInternationalization != null) {
            goods.setGoodsName(goodsInternationalization.getGoods_name());
            goods.setTitle(goodsInternationalization.getTitle());
            goods.setDescription(goodsInternationalization.getDescription());
            goods.setImgUrl(goodsInternationalization.getImg_url());
        }
        return goods;
    }

    //这里就更新一个 评论的数量的话 不考虑删除缓存了
    @Override
    public void updateGoodsAppraiseNumber(String goodsNumber, int count) {
        //修改商品评论数
        Goods goods = goodsDao.selectGoodsByGoodsNumber(goodsNumber);
        //评论数+1
        goods.setAppraiseNumber(goods.getAppraiseNumber() + count);
        goodsDao.updateAppraiseNumber(goods);
    }

    @Override
    //@Cacheable(value = "GoodsInternationalizationServiceImpl#${redis.exp1}",key = "targetClass.name+methodName+#parentId.toString()+#lang")
    public List<GoodsComboDto> listChildrenGoodsByParentId(Integer parentId, String lang) {

        List<Goods> listGoods = goodsDao.listChildrenGoods(parentId);
        List<GoodsComboDto> GoodsComboDtoList = new ArrayList();

        for (Goods goods : listGoods) {
            //商品国际化语言
            Goods newGoods = goodsLangStyle(lang, goods);
            GoodsComboDto goodsComboDto = new GoodsComboDto(newGoods);
            GoodsComboDtoList.add(goodsComboDto);
        }
        return GoodsComboDtoList;
    }

    @Override
    //@Cacheable(value = "GoodsInternationalizationServiceImpl#${redis.exp1}",key = "targetClass.name+methodName+#id.toString()+#lang")
    public List<Goods> listChildrenGoodsByParentId2(Integer id, String lang) {

        List<Goods> goodsList = goodsDao.listChildrenGoods(id);
        List<Goods> newGoodsList = new ArrayList<>();

        for (Goods goods : goodsList) {
            //商品国际化语言
            Goods newGoods = goodsLangStyle(lang, goods);
            newGoodsList.add(newGoods);
        }
        return newGoodsList;
    }

}
