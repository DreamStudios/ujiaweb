package com.blueshit.joke.controller;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.validator.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    public UserInfoController(UserInfoValidator userInfoValidator, UserInfoService userInfoService) {
        this.userInfoValidator = userInfoValidator;
        this.userInfoService = userInfoService;
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
        model.addAttribute("userInfo", userInfo);// 返回一个空developer对象,用于前台界面填充input框内容
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
        if (result.hasErrors()) {
            return "register";
        } else {
            boolean register = userInfoService.userRegister(userInfo);
            if(register){
                model.addAttribute("email",userInfo.getEmail());
                return "register2";
            }else {
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
    public String accountActive(String username, String activeCode) {
        if (userInfoService.active(username, activeCode)) {
            return "redirect:/login.html";
        }
        else {
            return "register3";
        }
    }

    /**
     * 重新发送激活邮件
     * 用户登陆提示未激活时，用户重新激活
     * @param username
     * @return
     */
    @RequestMapping("/resendActiveMail")
    public String resendActiveMail(HttpServletRequest request,
                                   @RequestParam(value = "username", required = true) String username){
        boolean result = userInfoService.resendActiveMail(username);
        if(result){
            return "register2";
        }else {
            request.getSession().setAttribute("loginMsg", "请求失败,请稍后再试");
            return "redirect:/login.html";
        }
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
        if(result) {
            boolean findResult = userInfoService.findPassword(email);
            if (findResult) {
                return "register2";
            } else {
                return "register3";
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
        if(password == null || "".equals(password) || password.length() < 6 || password.length() > 32){
            model.addAttribute("password","密码不能为空且长度为6-32位");
            return "updatePwd";
        }else {
            boolean result = userInfoService.updatePassword(username,resetCode,password);
            if(result){
                return "redirect:/login.html";
            }else {
                model.addAttribute("title", "找回密码");
                return "register3";
            }
        }
    }

}
