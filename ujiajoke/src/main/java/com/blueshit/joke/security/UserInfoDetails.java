package com.blueshit.joke.security;

import com.blueshit.joke.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 登陆后保存的用户资料
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/17 22:11
 * @description
 */
public class UserInfoDetails implements UserDetails {
    private static final long serialVersionUID = 4276435444210788904L;

    private UserInfo user;
    private String   username;
    private String   password;

    @Autowired
    public UserInfoDetails(final UserInfo user) {
        this.user = user;
        username = user.getEmail();
        password = user.getPassword();
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                //用户登陆后权限名称,此处要与WebSecurityConfig中配置的权限名称一致
                return "userIsLogin";
            }
        });
        return list;
    }
}
