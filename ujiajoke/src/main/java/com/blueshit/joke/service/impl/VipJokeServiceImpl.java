package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
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

    /**
     * 通过笑话ID获取笑话内容
     * @param id
     * @return
     */
    @Override
    public VipJoke getVipJokeById(int id){
        return vipJokeRepository.findOne(id);
    }

    /**
     * 根据用户查询vip笑话分页，按状态查询
     * @param userInfo
     * @param status 笑话状态(0:审核未通过 1:待审核 2:审核通过 3:全部)
     * @param page
     */
    @Override
    public Page<VipJoke> getVipJokePagesAll_byType(UserInfo userInfo, int status, int page){
        StringBuffer hql = new StringBuffer("From VipJoke where userInfo.uid="+userInfo.getUid());
        if (status != 3){
            hql.append(" and status="+status);
        }
        hql.append(" order by updateTime desc");
        return vipJokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }
}
