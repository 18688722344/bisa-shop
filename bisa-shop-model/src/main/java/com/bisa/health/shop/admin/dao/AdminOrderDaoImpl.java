package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;

import com.bisa.health.shop.admin.util.ParameterPackage;
import com.bisa.health.shop.model.Order;

@Repository
public class AdminOrderDaoImpl extends BaseDao<Order> implements IAdminOrderDao {

    @Override
    public Order selectOrderByOrderNumber(String orderNumber) {
        String sql = "SELECT * FROM s_order WHERE order_no = ?";
        return super.queryObjectBySql(sql, new Object[]{orderNumber}, Order.class);
    }

    @Override
    public Pager<Order> getPagerOrder() {
        String sql = "SELECT * FROM s_order";
        return super.findBySql(sql, new Object[]{}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListsByOnlyOrderNo(String incontent) {
        String sql = "SELECT * FROM s_order WHERE order_no = ?";
        return super.findBySql(sql, new Object[]{incontent}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListsByOnlyLogisticsNumber(String incontent) {
        String sql = "SELECT * FROM s_order WHERE logistics_number = ?";
        return super.findBySql(sql, new Object[]{incontent}, Order.class, true);
    }

    @Override
    public Order selectOrderByOrderId(Integer orderId) {
        String sql = "SELECT * FROM s_order WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{orderId}, Order.class);
    }

    @Override
    public Pager<Order> selectserviceList(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE after_sale_apply in (?,?)";
        return super.findBySql(sql, new Object[]{parameterPackage.getName1(), parameterPackage.getName2()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListsByOrderNo(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE order_no = ? AND after_sale_apply in (?,?)";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2()},
                Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListsByLogisticsNumber(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE logistics_number = ? AND after_sale_apply in (?,?)";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2()},
                Order.class, true);
    }

    @Override
    public boolean updateOrderCheckAfterSale(ParameterPackage parameterPackage) {
        String sql = "UPDATE s_order SET after_sale_apply = ? WHERE order_no = ?";
        int result = super.updateBySql(sql, new Object[]{parameterPackage.getName1(), parameterPackage.getName0()});
        return result >= 1 ? true : false;
    }

    @Override
    public Pager<Order> selectOperationOrderList(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE (tra_status in (?,?) OR after_sale_apply in (?,?))";
        return super.findBySql(sql, new Object[]{parameterPackage.getName1(), parameterPackage.getName2(), parameterPackage.getName3(),
                parameterPackage.getName4()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListByOrderID(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE id = ? AND (tra_status in (?,?) OR after_sale_apply in (?,?))";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2(),
                parameterPackage.getName3(), parameterPackage.getName4()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListByOrderNo(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE order_no = ? AND (tra_status in (?,?) OR after_sale_apply in (?,?))";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2(),
                parameterPackage.getName3(), parameterPackage.getName4()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListByLogisticsNumber(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE logistics_number = ? AND (tra_status in (?,?) OR after_sale_apply in (?,?))";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2(),
                parameterPackage.getName3(), parameterPackage.getName4()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListByConsigneePhone(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE phone = ? AND (tra_status in (?,?) OR after_sale_apply in (?,?))";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2(),
                parameterPackage.getName3(), parameterPackage.getName4()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderListByLogisticsName(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE logistics_name = ? AND (tra_status in (?,?) OR after_sale_apply in (?,?))";
        return super.findBySql(sql, new Object[]{parameterPackage.getName0(), parameterPackage.getName1(), parameterPackage.getName2(),
                parameterPackage.getName3(), parameterPackage.getName4()}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderShippingStatus(int value) {
        String sql = "SELECT * FROM s_order WHERE tra_status = ?";
        return super.findBySql(sql, new Object[]{value}, Order.class, true);
    }

    @Override
    public Pager<Order> selectOrderLogisticsCheck(ParameterPackage parameterPackage) {
        String sql = "SELECT * FROM s_order WHERE after_sale_apply in (?,?)";
        return super.findBySql(sql, new Object[]{parameterPackage.getName1(), parameterPackage.getName2()}, Order.class, true);
    }

    @Override
    public boolean updateDeliverOrderStatus(Order order) {
        String sql = "UPDATE s_order SET tra_status = ?,deliver_goods_time = ?,logistics_number = ?,logistics_name = ? WHERE id = ?";
        int result = super.updateBySql(sql, new Object[]{order.getTra_status(), order.getDeliver_goods_time(),
                order.getLogistics_number(), order.getLogistics_name(), order.getId()});
        return result >= 1 ? true : false;
    }

    @Override
    public boolean updateCancelOrderByOrderID(Integer orderId, Integer value) {
        String sql = "UPDATE s_order SET tra_status = ? WHERE id = ?";
        int result = super.updateBySql(sql, new Object[]{value, orderId});
        return result >= 1 ? true : false;

    }

    @Override
    public Order selectOrderById(Integer id) {
        String sql = "SELECT * FROM s_order WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{id}, Order.class);
    }

}
