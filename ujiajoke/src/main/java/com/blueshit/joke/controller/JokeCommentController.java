package com.blueshit.joke.controller;

import com.blueshit.joke.entity.*;
import com.blueshit.joke.service.*;
import com.blueshit.joke.utils.AuthorizationUser;
import com.blueshit.joke.utils.Constants;
import com.blueshit.joke.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * 笑话评论
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 15:54
 * @description
 */
@Controller
public class JokeCommentController {
    private static final String addr = "http://www.xiaoujia.com/ujiajoke/";

    private UserInfoService userInfoService;
    private JokeService jokeService;
    private VipJokeService vipJokeService;
    private JokeCommentService jokeCommentService;
    private VipJokeCommentService vipJokeCommentService;

    @Autowired
    public JokeCommentController(UserInfoService userInfoService, JokeService jokeService,VipJokeService vipJokeService,
                                 JokeCommentService jokeCommentService,VipJokeCommentService vipJokeCommentService) {
        this.userInfoService = userInfoService;
        this.jokeService = jokeService;
        this.vipJokeService = vipJokeService;
        this.jokeCommentService = jokeCommentService;
        this.vipJokeCommentService = vipJokeCommentService;
    }

    /**
     * 添加评论
     * @return
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public String addComment(String email,int jid,String content) {
        UserInfo userInfo = userInfoService.getUserByEmail(email);
        Joke joke = jokeService.getJokeById(jid,0);
        if(null == userInfo || null == joke){
            return "0";
        }else {
            boolean result = jokeCommentService.isComment(userInfo.getUid(),joke.getJid());
            if(result){
                return "2";
            }else{
                JokeComment jokeComment = new JokeComment();
                jokeComment.setUserInfo(userInfo);
                jokeComment.setContent(content);
                jokeComment.setJoke(joke);
                boolean addResult = jokeCommentService.addJokeComment(jokeComment);
                if(addResult){
                    return "1";
                }else {
                    return "0";
                }
            }
        }
    }

    /**
     * 添加VIP笑话评论
     * @return
     */
    @RequestMapping("/addVipComment")
    @ResponseBody
    public String addVipComment(String email,int jid,String content) {
        UserInfo userInfo = userInfoService.getUserByEmail(email);
        VipJoke joke = vipJokeService.getVipJokeById(jid,0);
        if(null == userInfo || null == joke){
            return "0";
        }else {
            boolean result = vipJokeCommentService.isComment(userInfo.getUid(),joke.getJid());
            if(result){
                return "2";
            }else{
                VipJokeComment jokeComment = new VipJokeComment();
                jokeComment.setUserInfo(userInfo);
                jokeComment.setContent(content);
                jokeComment.setVipJoke(joke);
                boolean addResult = vipJokeCommentService.addJokeComment(jokeComment);
                if(addResult){
                    return "1";
                }else {
                    return "0";
                }
            }
        }
    }

    /**
     * 查看普通笑话评论内容
     * @param jid
     * @return
     */
    @RequestMapping(value = "/getComment")
    public String getComment(Authentication authentication,HttpServletResponse response,int jid){
        List<JokeComment> list = jokeCommentService.getJokeCommentList(jid, 5);
        String info = "";

        String form = "<div style='text-align:center;'>登陆之后才能评论<a href='"+addr+"/login.html' style='font-size: 14;color: #03a8b1'>立即登陆</a></div>";
        if(authentication != null){
            UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
            form =
            "<div class='comment-input' style='text-align:left;'>" +
            "  <form>" +
            "    <input name='comment' class='comment-input-text' maxlength='50'>" +
            "    <span class='text-length'>50字</span>" +
            "    <input type='button' id='"+jid+"' name='"+userInfo.getEmail()+"' class='comment-submit' value='点评'>" +
            "  </form>" +
            "</div>";
        }

        if(null == list || 0 == list.size()){
            info = "<li class='nocomment'>还没有人评论过，赶快抢沙发吧！</li>";
        }else {
            for(JokeComment jk : list){
                info +=
                "<li>" +
                "  <div class='comment-content'>" +
                "    <a href='"+addr+jk.getUserInfo().getUid()+"/userCenter.html'><img src='"+addr + jk.getUserInfo().getPhoto()+"' alt='testa'><i></i></a>" +
                "    <p class='comment-username'><a href='"+addr+jk.getUserInfo().getUid()+"/userCenter.html'>"+jk.getUserInfo().getName()+"</a></p>" +
                "    <p>"+jk.getContent()+"</p>" +
                "  </div>" +
                "</li>";
            }
        }
        String footer =
                "<div class='list_comment_all'>" +
                "  <a href='"+addr+jid+"/jokeDetail.html#comment' target='_blank' class='comment_all'>查看全部评论</a>" +
                "  <a href='javascript:void(0);' rel='nofollow' class='comment_packUp'>收起↑</a>" +
                "</div>";
        String content =
                "<dd class='joke-list-comment' id='comment_905491-1-list' style='display: block;'>" +
                   form +
                "  <ul class='comment-list' id='comment-list'>" +
                      info +
                "  </ul>" +
                   footer +
                "</dd>";
        printContentToPage(response,content);
        return null;
    }

    /**
     * 查看VIP笑话评论内容
     * @param jid
     * @return
     */
    //@RequestMapping(value = "/getVipComment", produces = "text/html; charset=UTF-8") //解决response乱码问题
    @RequestMapping(value = "/getVipComment")
    public String getVipComment(Authentication authentication,HttpServletResponse response,int jid){
        List<VipJokeComment> list = vipJokeCommentService.getJokeCommentList(jid, 5);
        String info = "";

        String form = "<div style='text-align:center;'>登陆之后才能评论<a href='"+addr+"/login.html' style='font-size: 14;color: #03a8b1'>立即登陆</a></div>";
        if(authentication != null){
            UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
            form =
            "<div class='comment-input' style='text-align:left;'>" +
            "  <form>" +
            "    <input name='comment' class='comment-input-text' maxlength='50'>" +
            "    <span class='text-length'>50字</span>" +
            "    <input type='button' id='"+jid+"' name='"+userInfo.getEmail()+"' class='comment-submit' value='点评'>" +
            "  </form>" +
            "</div>";
        }

        if(null == list || 0 == list.size()){
            info = "<li class='nocomment'>还没有人评论过，赶快抢沙发吧！</li>";
        }else {
            for(VipJokeComment jk : list){
                info +=
                "<li>" +
                "  <div class='comment-content'>" +
                "    <a href='"+addr+jk.getUserInfo().getUid()+"/userCenter.html'><img src='"+addr + jk.getUserInfo().getPhoto()+"' alt='testa'><i></i></a>" +
                "    <p class='comment-username'><a href='"+addr+jk.getUserInfo().getUid()+"/userCenter.html'>"+jk.getUserInfo().getName()+"</a></p>" +
                "    <p>"+jk.getContent()+"</p>" +
                "  </div>" +
                "</li>";
            }
        }
        String footer =
                "<div class='list_comment_all'>" +
                "  <a href='"+addr+jid+"/jokeDetail.html#comment' target='_blank' class='comment_all'>查看全部评论</a>" +
                "  <a href='javascript:void(0);' rel='nofollow' class='comment_packUp'>收起↑</a>" +
                "</div>";
        String content =
                "<dd class='joke-list-comment' id='comment_905491-1-list' style='display: block;'>" +
                   form +
                "  <ul class='comment-list' id='comment-list'>" +
                   info +
                "  </ul>" +
                footer +
                "</dd>";
        printContentToPage(response,content);
        return null;
    }

    /**
     * 用户签到
     * @return
     */
    @RequestMapping(value = "/signIn")
    public String signIn(Authentication authentication,HttpServletResponse response){
        String result = "0"; //未登陆
        if(authentication != null){
            UserInfo userInfo = AuthorizationUser.getUserInfoEntity(authentication);
            UserInfo ui = userInfoService.getUserByEmail(userInfo.getEmail());
            if(DateUtils.diffDate(ui.getLastSign()) > 0){
                ui.setLastSign(System.currentTimeMillis());
                ui.setExperience(ui.getExperience() + 5);
                ui.setLevel(Constants.getLevel(ui.getExperience()));
                userInfoService.save(ui);
                result = "2";
            }else{
                result = "1"; //今天已经签到
            }
        }
        printContentToPage(response,result);
        return null;
    }

    /**
     * 顶、踩
     * @param request
     * @param response
     * @param flag 是否是VIP 1:是 0:否
     * @param jid 笑话ID
     * @return
     */
    @RequestMapping(value = {"/up","/down"})
    public String updown(HttpServletRequest request,HttpServletResponse response,int flag,int jid){
        String action = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        if ("up.html".equals(action)) { //顶
            if(1 == flag){
                vipJokeService.upDownVipJoke(jid,1);
            }else {
                jokeService.upDownJoke(jid,1);
            }
        }else{//踩
            if(1 == flag){
                vipJokeService.upDownVipJoke(jid,0);
            }else {
                jokeService.upDownJoke(jid,0);
            }
        }
        printContentToPage(response,"1");
        return null;
    }

    private void printContentToPage(HttpServletResponse response,String text){
        PrintWriter out = null;
        try {
            //禁止缓存数据
            response.setHeader("Content-type", "text/html;charset=UTF-8");
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
