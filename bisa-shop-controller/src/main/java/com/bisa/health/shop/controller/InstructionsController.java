package com.bisa.health.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 说明书
 * @author Administrator
 */

@Controller
public class InstructionsController {

    /**
     * App说明书
     * @return
     */
    @RequestMapping(value = "/Instructions", method = RequestMethod.GET)
    public String Instructions(Model model, HttpSession session, HttpServletRequest request) {
        return "instructions/Instructions";
    }
}
