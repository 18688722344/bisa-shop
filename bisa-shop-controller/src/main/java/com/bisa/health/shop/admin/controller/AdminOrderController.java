package com.bisa.health.shop.admin.controller;

import com.bisa.health.app.enumerate.LoginTypeEnum;
import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.common.email.utils.SendMailUtils;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.dto.PrintOrderListDto;
import com.bisa.health.shop.admin.dto.SAfterForRecordDto;
import com.bisa.health.shop.admin.enums.AfterCheckType;
import com.bisa.health.shop.admin.enums.DepartmentTpye;
import com.bisa.health.shop.admin.model.SAfterForProgress;
import com.bisa.health.shop.admin.model.SAfterForRecord;
import com.bisa.health.shop.admin.service.*;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.OrderGoods;
import com.bisa.health.shop.model.ServiceCardRecord;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单管理 控制器
 * @author Administrator
 */


@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_STORE"}, logical = Logical.OR)
@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {

    @Autowired
    private IAdminOrderService iOrderService;
    @Autowired
    private IAdminAfterForRecordService afterForRecordService;
    @Autowired
    private IAdminTradeService tradeService;
    @Autowired
    private IAdminAfterForProgressService afterForProgressService;
    @Autowired
    private IAdminServiceCardRecordService serviceCardRecordService;
    @Autowired
    private IAdminOrderGoodsService orderGoodsService;

    @Value("${domain.url}")
    private String domain;

    /**
     * 进去 订单列表 页面
     */
    @RequestMapping(value = "/orderList")
    public String adminOrderView() {
        return "admin/admin_order/adminOrderView";
    }

    /**
     * 发货管理
     */
    @RequestMapping(value = "/deliverGoods")
    public String deliverGoods() {
        return "admin/admin_order/adminDeliverGoods";
    }

    /**
     * 客服管理
     */
    @RequestMapping(value = "/adminService")
    public String service() {
        return "admin/admin_order/adminService";
    }

    /**
     * 激活卡頁面
     */
    @RequestMapping(value = "/adminCardRecord")
    public String adminCardRecord() {
        return "admin/admin_order/adminCardRecord";
    }

    /**
     * 查询 (所有订单) order 分页集合
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/selectOrderList")
    @ResponseBody
    public OrderListPageDto<Order> selectOrderList(HttpServletRequest request,Integer page,Integer limit) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        OrderListPageDto<Order> listPageDto = iOrderService.selectOrderList(page, limit, incontent, searchabout);
        return listPageDto;
    }

    /**
     * 加载订单详情数据
     * @param orderId 订单表id
     * @return
     */
    @RequestMapping(value = "/goodscontent", method = RequestMethod.POST)
    @ResponseBody
    public List<OrderGoods> goodscontent(Integer orderId) {
        List<OrderGoods> listOrderGoods = orderGoodsService.getOrderGoodsByOrderId(orderId);
        return listOrderGoods;
    }

    /**
     * 查询 --->(发货)(退货审核)<---order 分页集合
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/selectDeliverGoodsOrderList")
    @ResponseBody
    public OrderListPageDto<Order> selectDeliverGoodsOrderList(HttpServletRequest request, Integer page,Integer limit) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        String val = request.getParameter("key[val]");

        OrderListPageDto<Order> listPageDto = iOrderService.selectDeliverGoodsOrderList(page, limit, incontent, searchabout, val);
        return listPageDto;
    }

    /**
     * 查看用户售后的原因
     * @param userId  用户ID
     * @param orderNo 订单号
     * @return
     */
    @RequestMapping(value = "/AfterSalesCause", method = RequestMethod.POST)
    @ResponseBody
    public SAfterForRecordDto AfterSalesCause(
            @CurrentUser User sysUser, Integer userId,String orderNo) {



        SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNo);

        SAfterForRecordDto dto = new SAfterForRecordDto();
        dto.setUriPic(sysUser.getUriPic());
        dto.setUsername(sysUser.getUsername());
        dto.setAfterForCause(afterForRecord.getAfter_for_cause());
        dto.setAfterSalesType(afterForRecord.getAfter_sales_type());
        Date create_time = afterForRecord.getCreate_time();
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(create_time);
        dto.setCreateTime(str);
        dto.setOrderNo(afterForRecord.getOrder_no());
        return dto;
    }

    /**
     * 加载用户的信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public User userInfo(
            @CurrentUser User sysUser,Integer userId) {

        return sysUser;
    }

    /**
     * 提交了 运单号，后台改为已发货
     * @param logisticsNumber 运单号
     * @param logisticsName   物流公司名
     * @param orderId         订单表的id
     * @return
     */
    @RequestMapping(value = "/updateDeliverOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateDeliverOrderStatus(
            @CurrentUser User sysUser,String logisticsNumber, String logisticsName, Integer orderId) {

        JsonResult jsonResult = new JsonResult();
        try {
            iOrderService.updateDeliverOrderStatus(orderId, logisticsNumber, logisticsName);
            // 订单发货发送邮件
            Order order = iOrderService.getOrderById(orderId);

            // 发送账单邮件
            if (!StringUtils.isEmpty(order.getOrderEmail())) { // 已填账单邮件地址，无则不通知

                String href = domain + "/user/orderDetail?order_no=" + order.getOrder_no();
                SendMailUtils.getInstance().sendBillEmail(order.getOrderEmail(), sysUser.getUsername(), order.getOrder_no(), order.getCreate_time(), href, 2);
            }
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 售后服务，物流审核通过
     * @param orderNO     订单号
     * @param userId      用户ID
     * @param description 审核状态
     * @param textarea    审核原因
     * @return
     */
    @RequestMapping(value = "/checkAfterSaleSuccess", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkAfterSaleSuccess(@CurrentUser User sysUser,String orderNO,Integer userId,Integer description,String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            // 通过要 改order表和trade表的状态
            iOrderService.updateOrderCheckAfterSaleSuccess(orderNO);
            tradeService.updateOrderCheckAfterSaleSuccess(orderNO);
            // 这里还要一个 申请进度表操作
            SAfterForProgress progress = new SAfterForProgress();
            SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNO);
            progress.setAfter_for_record_id(afterForRecord.getId());
            progress.setCreate_time(new Date());
            progress.setDepartment_type(DepartmentTpye.logistics.getValue());
            progress.setProgress_description(textarea);
            progress.setSys_user_id(sysUser.getUser_guid());// 这里是审核人的id
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
     * 售后服务，物流审核拒绝
     * @param orderNO     订单号
     * @param userId      用户ID
     * @param description 审核状态
     * @param textarea    审核原因
     * @return
     */
    @RequestMapping(value = "/checkAfterSaleFail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkAfterSaleFail(@CurrentUser User sysUser, String orderNO,Integer userId,Integer description,String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            iOrderService.updateOrderCheckAfterSaleFail(orderNO);
            // 这里还要一个 申请进度表操作
            SAfterForProgress progress = new SAfterForProgress();
            SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNO);
            progress.setAfter_for_record_id(afterForRecord.getId());
            progress.setCreate_time(new Date());
            progress.setDepartment_type(DepartmentTpye.logistics.getValue());
            progress.setProgress_description(textarea);
            progress.setSys_user_id(sysUser.getUser_guid());// 这里是审核人的id
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

    /**
     * 根据order表的id,获取打印要的信息
     * @param jsonprintid order表的id
     * @return
     */
    @RequestMapping(value = "/getPrintOrderList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getPrintOrderList(@RequestParam(value = "jsonprintid[]") Integer[] jsonprintid) {
        List<PrintOrderListDto> printOrderList = iOrderService.getPrintOrderList(jsonprintid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("order_book", printOrderList);
        return jsonObject;
    }

    /**
     * 查询 --->(客服--》退货审核)<---order 分页集合
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/serviceList")
    @ResponseBody
    public OrderListPageDto<Order> serviceList(HttpServletRequest request,Integer page,Integer limit) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        OrderListPageDto<Order> listPageDto = iOrderService.selectServiceList(page, limit, incontent, searchabout);
        return listPageDto;
    }

    /**
     * 售后服务，客服审核通过
     * @param orderNO     订单号
     * @param userId      用户ID
     * @param description 审核状态
     * @param textarea    审核原因
     * @return
     */
    @RequestMapping(value = "/serviceCheckSuccess", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult serviceCheckSuccess(@CurrentUser User sysUser,String orderNO, Integer userId, Integer description, String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            // 通过要 改order表的状态
            iOrderService.updateOrderServiceCheckSuccess(orderNO);
            // 这里还要一个 申请进度表操作
            SAfterForProgress progress = new SAfterForProgress();
            SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNO);
            progress.setAfter_for_record_id(afterForRecord.getId());
            progress.setCreate_time(new Date());
            progress.setDepartment_type(DepartmentTpye.service.getValue());
            progress.setProgress_description(textarea);
            progress.setSys_user_id(sysUser.getUser_guid());// 这里是审核人的id
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
     * 售后服务，客服审核拒绝
     * @param orderNO     订单号
     * @param userId      用户ID
     * @param description 审核状态
     * @param textarea    审核原因
     * @return
     */
    @RequestMapping(value = "/serviceCheckFail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult serviceCheckFail(@CurrentUser User sysUser, String orderNO,Integer userId, Integer description,String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            // 通过要 改order表的状态
            iOrderService.updateOrderServiceCheckFail(orderNO);
            // 这里还要一个 申请进度表操作
            SAfterForProgress progress = new SAfterForProgress();
            SAfterForRecord afterForRecord = afterForRecordService.selectApplyForListByOrderNo(orderNO);
            progress.setAfter_for_record_id(afterForRecord.getId());
            progress.setCreate_time(new Date());
            progress.setDepartment_type(DepartmentTpye.service.getValue());
            progress.setProgress_description(textarea);
            progress.setSys_user_id(sysUser.getUser_guid());// 这里是审核人的id
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

    /**
     * 取消客户订单
     * @param orderId 订单表id
     * @return
     */
    @RequestMapping(value = "/cancelOrders")
    @ResponseBody
    public JsonResult cancelOrders(
            @CurrentUser User sysUser,Integer orderId) {

        JsonResult jsonResult = new JsonResult();
        try {
            iOrderService.updateCancelOrderByOrderID(orderId);
            Order order = iOrderService.getOrderById(orderId);
            // 发送账单邮件
            if (!StringUtils.isEmpty(order.getOrderEmail())) { // 已填账单邮件地址，无则不通知


                String href = domain+"/user/orderDetail?order_no=" + order.getOrder_no();
                SendMailUtils.getInstance().sendBillEmail(order.getOrderEmail(), sysUser.getUsername(), order.getOrder_no(), order.getCreate_time(), href, 3);
            }
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 查询(商品对象),goods分页集合
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/pagerServiceCardRecord")
    @ResponseBody
    public OrderListPageDto<ServiceCardRecord> selectAllCard(HttpServletRequest request,Integer page, Integer limit) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        OrderListPageDto<ServiceCardRecord> listPageDto = serviceCardRecordService.pageServiceCardRecord(page, limit, searchabout, incontent);

        return listPageDto;
    }

}
