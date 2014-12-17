package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.repository.UserInfoRepository;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.utils.Constants;
import com.blueshit.joke.utils.DateUtils;
import com.blueshit.joke.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Locale;

/**
 * 用户操作接口实现类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/11 11:16
 * @description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger logger   = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Value("${EMAIL}")
    private              String EMAIL    = "2112947929@qq.com";
    @Value("${PASSWORD}")
    private              String PASSWORD = "itboys";

    private   UserInfoRepository userInfoRepository;
    private   PasswordEncoder    passwordEncoder;
    protected MessageSource      resource;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder,
                               MessageSource resource) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
        this.resource = resource;
    }

    /**
     * 通过用户账号查找用户信息
     * @param email
     * @return
     */
    @Override
    public UserInfo getUserByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    /**
     * 用户注册接口
     * @param userInfo
     * @return
     */
    @Override
    public boolean userRegister(UserInfo userInfo) {
        try{
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);
            String content = resource.getMessage(Constants.Common.ACTIVE,
                                new Object[]{userInfo.getName(), userInfo.getEmail(),passwordEncoder.encode(userInfo.getCreateTime() + ""), DateUtils.getIntradayDateAndTime("")}, Locale.getDefault());
            MailUtils.sendEmail(EMAIL,PASSWORD,"账号激活",content,userInfo.getEmail());
        }catch (Exception ex){
            logger.error("用户注册失败：" + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 用户激活接口
     * @param email
     * @param activeCode
     * @return
     */
    @Override
    public boolean active(String email, String activeCode) {
        try{
            UserInfo userInfo = userInfoRepository.findByEmail(email);
            if (userInfo != null && passwordEncoder.matches(userInfo.getCreateTime() + "", activeCode)) {
                userInfo.setStatus(2); //0:禁用 1:未激活 2:启用
                userInfoRepository.save(userInfo);
                return true;
            } else {
                return false;
            }
        }catch (Exception ex){
            logger.error("用户激活失败:",ex);
            return false;
        }
    }

    /**
     * 重新发送激活邮件
     * @param email
     * @return
     */
    @Override
    public boolean resendActiveMail(String email) {
        try{
            UserInfo userInfo = userInfoRepository.findByEmail(email);
            String content = resource.getMessage(Constants.Common.ACTIVE,
                                                 new Object[]{userInfo.getName(), userInfo.getEmail(),passwordEncoder.encode(userInfo.getCreateTime() + ""), DateUtils.getIntradayDateAndTime("")}, Locale.getDefault());
            MailUtils.sendEmail(EMAIL,PASSWORD,"账号激活",content,userInfo.getEmail());
        }catch (Exception ex){
            logger.error("重新发送激活邮件失败",ex);
            return false;
        }
        return true;
    }

    /**
     * 用户找回密码
     * @return
     */
    @Override
    public boolean findPassword(String email) {
        try{
            UserInfo userInfo = userInfoRepository.findByEmail(email);
            if(null != userInfo){
                String content = resource.getMessage(Constants.Common.RESET_PWD,
                                                     new Object[]{userInfo.getName(), userInfo.getEmail(), userInfo.getEmail(), passwordEncoder.encode(userInfo.getCreateTime() + ""), DateUtils.getIntradayDateAndTime("")}, Locale.getDefault());
                MailUtils.sendEmail(EMAIL,PASSWORD,"找回密码",content,userInfo.getEmail());
            }else {
                return false;
            }
        }catch (Exception ex){
            logger.error("用户找回密码失败",ex);
            return false;
        }
        return true;
    }

    /**
     * 修改密码
     * @param email 账号
     * @Param resetCode 密文
     * @param password 密码
     * @return
     */
    @Override
    public boolean updatePassword(String email,String resetCode,String password){
        try{
            UserInfo userinfo = userInfoRepository.findByEmail(email);
            if(null != userinfo){
                if (passwordEncoder.matches(userinfo.getCreateTime() + "", resetCode)) {
                    userinfo.setPassword(passwordEncoder.encode(password));
                    userInfoRepository.save(userinfo);
                    return true;
                }
            }
        }catch (Exception ex){
            logger.error("修改用户密码失败",ex);
        }
        return false;
    }
}
