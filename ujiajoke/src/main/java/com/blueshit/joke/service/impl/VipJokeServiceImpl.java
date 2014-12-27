package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.entity.VipJoke;
import com.blueshit.joke.repository.VipJokeRepository;
import com.blueshit.joke.service.VipJokeService;
import com.blueshit.joke.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * vip笑话接口实现
 * Created by shulin on 2014/12/19.
 */
@Service
public class VipJokeServiceImpl implements VipJokeService{
    private Logger logger = LoggerFactory.getLogger(VipJokeServiceImpl.class);

    @Autowired
    VipJokeRepository vipJokeRepository;

    @Override
    public Page<VipJoke> getVipJokePage(int page) {
        StringBuffer hql = new StringBuffer("From VipJoke where status=2");
        hql.append(" order by updateTime desc");
        return vipJokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 通过ID获取笑话内容
     * @param id
     * @param flag 标识：-1：前一条笑话 0：当前笑话 1:下一条笑话
     * @return
     */
    @Override
    public VipJoke getVipJokeById(int id, int flag) {
        VipJoke joke = null;
        switch (flag) {
            case -1:
                joke = vipJokeRepository.findOne(id - 1 > 0 ? id-1 : 1);
                break;
            case 0:
                joke = vipJokeRepository.findOne(id);
                break;
            case 1:
                joke = vipJokeRepository.findOne(id + 1);
                break;
        }
        return joke;
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

    /**
     * 保存VIP笑话信息
     * @param joke
     * @return
     */
    @Override
    public boolean saveVipJoke(VipJoke joke){
        try{
            vipJokeRepository.save(joke);
        }catch (Exception ex){
            logger.error("保存VIP笑话失败",ex);
            return false;
        }
        return true;
    }
}
