package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.repository.JokeRepository;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014/12/18.
 */
@Service
public class JokeServiceImpl implements JokeService {

    @Autowired
    JokeRepository jokeRepository;

    /**
     * 按照更新时间倒叙
     * @param type 0:首页 1:囧图 2:嗅事 3：内涵图
     */
    @Override
    public Page<Joke> getJokePages_byType(int type, int page) {
        StringBuffer hql = new StringBuffer("From Joke where status=2");
        if (type != 0){
            hql.append(" and type="+type);
        }
        hql.append(" order by updateTime desc");
        return jokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 查询当前用户的笑话 按笑话状态分页
     * @param status 0:审核未通过 1:待审核 2:审核通过 3:全部
     * @return
     */
    public Page<Joke> getJokePagesAll_byType(UserInfo userInfo, int status, int page){
        StringBuffer hql = new StringBuffer("From Joke where userInfo.uid="+userInfo.getUid());
        if (status != 3){
            hql.append(" and status="+status);
        }
        hql.append(" order by updateTime desc");
        return jokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 查询非此用户的笑话列表
     * @param uid 用户ID
     * @param number 查询条数
     * @return
     */
    @Override
    public List<Joke> getOtherUserJokeList(int uid, int number) {
        StringBuffer hql = new StringBuffer("From Joke WHERE style=1 AND userInfo.uid != "+uid);
        hql.append("ORDER BY updateTime DESC");
        return jokeRepository.findTopByHql(hql.toString(),number);
    }

    /**
     * 通过ID获取笑话内容
     * @param id
     * @return
     */
    public Joke getJokeById(int id){
        return jokeRepository.findOne(id);
    }
}
