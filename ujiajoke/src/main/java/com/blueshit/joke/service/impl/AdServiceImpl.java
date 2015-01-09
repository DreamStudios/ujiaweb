package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Ad;
import com.blueshit.joke.repository.AdRepository;
import com.blueshit.joke.service.AdService;
import com.blueshit.joke.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告接口处理类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/24 19:23
 * @description
 */
@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    /**
     * 获取广告列表
     * @param number
     * @param type 广告类型(1:广告 2:友情链接 3:轮播)
     * @return
     */
    @Override
    public List<Ad> getTopAdList(int number,int type){
        StringBuffer hql = new StringBuffer("From Ad WHERE adStatus = 3 AND type=" + type);
        hql.append("ORDER BY updateTime DESC");
        return adRepository.findTopByHql(hql.toString(),number);
    }

    /**
     * 获取应用列表
     * @param number
     * @param style 广告分类(1:精品专辑 2:装机必备 3:精品游戏 4:神友推荐)
     * @return
     */
    @Override
    public List<Ad> getTopAdListByStyle(int number,int style){
        StringBuffer hql = new StringBuffer("From Ad WHERE adStatus=3 AND type=1 AND style=" + style);
        hql.append("ORDER BY updateTime DESC");
        return adRepository.findTopByHql(hql.toString(),number);
    }

    /**
     * 获取精品推荐分页
     */
    public Page<Ad> getAdPage(int page){
        String hql = "From Ad WHERE adStatus = 3 AND type=1 ORDER BY weight";
        return adRepository.findByHql(hql, Constants.Common.PAGE_SIZE, page);
    }
}
