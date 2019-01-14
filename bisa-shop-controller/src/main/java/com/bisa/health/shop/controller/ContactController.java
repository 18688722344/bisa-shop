package com.bisa.health.shop.controller;

import com.bisa.health.shop.model.Guestbook;
import com.bisa.health.shop.service.IGuestbookService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * 联系我们
 * @author Administrator
 */

@Controller
public class ContactController {

    @Autowired
    private IGuestbookService iGuestbookService;

    private Logger logger = LogManager.getLogger(ContactController.class);

    /**
     * 联系我们
     * @return
     */
    @RequestMapping(value = "/contactUs", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.setAttribute("request_type", "contactUs");
        return "other/ContactUs";
    }

    /**
     * 免责条款
     * @return
     */
    @RequestMapping(value = "/disclaimer", method = RequestMethod.GET)
    public String disclaimer(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.setAttribute("request_type", "disclaimer");
        return "other/ContactUs";
    }

    /**
     * 隐私条例
     * @return
     */
    @RequestMapping(value = "/privacyPrinciple", method = RequestMethod.GET)
    public String privacyPrinciple(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.setAttribute("request_type", "privacyPrinciple");
        return "other/ContactUs";
    }

    /**
     * 提交留言的内容
     * @param name  名字
     * @param phone 手机号码
     * @param mail  邮箱
     * @param text  内容
     * @return
     */
    @RequestMapping(value = "/contactUs", method = RequestMethod.POST)
    @ResponseBody
   
    public boolean contactUsPOST(String name,String phone,String mail,String text) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Guestbook guestbook = new Guestbook();
        guestbook.setName(name);
        guestbook.setPhone(phone);
        guestbook.setMail(mail);
        guestbook.setMessage(text);
        guestbook.setMessage_time(sdf.format(System.currentTimeMillis()));

        try {
            iGuestbookService.addGuestbook(guestbook);
        } catch (Exception e) {
            logger.debug("添加留言失败---", e);
            return false;
        }
        return true;
    }

}