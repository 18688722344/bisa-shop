package com.bisa.health.shop.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.dao.IGoodsInternationalizationDao;
import com.bisa.health.shop.dao.IOrderDao;
import com.bisa.health.shop.dao.IOrderGoodsDao;
import com.bisa.health.shop.dto.UserOrderDto;
import com.bisa.health.shop.enumerate.InternationalizationEnum;
import com.bisa.health.shop.model.GoodsInternationalization;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "OrderServiceImpl#${redis.exp1}")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderGoodsDao orderGoodsDao;
    @Autowired
    private IGoodsInternationalizationDao goodsInternationalizationDao;

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#order_no")
    public Order selectOrderByOrderNo(String order_no) {
        return orderDao.selectOrderByOrderNumber(order_no);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#user_guid")
    public List<Order> getListOrderByUserGuid(int user_guid) {
        return orderDao.selectOrdersByUserId(user_guid);
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateReceivingOrderStatus(String order_no, Integer order_status) {
        orderDao.updateReceivingOrderStatus(order_no, order_status);
    }

    //这个方法是银联支付用的方法，不走缓存
    @Override
    public void updateOrderPaymentIdByOrderNo(String order_no, String PaymentId) {
        orderDao.updateOrderPaymentIdByOrderNo(order_no, PaymentId);
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderStatus(String order_no, int order_status) {
        orderDao.updateOrderStatus(order_no, order_status);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#userGuid +#order_status.toString() +#lang")
    public Pager<UserOrderDto> getPagerOrderByStatus(Integer page, Integer limit, int userGuid, Integer order_status, String lang) {

        SystemContext.setPageOffset(page);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        Pager<Order> orderPager = null;
        if (order_status == -1) {
            //全部订单分页
            orderPager = orderDao.getPagerOrderByUserGuid(userGuid);
        } else {
            //根据订单状态查询分页
            orderPager = orderDao.getPagerOrderByStatus(userGuid, order_status);
        }

        List<UserOrderDto> userOrderDtoList = new ArrayList();
        for (Order order : orderPager.getDatas()) {
            // 根据订单的编号，获取下面的所有订单的明细
            List<OrderGoods> orderGoodsList = orderGoodsDao.selectOrderDetailByOrderId(order.getId());
            List<OrderGoods> newOrderGoodsList = new ArrayList<>();

            //这里查出来的订单明细中的商品的名字要国际化处理下
            for (OrderGoods orderGoods : orderGoodsList) {

                //(商品的名字和图片都要替换)
                if (InternationalizationEnum.zh_CN.getName().equals(lang)) {
                    orderGoods = getOrderGoods(orderGoods, InternationalizationEnum.zh_CN);
                } else if (InternationalizationEnum.en_US.getName().equals(lang)) {
                    orderGoods = getOrderGoods(orderGoods, InternationalizationEnum.en_US);
                } else {
                    orderGoods = getOrderGoods(orderGoods, InternationalizationEnum.zh_HK);
                }
                //替换语言版本
                newOrderGoodsList.add(orderGoods);
            }

            //封装订单信息
            UserOrderDto userOrderDto = new UserOrderDto(order, newOrderGoodsList);
            userOrderDtoList.add(userOrderDto);
        }

        //重组分页数据
        Pager<UserOrderDto> orderPagers = new Pager();
        orderPagers.setDatas(userOrderDtoList);
        orderPagers.setOffset(orderPager.getOffset());
        orderPagers.setSize(orderPager.getSize());
        orderPagers.setTotal(orderPager.getTotal());
        return orderPagers;
    }

    //获取订单明细对象（国际化）
    private OrderGoods getOrderGoods(OrderGoods orderGoods, InternationalizationEnum zh_cn) {
        GoodsInternationalization goodsInternationalization = goodsInternationalizationDao.selectGoodsInternationalizationByGoodsNumber(orderGoods.getGoodsNumber(), zh_cn.getValue());
        orderGoods.setGoodsName(goodsInternationalization.getGoods_name());
        orderGoods.setImgUrl(goodsInternationalization.getImg_url());
        return orderGoods;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "OrderServiceImpl", allEntries = true),
            @CacheEvict(value = "OrderGoodsServiceImpl", allEntries = true)
    })
    public void updateDelectOrder(String order_no) {
        Order order = orderDao.selectOrderByOrderNumber(order_no);
        orderDao.updateDelectOrder(order_no);
        orderGoodsDao.deleteOrderGoods(order.getId());
    }
}
