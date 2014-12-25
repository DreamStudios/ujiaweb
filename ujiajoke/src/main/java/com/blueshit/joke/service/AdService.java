package com.blueshit.joke.service;

import com.blueshit.joke.entity.Ad;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 广告处理接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/24 19:22
 * @description
 */
public interface AdService {

    /**
     * 获取广告列表
     * @param number
     * @param style
     * @return
     */
    public List<Ad> getTopAdList(int number,int style);

    /**
     * 获取精品推荐分页
     */
    public Page<Ad> getAdPage(int page);
}
