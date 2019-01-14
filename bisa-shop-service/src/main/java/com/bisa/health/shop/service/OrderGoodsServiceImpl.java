package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IGoodsDao;
import com.bisa.health.shop.dao.IGoodsInternationalizationDao;
import com.bisa.health.shop.dao.IOrderGoodsDao;
import com.bisa.health.shop.enumerate.InternationalizationEnum;
import com.bisa.health.shop.model.GoodsInternationalization;
import com.bisa.health.shop.model.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "OrderGoodsServiceImpl#${redis.exp1}")
public class OrderGoodsServiceImpl implements IOrderGoodsService {

    @Autowired
    private IOrderGoodsDao orderGoodsDao;
    @Autowired
    private IGoodsInternationalizationDao goodsInternationalizationDao;
    @Autowired
    private IGoodsDao goodsDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#orderId.toString()+#lang")
    public List<OrderGoods> selectOrderGoodsListByOrderId(Integer orderId, String lang) {
        // 根据订单的编号，获取下面的所有订单的明细
        List<OrderGoods> orderGoods = orderGoodsDao.selectOrderDetailByOrderId(orderId);
        List<OrderGoods> newOrderGoodsList = new ArrayList<>();

        for (OrderGoods orderGood : orderGoods) {
            //(商品的名字和图片都要替换)
            if (InternationalizationEnum.zh_CN.getName().equals(lang)) {
                orderGood = getGoodsInternationalization(orderGood, InternationalizationEnum.zh_CN);
            } else if (InternationalizationEnum.en_US.getName().equals(lang)) {
                orderGood = getGoodsInternationalization(orderGood, InternationalizationEnum.en_US);
            } else {
                orderGood = getGoodsInternationalization(orderGood, InternationalizationEnum.zh_HK);
            }
            //替换语言版本
            newOrderGoodsList.add(orderGood);
        }
        return newOrderGoodsList;
    }

    private OrderGoods getGoodsInternationalization(OrderGoods orderGood, InternationalizationEnum zh_cn) {
        GoodsInternationalization goodsInternationalization = goodsInternationalizationDao.selectGoodsInternationalizationByGoodsNumber(orderGood.getGoodsNumber(), zh_cn.getValue());
        orderGood.setGoodsName(goodsInternationalization.getGoods_name());
        orderGood.setImgUrl(goodsInternationalization.getImg_url());
        return orderGood;
    }

    @Override
    public void addOrderGoods(OrderGoods orderGoods) {
        orderGoodsDao.addOrderGoods(orderGoods);
    }
}
