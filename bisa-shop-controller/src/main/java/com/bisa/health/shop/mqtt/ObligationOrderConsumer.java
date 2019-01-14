package com.bisa.health.shop.mqtt;

import com.bisa.health.common.email.utils.SendMailUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.service.IOrderService;
import com.google.gson.Gson;

/**
 * 取消订单 的消费
 */

@Component
public class ObligationOrderConsumer implements MessageListener {

    private Logger logger = LogManager.getLogger(ObligationOrderConsumer.class);

    @Autowired
    private IOrderService orderService;
    
    final Gson gson=new Gson();

    @Override
    public void onMessage(Message message) {
        try {

            // byte[] -- > string类型
            String jsonString = new String(message.getBody());
            Order queueOrder =gson.fromJson(jsonString, Order.class);

            // 在这里判断 订单有没有付款 (没有付款就 取消订单)
            Order order = orderService.selectOrderByOrderNo(queueOrder.getOrder_no());

            if (order.getTra_status() == PayStatus.unpaid.getValue()) {
                orderService.updateOrderStatus(order.getOrder_no(), PayStatus.close.getValue());
                //这里发个取消订单的邮件
                if (!org.springframework.util.StringUtils.isEmpty(order.getOrderEmail())) { // 已填账单邮件地址，无则不通知
                    String nickName = null; // 后期加上权限登录之后，可以直接从注解获得
                    String href = "hk-shop.bisahealth.com/user/orderDetail?order_no=" + order.getOrder_no();
                    SendMailUtils.getInstance().sendBillEmail(order.getOrderEmail(), nickName, order.getOrder_no(), order.getCreate_time(), href, 5);
                }
                logger.debug("订单关闭了------>" + queueOrder.getOrder_no());
            }
        } catch (Exception e) {
            logger.info("取消订单 失败了--->", e);
        }
    }

}
