package com.bisa.health.shop.controller;

import com.bisa.health.app.enumerate.LoginTypeEnum;
import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.dto.OrderAppraiseDto;
import com.bisa.health.shop.dto.UserAppraiseDto;
import com.bisa.health.shop.enumerate.GoodsClassify;
import com.bisa.health.shop.enumerate.GoodsTypeEnum;
import com.bisa.health.shop.model.*;
import com.bisa.health.shop.service.IComboService;
import com.bisa.health.shop.service.IGoodsComboService;
import com.bisa.health.shop.service.IGoodsImgService;
import com.bisa.health.shop.service.IGoodsService;
import com.bisa.health.shop.service.IOrderGoodsService;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.service.IUserAppraiseService;
import com.bisa.health.shop.utils.InternationalizationUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评价控制器 (商品评价  用户评价)
 */

@Controller
public class AppraiseController {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private IGoodsImgService goodsImgService;
    @Resource
    private IOrderService orderService;
    @Resource
    private IOrderGoodsService orderGoodsService;
    @Resource
    private IUserAppraiseService userAppraiseService;
    @Resource
    private IComboService comboService;
    @Resource
    private IGoodsComboService goodsComboService;

    /**
     * 用户评价(还没有使用)
     * @return
     */
    @RequestMapping(value = "/user/userAppraise", method = RequestMethod.GET)
    public String userAppraise() {
        return "order-center/userAppraise";
    }

    /**
     * 根据商品编号,获取所有用户评价
     * @param goodsNumber 商品的编号
     */
    @RequestMapping(value = "/web/call/appraiseRest", method = RequestMethod.GET)
    @ResponseBody
    public Object appraiseRest(HttpServletRequest request, String goodsNumber) {

        String pageOffset = request.getParameter("page"); // 分页页码0,10,20
        String limit = request.getParameter("limit"); // 每页显示数量
        // 设置分页
        int page_offset = 0, page_limit = 0;
        if (pageOffset != null) {
            page_offset = Integer.parseInt(pageOffset); // 0,10,20
        }
        if (limit != null) {
            page_limit = Integer.parseInt(limit); // 默认10， 从js文件中设置
        }
        SystemContext.setPageOffset(page_offset);
        SystemContext.setPageSize(page_limit);
        SystemContext.setSort("appraise_one_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");
        Pager<UserAppraise> appraisePager = userAppraiseService.getPagerAppraiseByGoodsNumber(goodsNumber);
        return appraisePager;
    }

    /**
     * 订单评价入口
     */
    @RequestMapping(value = "/user/orderAppraise", method = RequestMethod.GET)
    public String orderAppraise() {
        return "order-center/orderAppraise";
    }

    /**
     * 订单评价页面  加载数据
     * @param order_no 订单的编号
     */
    @RequestMapping(value = "/user/orderAppraiseRest", method = RequestMethod.GET)
    @ResponseBody
    public Object orderAppraiseRest(String order_no, HttpSession session) {

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        Order order = orderService.selectOrderByOrderNo(order_no);
        if (order.getTra_status().equals(PayStatus.appraise_one.getValue())) {
            //已经评价过
            return null;
        }
        //根据订单号查出订单详情
        List<OrderGoods> orderGoodsList = orderGoodsService.selectOrderGoodsListByOrderId(order.getId(), lang);
        for (OrderGoods orderGoods : orderGoodsList) {
            if (orderGoods.getGoodsType() == GoodsTypeEnum.combo.getValue()) {
                //套餐或激活卡
                Combo combo = comboService.selectComboByGuid(orderGoods.getGoodsNumber()).get(0);
                //虚拟服务
                if (combo.getClassifyId() == GoodsClassify.COUNT.getValue() || combo.getClassifyId() == GoodsClassify.TIME.getValue()) {
                    //评论对象为虚拟服务卡的父级商品
                    GoodsCombo goodsCombo = goodsComboService.getGoodsComboByGuid(combo.getComboGuid());
                    orderGoods.setGoodsNumber(goodsCombo.getGoodsNumber());
                } else {
                    //实体商品
                    orderGoods.setGoodsNumber(combo.getGoodsNumber());
                }
            }
        }
        return orderGoodsList;
    }

    /**
     * 发表评论
     * @param appraise_data 评论的数据
     * @param order_no      订单编号
     */
    @RequestMapping(value = "/user/commitAppraise", method = RequestMethod.POST)
    @ResponseBody
    public String commitAppraise(@CurrentUser User sysUser,String appraise_data, String order_no) {

        //[{"goodsNumber":"123741238SD","appraise":"好用","rateValue":"4"},{"goodsNumber":"121232378SD","appraise":"还可以","rateValue":"3"}]
        try {
            JSONArray jsonArray = JSONArray.fromObject(appraise_data);
            if (jsonArray.size() == 0) {
                return ExceptionCodeEnum.FAILURE.getValue();
            }

            //获得用户昵称或用户名
            String nickName = sysUser.getUsername();
            //匿名处理
            nickName = nickName.substring(1, nickName.length()) + "***" + nickName.substring(nickName.length() - 1, nickName.length());

            for (Object object : jsonArray) {
                JSONObject jsonObject = JSONObject.fromObject(object);
                OrderAppraiseDto orderAppraiseDto = (OrderAppraiseDto) JSONObject.toBean(jsonObject, OrderAppraiseDto.class);

                UserAppraise userAppraise = new UserAppraise();
                userAppraise.setUserGuid(sysUser.getUser_guid());
                userAppraise.setGoodsNumber(orderAppraiseDto.getGoodsNumber());
                userAppraise.setAppraiseOne(orderAppraiseDto.getAppraise());
                userAppraise.setAppraiseOneTime(new Date());
                userAppraise.setAppraiseDegree(orderAppraiseDto.getRateValue());
                userAppraise.setUsername(nickName);
                userAppraise.setUserAvator(sysUser.getUriPic());

                userAppraiseService.addUserAppraise(userAppraise);
                goodsService.updateGoodsAppraiseNumber(orderAppraiseDto.getGoodsNumber(), 1);
            }

            //修改订单状态
            Order order = orderService.selectOrderByOrderNo(order_no);
            order.setTra_status(PayStatus.appraise_one.getValue());
            orderService.updateOrderStatus(order_no, PayStatus.appraise_one.getValue());

            return ExceptionCodeEnum.SUCCESS.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 评价列表
     */
    @RequestMapping(value = "/user/userAppraiseList", method = RequestMethod.GET)
    public String userAppraiseList() {
        return "order-center/userAppraiseList";
    }

    /**
     * 加载，用户评价列表，数据
     * @param page  页码
     * @param limit 每页的数量
     */
    @RequestMapping(value = "/user/userAppraiseListRest", method = RequestMethod.GET)
    @ResponseBody
    public Object userAppraiseListRest(@CurrentUser User sysUser, Integer page, Integer limit, HttpSession session) {

        SystemContext.setPageOffset(page);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("appraise_one_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        try {
            Pager<UserAppraise> appraisePager = userAppraiseService.getPagerAppraiseByUserGuid(sysUser.getUser_guid());
            List<UserAppraiseDto> userAppraiseDtoList = new ArrayList<>();

            for (UserAppraise appraise : appraisePager.getDatas()) {
                Goods goods = goodsService.selectGoodsByGoodsNumber(appraise.getGoodsNumber(), lang);
                String mainPic = goodsImgService.loadMainPicByGoodNumber(appraise.getGoodsNumber(), lang);
                UserAppraiseDto userAppraiseDto = new UserAppraiseDto(appraise, goods.getGoodsName(), goods.getGoodsNumber(), mainPic);
                userAppraiseDtoList.add(userAppraiseDto);
            }

            Pager<UserAppraiseDto> userAppraiseDtoPager = new Pager<UserAppraiseDto>();
            userAppraiseDtoPager.setDatas(userAppraiseDtoList);
            userAppraiseDtoPager.setTotal(appraisePager.getTotal());
            userAppraiseDtoPager.setSize(appraisePager.getSize());

            return userAppraiseDtoPager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加追评
     * @param id          评论表的id
     * @param appraiseTwo 追评的内容
     * @return
     */
    @RequestMapping(value = "/user/commitAppraiseTwo", method = RequestMethod.POST)
    @ResponseBody
    private String commitAppraiseTwo(Integer id,String appraiseTwo) {

        if (!StringUtils.isEmpty(appraiseTwo)) {
            UserAppraise userAppraise = userAppraiseService.getAppraiseById(id);
            userAppraise.setAppraiseTwo(appraiseTwo);
            userAppraise.setAppraiseTwoTime(new Date());

            boolean result = userAppraiseService.updateUserAppraise(userAppraise);
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

}
