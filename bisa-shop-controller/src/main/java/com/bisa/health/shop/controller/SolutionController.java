package com.bisa.health.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 解决方案
 * @author Administrator
 */

@Controller
public class SolutionController {

    /**
     * 解决方案页面
     * @return
     */
    @RequestMapping(value = "/solution", method = RequestMethod.GET)
    public String requestReport() {
        return "other/solution";
    }
}
