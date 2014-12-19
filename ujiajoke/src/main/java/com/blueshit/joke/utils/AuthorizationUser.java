package com.blueshit.joke.utils;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.security.UserInfoDetails;
import org.springframework.security.core.Authentication;

/**
 * 获取当前登陆信息
 * Created by Administrator on 2014/12/19.
 */
public class AuthorizationUser {

    public final static UserInfo getUserInfoEntity(Authentication authentication) {
        UserInfo userInfo = null;
        UserInfoDetails userDetails;
        if (authentication != null) {
            userDetails = (UserInfoDetails) authentication.getPrincipal();
            userInfo = userDetails.getUser();
        }
        return userInfo;
    }

}
