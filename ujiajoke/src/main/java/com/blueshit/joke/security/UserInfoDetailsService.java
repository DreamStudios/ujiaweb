package com.blueshit.joke.security;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.repository.UserInfoRepository;
import com.blueshit.joke.utils.Constants;
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
        float lastLoginTime = userInfo.getLastLoginTime();
        userInfo.setLastLoginTime(System.currentTimeMillis());
        userInfoRepository.save(userInfo);
        //如果是第一次登陆且有父类邀请码，父类邀请码奖励50经验
        if(0 == lastLoginTime && null != userInfo.getParentInviteCode()){
            UserInfo ui = userInfoRepository.findByParentInviteCode(userInfo.getParentInviteCode());
            ui.setExperience(ui.getExperience() + 50);
            ui.setLevel(Constants.getLevel(ui.getExperience()));
            userInfoRepository.save(ui);
        }
        UserInfoDetails userInfoDetails = new UserInfoDetails(userInfo);
        return userInfoDetails;
    }
}
