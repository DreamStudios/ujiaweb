package com.blueshit.joke.service;

import com.blueshit.joke.entity.UserInfo;

/**
 * 用户信息操作接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/11 11:16
 * @description
 */
public interface UserInfoService {
    /**
     * 通过用户账号查找用户信息
     * @param email
     * @return
     */
    public UserInfo getUserByEmail(String email);

    /**
     * 用户注册接口
     * @param userInfo
     * @return
     */
    public boolean userRegister(UserInfo userInfo);

    /**
     * 用户激活接口
     * @param email
     * @param activeCode
     * @return
     */
    public boolean active(String email, String activeCode);

    /**
     * 重新发送激活邮件
     * @param email
     * @return
     */
    public boolean resendActiveMail(String email);

    /**
     * 用户找回密码
     * @return
     */
    public boolean findPassword(String email);

    /**
     * 修改密码
     * @param email 账号
     * @Param resetCode 密文
     * @param password 密码
     * @return
     */
    public boolean updatePassword(String email, String resetCode, String password);
}
