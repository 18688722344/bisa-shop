package com.bisa.health.shop.dto;

import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;

import java.io.Serializable;
import java.util.List;

/**
 * 订单中心页面  数据封装类
 * @author Administrator
 */

public class UserOrderDto implements Serializable {

    private Order order;
    private List<OrderGoods> orderGoodsList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public UserOrderDto() {
        super();
    }

    public UserOrderDto(Order order, List<OrderGoods> orderGoodsList) {
        super();
        this.order = order;
        this.orderGoodsList = orderGoodsList;
    }

}
