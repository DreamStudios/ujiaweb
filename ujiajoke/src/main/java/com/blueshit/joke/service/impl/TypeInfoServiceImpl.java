package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.TypeInfo;
import com.blueshit.joke.repository.TypeInfoRepository;
import com.blueshit.joke.service.TypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 笑点分类接口处理类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/24 19:23
 * @description
 */
@Service
public class TypeInfoServiceImpl implements TypeInfoService{

    @Autowired
    private TypeInfoRepository typeInfoRepository;

    /**
     * 获取笑点列表
     * @param number 数量
     * @param flag 0:降序 1:升序
     * @return
     */
    @Override
    public List<TypeInfo> getTopTypeInfoList(int number, int flag) {
        StringBuffer hql = new StringBuffer("From TypeInfo");
        hql.append(" ORDER BY createTime ");
        hql.append(flag == 0 ? "DESC" : "ASC");
        return typeInfoRepository.findTopByHql(hql.toString(),number);
    }

    /**
     * 通过ID查询类型信息
     * @param id
     * @return
     */
    public TypeInfo getTypeInfoById(int id){
        return typeInfoRepository.findOne(id);
    }
}
