package com.bisa.health.shop.mqtt;

import com.bisa.health.app.enumerate.LoginTypeEnum;
import com.bisa.health.common.app.service.ISmsService;
import com.bisa.health.common.email.utils.SendMailUtils;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.service.IOrderService;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.text.GapContent;

import java.util.List;

/**
 * 下单的消费
 */

@Component
public class OrdersConsumer implements MessageListener {

    private Logger logger = LogManager.getLogger(OrdersConsumer.class);

    @Value("${check_order_url}")
    private String check_order_url;
    @Resource
    private RabbitMqProducer producer;
    @Resource
    private IOrderService orderService;
    @Resource
    private ISmsService smsService;
    
    final Gson gson=new Gson();

    @Override
    public void onMessage(Message message) {
        try {

            //byte[] -- >   string类型
            String jsonString = new String(message.getBody());
            //解析成一个 order对象
            Order queueOrder =gson.fromJson(jsonString, Order.class);

            //在这里判断 订单有没有付款 (没有付款就 发短信)
            Order order = orderService.selectOrderByOrderNo(queueOrder.getOrder_no());
            if (order.getTra_status() == PayStatus.unpaid.getValue()) {

                //12小时内未付款发送邮件通知
                if (!StringUtils.isEmpty(order.getOrderEmail())) {
                    // 已填账单邮件地址，无则不通知
                    String nickName = null;
                    // 后期加上权限登录之后，可以直接从注解获得
                    String href = "hk-shop.bisahealth.com/user/orderDetail?order_no=" + order.getOrder_no();
                    SendMailUtils.getInstance().sendBillEmail(order.getOrderEmail(), nickName, order.getOrder_no(), order.getCreate_time(), href, 4);
                }

//                List<UserAuths> userList = userService.listUserGuid(order.getUser_id());
//                UserAuths user = UserAuths.getUserAuthsByLtype(userList, LoginTypeEnum.PHONE);
//                if (user != null) {
//                    //[悉心康健]您有一个订单尚未支付，12个小时后尚未付款将自动关闭订单。点击以下链接登录商城进行支付:
//                    smsService.sendUnpaidTips(user.getPhonecode(), user.getUsername(), order.getOrder_no(), check_order_url);
//                }
                //再把没有付款的order放到 取消订单的队列
                producer.obligationOrderQueue(order);
            }
        } catch (Exception e) {
            logger.info("下单的消费失败了--->", e);
        }
    }

}
