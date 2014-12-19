package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.VipJoke;
import com.blueshit.joke.repository.VipJokeRepository;
import com.blueshit.joke.service.VipJokeService;
import com.blueshit.joke.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * vip笑话接口实现
 * Created by shulin on 2014/12/19.
 */
@Service
public class VipJokeServiceImpl implements VipJokeService{

    @Autowired
    VipJokeRepository vipJokeRepository;

    @Override
    public Page<VipJoke> getVipJokePage(int page) {
        StringBuffer hql = new StringBuffer("From VipJoke where status=2");
        hql.append(" order by updateTime desc");
        return vipJokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }
}
