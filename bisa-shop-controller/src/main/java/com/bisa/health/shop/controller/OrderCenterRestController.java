package com.bisa.health.shop.controller;

import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.dto.UserOrderDto;
import com.bisa.health.shop.enumerate.GoodsStatusEnum;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;
import com.bisa.health.shop.service.IGoodsService;
import com.bisa.health.shop.service.IOrderGoodsService;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.utils.InternationalizationUtil;
import com.bisa.health.shop.utils.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 订单详情controller
 */

@RestController
@RequestMapping("/user")
public class OrderCenterRestController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private IGoodsService goodsService;

    /**
     * 加载订单分页数据       /user/loadUserOrderList?order_status=0&page=0&limit=10
     * @param order_status 订单的状态
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/loadUserOrderList", method = RequestMethod.GET)
    public Pager<UserOrderDto> loadUserOrderList(@CurrentUser User sysUser, Integer page, Integer limit, HttpSession session,
                                                 Integer order_status) {

        String lang = InternationalizationUtil.getLang(session);
        Pager<UserOrderDto> orderPager = orderService.getPagerOrderByStatus(page, limit, sysUser.getUser_guid(), order_status, lang);
        return orderPager;
    }

    /**
     * 根据订单号，判断订单明细的商品库存是否够
     * @param order_no 订单号
     * @retrun
     */
    @RequestMapping(value = "/judgeGoodsRepertory", method = RequestMethod.POST)
    public String judgeGoodsRepertory(String order_no, HttpSession session) {

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        Order order = orderService.selectOrderByOrderNo(order_no);
        List<OrderGoods> orderGoodsList = orderGoodsService.selectOrderGoodsListByOrderId(order.getId(), lang);
        for (OrderGoods orderGoods : orderGoodsList) {

            Goods goods = goodsService.selectGoodsByGoodsNumber(orderGoods.getGoodsNumber(), lang);
            //判断商品的库存是否够
            if (goods.getStoreNumber() >= orderGoods.getCount() && goods.getGoodsStatus() == GoodsStatusEnum.in_sale.getValue()) {
                //有货
                return "200";
            } else if (goods.getStoreNumber() < orderGoods.getCount() || goods.getGoodsStatus() == GoodsStatusEnum.sale_out.getValue()) {
                //没有货了
                return "300";
            } else if (goods.getGoodsStatus() == GoodsStatusEnum.invalid.getValue()) {
                //下架了
                return "400";
            }
        }
        return null;
    }

    /**
     * 取消订单
     * @param order_no 订单编号
     * @return
     */
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public JsonResult cancelOrder(String order_no) {

        JsonResult jsonResult = new JsonResult();
        try {
            orderService.updateOrderStatus(order_no, PayStatus.close.getValue());
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 确认收货
     * @param order_no 订单编号
     * @return
     */
    @RequestMapping(value = "/confirmDelivery", method = RequestMethod.POST)
    public JsonResult confirmDelivery(String order_no) {

        JsonResult jsonResult = new JsonResult();
        try {
            // 获取订单的状态，必须要等于3 才能收货
            Order order = orderService.selectOrderByOrderNo(order_no);
            // 还没有发货
            if (order.getTra_status() == PayStatus.paid.getValue()) {
                jsonResult.setMsg("1");
                return jsonResult;
            }
            orderService.updateReceivingOrderStatus(order_no, PayStatus.received.getValue());

            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 刪除订单
     * @param order_no 订单编号
     * @return
     */
    @RequestMapping(value = "/delectOrder", method = RequestMethod.POST)
    public JsonResult delectOrder(String order_no) {

        JsonResult jsonResult = new JsonResult();
        try {
            orderService.updateDelectOrder(order_no);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

}
