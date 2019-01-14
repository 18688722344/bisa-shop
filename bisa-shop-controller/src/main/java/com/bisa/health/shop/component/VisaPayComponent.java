package com.bisa.health.shop.component;

import com.bisa.health.common.pay.utils.VisaCreatSign;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

/**
 * visa支付的组件
 * @author Administrator
 */
@Component
public class VisaPayComponent {
    @Autowired
    private IOrderService orderService;

    @Value("${visa_secret_key}")
    private String secret_key;
    @Value("${visa_access_key}")
    private String access_key;
    @Value("${visa_profile_id}")
    private String profile_id;
    @Value("${visa_currency}")
    private String currency;

    /**
     * 获得visa支付参数map
     */
    public HashMap<String, String> getVisaPayMap(String order_no, String price) {
        HashMap<String, String> treeMap = new HashMap<String, String>();
            //根据订单编号获取订单的基本信息
        System.out.println(order_no+">>>>>>>>>>>订单号");
        Order order = orderService.selectOrderByOrderNo(order_no);
        String bill_to_forename = "noreal";
        String bill_to_surname = "name";
        String bill_to_email = "null@cybersource.com";
        String bill_to_address_country = "US";
        String bill_to_address_state = "CA";
        String bill_to_address_city = "Mountain View";
        String bill_to_address_line1 = "1295 Charleston Rd";
        String bill_to_address_postal_code = "94043";
        treeMap.put("access_key", access_key);
        treeMap.put("profile_id", profile_id);
        treeMap.put("transaction_uuid", UUID.randomUUID().toString());
        treeMap.put("signed_field_names",
                "reference_number,access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,amount,currency");
        treeMap.put("unsigned_field_names",
                "bill_to_address_line1,bill_to_address_city,bill_to_address_country,bill_to_surname,bill_to_email,bill_to_address_state,bill_to_address_postal_code,bill_to_forename");
        treeMap.put("signed_date_time", VisaCreatSign.getUTCDateTime());
        treeMap.put("locale", "en");
        treeMap.put("reference_number", order_no);
        //treeMap.put("amount", "2");             // "商品价格"
        treeMap.put("amount", order.getActual_payment().toString());             // "商品价格"
        treeMap.put("currency", currency);      // 货币 单位(HKD：港币，USD：美元)
        treeMap.put("transaction_type", "sale");// 授权 authorization

        treeMap.put("bill_to_surname", bill_to_surname);
        treeMap.put("bill_to_forename", bill_to_forename);
        treeMap.put("bill_to_address_line1", bill_to_address_line1);
        treeMap.put("bill_to_email", bill_to_email);
        treeMap.put("bill_to_address_city", bill_to_address_city);
        treeMap.put("bill_to_address_country", bill_to_address_country);
        treeMap.put("bill_to_address_postal_code", bill_to_address_postal_code);
        treeMap.put("bill_to_address_state", bill_to_address_state);

        String signature;
        try {
            signature = VisaCreatSign.sign(treeMap, secret_key);
        } catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        treeMap.put("signature", signature);
        return treeMap;
    }

}
