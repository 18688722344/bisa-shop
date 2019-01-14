package com.bisa.health.shop.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.dto.UserOrderDto;
import com.bisa.health.shop.model.Order;

import java.util.List;

/**
 * 订单 操作
 */

public interface IOrderService {

    /**
     * 创建一个待付款的新订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 根据订单号，查询对应的一条订单的信息
     * @param order_no
     * @return
     */
    Order selectOrderByOrderNo(String order_no);

    /**
     * 查询用户所有订单，非分页
     * @param user_guid
     * @return
     */
    List<Order> getListOrderByUserGuid(int user_guid);

    /**
     * 确认收货
     * @param order_no
     * @param order_status
     * @return
     */
    void updateReceivingOrderStatus(String order_no, Integer order_status);

    /**
     * 更新订单的PaymentId
     * @param order_no
     * @param PaymentId
     */
    void updateOrderPaymentIdByOrderNo(String order_no, String PaymentId);

    /**
     * 更新订单的状态
     * @param order_no
     * @param value
     */
    void updateOrderStatus(String order_no, int value);

    /**
     * 订单分页
     * @param page
     * @param limit
     * @param userGuid
     * @param order_status
     * @param lang
     * @return
     */
    Pager<UserOrderDto> getPagerOrderByStatus(Integer page, Integer limit, int userGuid, Integer order_status, String lang);

    /**
     * 刪除订单
     * @param order_no
     */
    void updateDelectOrder(String order_no);
}
