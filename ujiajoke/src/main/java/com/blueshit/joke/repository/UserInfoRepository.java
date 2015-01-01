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

    /**
     * 通过用户ID查找用户信息
     * @param uid 用户ID
     * @return
     */
    public UserInfo findByUid(int uid);

    /**
     * 根据父类邀请码查询用户信息
     * @param parentInviteCode 父类邀请码
     * @return
     */
    public UserInfo findByParentInviteCode(String parentInviteCode);
}
