package com.blueshit.joke.service;

import com.blueshit.joke.entity.TypeInfo;

import java.util.List;

/**
 * 笑点分类处理接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/24 19:22
 * @description
 */
public interface TypeInfoService {

    /**
     * 获取笑点列表
     * @param number 数量
     * @param flag 0:降序 1:升序
     * @return
     */
    public List<TypeInfo> getTopTypeInfoList(int number, int flag);
}
