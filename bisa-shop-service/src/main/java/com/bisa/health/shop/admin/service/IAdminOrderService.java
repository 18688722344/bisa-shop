package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.dto.PrintOrderListDto;
import com.bisa.health.shop.model.Order;

import java.util.List;


/**
 * 订单 操作
 * @author Administrator
 */

public interface IAdminOrderService {

    /**
     * 查询 order 分页集合   +  高级查询
     * @param page
     * @param limit
     */
    OrderListPageDto<Order> selectOrderList(Integer page, Integer limit, String incontent, String searchabout);

    /**
     * 查询 发货  order 分页集合
     * @param page
     * @param limit
     * @param incontent   input输入的内容   和下面对应
     * @param searchabout option选择 1 订单表ID  2 订单号  和   3  运单号  4  收货人联系电话  5 物流公司 6购买人联系电话
     * @param val         0 全部(未发货和已发货)   1 未发货    2  已发货
     * @return
     */
    OrderListPageDto<Order> selectDeliverGoodsOrderList(Integer page, Integer limit, String incontent, String searchabout, String val);

    /**
     * 提交了 运单号，后台改为已发货
     * @param orderId
     * @param logisticsNumber
     */
    void updateDeliverOrderStatus(Integer orderId, String logisticsNumber, String logisticsName);

    /**
     * 根据 order表的id  获取打印要的信息
     * @param jsonprintid
     */
    List<PrintOrderListDto> getPrintOrderList(Integer[] jsonprintid);

    /**
     * 查询 --->(客服--》退货审核)<---order 分页集合
     * @param page
     * @param limit
     * @param incontent
     * @param searchabout
     * @return
     */
    OrderListPageDto<Order> selectServiceList(Integer page, Integer limit, String incontent, String searchabout);

    /**
     * 物流 审核 售后  拒绝
     * @param orderNO
     */
    void updateOrderCheckAfterSaleFail(String orderNO);

    /**
     * 物流 审核 售后  通过
     * @param orderNO
     */
    void updateOrderCheckAfterSaleSuccess(String orderNO);

    /**
     * 财务 审核 售后  通过
     * @param orderNO
     */
    void updateOrderFinanceCheckSuccess(String orderNO);

    /**
     * 财务 审核 售后  拒绝
     * @param orderNO
     */
    void updateOrderFinanceCheckFail(String orderNO);

    /**
     * 客服 审核 售后  通过
     * @param orderNO
     */
    void updateOrderServiceCheckSuccess(String orderNO);

    /**
     * 客服 审核 售后  拒绝
     * @param orderNO
     */
    void updateOrderServiceCheckFail(String orderNO);

    /**
     * 取消订单
     * @param orderId
     */
    void updateCancelOrderByOrderID(Integer orderId);

    /**
     * 根据订单编号获取对象
     * @param orderNo
     * @return
     */
    Order selectOrderByOrderNo(String orderNo);

    /**
     * 根据订单表id 获取对象
     * @param id
     * @return
     */
    Order getOrderById(Integer id);
}
