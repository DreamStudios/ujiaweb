package com.blueshit.joke.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

/**
 * WEB安全配置：沙之守鹤
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/15 17:15
 * @description
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Spring Security ignores request to static resources such as CSS or JS files.
        web.ignoring().antMatchers("/static/**");
    }

    //权限配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置需要权限的地址
        http.authorizeRequests().antMatchers("/vip/*.html").hasAuthority("userIsLogin");
        //设置允许访问的资源
        http.authorizeRequests().antMatchers("/", "/upload/**", "/*.html", "/kaptcha.jpg", "/about/**", "/img/**", "/css/**", "/js/**")
                .permitAll().anyRequest()
                .authenticated().and().formLogin().defaultSuccessUrl("/indexinfo")
                .loginPage("/login.html").permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout.html")).permitAll();
    }

    //Spring安全设置
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(userDetailsService);
        impl.setHideUserNotFoundExceptions(false);
        impl.setPasswordEncoder(passwordEncoder());
        return impl;
    }

    /**
     * Spring安全加密规则
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * CSRF防跨域
     * @return
     */
    @Bean
    public RequestDataValueProcessor requestDataValueProcessor() {
        return new CsrfRequestDataValueProcessor();
    }
}
