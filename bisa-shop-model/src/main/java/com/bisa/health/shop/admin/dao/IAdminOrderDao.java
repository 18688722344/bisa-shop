package com.bisa.health.shop.admin.dao;


import com.bisa.health.basic.entity.Pager;

import com.bisa.health.shop.admin.util.ParameterPackage;
import com.bisa.health.shop.model.Order;


/**
 * 订单表
 * @author Administrator
 */

public interface IAdminOrderDao {

    /**
     * 全部订单分页
     * @return
     */
    Pager<Order> getPagerOrder();

    /**
     * 根据订单号查询集合
     * @param incontent
     * @return
     */
    Pager<Order> selectOrderListsByOnlyOrderNo(String incontent);

    /**
     * 根据运单号查询集合
     * @param incontent
     * @return
     */
    Pager<Order> selectOrderListsByOnlyLogisticsNumber(String incontent);

    /**
     * 根据order表的id获取对象
     * @param orderId
     * @return
     */
    Order selectOrderByOrderId(Integer orderId);

    /**
     * 查询全部(客服要的信息)
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectserviceList(ParameterPackage parameterPackage);

    /**
     * 根据订单号查询客服要的数据(就是和客服相关的订单详细)
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListsByOrderNo(ParameterPackage parameterPackage);

    /**
     * 根据运单号查询客服要的数据(就是和客服相关的订单详细)
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListsByLogisticsNumber(ParameterPackage parameterPackage);

    /**
     * 客服 审核 售后  通过
     * @param parameterPackage
     * @return
     */
    boolean updateOrderCheckAfterSale(ParameterPackage parameterPackage);

    /**
     * (默认)查询全部要操作的数据
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOperationOrderList(ParameterPackage parameterPackage);

    /**
     * 根据订单表ID 查询  要操作的数据
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListByOrderID(ParameterPackage parameterPackage);

    /**
     * 根据 订单号查询  要操作的数据
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListByOrderNo(ParameterPackage parameterPackage);

    /**
     * 根据 运单号查询  要操作的数据
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListByLogisticsNumber(ParameterPackage parameterPackage);

    /**
     * 根据 收货人联系电话查询  要操作的数据
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListByConsigneePhone(ParameterPackage parameterPackage);

    /**
     * 根据 物流公司查询   要操作的数据
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderListByLogisticsName(ParameterPackage parameterPackage);

    /**
     * 1 未发货  或者  已发货了
     * @param value
     * @return
     */
    Pager<Order> selectOrderShippingStatus(int value);

    /**
     * 3 待审核
     * @param parameterPackage
     * @return
     */
    Pager<Order> selectOrderLogisticsCheck(ParameterPackage parameterPackage);

    /**
     * 提交了 运单号，后台改为已发货
     * @param order
     * @return
     */
    boolean updateDeliverOrderStatus(Order order);

    /**
     * 根据order表id,获取对象
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);

    /**
     * 根据订单表ID 取消订单
     * @param orderId
     * @param value
     * @return
     */
    boolean updateCancelOrderByOrderID(Integer orderId, Integer value);

    /**
     * 根据订单编号获取对象
     * @param order_no
     * @return
     */
    Order selectOrderByOrderNumber(String order_no);

}
