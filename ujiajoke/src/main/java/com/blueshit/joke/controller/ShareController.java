package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.VipJoke;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.VipJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${SHARE}")
    private String SHARE = "http://10.0.3.73:10012/";

    private JokeService    jokeService;
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
    public String share(int type, int jid, int flag) {
        if (flag == 1) {
            VipJoke vipJoke = vipJokeService.getVipJokeById(jid);
            return vipJokeShare(vipJoke, type);
        } else {
            Joke joke = jokeService.getJokeById(jid,0);
            return jokeShare(joke,type);
        }
    }

    /**
     * 普通笑话分享
     * @return
     */
    private String jokeShare(Joke joke ,int type){
        String title = joke.getTitle();
        String url = SHARE + joke.getJid() + "/jokeDetail.html";
        String picture = joke.getStyle() == 1 ? SHARE + joke.getPicture() : "";
        String addr = "";
        switch (type){
            case 1: //QQ空间
                addr = shareQzone(title,url,picture);
                break;
            case 2://腾讯微博
                addr = shareQweibo(title,url,picture);
                break;
            case 3://新浪微博
                addr = shareSinaWeibo(title,url,picture);
                break;
            case 4://QQ好友
                addr = shareQfrient(title,url);
                break;
        }
        return "redirect:" + addr;
    }

    /**
     * 会员笑话分享
     * @return
     */
    private String vipJokeShare(VipJoke vipJoke,int type){
        String title = vipJoke.getTitle();
        String url = SHARE + vipJoke.getJid() + "/jokeDetail.html";
        String picture = vipJoke.getStyle() == 1 ? SHARE + vipJoke.getPicture() : "";
        String addr = "";
        switch (type){
            case 1: //QQ空间
                addr = shareQzone(title,url,picture);
                break;
            case 2://腾讯微博
                addr = shareQweibo(title,url,picture);
                break;
            case 3://新浪微博
                addr = shareSinaWeibo(title,url,picture);
                break;
            case 4://QQ好友
                addr = shareQfrient(title,url);
                break;
        }
        return "redirect:" + addr;
    }

    //分享到QQ空间
    private String shareQzone(String title,String url,String picture){
        return "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?title=" + title +
                "&url=" + url + "&pics=" + picture;
    }

    //腾讯微博
    private String shareQweibo(String title,String url,String picture){
        return "http://share.v.t.qq.com/index.php?c=share&a=index&title=" + title +
        "&url=" + url + "&pic=" + picture;
    }

    //新浪微博
    private String shareSinaWeibo(String title,String url,String picture){
        return "http://service.weibo.com/share/share.php?title=" + title +
                "&url=" + url + "&pic=" + picture;
    }

    //分享到QQ好友
    private String shareQfrient(String title,String url){
        return "http://connect.qq.com/widget/shareqq/index.html?title=" + title +
                "&url=" + url;
    }
}
