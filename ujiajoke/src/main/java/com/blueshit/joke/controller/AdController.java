package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Ad;
import com.blueshit.joke.service.AdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    private ObjectMapper objectMapper;

    @Autowired
    public AdController(AdService adService,ObjectMapper objectMapper) {
        this.adService = adService;
        this.objectMapper = objectMapper;
    }

    //进入网页版应用中心
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String webAppCenter(Model model){
        //获取精品专辑
        List<Ad> list = adService.getTopAdListByStyle(10,1);
        model.addAttribute("jpList",list);
        return "appcenter/index";
    }


    //进入手机网页版应用中心
    @RequestMapping(value = "/phoneAppCenter", method = RequestMethod.GET)
    public String phoneAppCenter(Model model){
        //获取精品专辑
        List<Ad> list = adService.getAdPageListByStyle(1,20,0);
        model.addAttribute("appList",list);
        return "appcenter/phoneAppCenter";
    }

    //手机网页版应用中心--获取更多
    @RequestMapping(value = "/getMore", method = RequestMethod.GET)
    public String phoneAppCenterGetMore(HttpServletResponse response,
                                        @RequestParam(value = "page", required = true) String page) throws Exception{
        int pageNo;
        try{
            pageNo = Integer.parseInt(page);
        }catch (Exception ex){
            pageNo = 1;
        }
        //获取精品专辑
        List<Ad> list = adService.getAdPageListByStyle(pageNo,20,0);
        printContentToPage(response,objectMapper.writeValueAsString(list));
        return null;
    }

    /**
     * 进入应用详情页面
     * @param adid
     * @param model
     * @return
     */
    @RequestMapping({"/{adid}/phoneAppDetail.html"})
    public String homeHtml(@PathVariable String adid, Model model) {
        Ad ad = adService.getAdByAdId(Integer.parseInt(adid));
        model.addAttribute("ad", ad);
        return "appcenter/phoneAppDetail";
    }

    private void printContentToPage(HttpServletResponse response,String text){
        PrintWriter out = null;
        try {
            //禁止缓存数据
            response.setHeader("Content-type", "text/json;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.print(text);
        } catch (IOException e) {
        }finally{
            out.flush();
            out.close();
        }
    }
}
