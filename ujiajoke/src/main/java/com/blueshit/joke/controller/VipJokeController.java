package com.blueshit.joke.controller;

import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.VipJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Autowired
    VipJokeService vipJokeService;

    /**
     * 会员专享模块
     * @return
     */
    @RequestMapping(value = "/vip_index", method = RequestMethod.GET)
    public String memberShip(Authentication authentication,Model model, String page){
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages", vipJokeService.getVipJokePage(pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("type",4);
        return "/vip/vip_index";
    }

    /** 页数转化  */
    private int getStringParseInt(String page){
        int num = 1;
        try{
            num =page==null?1:Integer.parseInt(page);
        }catch (Exception e){}
        return num;
    }
}
