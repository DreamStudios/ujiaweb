package com.blueshit.joke.service;

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
}
