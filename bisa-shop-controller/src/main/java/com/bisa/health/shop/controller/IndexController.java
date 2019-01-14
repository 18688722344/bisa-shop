package com.bisa.health.shop.controller;

import com.bisa.health.shop.model.GoodsImg;
import com.bisa.health.shop.service.IGoodsImgService;
import com.bisa.health.shop.utils.InternationalizationUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 商城首页
 * @author Administrator
 */

@Controller
public class IndexController {

    @Autowired
    private IGoodsImgService goodsImgService;

    /**
     * 商城首页   http://localhost:8080/health-shop/index
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }

    /**
     * 获取首页图片的轮播图，图片集合
     * @param session
     */
    @RequestMapping(value = "web/call/getIndexImg", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsImg> getIndexImg(HttpSession session) {

        String lang = InternationalizationUtil.getLang(session);
        List<GoodsImg> indexImgs = goodsImgService.getIndexImg(lang);
        return indexImgs;
    }

}
