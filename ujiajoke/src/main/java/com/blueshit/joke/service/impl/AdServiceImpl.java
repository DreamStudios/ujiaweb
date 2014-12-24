package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Ad;
import com.blueshit.joke.repository.AdRepository;
import com.blueshit.joke.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 广告接口处理类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/24 19:23
 * @description
 */
public class AdServiceImpl implements AdService{

    private AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    /**
     * 获取广告列表
     * @param number
     * @param type
     * @return
     */
    @Override
    public List<Ad> getTopAdList(int number,int type){
        StringBuffer hql = new StringBuffer("From Ad WHERE adStatus = 3 AND type=" + type);
        hql.append("ORDER BY updateTime DESC");
        return adRepository.findTopByHql(hql.toString(),number);
    }
}
