package com.bisa.health.shop.controller;

import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.client.entity.User;
import com.bisa.health.common.email.utils.SendMailUtils;
import com.bisa.health.common.utils.RandomUtils;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.component.EasylinkPayComponnent;
import com.bisa.health.shop.component.ShopCartComponent;
import com.bisa.health.shop.component.VisaPayComponent;
import com.bisa.health.shop.dto.GoodsComboDto;
import com.bisa.health.shop.dto.ShopCartCommitDto;
import com.bisa.health.shop.enumerate.GoodsTypeEnum;
import com.bisa.health.shop.enumerate.NeedPostType;
import com.bisa.health.shop.model.*;
import com.bisa.health.shop.mqtt.RabbitMqProducer;
import com.bisa.health.shop.service.IAddressService;
import com.bisa.health.shop.service.IComboService;
import com.bisa.health.shop.service.IGoodsService;
import com.bisa.health.shop.service.IOrderGoodsService;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.utils.InternationalizationUtil;
import com.bisa.health.shop.utils.OrderNoUtils;
import com.bisa.health.shop.utils.TokenCreateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单controller
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/user")
public class OrderController {

    private Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private RabbitMqProducer rabbitMqProducer;
    @Autowired
    private VisaPayComponent visaPayComponent;
    @Autowired
    private EasylinkPayComponnent easylinkPayComponnent;
    @Autowired
    private ShopCartComponent shopCartComponent;
    @Autowired
    private IComboService comboService;

    @Value("${deploy_location_mainland}")
    private String deploy_location_mainland;
    @Value("${visa_pay_url}")
    private String visa_pay_url;
    @Value("${full_free_post}")
    private String freePost;
    @Value("${fixed_post}")
    private String fixedPost;
    @Value("${frontendUrl}")
    private String frontendUrl;

    private static final String BUYER_CART = "cart";

    /**
     * 商品详情页 - 立即购买按键  - 入口 (这里还没有考虑商品的数量，前台没数量的按钮)
     * @param goodsGuid 商品的编号/套餐的编号
     * @param goodsType 单品/套餐
     */
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
    public String confirmOrder(String goodsGuid, Integer goodsType, Model model, HttpSession session) {
        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        BigDecimal totalPrice = BigDecimal.ZERO;        // 所有商品总价
        BigDecimal actuallyPrice = BigDecimal.ZERO;     // 订单实际总价
        BigDecimal discountPirce = BigDecimal.ZERO;     // 订单总优惠额度
        BigDecimal postPrice = BigDecimal.ZERO;         // 邮费
        Integer totalCount = 0;                         // 所有商品总数量

        List<GoodsComboDto> goodsComboDtoList = new ArrayList<>();
        int needPost = NeedPostType.no_post.getValue(); // 默认不用邮递

        if (goodsType == GoodsTypeEnum.single.getValue()) {  // 实体单品
            Goods goods = goodsService.selectGoodsByGoodsNumber(goodsGuid, lang);
            totalPrice = goods.getGoodsPrice();
            actuallyPrice = goods.getGoodsPrice();
            discountPirce = totalPrice.subtract(actuallyPrice);
            totalCount++;
            goodsComboDtoList.add(new GoodsComboDto(goods));
            needPost = NeedPostType.need_post.getValue();

        } else if (goodsType == GoodsTypeEnum.combo.getValue()) {//套餐(就是和实体)
            List<Combo> combos = comboService.selectComboByGuid(goodsGuid);
            for (Combo combo : combos) {
                totalPrice = totalPrice.add(combo.getGoodsPrice());
                actuallyPrice = actuallyPrice.add(combo.getDiscountPrice());
                totalCount++;
                goodsComboDtoList.add(new GoodsComboDto(combo));

                if (combo.getNeedPost() == NeedPostType.need_post.getValue()) {
                    needPost = combo.getNeedPost();
                }
            }
            discountPirce = totalPrice.subtract(actuallyPrice);

        } else if (goodsType == GoodsTypeEnum.virtual.getValue()) {//虚拟商品
            Goods goods = goodsService.selectGoodsByGoodsNumber(goodsGuid, lang);
            totalPrice = goods.getGoodsPrice();
            actuallyPrice = goods.getGoodsPrice();
            discountPirce = totalPrice.subtract(actuallyPrice);
            totalCount++;
            goodsComboDtoList.add(new GoodsComboDto(goods));
        }

        // 低于免邮额度，且商品需要邮递
        if (actuallyPrice.compareTo(new BigDecimal(freePost)) == -1 && needPost == NeedPostType.need_post.getValue()) {
            postPrice = new BigDecimal(fixedPost); // 邮费
        }
        actuallyPrice = actuallyPrice.add(postPrice);// 付款价格 要加上 邮费

        model.addAttribute("goodsList", goodsComboDtoList);
        model.addAttribute("totalCount", totalCount); //商品件数
        model.addAttribute("totalPrice", totalPrice); //订单总价
        model.addAttribute("discountPirce", discountPirce); //总优惠
        model.addAttribute("actuallyPrice", actuallyPrice);//订单实际价格
        model.addAttribute("needPost", needPost); //是否免邮
        model.addAttribute("postPrice", postPrice); //邮费

        //这里把商品的信息放到session中取
        JSONObject confirmOrder = new JSONObject();
        confirmOrder.put("goodsComboDtoList", goodsComboDtoList);
        confirmOrder.put("totalPrice", totalPrice);
        confirmOrder.put("discountPirce", discountPirce);
        confirmOrder.put("actuallyPrice", actuallyPrice);
        confirmOrder.put("postPrice", postPrice);
        session.setAttribute("confirmOrder", confirmOrder);

        // 生成令牌防止表单重复提交
        String token = TokenCreateUtils.generateToken(RandomUtils.randomSixNum());
        model.addAttribute("token", token);

        // 表示配置 到大陆的服务器
        if (deploy_location_mainland.equals("true")) {
            return "order/order";
        } else {
            return "order/en_order";
        }
    }

    /**
     * 购物车去结算 --入口
     * @param productData
     */
    @RequestMapping(value = "/shopCartConfirmOrder", method = RequestMethod.POST)
    public String shopCartConfirmOrder(@CurrentUser User sysUser, ModelMap model, String productData, HttpSession session) {

        // 用户收货地址
        List<Address> addresseList = addressService.selectAddressListByUserId(sysUser.getUser_guid());
        model.addAttribute("addresseList", addresseList);

        BigDecimal totalPrice = BigDecimal.ZERO;         // 所有商品总价
        BigDecimal actuallyPrice = BigDecimal.ZERO;     // 所有商品 应付价格
        Integer totalCount = 0;                         // 所有商品总数量
        BigDecimal postPrice = new BigDecimal(0); // 邮费

        List<GoodsComboDto> goodsComboDtoList = new ArrayList<GoodsComboDto>();
        JSONArray jsonArray = JSONArray.fromObject(productData);// 把String转换为json
        List<ShopCartCommitDto> list = JSONArray.toList(jsonArray, ShopCartCommitDto.class);
        Set<Integer> shopCartIndexSet = new HashSet<Integer>();
        int needPost = 0; //是否需要邮递

        // 获得jsonArray的第一个元素
        for (ShopCartCommitDto shopCartCommitDto : list) {
            shopCartIndexSet.add(shopCartCommitDto.getCartIndex());
            GoodsComboDto goodsComboDto = shopCartCommitDto.getGoodsComboDto();
            int count = shopCartCommitDto.getCount();

            totalPrice = totalPrice.add(goodsComboDto.getGoodsPrice().multiply(new BigDecimal(count))); // 订单总价原价
            totalCount += count; // 商品数量
            actuallyPrice = actuallyPrice.add(goodsComboDto.getDiscountPrice().multiply(new BigDecimal(count))); // 优惠后的订单总价

            goodsComboDto.setCount(count); // 购物车中商品数量
            goodsComboDtoList.add(goodsComboDto);
            if (goodsComboDto.getNeedPost() == 1) {
                needPost += 1;
            }
        }
        BigDecimal discountPirce = totalPrice.subtract(actuallyPrice); // 所有商品
        // 优惠价格
        if (new BigDecimal(freePost).compareTo(actuallyPrice) == 1 && needPost != 0) { // 低于免邮额度，且商品需要邮递
            postPrice = new BigDecimal(fixedPost); // 邮费
        }
        actuallyPrice = actuallyPrice.add(postPrice);// 付款价格 要加上 邮费

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("postPrice", postPrice);
        model.addAttribute("discountPirce", discountPirce);
        model.addAttribute("actuallyPrice", actuallyPrice);
        model.addAttribute("goodsList", goodsComboDtoList);
        model.addAttribute("needPost", needPost);

        //这里把商品的信息放到session中取
        JSONObject confirmOrder = new JSONObject();
        confirmOrder.put("goodsComboDtoList", goodsComboDtoList);
        confirmOrder.put("totalPrice", totalPrice);
        confirmOrder.put("discountPirce", discountPirce);
        confirmOrder.put("actuallyPrice", actuallyPrice);
        confirmOrder.put("postPrice", postPrice);
        session.setAttribute("confirmOrder", confirmOrder);

        //用于提交订单，删除购物车数据
        session.setAttribute("shopCartIndexSet", shopCartIndexSet);
        // 生成令牌防止表单重复提交
        String token = TokenCreateUtils.generateToken(RandomUtils.randomSixNum());
        model.addAttribute("token", token);

        // 表示配置 到大陆的服务器
        if (deploy_location_mainland.equals("true")) {
            return "order/order";
        } else {
            return "order/en_order";
        }
    }

    /**
     * 确认订单页面 -- 立即下单
     * @param logistics_index 选择物流公司的索引
     * @param address_id      地址id
     * @param token           用于判断表单重复提交的token
     * @param email           账单邮件地址
     * @param postCompany     香港、国际物流公司
     * @param postAccount     香港、国际订单到付，物流付费账号
     * @return
     */
    @RequestMapping(value = "/commitOrder", method = RequestMethod.POST)
    @ResponseBody
    public String commitOrder(Integer address_id, String token, HttpSession session, @CurrentUser User user,
                              @RequestParam(required = false) Integer logistics_index,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String postCompany,
                              @RequestParam(required = false) String postAccount) {

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        //获取商品详情页面直接下单的数据
        JSONObject jsonObject = (JSONObject) session.getAttribute("confirmOrder");
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("goodsComboDtoList"));//转换成json集合
        List<GoodsComboDto> list = JSONArray.toList(jsonArray, GoodsComboDto.class);

        //循环判断订单明细的商品数量和库存对比
        for (GoodsComboDto goodsComboDto : list) {
            Goods goods = goodsService.selectGoodsByGoodsNumber(goodsComboDto.getGoodsNumber(), lang);
            if (goods.getStoreNumber() < goodsComboDto.getCount()) {
                return "300";//表示库存不够
            }
        }

        //判断订单重复的问题
        String preToken = (String) session.getAttribute("token");
        if (StringUtils.isEmpty(preToken)) {
            preToken = "";
        }
        if (preToken.equals(token)) {
            // 重复提交表单
            return "102";
        }

        //这样要考虑下用户选择了那种快递公司(有到付/有在线付款的)(1,2,3 是在线付款的物流)
        BigDecimal actuallyPrice = BigDecimal.ZERO;
        BigDecimal postPrice = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal discountPirce = BigDecimal.ZERO;

        //这个就是国内支付的时候的情况 和 香港在线支付的情况
        if (logistics_index == null || logistics_index <= 3) {
            postPrice = new BigDecimal(jsonObject.getString("postPrice"));
            actuallyPrice = new BigDecimal(jsonObject.getString("actuallyPrice"));
            totalPrice = new BigDecimal(jsonObject.getString("totalPrice"));
            discountPirce = new BigDecimal(jsonObject.getString("discountPirce"));

        } else if (logistics_index > 3) {
            //香港到付的情况
            totalPrice = new BigDecimal(jsonObject.getString("totalPrice"));
            actuallyPrice = new BigDecimal(jsonObject.getString("actuallyPrice")).subtract(new BigDecimal(jsonObject.getString("postPrice")));
            discountPirce = new BigDecimal(jsonObject.getString("discountPirce"));
        }

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

            // 已填账单邮件地址，无则不通知
            if (!org.springframework.util.StringUtils.isEmpty(email)) {
                String nickName = null; // 后期加上权限登录之后，可以直接从注解获得
                String href = "hk-shop.bisahealth.com/user/orderDetail?order_no=" + order.getOrder_no();
                SendMailUtils.getInstance().sendBillEmail(email, nickName, order.getOrder_no(), order.getCreate_time(), href, 4);
            }
            // 获取用户的 收货地址对象
            Address address = addressService.selectAddressByAddressId(address_id);
            order.setConsignee(address.getConsignee());
            order.setPhone(address.getPhone());
            order.setDetail_address(address.getProvince() + " " + address.getCity() + " " + address.getCounty() + " " + address.getDetail_address());
            order.setOrderEmail(email); //账单邮件地址
            order.setLogisticsAccount(postAccount); //香港和国际邮费到付订单（物流公司付费账号）
            order.setLogistics_name(postCompany); //香港和国际邮费订单（物流公司名称）
            orderService.addOrder(order);// 保存订单表

            // 把新订单对象,放到rabbitMQ中去
            rabbitMqProducer.commitOrderQueue(order);

            // ---------------------------------------保存订单明细表-------------------------------------
            Order curOrder = orderService.selectOrderByOrderNo(orderNumber);// 这个就是上面保存的订单的对象
            //循环保存订单明细
            for (GoodsComboDto goodsComboDto : list) {
                OrderGoods orderGoods = new OrderGoods();
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

            // 删除购物车数据
            Set<Integer> shopCartIndexSet = (Set<Integer>) session.getAttribute("shopCartIndexSet");
            if (shopCartIndexSet != null) {
                String redisKey = BUYER_CART + ":" + user.getUser_guid();
                for (Integer shopCartIndex : shopCartIndexSet) {
                    shopCartComponent.deleteShopCartsInRedis(redisKey, shopCartIndex);
                }
                session.removeAttribute("shopCartIndexSet");
            }
            session.setAttribute("token", token); // 提交订单成功 ，将token放到session
            session.setAttribute("orderNumber", orderNumber); // 订单编号，下个方法使用

            return "200";
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("立即下单失败了---", e);
        }
        return "400";
    }

    /**
     * 跳转到 订单提交成功页(就是订单已经创建成功了，扫码操作页面)
     */
    @RequestMapping(value = "/payMentPage", method = RequestMethod.GET)
    public String payMentPage(HttpSession session, Model model, HttpServletRequest request, @CurrentUser User user) {

        // 获取上一个方法的 订单编号
        String orderNumber = (String) session.getAttribute("orderNumber");
        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        // 这里是用户的个人中心 订单页面 点击付款的按钮的
        String order_no = request.getParameter("order_no");
        if (order_no != null) {
            orderNumber = order_no;
        }

        // 根据商品 编号查询订单
        Order order = orderService.selectOrderByOrderNo(orderNumber);
        // 根据订单的id 查下面的订单详情
        List<OrderGoods> OrderGoodsList = orderGoodsService.selectOrderGoodsListByOrderId(order.getId(), lang);
        OrderGoods orderGoods = OrderGoodsList.get(0);

        model.addAttribute("order", order);
        model.addAttribute("orderNumber", orderNumber);
        model.addAttribute("orderGoods", orderGoods);
        model.addAttribute("user_guid", user.getUser_guid());

        if (deploy_location_mainland.equals("true")) {// 表示配置 到大陆的服务器
            return "order/zh_payment";
        } else {
            //visa支付的地址
            model.addAttribute("visaPayUrl", visa_pay_url);
            return "order/en_payment";
        }
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
     * @param order_no 订单编号
     */
    @RequestMapping(value = "/easylinkPay", method = RequestMethod.POST)
    public void easylinkPay(HttpServletRequest request, HttpServletResponse response, String order_no) {
        try {
            easylinkPayComponnent.easylickPay(request, response, order_no, frontendUrl);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 银联支付成功后的同步方法
     */
    @RequestMapping(value = "/easylinkPay/return_url", method = RequestMethod.POST)
    public String return_url(ModelMap modelMap, HttpServletRequest request) {
        String result = null;
        try {
            result = easylinkPayComponnent.payCompleted(modelMap, request);

        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
            return "order/payCompleted";
        }
        return result;

    }

    /**
     * 支付成功   跳转到支付成功的页面
     */
    @RequestMapping(value = "/orderSuccess", method = RequestMethod.GET)
    public String orderSuccess(String order_no, Model model, HttpSession session) {
        String orderNumber = (String) session.getAttribute("orderNumber");// 到支付成功了，订单的信息可以删除了
        if (orderNumber != null) {
            session.removeAttribute("orderNumber");
        }
        model.addAttribute("order_no", order_no);
        return "order/success";
    }

    /**
     * 重复提交订单跳转页面
     */
    @RequestMapping(value = "/payRepetition", method = RequestMethod.GET)
    public String payRepetition() {
        return "order/payRepetition";
    }

}
