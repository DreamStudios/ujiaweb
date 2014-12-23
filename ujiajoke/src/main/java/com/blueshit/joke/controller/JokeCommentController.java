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
}
