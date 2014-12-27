package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.JokeComment;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.entity.VipJokeComment;
import com.blueshit.joke.service.JokeCommentService;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.service.VipJokeCommentService;
import com.blueshit.joke.utils.AuthorizationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private static final String addr = "http://www.xiaoujia.com/";

    private UserInfoService userInfoService;
    private JokeService jokeService;
    private JokeCommentService jokeCommentService;
    private VipJokeCommentService vipJokeCommentService;

    @Autowired
    public JokeCommentController(UserInfoService userInfoService, JokeService jokeService,
                                 JokeCommentService jokeCommentService,VipJokeCommentService vipJokeCommentService) {
        this.userInfoService = userInfoService;
        this.jokeService = jokeService;
        this.jokeCommentService = jokeCommentService;
        this.vipJokeCommentService = vipJokeCommentService;
    }

    /**
     * 添加评论
     * @return
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public String syncDevIncome(String email,int jid,String content) {
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
                info =
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
                info =
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
