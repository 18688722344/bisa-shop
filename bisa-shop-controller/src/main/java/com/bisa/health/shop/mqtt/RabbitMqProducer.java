package com.bisa.health.shop.mqtt;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * 消息生产者
 * @author Administrator
 */

@Component
public class RabbitMqProducer {

    @Autowired
    private AmqpTemplate AmqpTemplate;

    @Value("${exchange1}")
    private String exchange1;
    @Value("${routingkey1}")
    private String routingkey1;

    @Value("${exchange3}")
    private String exchange3;
    @Value("${routingkey3}")
    private String routingkey3;
    
    final Gson gson=new Gson();

    private Logger logger = LogManager.getLogger(RabbitMqProducer.class);

    /**
     * 确认订单的消息队列(设置 24小时)
     * @param message order对象
     */
    public void commitOrderQueue(Object message) throws IOException {
        //把 java对象---> json对象 --->byte[]  (消费的时候 的类型就是byte[])
        byte[] orderBytes =gson.toJson(message).getBytes();
        AmqpTemplate.convertAndSend(exchange1, routingkey1, orderBytes);

        logger.debug("下单缓存队列-------->" + message.toString());
    }

    /**
     * 确认订单  --》  发完短信之后 ---》 把没有付款的order 放到队列中来
     * @param message order对象
     */
    public void obligationOrderQueue(Object message) throws IOException {
        //把 java对象---> json对象 --->byte[]  (消费的时候 的类型就是byte[])
        byte[] obligationOrderBytes = gson.toJson(message).getBytes();
        AmqpTemplate.convertAndSend(exchange3, routingkey3, obligationOrderBytes);

        logger.debug("取消订单队列-------->" + message.toString());
    }

}
