package com.bisa.health.shop.component;

import com.bisa.health.shop.dto.PayNotifyResponse;
import com.bisa.health.shop.dto.PayRequest;
import com.bisa.health.shop.dto.PayResponse;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.utils.EasylinkSignatureUtil;
import com.bisa.health.shop.utils.HttpClientPost;
import com.bisa.health.shop.utils.OrderNoUtils;
import com.bisa.health.shop.utils.SignatureUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 银联支付组件
 * @author Administrator
 */

@Component
public class EasylinkPayComponnent {

    public static Logger orderManager = LogManager.getLogger("orderManager");
    public static Logger orderState = LogManager.getLogger("orderState");
    public static Logger logger = LogManager.getLogger(EasylinkPayComponnent.class);
    @Autowired
    private IOrderService orderService;
    @Value("${channel_url}")
    private String url;
    @Value("${pin}")
    private String pin;
    @Value("${secPin}")
    private String secPin;
    @Value("${channel}")
    private String channel;
    @Value("${scretKey}")
    private String secretKey;
    @Value("${callbackUrl}")
    private String callbackUrl;

    final Gson gson=new Gson();
    /**
     * 银联支付组件
     * @param order_no
     * @throws IOException
     * @throws ServletException
     */
    public void easylickPay(HttpServletRequest request, HttpServletResponse response, String order_no, String frontendUrl)
            throws ServletException, IOException {


        //根据订单编号获取订单的基本信息
        Order order = orderService.selectOrderByOrderNo(order_no);
        PayRequest payRequest = new PayRequest();
        payRequest.setPin(pin);
        payRequest.setSecPin(secPin);
        payRequest.setAmount(order.getActual_payment().toString());
        //payRequest.setAmount("1");
        String stringDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreate_time());
        payRequest.setOrderCreateTime(stringDate);

        String orderId=order.getOrder_no()+ "-" + OrderNoUtils.getOrderRandom();
        payRequest.setOrderId(orderId);
        payRequest.setCallbackUrl(callbackUrl);
        payRequest.setFrontendUrl(frontendUrl);
        payRequest.setChannel(channel);
        payRequest.setCustomerIp(getRemoteAddress(request));

        // 设置签名信息
        payRequest.setAccessKey(EasylinkSignatureUtil.generateSignature(payRequest, secretKey));
        // 将bean转换为map
        Map<String, String> data = SignatureUtil.beanToMap(payRequest);

        String resp = null;
        try {
            resp = HttpRequest.post(url).trustAllCerts().trustAllHosts().form(data).connectTimeout(60 * 1000).readTimeout(60 * 1000).body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resp == null) {
            request.setAttribute("message", "连接渠道通讯失败");
            request.getRequestDispatcher("/WEB-INF/jsp/order/message.jsp").forward(request, response);
            return;
        }
        PayResponse payResponse =gson.fromJson(resp, PayResponse.class);
        //保存log信息
        orderManager.info("订单编号："+orderId+";"+"银联的订单编号："+payResponse.getPaymentId()+";"+"支付状态："+payResponse.getStatus()+"(0未支付，1已支付)");
        if (payResponse.getRespCode() != null && !"00".equals(payResponse.getRespCode())) {
            request.setAttribute("message", payResponse.getRespCode() + ":" + payResponse.getRespMsg());
            request.getRequestDispatcher("/WEB-INF/jsp/order/message.jsp").forward(request, response);
            return;
        }

        try {
            //银联支付的时候会自动生成一个PaymentId 作为商戶對賬凭证  要保存到数据库中，异步回调，查账，退款 可 以用
            orderService.updateOrderPaymentIdByOrderNo(order_no, payResponse.getPaymentId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "保存PaymentId失败，系统异常");

            request.getRequestDispatcher("/WEB-INF/jsp/order/message.jsp").forward(request, response);
        }

        HttpClientPost poster = new HttpClientPost(response, payResponse.getFormData());
        // 请请求信息发送至渠道进行支付处理
        poster.sendByPost(payResponse.getUrl());
    }

    // 請求地址
    private String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }

    /**
     * 银联支付后的同步操作组件
     * @param modelMap
     * @param request
     * @return
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String payCompleted(ModelMap modelMap, HttpServletRequest request)
            throws IOException, IllegalAccessException, InvocationTargetException {

        Map<String, String> requestMap = EasylinkSignatureUtil.buildRequestParameters(request);
        PayNotifyResponse payNotifyResponse = new PayNotifyResponse();
        BeanUtils.populate(payNotifyResponse, requestMap);
        // 交易回调进行验签处理
        String signature = EasylinkSignatureUtil.generateSignature(payNotifyResponse, secretKey);
        orderState.info("银联的订单编号："+payNotifyResponse.getPaymentId()+"; 支付状态"+payNotifyResponse.getStatus()+";（0未支付，1已支付） 支付金额"+payNotifyResponse.getSettleAmount());
        logger.info("同步交易状态为1，表示支付成功了--->" + payNotifyResponse.getStatus());
        if (signature.equals(payNotifyResponse.getAccessKey()) && payNotifyResponse.getStatus().equals("1")) {
        } else {
            return "order/payCompleted";
        }
        return "order/payCompleted";
    }

}
