package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.VipJokeService;
import com.blueshit.joke.utils.AuthorizationUser;
import com.blueshit.joke.validator.JokeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 前台页面，笑话Controller
 * Created by Administrator on 2014/12/18.
 */
@Controller
public class JokeController {

    private JokeValidator jokeValidator;
    private JokeService jokeService;
    private VipJokeService vipJokeService;

    @Autowired
    public JokeController(JokeValidator jokeValidator, JokeService jokeService, VipJokeService vipJokeService) {
        this.jokeValidator = jokeValidator;
        this.jokeService = jokeService;
        this.vipJokeService = vipJokeService;
    }

    /** 首页 */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model, String page){
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

    /**
     * 普通笑话详情
     * @param id
     * @return
     */
    @RequestMapping({"{id}/jokeDetail.html"})
    public String jokeDetail(@PathVariable int id){
        return "/detail/jokedetail";
    }

    /**
     * 普通笑话详情
     * @param id
     * @return
     */
    @RequestMapping({"{id}/vipJokeDetail.html"})
    public String vipJokeDetail(@PathVariable int id){
        return "/detail/jokedetail";
    }

    /**
     * myJoke 分类分页
     * @param status 笑话状态(0:审核未通过 1:待审核 2:审核通过 3:全部)
     */
    @RequestMapping("/myJoke")
    public String neihan(Authentication authentication, Model model, Integer status, String page){
        if(authentication==null){
            return "redirect:/login.html";
        }
        UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
        int pagenumber = getStringParseInt(page);
        status = status==null?3:status;
        model.addAttribute("pages", jokeService.getJokePagesAll_byType(userInfo, status, pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("status", status);
        return "myJoke";
    }

    /**************************************************vip joke***************************************************/
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
    @RequestMapping(value="/releaseJoke", method = RequestMethod.GET)
    public String addGoodsStatusGet(Authentication authentication,Model model,HttpSession session){
        /*if(authentication==null){
            return "redirect:/login.html";
        }*/
        session.removeAttribute("jokePicture");
        Joke joke = new Joke();
        model.addAttribute("joke",joke);
        return "releaseJoke";
    }

    /**
     * 添加笑话
     * @param model
     * @param joke
     * @param session
     * @return
     */
    @RequestMapping(value="/releaseJoke", method = RequestMethod.POST)
    public String addGoodsStatusPost(Authentication authentication,HttpSession session,Model model,@ModelAttribute("joke") Joke joke, BindingResult result){
        /*if(authentication==null){
            return "redirect:/login.html";
        }*/
        String jokePicture = (String) session.getAttribute("jokePicture");
        session.getAttributeNames();
        joke.setPicture(jokePicture);

        jokeValidator.validate(joke,result);
        if (!result.hasErrors()) {
            UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
            joke.setUserInfo(userInfo);
            boolean jokeResult = jokeService.saveJoke(joke);
            if(jokeResult){
                return "redirect:/success.html";
            }else {
                return "redirect:/failure.html";
            }
        }
        session.removeAttribute("jokePicture");
        return "releaseJoke";
    }
}
