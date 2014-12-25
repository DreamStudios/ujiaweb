package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.TypeInfo;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.JokeCommentService;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.TypeInfoService;
import com.blueshit.joke.service.VipJokeService;
import com.blueshit.joke.utils.AuthorizationUser;
import com.blueshit.joke.validator.JokeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private JokeCommentService jokeCommentService;
    private TypeInfoService typeInfoService;

    @Autowired
    public JokeController(JokeValidator jokeValidator, JokeService jokeService,
                          VipJokeService vipJokeService,JokeCommentService jokeCommentService,TypeInfoService typeInfoService) {
        this.jokeValidator = jokeValidator;
        this.jokeService = jokeService;
        this.vipJokeService = vipJokeService;
        this.jokeCommentService = jokeCommentService;
        this.typeInfoService = typeInfoService;
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
     * @param id 笑话ID
     * @param flag 标识：-1：前一条笑话 0：当前笑话 1:下一条笑话
     * @return
     */
    @RequestMapping({"{id}/jokeDetail.html"})
    public String jokeDetail(@PathVariable int id,String flag,String page,Model model){
        if(flag == null || "".equals(flag)){
            flag = "0";
        }
        Joke joke = jokeService.getJokeById(id,Integer.parseInt(flag));

        //评论内容
        int pagenumber = getStringParseInt(page);
        model.addAttribute("pages",jokeCommentService.getPageJokeCommentByJid(id,pagenumber));
        model.addAttribute("newPage",pagenumber);
        model.addAttribute("joke",joke);
        model.addAttribute("jokeType",0);//普通笑话
        return "/detail/jokedetail";
    }

    /**
     * VIP笑话详情
     * @param id
     * @return
     */
    @RequestMapping({"{id}/vipJokeDetail.html"})
    public String vipJokeDetail(@PathVariable int id,String flag,String page,Model model){
        model.addAttribute("jokeType",1);//会员笑话
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

    /**
     * 文字笑话、根据笑点查询笑话
     * @param model
     * @param page 当前页
     * @param type 查询类型(1:文字笑话 2:笑点)
     * @param value 查询条件值
     * @return
     */
    @RequestMapping("/otherJoke")
    public String otherJoke(Model model, String page,int type,String value){
        model.addAttribute("pages",jokeService.getOtherJokePages(getStringParseInt(page),type,value));
        model.addAttribute("type",type);
        model.addAttribute("value",value);
        return "otherJoke";
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
        if(authentication==null){
            return "redirect:/login.html";
        }
        session.removeAttribute("picture");
        Joke joke = new Joke();
        model.addAttribute("joke",joke);
        return "releaseJoke";
    }

    /**
     * 添加笑话
     * @param joke
     * @param session
     * @return
     */
    @RequestMapping(value="/releaseJoke", method = RequestMethod.POST)
    public String addGoodsStatusPost(Authentication authentication,HttpSession session,HttpServletRequest request,@ModelAttribute("joke") Joke joke, BindingResult result){
        if(authentication==null){
            return "redirect:/login.html";
        }
        String jokePicture = (String) session.getAttribute("picture");
        joke.setPicture(jokePicture);

        jokeValidator.validate(joke,result);
        if (!result.hasErrors()) {
            String tid = request.getParameter("tid");
            TypeInfo typeInfo = typeInfoService.getTypeInfoById(Integer.parseInt(tid));
            UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
            joke.setUserInfo(userInfo);
            joke.setTypeInfo(typeInfo);
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
