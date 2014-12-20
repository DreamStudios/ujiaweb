package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.VipJoke;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.VipJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户分享单个笑话接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/20 18:16
 * @description
 */
@Controller
public class ShareController {

    private JokeService jokeService;
    private VipJokeService vipJokeService;

    @Autowired
    public ShareController(JokeService jokeService, VipJokeService vipJokeService) {
        this.jokeService = jokeService;
        this.vipJokeService = vipJokeService;
    }

    /**
     * 用户分享
     * @param type 分享类型(1:QQ空间 2:腾讯微博 3:新浪微博 4:QQ好友)
     * @param jid 笑话ID
     * @param flag 是否是会员笑话(1:是 0:否)
     * @return
     */
    @RequestMapping("/share")
    public String share(int type,int jid,int flag){

        if(flag == 1){
            VipJoke vipJoke = vipJokeService.getVipJokeById(jid);
            return vipJokeShare(vipJoke,type);
        }else {
            Joke joke = jokeService.getJokeById(jid);
            return jokeShare(joke,type);
        }
    }

    /**
     * 普通笑话分享
     * @return
     */
    private String jokeShare(Joke joke ,int type){
        switch (type){
            case 1: //QQ空间

                break;
            case 2://腾讯微博

                break;
            case 3://新浪微博

                break;
            case 4://QQ好友
                break;
        }
        return "redirect:http://www.baidu.com";
    }

    /**
     * 会员笑话分享
     * @return
     */
    private String vipJokeShare(VipJoke vipJoke,int type){
        switch (type){
            case 1: //QQ空间

                break;
            case 2://腾讯微博

                break;
            case 3://新浪微博

                break;
            case 4://QQ好友
                break;
        }
        return "redirect:http://www.baidu.com";
    }
}
