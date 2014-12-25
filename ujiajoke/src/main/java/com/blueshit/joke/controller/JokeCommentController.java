package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.JokeComment;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.JokeCommentService;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 笑话评论
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 15:54
 * @description
 */
@RestController
public class JokeCommentController {
    private static final String addr = "http://10.0.3.73:10012/ujiajoke/";

    private UserInfoService userInfoService;
    private JokeService jokeService;
    private JokeCommentService jokeCommentService;

    @Autowired
    public JokeCommentController(UserInfoService userInfoService, JokeService jokeService, JokeCommentService jokeCommentService) {
        this.userInfoService = userInfoService;
        this.jokeService = jokeService;
        this.jokeCommentService = jokeCommentService;
    }

    /**
     * 添加评论
     * @return
     */
    @RequestMapping("/addComment")
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
     * 查看评论内容
     * @param jid
     * @return
     */
    @RequestMapping("/getComment")
    public String getComment(int jid){
        List<JokeComment> list = jokeCommentService.getJokeCommentList(jid, 5);
        String info = "";

        String footer =
                "<div class='list_comment_all'>" +
                "  <a href='"+addr+jid+"/jokeDetail.html#comment' target='_blank' class='comment_all'>查看全部评论</a>" +
                "  <a href='javascript:void(0);' rel='nofollow' class='comment_packUp'>收起↑</a>" +
                "</div>";


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
        String content =
                "<dd class='joke-list-comment' id='comment_905491-1-list' style='display: block;'>" +
                "  <div class='comment-input'>" +
                "    <form onsubmit='return jokeListSubmitComment(this)'>" +
                "      <input name='comment' class='comment-input-text'>" +
                "      <span class='text-length'>0/50字</span>" +
                "      <input type='submit' class='comment-submit' value='评论'>" +
                "    </form>" +
                "  </div>" +
                "  <ul class='comment-list' id='comment-list'>" +
                      info +
                "  </ul>" +
                   footer +
                "</dd>";
        return content;
    }
}
