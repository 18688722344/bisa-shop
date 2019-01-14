package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.OrderGoods;

import java.util.List;

/**
 * 订单详情表
 */
public interface IOrderGoodsDao {

    /**
     * 保存 商品订单明细表
     * @param orderGoods
     * @return
     */
    boolean addOrderGoods(OrderGoods orderGoods);

    /**
     * 根据订单的id，获取下面的所有订单的明细
     * @param id
     * @return
     */
    List<OrderGoods> selectOrderDetailByOrderId(Integer id);

    /**
     * 删除订单下面的所有明细
     * @param id
     */
    int deleteOrderGoods(Integer id);
}
