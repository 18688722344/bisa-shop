package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.model.OrderGoods;

/**
 * 订单明细表
 * @author Administrator
 */

public interface IAdminOrderGoodsDao {

    /**
     * 获取order下面的商品详情
     * @param orderId
     * @return
     */
    List<OrderGoods> selectOrderGoodsByOrderId(Integer orderId);

}
