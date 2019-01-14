package com.bisa.health.shop.service;

import com.bisa.health.shop.model.OrderGoods;

import java.util.List;

/**
 * 订单详情表
 */
public interface IOrderGoodsService {

    /**
     * 根据订单表的id ，查下面所有的订单详情
     * @param id 订单表的id
     * @param lang
     */
    List<OrderGoods> selectOrderGoodsListByOrderId(Integer id, String lang);

    /**
     * 保存商品订单明细表
     * @param orderGoods
     * @return
     */
    void addOrderGoods(OrderGoods orderGoods);

}
