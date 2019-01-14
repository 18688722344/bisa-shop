package com.bisa.health.shop.controller;

import com.bisa.health.shop.dto.OrderDistinguishDto;
import com.bisa.health.shop.enumerate.GoodsClassify;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;
import com.bisa.health.shop.service.IGoodsService;
import com.bisa.health.shop.service.IOrderGoodsService;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.utils.InternationalizationUtil;
import com.bisa.health.shop.utils.KdApiOrderDistinguish;
import com.bisa.health.shop.utils.KdniaoTrackQueryAPI;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单中心
 * @author Administrator
 */

@Controller
@RequestMapping("/user")
public class OrderCenterController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private IGoodsService goodsService;

    @Value("${EBusinessID}")
    private String EBusinessID;
    @Value("${AppKey}")
    private String AppKey;
    @Value("${number_identification_url}")
    private String number_identification_url;
    @Value("${track_query_url}")
    private String track_query_url;

    /**
     * 跳转到 用户 订单中心页面(默认加载用户的全部 订单)
     */
    @RequestMapping(value = "/orderCenter", method = RequestMethod.GET)
    public String orderCenter() {
        //判断是否登录状态
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {//未登录
            return "redirect:../login";
        } else {//已登录
            return "order-center/userOrder";
        }
    }

    /**
     * 跳转到 订单详情页面
     * @param order_no 订单编号
     * @return
     */
    @RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
    public String orderDetail(String order_no, Model model, HttpSession session) {

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        // 根据订单号，查询对应的一条订单的信息
        Order order = orderService.selectOrderByOrderNo(order_no);
        List<OrderGoods> orderGoodsList = orderGoodsService.selectOrderGoodsListByOrderId(order.getId(),lang);

        String Logistics_button = "false";//表示没有快递的按钮
        //这里要判断订单里面有没有实体的产品，有就会出现物流的按钮，没有就不出现了
        for (OrderGoods orderGoods : orderGoodsList) {
            Goods goods = goodsService.selectGoodsByGoodsNumber(orderGoods.getGoodsNumber(), lang);

            //判断订单下面有没有实体商品，主要有一个就给他按钮
            if (goods.getClassifyId() != GoodsClassify.COUNT.getValue() && goods.getClassifyId() != GoodsClassify.TIME.getValue()) {
                Logistics_button = "true";
            }
        }

        // 前端倒计时显示时间
        // String countdownDate = getCountdownDate(order.getCreate_time());
        // dto.addAttribute("countdownDate", countdownDate);

        model.addAttribute("orderGoodsList", orderGoodsList);
        model.addAttribute("order", order);
        model.addAttribute("orderExpireTime", getOrderExpireTime(order.getCreate_time()));
        model.addAttribute("Logistics_button", Logistics_button);

        return "order-center/userOrderDetail";
    }

    /**
     * 订单详情  查看物流的状态
     * @param logistics_number 物流的编号
     * @return
     */
    @RequestMapping(value = "/lookLogistics", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String lookLogistics(String logistics_number) {
        String orderTracesByJson = null;
        try {
            OrderDistinguishDto orderDistinguishDto = new KdApiOrderDistinguish().getOrderTracesByJson(logistics_number, EBusinessID,
                    AppKey, number_identification_url);
            //根據运单号去查询运单公司的名称，没有就是查不到运单号的意思
            orderTracesByJson = new KdniaoTrackQueryAPI().getOrderTracesByJson(orderDistinguishDto.getShipperCode(),
                    orderDistinguishDto.getLogisticCode(), EBusinessID, AppKey, track_query_url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderTracesByJson;
    }

    /**
     * 获得订单支付截止时间
     * @param create_time
     * @return
     */
    private String getOrderExpireTime(Date create_time) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long yi = create_time.getTime() + nd;// 下单的时间 + 一天的毫秒数 就是 理论上取消订单的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(yi);
    }

    /**
     * 前台倒计时的时间 格式
     * @param create_time
     * @return
     */
    @SuppressWarnings("unused")
    private String getCountdownDate(Date create_time) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数

        long yi = create_time.getTime() + nd;// 下单的时间 + 一天的毫秒数 就是 理论上取消订单的时间
        long er = new Date().getTime();
        long cha = yi - er;

        String miao = (cha % nd % nh % nm / ns) + "";
        // 这个要判断一下秒数是不是个位数 ，前台要2位数字的 时间(如果是 各位数的秒 是有点问题的)
        if ((cha % nd % nh % nm / ns) <= 9) {
            miao = 0 + miao;
        }

        String formatDate = (cha % nd / nh) + ":" + (cha % nd % nh / nm) + ":" + miao;
        return formatDate;
    }

}
