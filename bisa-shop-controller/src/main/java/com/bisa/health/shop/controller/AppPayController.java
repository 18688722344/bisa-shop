package com.bisa.health.shop.controller;

import com.bisa.health.app.ecg.dto.BelowOrderProductDto;
import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.component.EasylinkPayComponnent;
import com.bisa.health.shop.component.VisaPayComponent;
import com.bisa.health.shop.dto.GoodsComboDto;
import com.bisa.health.shop.dto.ObjectResult;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;
import com.bisa.health.shop.mqtt.RabbitMqProducer;
import com.bisa.health.shop.service.IGoodsService;
import com.bisa.health.shop.service.IOrderGoodsService;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.utils.InternationalizationUtil;
import com.bisa.health.shop.utils.OrderNoUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * app 请求支付相关的数据
 */

@RequestMapping(value = "/mi")
public class AppPayController {

    private Logger logger = LogManager.getLogger(AppPayController.class);

    @Autowired
    private IOrderService orderService;
    @Autowired
    private VisaPayComponent visaPayComponent;
    @Autowired
    private EasylinkPayComponnent easylinkPayComponnent;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RabbitMqProducer rabbitMqProducer;
    @Autowired
    private IOrderGoodsService orderGoodsService;

    /**
     * app 下单按钮
     * @param productData 下单数据
     */
    @RequestMapping(value = "/appBelowOrder", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult appBelowOrder(String productData, @CurrentUser User user, HttpSession session) {

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        //解析json字符串
        JSONObject jsonObject = JSONObject.fromObject(productData);
        //转换成json集合
        JSONArray products = JSONArray.fromObject(jsonObject.getString("products"));
        List<BelowOrderProductDto> list = JSONArray.toList(products, BelowOrderProductDto.class);

        BigDecimal totalPrice = BigDecimal.ZERO;        // 所有商品总价
        BigDecimal actuallyPrice = BigDecimal.ZERO;     // 订单实际总价
        BigDecimal discountPirce = BigDecimal.ZERO;     // 订单总优惠额度
        BigDecimal postPrice = BigDecimal.ZERO;         // 邮费

        for (BelowOrderProductDto belowOrderProductDto : list) {
            Integer count = belowOrderProductDto.getCount();//订单需求商品的数量

            Goods goods = goodsService.selectGoodsByGoodsNumber(belowOrderProductDto.getGoodsNumber(), lang);
            //判断商品有没有货
            if (goods.getStoreNumber() < belowOrderProductDto.getCount()) {
                return new ObjectResult("300", "货不够了", "");
            }
            totalPrice = totalPrice.add(goods.getGoodsPrice().multiply(new BigDecimal(count)));
        }
        actuallyPrice = actuallyPrice.add(totalPrice);
        discountPirce = totalPrice.subtract(actuallyPrice);

        //===========保存订单=======================
        try {
            Order order = new Order();
            order.setActual_payment(actuallyPrice);// 实际付款价格
            order.setTotal_price(totalPrice);      // 商品的原价
            order.setPreferential_price(discountPirce);// 优惠价格
            order.setPost_price(postPrice);        // 邮费
            order.setUser_id(user.getUser_guid());
            order.setCreate_time(new Date());
            order.setTra_status(PayStatus.unpaid.getValue());
            String orderNumber = OrderNoUtils.getOrderIdByTime();
            order.setOrder_no(orderNumber);
            order.setConsignee(user.getUsername());
            order.setPhone(user.getUsername());
            orderService.addOrder(order);// 保存订单表

            // 把新订单对象,放到rabbitMQ中去
            rabbitMqProducer.commitOrderQueue(order);

            // ---------------------------------------保存订单明细表-------------------------------------
            Order curOrder = orderService.selectOrderByOrderNo(orderNumber);// 这个就是上面保存的订单的对象
            //循环保存订单明细
            for (BelowOrderProductDto belowOrderProductDto : list) {
                Integer count = belowOrderProductDto.getCount();
                String goodsNumber = belowOrderProductDto.getGoodsNumber();

                OrderGoods orderGoods = new OrderGoods();
                Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumber, lang);
                GoodsComboDto goodsComboDto = new GoodsComboDto(goods);

                orderGoods.setOrderId(curOrder.getId());
                orderGoods.setCount(goodsComboDto.getCount());
                orderGoods.setGoodsNumber(goodsComboDto.getGoodsNumber());
                orderGoods.setGoodsPrice(goodsComboDto.getGoodsPrice());// 2中商品都有这个字段
                orderGoods.setDiscountPrice(goodsComboDto.getDiscountPrice());
                orderGoods.setGoodsType(goodsComboDto.getType());
                orderGoods.setGoodsName(goodsComboDto.getGoodsName());
                orderGoods.setImgUrl(goodsComboDto.getImgUrl());
                // 保存 商品订单明细表
                orderGoodsService.addOrderGoods(orderGoods);
            }

            return new ObjectResult("200", "下单成功", curOrder);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("立即下单失败了---", e);
        }
        return new ObjectResult("400", "立即下单失败了---", "");
    }

    /**
     * 获取visa支付，要的签名
     * @param orderNumber 订单的编号
     */
    @RequestMapping(value = "/getVisaPayMap", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> getVisaPayMap(String orderNumber) {

        Order order = orderService.selectOrderByOrderNo(orderNumber);
        // 根据订单的信息 获取visa签名
        HashMap<String, String> visaPayMap = visaPayComponent.getVisaPayMap(orderNumber, order.getActual_payment().toString());
        return visaPayMap;
    }

    /**
     * 银联支付的按钮(跳转到银联支付的页面)
     * @param order_no    订单编号
     * @param frontendUrl 银联支付的同步地址
     */
    @RequestMapping(value = "/easylinkPay", method = RequestMethod.POST)
    public void easylinkPay(HttpServletRequest request, HttpServletResponse response, String order_no, String frontendUrl) {
        try {
            easylinkPayComponnent.easylickPay(request, response, order_no, frontendUrl);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
