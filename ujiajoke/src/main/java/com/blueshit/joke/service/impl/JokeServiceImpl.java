package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.repository.JokeRepository;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
}
