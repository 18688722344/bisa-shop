package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.model.OrderGoods;

import java.util.List;

/**
 * 订单详情表
 */

public interface IAdminOrderGoodsService {

    /**
     * 获取order下面的所有商品的名称
     * @param orderId
     * @return
     */
    List<OrderGoods> getOrderGoodsByOrderId(Integer orderId);
}
