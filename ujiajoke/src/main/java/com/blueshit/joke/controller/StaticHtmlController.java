package com.blueshit.joke.controller;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登陆
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 202014/11/17 21:22
 * @description
 */
@Controller
public class StaticHtmlController {
    private UserInfoService userInfoService;

    @Autowired
    public StaticHtmlController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping({"/login.html"})
    public String login(HttpServletRequest request,Model model) {
        //处理spring security 的错误信息
        Object error = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if(error != null){
            //此处用来决定前台页面是否显示验证码
            request.getSession().setAttribute("loginStatus", "false");
        }
        //获取今日之星
        List<UserInfo> list = userInfoService.getTodayStar(30);
        model.addAttribute("list",list);
        return "login";
    }

    /**
     * 登陆成功默认页
     *
     * @return
     */
    @RequestMapping({"/indexinfo"})
    public String successDefaultPage() {
            return "redirect:/index.html";
    }

    /**
     * 其他不需要controller的页面自动匹配
     *
     * @return
     */
    @RequestMapping({"/{name}.html"})
    public String homeHtml(@PathVariable String name) {
        System.out.println("test");
        return "/" + name;
    }

    /**
     * 处理成功页面
     * @param model
     * @return
     */
    @RequestMapping("/success")
    public String proSuccess(Model model){
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        return "success";
    }

    /**
     * 处理失败页面
     * @param model
     * @return
     */
    @RequestMapping("/failure")
    public String proFailure(Model model){
        List<UserInfo> userInfoList = userInfoService.getTodayStar(15);
        model.addAttribute("userInfoList",userInfoList);
        return "failure";
    }


    //测试跳转到外部网页
    @RequestMapping({"/aa"})
    public String successDefaultPage2() {
        return "redirect:http://www.baidu.com";
    }
}
