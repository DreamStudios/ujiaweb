package com.blueshit.joke.service;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

/**
 *  笑话接口
 * Created by shulin on 2014/12/18.
 */
public interface JokeService {

    /**
     * 查询笑话分页 -按照更新时间倒叙
     * @param type 0:首页 1:囧图 2:嗅事 3：内涵图
     * @param page
     * @return
     */
    public Page<Joke> getJokePages_byType(int type,int page);

    /**
     * 查询当前用户的笑话 按笑话状态分页
     * @param status 0:审核未通过 1:待审核 2:审核通过 3:全部
     * @param page
     * @return
     */
    public Page<Joke> getJokePagesAll_byType(UserInfo userInfo, int status, int page);

    /**
     * 查询非此用户的笑话列表
     * @param uid 用户ID
     * @param number 查询条数
     * @return
     */
    public List<Joke> getOtherUserJokeList(int uid,int number);

    /**
     * 通过ID获取笑话内容
     * @param id 笑话ID
     * @param flag 标识：-1：前一条笑话 0：当前笑话 1:下一条笑话
     * @return
     */
    public Joke getJokeById(int id,int flag);

    /**
     * 通过UID获取笑话总数、笑话未通过总数
     * @param uid
     * @return
     */
    public HashMap<String, Object> getJokeSumNumberById(int uid);

    /**
     * 保存普通笑话信息
     * @param joke
     * @return
     */
    public boolean saveJoke(Joke joke);

    /**
     * 获取此笑话外的其它笑话
     * @param jid
     * @return
     */
    public List<Joke> getOtherJokeList(int jid,int number);
}
