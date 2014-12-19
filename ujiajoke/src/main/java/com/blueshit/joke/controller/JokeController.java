package com.blueshit.joke.controller;

import com.blueshit.joke.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台页面，笑话Controller
 * Created by Administrator on 2014/12/18.
 */
@Controller
public class JokeController {

    @Autowired
    JokeService jokeService;

    /** 首页 */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model, String page,Authentication authentication){
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages", jokeService.getJokePages_byType(0, pagenumber));
        model.addAttribute("type",0);
        model.addAttribute("newPage",pagenumber);
        return "index";
    }

    /** 囧图 */
    @RequestMapping("/jiongtu")
    public String jiongtu(Model model, String page){
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages", jokeService.getJokePages_byType(1,pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("type", 1);
        return "jiongtu";
    }

    /** 糗事 */
    @RequestMapping("/qiushi")
    public String xiushi(Model model,String page){
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages", jokeService.getJokePages_byType(2,pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("type", 2);
        return "qiushi";
    }

    /** 内涵 */
    @RequestMapping("/neihan")
    public String neihan(Model model,String page){
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages", jokeService.getJokePages_byType(3,pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("type", 3);
        return "neihan";
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
