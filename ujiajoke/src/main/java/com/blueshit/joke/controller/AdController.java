package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Ad;
import com.blueshit.joke.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * 关于有家笑话相关页面
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/18 13:55
 * @description
 */
@Controller
@RequestMapping("/appcenter")
public class AdController {

    private AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    //进入应用中心
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String proposal(Model model){
        //获取精品专辑
        List<Ad> list = adService.getTopAdListByStyle(10,1);
        model.addAttribute("jpList",list);
        return "appcenter/index";
    }
}
