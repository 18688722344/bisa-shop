package com.bisa.health.shop.admin.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bisa.health.shop.admin.service.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisa.health.pay.model.Trade;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.enums.AfterCheckType;
import com.bisa.health.shop.admin.enums.DepartmentTpye;
import com.bisa.health.shop.admin.model.SAfterForProgress;
import com.bisa.health.shop.admin.model.SAfterForRecord;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;


/**
 * 财务管理
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/finance")
@RequiresRoles("ROLE_ADMIN")
public class AdminFinanceController {

    @Autowired
    private IAdminTradeService tradeService;
    @Autowired
    private IAdminOrderService orderService;
    @Autowired
    private IAdminAfterForProgressService afterForProgressService;
    @Autowired
    private IAdminAfterForRecordService afterForRecordService;
    @Autowired
    private IAdminOrderGoodsService orderGoodsService;

    /**
     * 进去   财务交易   页面
     */
    @RequestMapping(value = "/tradeInfo", method = RequestMethod.GET)
    public String tradeInfoPage() {
        return "admin/admin_finance/tradeInfo";
    }

    /**
     * 进入  退款的   页面
     */
    @RequestMapping(value = "/refundView", method = RequestMethod.GET)
    public String refundViewPage() {
        return "admin/admin_finance/refundView";
    }

    /**
     * 加载，交易流水信息
     * @param page  第几页
     * @param limit 每页有多少数据
     */
    @RequestMapping(value = "/tradeview", method = RequestMethod.GET)
    @ResponseBody
    public OrderListPageDto<Trade> tradeview(Integer page,Integer limit,
            HttpServletRequest request) {

        String incontent = request.getParameter("incontent");
        String searchabout = request.getParameter("searchabout");

        OrderListPageDto<Trade> tradeInfoAll = null;
        try {
            tradeInfoAll = tradeService.selectTradeInfoAll(page, limit, searchabout, incontent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tradeInfoAll;
    }

    /**
     * 加载,退款交易信息
     * @param page  第几页
     * @param limit 每页有多少数据
     */
    @RequestMapping(value = "/tradeRefundList", method = RequestMethod.GET)
    @ResponseBody
    public OrderListPageDto<Trade> tradeRefundList(HttpServletRequest request, Integer page, Integer limit) {

        String incontent = request.getParameter("incontent");
        String searchabout = request.getParameter("searchabout");
        String val = request.getParameter("key[val]");

        OrderListPageDto<Trade> tradeRefundList = tradeService.selectTradeRefundList(page, limit, searchabout, incontent, val);
        return tradeRefundList;
    }

    /**
     * 查看订单详情商品名称
     * @param orderNo 订单编号
     * @return
     */
    @RequestMapping(value = "/orderGoodsNames", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderGoods> orderGoodsNames( String orderNo) {

        //查询订单详情
        Order order = orderService.selectOrderByOrderNo(orderNo);
        List<OrderGoods> listOrderGoods = orderGoodsService.getOrderGoodsByOrderId(order.getId());
        return listOrderGoods;
    }

    /**
     * 售后服务，财务审核通过
     * @param orderNO     订单的编号
     * @param userId      用户的id
     * @param description 审核状态
     * @param textarea    审核原因
     * @return
     */
    @RequestMapping(value = "/financeCheckSuccess", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult financeCheckSuccess(String orderNO, Integer userId,Integer description,String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            orderService.updateOrderFinanceCheckSuccess(orderNO);
            tradeService.updateOrderFinanceCheckSuccess(orderNO);
            // 这里还要一个 申请进度表操作
            SAfterForProgress progress = new SAfterForProgress();
            SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNO);
            progress.setAfter_for_record_id(afterForRecord.getId());
            progress.setCreate_time(new Date());
            progress.setDepartment_type(DepartmentTpye.finance.getValue());
            progress.setProgress_description(textarea);
            progress.setSys_user_id(1);// 这里是审核人的id
            progress.setCheck_type(AfterCheckType.conform.getValue());
            progress.setUser_id(userId);
            afterForProgressService.insert(progress);

            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 售后服务，财务审核拒绝
     * @param orderNO     订单的编号
     * @param userId      用户的id
     * @param description 审核状态
     * @param textarea    审核原因
     * @return
     */
    @RequestMapping(value = "/financeCheckFail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult financeCheckFail(String orderNO,Integer userId, Integer description, String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            orderService.updateOrderFinanceCheckFail(orderNO);
            tradeService.updateOrderFinanceCheckFail(orderNO);
            // 这里还要一个 申请进度表操作
            SAfterForProgress progress = new SAfterForProgress();
            SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNO);
            progress.setAfter_for_record_id(afterForRecord.getId());
            progress.setCreate_time(new Date());
            progress.setDepartment_type(DepartmentTpye.finance.getValue());
            progress.setProgress_description(textarea);
            progress.setSys_user_id(1);// 这里是审核人的id
            progress.setCheck_type(AfterCheckType.unconform.getValue());
            progress.setUser_id(userId);
            afterForProgressService.insert(progress);

            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

}
