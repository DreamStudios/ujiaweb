package com.blueshit.joke.service;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
import org.springframework.data.domain.Page;

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
}
