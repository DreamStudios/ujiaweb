package com.blueshit.joke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 会员专享模块controller
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/19 8:54
 * @description
 */
@Controller
@RequestMapping("/vip")
public class VipJokeController {


    /**
     * 会员专享模块
     * @return
     */
    @RequestMapping(value = "/membership", method = RequestMethod.GET)
    public String memberShip(Model model, String page){
        return null;
    }
}
