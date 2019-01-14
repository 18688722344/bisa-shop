package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends BaseDao<Order> implements IOrderDao {

    @Override
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO s_order(order_no, user_id, consignee, phone, detail_address,"
                + " tra_status, total_price, post_price, preferential_price, actual_payment,"
                + " create_time, order_email, logistics_name, logistics_account) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = super.addBySql(sql,
                new Object[]{order.getOrder_no(), order.getUser_id(), order.getConsignee(), order.getPhone(), order.getDetail_address(),
                        order.getTra_status(), order.getTotal_price(), order.getPost_price(), order.getPreferential_price(),
                        order.getActual_payment(), order.getCreate_time(), order.getOrderEmail(), order.getLogistics_name(),
                        order.getLogisticsAccount()});
        return result >= 1 ? true : false;
    }

    @Override
    public Order selectOrderByOrderNumber(String orderNumber) {
        String sql = "SELECT * FROM s_order WHERE order_no = ?";
        return super.queryObjectBySql(sql, new Object[]{orderNumber}, Order.class);
    }

    @Override
    public List<Order> selectOrdersByUserId(int user_guid) {
        String sql = "SELECT * FROM s_order WHERE user_id = ?";
        return super.listBySql(sql, new Object[]{user_guid}, Order.class);
    }

    @Override
    public Pager<Order> getPagerOrderByStatus(int user_guid, Integer tra_status) {
        String sql = "SELECT * FROM s_order WHERE user_id=? AND tra_status=?";
        return super.findBySql(sql, new Object[]{user_guid, tra_status}, Order.class, true);
    }

    @Override
    public Pager<Order> getPagerOrderByUserGuid(int user_guid) {
        String sql = "SELECT * FROM s_order WHERE user_id=?";
        return super.findBySql(sql, new Object[]{user_guid}, Order.class, true);
    }

    @Override
    public int updateReceivingOrderStatus(String order_no, Integer order_status) {
        String sql = "UPDATE s_order SET tra_status = ?,receive_goods_time = NOW() WHERE order_no = ?";
        return super.updateBySql(sql, new Object[]{order_status, order_no});
    }

    @Override
    public int updateOrderPaymentIdByOrderNo(String order_no, String paymentId) {
        String sql = "UPDATE s_order SET payment_id = ? WHERE order_no = ?";
        return super.updateBySql(sql, new Object[]{paymentId, order_no});
    }

    @Override
    public int updateOrderStatus(String order_no, int order_status) {
        String sql = "UPDATE s_order SET tra_status = ? WHERE order_no = ?";
        return super.updateBySql(sql, new Object[]{order_status, order_no});
    }

    @Override
    public int updateDelectOrder(String order_no) {
        String sql = "DELETE FROM s_order WHERE order_no=?";
        return super.deleteBySql(sql, new Object[]{order_no});
    }

}
