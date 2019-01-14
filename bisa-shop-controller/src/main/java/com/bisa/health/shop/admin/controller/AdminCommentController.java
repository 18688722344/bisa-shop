package com.bisa.health.shop.admin.controller;

import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.enums.HandleStatus;
import com.bisa.health.shop.admin.model.GuestBookRemark;
import com.bisa.health.shop.admin.service.IAdminGoodsService;
import com.bisa.health.shop.admin.service.IAdminGuestBookRemarkService;
import com.bisa.health.shop.admin.service.IAdminGuestBookService;
import com.bisa.health.shop.admin.service.IAdminUserAppraiseService;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.Guestbook;
import com.bisa.health.shop.model.UserAppraise;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评价管理
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin/comment")
@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER"}, logical = Logical.OR)
public class AdminCommentController {

    @Autowired
    private IAdminUserAppraiseService appraiseService;
    @Autowired
    private IAdminGoodsService goodsService;
    @Autowired
    private IAdminGuestBookService guestBookService;
    @Autowired
    private IAdminGuestBookRemarkService guestBookRemarkService;

    /**
     * 进去 bisa  评价列表 页面
     */
    @RequestMapping(value = "/commentList")
    public String commentList() {
        return "admin/admin_comment/HK_AdminCommentList";
    }

    /**
     * 进去 bisa  留言列表 页面
     */
    @RequestMapping(value = "/leaveListMsg")
    public String leaveListMsg() {
        return "admin/admin_comment/HK_AdminMsg";
    }

    /**
     * 留言详情页面
     *
     * @param id 留言表的id
     */
    @RequestMapping(value = "/leaveDetailMsg")
    public String leaveDetailMsg(Integer id, Model model) {

        // 根据留言表的id 去查询留言对象
        Guestbook guestbook = guestBookService.getLeaveMsg(id);
        model.addAttribute("guestbook", guestbook);
        return "admin/admin_comment/HK_AdminMsgDetail";
    }

    /**
     * 查询评价,userAppraise分页集合
     *
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/commentLists")
    @ResponseBody
    public OrderListPageDto<UserAppraise> selectOrderList(HttpServletRequest request,Integer page, Integer limit) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        String val = request.getParameter("key[val]");

        OrderListPageDto<UserAppraise> listPageDto = appraiseService.selectAllUserAppraise(page, limit, incontent, searchabout, val);
        return listPageDto;
    }

    /**
     * 查询留言,guestBook分页集合
     *
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/leaveMsgList")
    @ResponseBody
    public OrderListPageDto<Guestbook> leaveMsgList(HttpServletRequest request, Integer page, Integer limit) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        String val = request.getParameter("key[val]");

        OrderListPageDto<Guestbook> listPageDto = guestBookService.getLeaveMsgList(page, limit, incontent, searchabout, val);
        return listPageDto;
    }

    /**
     * 根据guestbookId 查询 对应的 (留言备注)  GuestBookRemark 分页集合
     *
     * @param page        第几页
     * @param limit       每页有多少数据
     * @param guestbookId 这个是留言表的id
     * @return
     */
    @RequestMapping(value = "/guestBookRemarkList")
    @ResponseBody
    public OrderListPageDto<GuestBookRemark> guestBookRemarkList(Integer page,Integer limit,Integer guestbookId) {

        OrderListPageDto<GuestBookRemark> listPageDto = guestBookRemarkService.getGuestBookRemarkList(page, limit, guestbookId);
        return listPageDto;
    }

    /**
     * 处理留言
     *
     * @param guestbookId  评论表的id
     * @param textarea     回复的内容
     * @param LeaveMsgTpye 留言的类型
     * @param HandleWay    处理的类型
     * @return
     */
    @RequestMapping(value = "/handleGuestbook", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult handleGuestbook(@CurrentUser User sysUser,Integer guestbookId,String textarea,Integer LeaveMsgTpye,Integer HandleWay) {

        JsonResult jsonResult = new JsonResult();
        try {
            Guestbook guestbook = new Guestbook();
            guestbook.setHandle_status(HandleStatus.figured_out.getValue());
            String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            guestbook.setHandle_time(strDate);
            guestbook.setHandle_way(HandleWay);
            guestbook.setMessage_type(LeaveMsgTpye);
            guestbook.setReply_content(textarea);
            //这里要根据处理人的id  去查询他的对象
            guestbook.setReply_name(sysUser.getUsername());
            guestbook.setId(guestbookId);
            guestBookService.updateGuestbook(guestbook);

            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 留言备注
     *
     * @param guestbookId 留言表的id
     * @param textarea    回复的内容
     * @return
     */
    @RequestMapping(value = "/leaveMsgRemark", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult leaveMsgRemark(@CurrentUser User sysUser, Integer guestbookId,String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            GuestBookRemark guestBookRemark = new GuestBookRemark();
            guestBookRemark.setCreate_time(new Date());
            guestBookRemark.setGuestbook_id(guestbookId);
            guestBookRemark.setRemark_content(textarea);
            guestBookRemark.setRemark_name(sysUser.getUsername());

            guestBookRemarkService.leaveMsgRemark(guestBookRemark);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 回复评论
     *
     * @param UserAppraiseId 评论表的id
     * @param textarea       回复的内容
     * @return
     */
    @RequestMapping(value = "/replyComment", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delectComment(@CurrentUser User sysUser,Integer UserAppraiseId,String textarea) {

        JsonResult jsonResult = new JsonResult();
        try {
            UserAppraise userAppraise = new UserAppraise();
            userAppraise.setId(UserAppraiseId);
            userAppraise.setReplyUserGuid(sysUser.getUser_guid());// 后台回复人的id
            userAppraise.setReplyTime(new Date());
            userAppraise.setReply(textarea);

            appraiseService.updateReplyComment(userAppraise);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 根据商品的编号,去查询商品的对象名称
     *
     * @param goodsNumberId 商品的编号
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    @ResponseBody
    public Goods selectGoods(String goodsNumberId) {

        Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumberId);
        return goods;
    }
}
