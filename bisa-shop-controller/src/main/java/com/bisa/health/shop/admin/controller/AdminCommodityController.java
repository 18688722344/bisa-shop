package com.bisa.health.shop.admin.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 商品管理-页面控制器
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/commodity")
@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER"}, logical = Logical.OR)
public class AdminCommodityController {

    /**
     * 商品列表页面
     * @return
     */
    @RequestMapping(value = "/commodityView", method = RequestMethod.GET)
    public String commodityView() {
        return "admin/admin_commodity/commodityView";
    }

    /**
     * 添加商品语言版本
     * @return
     */
    @RequestMapping(value = "/goodsInternationalzation", method = RequestMethod.GET)
    public String Internationalzation() {
        return "admin/admin_commodity/goodsInternationalzation";
    }

    /**
     * 新增 商品页面
     * @return
     */
    @RequestMapping(value = "/addCommodityPage", method = RequestMethod.GET)
    public String addCommodityPage() {
        return "admin/admin_commodity/addCommodityPage";
    }

    /**
     * 查看服务卡页面
     * @param
     * @return
     */
    @RequestMapping(value = "/listCard", method = RequestMethod.GET)
    public String listCard() {
        return "admin/admin_commodity/listCard";
    }

    /**
     * 编辑商品页面
     * @return
     */
    @RequestMapping(value = "/updateCommodityPage", method = RequestMethod.GET)
    public String updateCommodityPage() {
        return "admin/admin_commodity/updateCommodity";
    }

    /**
     * 查看商品细节页面
     * @param goodsNumber 商品的编号
     * @return
     */
    @RequestMapping(value = "/showCommodityDetail", method = RequestMethod.GET)
    public String showCommodityDetail(String goodsNumber, HttpSession session) {
        session.setAttribute("goodsNumber", goodsNumber);
        return "admin/admin_commodity/showCommodityDetail";
    }

    /**
     * 商品 套餐详情 页面
     * @return
     */
    @RequestMapping(value = "/goodsComboView", method = RequestMethod.GET)
    public String Goodscombo() {
        return "admin/admin_commodity/goodsComboView";
    }

    /**
     * 添加套餐 页面
     * @return
     */
    @RequestMapping(value = "/addCombo", method = RequestMethod.GET)
    public String addCombo() {
        return "admin/admin_commodity/addCombo";
    }

    /**
     * 新增服务激活卡
     * @return
     */
    @RequestMapping(value = "/addCard", method = RequestMethod.GET)
    public String addCard() {
        return "admin/admin_commodity/addCard";
    }

}
