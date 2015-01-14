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
     * @param type 广告类型(1:广告 2:友情链接 3:轮播)
     * @return
     */
    public List<Ad> getTopAdList(int number,int type);

    /**
     * 获取应用列表
     * @param number
     * @param style 广告分类(0：手机页面专享 1:精品专辑 2:装机必备 3:精品游戏 4:神友推荐)
     * @return
     */
    public List<Ad> getTopAdListByStyle(int number,int style);

    /**
     * 获取应用列表
     * @param pageNO 页码
     * @param pageSize 每页条数
     * @param style 广告分类(0：手机页面专享 1:精品专辑 2:装机必备 3:精品游戏 4:神友推荐)
     * @return
     */
    public List<Ad> getAdPageListByStyle(int pageNO,int pageSize,int style);

    /**
     * 获取精品推荐分页
     */
    public Page<Ad> getAdPage(int page);

    /**
     * 通过广告ID获取广告详情信息
     * @param adid 广告ID
     * @return
     */
    public Ad getAdByAdId(int adid);
}
