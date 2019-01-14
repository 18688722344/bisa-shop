package com.bisa.health.shop.admin.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.admin.dao.IAdminOrderGoodsDao;
import com.bisa.health.shop.admin.dao.IAdminGoodsDao;
import com.bisa.health.shop.admin.dao.IAdminOrderDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.dto.PrintOrderGoodsListDto;
import com.bisa.health.shop.admin.dto.PrintOrderListDto;
import com.bisa.health.shop.admin.util.ParameterPackage;
import com.bisa.health.shop.enumerate.AfterSalesStatus;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "OrderServiceImpl#${redis.exp1}")
public class AdminOrderServiceImpl implements IAdminOrderService {

    @Autowired
    private IAdminOrderDao orderDao;
    @Autowired
	private IAdminOrderGoodsDao orderGoodsDao;
    @Autowired
    private IAdminGoodsDao goodsDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#order_no")
    public Order selectOrderByOrderNo(String order_no) {
        return orderDao.selectOrderByOrderNumber(order_no);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id.toString()")
    public Order getOrderById(Integer id) {
        return orderDao.selectOrderById(id);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#incontent +#searchabout")
    public OrderListPageDto<Order> selectOrderList(Integer page, Integer limit, String incontent, String searchabout) {
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        // 高级查询
        Pager<Order> pagerOrder = null;
        if (searchabout == null) {// 查询全部
            pagerOrder = orderDao.getPagerOrder();
        } else if (searchabout.equals("1")) {// 根据订单号查询集合
            pagerOrder = orderDao.selectOrderListsByOnlyOrderNo(incontent);
        } else if (searchabout.equals("2")) {// 根据运单号查询集合
            pagerOrder = orderDao.selectOrderListsByOnlyLogisticsNumber(incontent);
        }

        // 封装layui要的数据结构
        OrderListPageDto<Order> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#incontent +#searchabout +#val")
    public OrderListPageDto<Order> selectDeliverGoodsOrderList(Integer page, Integer limit, String incontent, String searchabout, String val) {
        // 查询 发货 order 分页集合 1 订单表ID 2 订单号 和 3 运单号 4 收货人联系电话 5 物流公司 6购买人联系电话
        // @param val 0 全部(未发货和已发货) 1 未发货 2 已发货 3待审核
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        Pager<Order> pagerOrder = null;
        if (searchabout == null && val == null) {// (默认)查询全部要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(PayStatus.paid.getValue(), PayStatus.waitDelivery.getValue(),
                    AfterSalesStatus.service_success.getValue(), AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOperationOrderList(parameterPackage);
        } else if ("1".equals(searchabout)) {// 根据订单表ID 查询 要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(incontent, PayStatus.paid.getValue(),
                    PayStatus.waitDelivery.getValue(), AfterSalesStatus.service_success.getValue(),
                    AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOrderListByOrderID(parameterPackage);
        } else if ("2".equals(searchabout)) {// 根据 订单号查询 要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(incontent, PayStatus.paid.getValue(),
                    PayStatus.waitDelivery.getValue(), AfterSalesStatus.service_success.getValue(),
                    AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOrderListByOrderNo(parameterPackage);
        } else if ("3".equals(searchabout)) {// 根据 运单号查询 要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(incontent, PayStatus.paid.getValue(),
                    PayStatus.waitDelivery.getValue(), AfterSalesStatus.service_success.getValue(),
                    AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOrderListByLogisticsNumber(parameterPackage);
        } else if ("4".equals(searchabout)) {// 根据 收货人联系电话查询 要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(incontent, PayStatus.paid.getValue(),
                    PayStatus.waitDelivery.getValue(), AfterSalesStatus.service_success.getValue(),
                    AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOrderListByConsigneePhone(parameterPackage);
        } else if ("5".equals(searchabout)) {// 根据 物流公司查询 要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(incontent, PayStatus.paid.getValue(),
                    PayStatus.waitDelivery.getValue(), AfterSalesStatus.service_success.getValue(),
                    AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOrderListByLogisticsName(parameterPackage);
        } else if ("0".equals(val)) {// 查询全部要操作的数据
            ParameterPackage parameterPackage = new ParameterPackage(PayStatus.paid.getValue(), PayStatus.waitDelivery.getValue(),
                    AfterSalesStatus.service_success.getValue(), AfterSalesStatus.finance_fail.getValue());
            pagerOrder = orderDao.selectOperationOrderList(parameterPackage);
        } else if ("1".equals(val)) {// 1 未发货
            pagerOrder = orderDao.selectOrderShippingStatus(PayStatus.paid.getValue());
        } else if ("2".equals(val)) {// 2 已发货
            pagerOrder = orderDao.selectOrderShippingStatus(PayStatus.waitDelivery.getValue());
        } else if ("3".equals(val)) {// 3 待审核
            pagerOrder = orderDao.selectOrderLogisticsCheck(
                    new ParameterPackage(AfterSalesStatus.service_success.getValue(), AfterSalesStatus.finance_fail.getValue()));
        }

        // 封装layui要的数据结构
        OrderListPageDto<Order> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateDeliverOrderStatus(Integer orderId, String logisticsNumber, String logisticsName) {
        Order order = new Order();
        order.setTra_status(PayStatus.waitDelivery.getValue());
        order.setDeliver_goods_time(new Date());
        order.setId(orderId);
        order.setLogistics_number(logisticsNumber);
        order.setLogistics_name(logisticsName);
        orderDao.updateDeliverOrderStatus(order);
    }

    @Override
    public List<PrintOrderListDto> getPrintOrderList(Integer[] jsonprintid) {

        List<PrintOrderListDto> list = new ArrayList<>();
        for (Integer orderID : jsonprintid) {
            PrintOrderListDto orderListDto = new PrintOrderListDto();
            // 封装 打印订单集合的对象
            Order order = orderDao.selectOrderByOrderId(orderID);
            orderListDto.setName(order.getConsignee());
            orderListDto.setOrder_no(order.getOrder_no());
            Date create_time = order.getCreate_time();
            String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(create_time);
            orderListDto.setStart_time(str);
            orderListDto.setCounty_address(order.getDetail_address());

            // 获取 订单下面的所有的商品详细信息
            List<PrintOrderGoodsListDto> list2 = new ArrayList<>();
            Integer count_total = 0;// 商品总数
            List<OrderGoods> orderGoodsList = orderGoodsDao.selectOrderGoodsByOrderId(orderID);
            for (OrderGoods orderGoods : orderGoodsList) {
                PrintOrderGoodsListDto orderGoodsListDto = new PrintOrderGoodsListDto();

                // 获取商品的对象
                Goods goods = goodsDao.selectGoodsByGoodsNumber(orderGoods.getGoodsNumber());
                orderGoodsListDto.setProduct_name(goods.getGoodsName());
                orderGoodsListDto.setAscription_guid(goods.getGoodsNumber());
                orderGoodsListDto.setCount(orderGoods.getCount());
                orderGoodsListDto.setPrice(orderGoods.getDiscountPrice().multiply(new BigDecimal(orderGoods.getCount())));// 单项总价(优惠后的价格)
                list2.add(orderGoodsListDto);

                count_total = count_total + orderGoods.getCount();
            }
            orderListDto.setCount_total(count_total);
            orderListDto.setSub_total(order.getTotal_price());
            orderListDto.setPost_price(order.getPost_price());
            orderListDto.setPrice_total(order.getActual_payment());
            orderListDto.setPage_side(list2);
            list.add(orderListDto);
        }
        return list;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#incontent +#searchabout")
    public OrderListPageDto<Order> selectServiceList(Integer page, Integer limit, String incontent, String searchabout) {
        // 查询 --->(客服--》退货审核)<---order 分页集合
        // * @param incontent input输入的内容 和下面对应
        // * @param searchabout option选择 1 订单号 和 2 运单号
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("receive_goods_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        Pager<Order> pagerOrder = null;
        if (searchabout == null) {// 查询全部(客服要的信息)
            ParameterPackage parameterPackage = new ParameterPackage(AfterSalesStatus.apply_for_ing.getValue(),
                    AfterSalesStatus.logistics_fail.getValue());
            pagerOrder = orderDao.selectserviceList(parameterPackage);
        } else if (searchabout.equals("1")) {// 根据订单号查询客服要的数据(就是和客服相关的订单详细)
            ParameterPackage parameterPackage = new ParameterPackage(incontent, AfterSalesStatus.apply_for_ing.getValue(),
                    AfterSalesStatus.logistics_fail.getValue());
            pagerOrder = orderDao.selectOrderListsByOrderNo(parameterPackage);
        } else if (searchabout.equals("2")) {// 根据运单号查询客服要的数据(就是和客服相关的订单详细)
            ParameterPackage parameterPackage = new ParameterPackage(incontent, AfterSalesStatus.apply_for_ing.getValue(),
                    AfterSalesStatus.logistics_fail.getValue());
            pagerOrder = orderDao.selectOrderListsByLogisticsNumber(parameterPackage);
        }

        // 封装layui要的数据结构
        OrderListPageDto<Order> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderCheckAfterSaleFail(String orderNO) {
        orderDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.logistics_fail.getValue()));
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderCheckAfterSaleSuccess(String orderNO) {
        orderDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.logistics_success.getValue()));
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderFinanceCheckSuccess(String orderNO) {
        orderDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.finance_success.getValue()));
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderFinanceCheckFail(String orderNO) {
        orderDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.finance_fail.getValue()));
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderServiceCheckSuccess(String orderNO) {
        orderDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.service_success.getValue()));
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateOrderServiceCheckFail(String orderNO) {
        orderDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.service_fail.getValue()));
    }

    @Override
    @CacheEvict(value = "OrderServiceImpl", allEntries = true)
    public void updateCancelOrderByOrderID(Integer orderId) {
        orderDao.updateCancelOrderByOrderID(orderId, PayStatus.close.getValue());
    }

}
