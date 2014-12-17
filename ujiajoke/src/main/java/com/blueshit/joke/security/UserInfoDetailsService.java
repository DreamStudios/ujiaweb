package com.blueshit.joke.security;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 登陆验证服务
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/17 22:16
 * @description
 */
@Service
public class UserInfoDetailsService implements UserDetailsService {

    private UserInfoRepository userInfoRepository;

    @Autowired
    public HttpSession httpSession;

    @Autowired
    public UserInfoDetailsService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByEmail(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("账号或者密码错误");
        } else {
            if (userInfo.getStatus() == 0) {
                throw new DisabledException("账号已禁用");
            } else if (userInfo.getStatus() == 1) {
                throw new DisabledException("<span>账号未激活<a href='resendActiveMail.html?username=" + userInfo.getEmail() + "' color='red'>点此激活</a></span>");
            }
        }
        userInfo.setLastLoginTime(System.currentTimeMillis());
        userInfoRepository.save(userInfo);
        UserInfoDetails userInfoDetails = new UserInfoDetails(userInfo);
        return userInfoDetails;
    }
}
