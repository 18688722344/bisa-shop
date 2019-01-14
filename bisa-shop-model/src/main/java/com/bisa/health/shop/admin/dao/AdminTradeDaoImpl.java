package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.pay.model.Trade;
import com.bisa.health.shop.admin.util.ParameterPackage;

@Repository
public class AdminTradeDaoImpl extends BaseDao<Trade> implements IAdminTradeDao {

    @Override
    public Pager<Trade> selectAll() {
        String sql = "SELECT * FROM s_trade ";
        return super.findBySql(sql, new Object[]{}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByTradeNo(String incontent) {
        String sql = "SELECT * FROM s_trade where trade_no = ?";
        return super.findBySql(sql, new Object[]{incontent}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYear(String incontent) {
        String sql = "SELECT * FROM s_trade where date_format(create_time,'%Y') = ?";
        return super.findBySql(sql, new Object[]{incontent}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYearMonth(String incontent) {
        String sql = "SELECT * FROM s_trade where date_format(create_time,'%Y-%m') = ?";
        return super.findBySql(sql, new Object[]{incontent}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYearMonthDay(String incontent) {
        String sql = "SELECT * FROM s_trade where date_format(create_time,'%Y-%m-%d') = ?";
        return super.findBySql(sql, new Object[]{incontent}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeStatus(int value) {
        String sql = "SELECT * FROM s_trade where pay_status = ?";
        return super.findBySql(sql, new Object[]{value}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeRefundList(int value, int value4, int value2, int value3) {
        String sql = "SELECT * FROM s_trade where pay_status in (?,?,?,?) ";
        return super.findBySql(sql, new Object[]{value, value2, value3, value4}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYearMonthDay0(String incontent, int value, int value4, int value2, int value3) {
        String sql = "SELECT * FROM s_trade where trade_no = ? AND pay_status in (?,?,?,?) ";
        return super.findBySql(sql, new Object[]{incontent, value, value2, value3, value4}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYearMonthDay1(String incontent, int value, int value4, int value2, int value3) {
        String sql = "SELECT * FROM s_trade where date_format(refund_time,'%Y') = ? AND pay_status in (?,?,?,?) ";
        return super.findBySql(sql, new Object[]{incontent, value, value2, value3, value4}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYearMonthDay2(String incontent, int value, int value4, int value2, int value3) {
        String sql = "SELECT * FROM s_trade where date_format(refund_time,'%Y-%m') = ? AND pay_status in (?,?,?,?) ";
        return super.findBySql(sql, new Object[]{incontent, value, value2, value3, value4}, Trade.class, true);
    }

    @Override
    public Pager<Trade> selectTradeByYearMonthDay3(String incontent, int value, int value4, int value2, int value3) {
        String sql = "SELECT * FROM s_trade where date_format(refund_time,'%Y-%m-%d') = ? AND pay_status in (?,?,?,?) ";
        return super.findBySql(sql, new Object[]{incontent, value, value2, value3, value4}, Trade.class, true);
    }

    @Override
    public boolean updateOrderCheckAfterSale(ParameterPackage parameterPackage) {
        String sql = "UPDATE s_trade SET pay_status = ? WHERE order_no = ?";
        int result = super.updateBySql(sql, new Object[]{parameterPackage.getName1(), parameterPackage.getName0()});
        return result >= 1 ? true : false;
    }

    @Override
    public Pager<Trade> selectTradeByOrderNo(String incontent) {
        String sql = "SELECT * FROM s_trade where order_no = ?";
        return super.findBySql(sql, new Object[]{incontent}, Trade.class, true);
    }

}
