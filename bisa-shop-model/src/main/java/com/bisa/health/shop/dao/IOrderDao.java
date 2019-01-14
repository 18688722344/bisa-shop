package com.bisa.health.shop.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.Order;

import java.util.List;

/**
 * 订单表
 */
public interface IOrderDao {

    /**
     * 创建一个订单
     * @param order
     * @return
     */
    boolean addOrder(Order order);

    /**
     * 根据商品 编号查询订单
     * @param orderNumber
     * @return
     */
    Order selectOrderByOrderNumber(String orderNumber);

    /**
     * 获取该 用户下的所有订单详细
     * @param user_guid
     * @return
     */
    List<Order> selectOrdersByUserId(int user_guid);

    /**
     * 根据订单状态，查询订单分页
     * @param user_guid
     * @param order_status
     * @return
     */
    Pager<Order> getPagerOrderByStatus(int user_guid, Integer order_status);

    /**
     * 全部订单分页
     * @param user_guid
     * @return
     */
    Pager<Order> getPagerOrderByUserGuid(int user_guid);

    /**
     * 确认收货
     * @param order_no
     * @param order_status
     * @return
     */
    int updateReceivingOrderStatus(String order_no, Integer order_status);

    /**
     * 更新订单的PaymentId
     * @param order_no
     * @param paymentId
     * @return
     */
    int updateOrderPaymentIdByOrderNo(String order_no, String paymentId);

    /**
     * 更新订单的状态
     * @param order_no
     * @param order_status
     * @return
     */
    int updateOrderStatus(String order_no, int order_status);

    /**
     * 刪除订单
     * @param order_no
     */
    int updateDelectOrder(String order_no);
}
