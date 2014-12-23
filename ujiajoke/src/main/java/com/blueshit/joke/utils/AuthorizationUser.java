package com.blueshit.joke.utils;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.security.UserInfoDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    /**
     * 获取登陆用户信息，无需传参数版本
     * @return
     */
    public UserInfo getUserEntity() {
        UserInfo user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            UserInfoDetails userDetails = (UserInfoDetails) auth.getPrincipal();
            return userDetails.getUser();
        }
        return user;
    }

}
