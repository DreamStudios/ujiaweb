package com.blueshit.joke.repository;

import com.blueshit.joke.entity.UserInfo;

/**
 * 用户信息接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/17 22:16
 * @description
 */
public interface UserInfoRepository extends BaseRepository<UserInfo, Integer>  {
    /**
     * 通过邮箱查找用户信息
     */
    public UserInfo findByEmail(String email);
}
