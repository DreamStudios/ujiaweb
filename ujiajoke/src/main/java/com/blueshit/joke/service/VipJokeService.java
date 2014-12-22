package com.blueshit.joke.service;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.entity.VipJoke;
import org.springframework.data.domain.Page;

/**
 * vip笑话接口
 * Created by shulin on 2014/12/19.
 */
public interface VipJokeService {

    /**
     * 查询vip笑话分页 -按照更新时间倒叙
     * @param page
     * @return
     */
    public Page<VipJoke> getVipJokePage(int page);

    /**
     * 通过笑话ID获取笑话内容
     * @param id
     * @return
     */
    public VipJoke getVipJokeById(int id);

    /**
     * 根据用户查询vip笑话分页，按状态查询
     * @param userInfo
     * @param status 笑话状态(0:审核未通过 1:待审核 2:审核通过 3:全部)
     * @param page
     * @return
     */
    public Page<VipJoke> getVipJokePagesAll_byType(UserInfo userInfo, int status, int page);
}
