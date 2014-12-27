package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.TypeInfo;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.entity.VipJoke;
import com.blueshit.joke.service.*;
import com.blueshit.joke.utils.AuthorizationUser;
import com.blueshit.joke.validator.VipJokeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 会员专享模块controller
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/19 8:54
 * @description
 */
@Controller
public class VipJokeController {

    private VipJokeService vipJokeService;
    private VipJokeCommentService vipJokeCommentService;
    private VipJokeValidator vipJokeValidator;

    @Autowired
    public VipJokeController(VipJokeService vipJokeService, VipJokeCommentService vipJokeCommentService,
                             VipJokeValidator vipJokeValidator) {
        this.vipJokeService = vipJokeService;
        this.vipJokeCommentService = vipJokeCommentService;
        this.vipJokeValidator = vipJokeValidator;
    }

    /**
     * 会员专享模块
     * @return
     */
    @RequestMapping(value = "/vip/vip_index", method = RequestMethod.GET)
    public String memberShip(Authentication authentication,Model model, String page){
        if(authentication==null){
            return "redirect:/login.html";
        }
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

    /**
     * VIP笑话详情
     * @param id 笑话ID
     * @param flag 标识：-1：前一条笑话 0：当前笑话 1:下一条笑话
     * @return
     */
    @RequestMapping({"{id}/vipJokeDetail.html"})
    public String vipJokeDetail(Authentication authentication,@PathVariable int id,String flag,String page,Model model){
        if(authentication==null){
            return "redirect:/login.html";
        }
        if(flag == null || "".equals(flag)){
            flag = "0";
        }
        VipJoke joke = vipJokeService.getVipJokeById(id, Integer.parseInt(flag));

        //评论内容
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages",vipJokeCommentService.getPageJokeCommentByJid(id,pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("joke",joke);
        model.addAttribute("jokeType",1);//会员笑话
        return "/detail/vipjokedetail";
    }

    /**
     * myVipJoke 分类分页
     * @param status 笑话状态(0:审核未通过 1:待审核 2:审核通过 3:全部)
     */
    @RequestMapping("/myVipJoke")
    public String myVipJoke(Authentication authentication, Model model, Integer status, String page){
        if(authentication==null){
            return "redirect:/login.html";
        }
        UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
        int pagenumber = getStringParseInt(page);
        status = status==null?3:status;
        model.addAttribute("pages", vipJokeService.getVipJokePagesAll_byType(userInfo, status, pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("status", status);
        return "myVipJoke";
    }

    /**
     * 进入添加笑话页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/releaseVipJoke", method = RequestMethod.GET)
    public String addGoodsStatusGet(Authentication authentication,Model model,HttpSession session){
        if(authentication==null){
            return "redirect:/login.html";
        }
        session.removeAttribute("picture");
        Joke joke = new Joke();
        model.addAttribute("joke",joke);
        return "releaseVipJoke";
    }

    /**
     * 添加笑话
     * @param joke
     * @param session
     * @return
     */
    @RequestMapping(value="/releaseVipJoke", method = RequestMethod.POST)
    public String addGoodsStatusPost(Authentication authentication,HttpSession session,HttpServletRequest request,@ModelAttribute("joke") VipJoke joke, BindingResult result){
        if(authentication==null){
            return "redirect:/login.html";
        }
        String jokePicture = (String) session.getAttribute("picture");
        joke.setPicture(jokePicture);

        vipJokeValidator.validate(joke,result);
        if (!result.hasErrors()) {
            UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
            joke.setUserInfo(userInfo);
            boolean jokeResult = vipJokeService.saveVipJoke(joke);
            if(jokeResult){
                return "redirect:/success.html";
            }else {
                return "redirect:/failure.html";
            }
        }
        session.removeAttribute("jokePicture");
        return "releaseVipJoke";
    }
}
