package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.OrderGoods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderGoodsDaoImpl extends BaseDao<OrderGoods> implements IOrderGoodsDao {

    @Override
    public List<OrderGoods> selectOrderDetailByOrderId(Integer id) {
        String sql = "SELECT * FROM s_order_goods WHERE order_id = ?";
        return super.listBySql(sql, new Object[]{id}, OrderGoods.class);
    }

    @Override
    public int deleteOrderGoods(Integer id) {
        String sql = "DELETE FROM s_order_goods WHERE order_id = ?";
        return super.deleteBySql(sql, new Object[]{id});
    }

    @Override
    public boolean addOrderGoods(OrderGoods orderGoods) {
        String sql = "INSERT INTO s_order_goods(order_id ,goods_number, count, goods_price,"
                + " discount_price, goods_type, goods_name, img_url) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        int result = super.addBySql(sql,
                new Object[]{orderGoods.getOrderId(), orderGoods.getGoodsNumber(), orderGoods.getCount(), orderGoods.getGoodsPrice(),
                        orderGoods.getDiscountPrice(), orderGoods.getGoodsType(), orderGoods.getGoodsName(), orderGoods.getImgUrl()});
        return result >= 1 ? true : false;
    }
}
