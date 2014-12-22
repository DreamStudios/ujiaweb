package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.validator.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户信息操作
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/11 17:39
 * @description
 */
@Controller
public class UserInfoController {

    private UserInfoValidator userInfoValidator;
    private UserInfoService   userInfoService;
    private JokeService jokeService;

    @Autowired
    public UserInfoController(UserInfoValidator userInfoValidator, UserInfoService userInfoService, JokeService jokeService) {
        this.userInfoValidator = userInfoValidator;
        this.userInfoService = userInfoService;
        this.jokeService = jokeService;
    }

    /**
     * 进入注册页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        UserInfo userInfo = new UserInfo();
        model.addAttribute("userInfo", userInfo);// 返回一个空用户对象,用于前台界面填充input框内容

        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        return "register";
    }

    /**
     * 用户注册：检查注册字段是否合法与保存用户注册信息，并发送邮件用于激活帐号
     *
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, Model model, @ModelAttribute("userInfo") UserInfo userInfo, BindingResult result) {
        userInfoValidator.validate(userInfo, result);

        String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String kaptchaReceived = request.getParameter("kaptcha");

        if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
            result.reject("kaptcha", "验证码错误");
            model.addAttribute("kaptcha", "验证码错误");
        }
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        if (result.hasErrors()) {
            return "register";
        } else {
            boolean register = userInfoService.userRegister(userInfo);
            if(register){
                model.addAttribute("email",userInfo.getEmail());
                return "mailSuccess";
            }else {
                model.addAttribute("kaptcha", "注册失败<a href='about/fankuiyijian.html' color='red'>立即反馈</a>");
                return "register";
            }
        }
    }

    /**
     * 新用户注册后激活
     * @param username
     * @param activeCode
     * @return
     */
    @RequestMapping("/account_active")
    public String accountActive(Model model,String username, String activeCode) {
        UserInfo userInfo = userInfoService.getUserByEmail(username);
        if (userInfoService.active(userInfo, activeCode)) {
            return "redirect:/login.html";
        }else {//激活失败
            List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
            model.addAttribute("userInfoList",userInfoList);
            return "activeError";
        }
    }

    /**
     * 重新发送激活邮件
     * 用户登陆提示未激活时，用户重新激活
     * @param username
     * @return
     */
    @RequestMapping("/resendActiveMail")
    public String resendActiveMail(HttpServletRequest request,Model model,
                                   @RequestParam(value = "username", required = true) String username){
        boolean result = userInfoService.resendActiveMail(username);
        if(result){
            List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
            model.addAttribute("userInfoList",userInfoList);
            return "mailSuccess";
        }else {
            request.getSession().setAttribute("loginMsg", "请求失败,请稍后再试");
            return "redirect:/login.html";
        }
    }

    /**
     * 进入找回密码页面
     * @param model
     * @return
     */
    @RequestMapping("/findPwd")
    public String findPassword(Model model){
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        return "findPwd";
    }

    /**
     * 用户找回密码
     * @return
     */
    @RequestMapping("/findPassword")
    public String findPassword(HttpServletRequest request,Model model,String email,String kaptcha){
        boolean result = true;
        model.addAttribute("title", "找回密码");
        if(null == email || "".equals(email)){
            model.addAttribute("email","邮箱不能为空");
            result = false;
        }
        String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if(!kaptchaExpected.equals(kaptcha)){
            model.addAttribute("kaptcha","验证码错误");
            result = false;
        }
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        if(result) {
            boolean findResult = userInfoService.findPassword(email);
            if (findResult) {
                return "mailSuccess";
            } else {
                return "activeError";
            }
        }else {
            return "findPwd";
        }
    }

    /**
     * 进入修改密码页面
     * @param model
     * @param username
     * @param resetCode
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword(Model model,@RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "resetCode", required = true)String resetCode){
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        model.addAttribute("username",username);
        model.addAttribute("resetCode",resetCode);
        return "updatePwd";
    }
    /**
     * 重置密码
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(Model model,String username,
                                String resetCode,String password){
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        if(password == null || "".equals(password) || password.length() < 6 || password.length() > 32){
            model.addAttribute("password","密码不能为空且长度为6-32位");
            model.addAttribute("userInfoList",userInfoList);
            return "updatePwd";
        }else {
            boolean result = userInfoService.updatePassword(username,resetCode,password);
            if(result){
                return "redirect:/login.html";
            }else {
                model.addAttribute("userInfoList",userInfoList);
                model.addAttribute("title", "找回密码");
                return "activeError";
            }
        }
    }

    /**
     * 进入其它笑友个人主页，非自己主页
     * @param id
     * @return
     */
    @RequestMapping({"{id}/userCenter.html"})
    public String userCenter(Model model,@PathVariable int id,Integer page){
        UserInfo userInfo = userInfoService.getUserByUid(id);
        int pagenumber = page == null ? 1 : page;
        //查询此用户笑话列表
        Page<Joke> pages = jokeService.getJokePagesAll_byType(userInfo, 2, pagenumber);
        //查询其它用户信息
        List<UserInfo> userInfoList = userInfoService.getTodayStar(12);
        //查询非此用户的笑话列表
        List<Joke> jokeList = jokeService.getOtherUserJokeList(userInfo.getUid(),10);

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("pages",pages);
        model.addAttribute("userInfoList",userInfoList);
        model.addAttribute("jokeList",jokeList);
        model.addAttribute("newPage",pagenumber);
        return "/detail/usercenter";
    }

}
