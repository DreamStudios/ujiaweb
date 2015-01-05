package com.blueshit.joke.service.impl;

import com.blueshit.joke.bean.MailProperties;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.repository.UserInfoRepository;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.utils.Constants;
import com.blueshit.joke.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

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

    @Autowired
    private   UserInfoRepository userInfoRepository;
    @Autowired
    private   PasswordEncoder    passwordEncoder;
    @Autowired
    protected MessageSource      resource;

    @Autowired
    public MailProperties jukuMailProperties;

    /**
     * 获取今日之星
     * @return
     */
    @Override
    public List<UserInfo> getTodayStar(int number) {
        String hql = " FROM UserInfo WHERE status=2 ORDER BY isVip DESC,experience DESC, integral DESC,sex DESC,lastLoginTime DESC";
        List<UserInfo> list = userInfoRepository.findTopByHql(hql, number);
        return list;
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
        try {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);
            String content = resource.getMessage(Constants.Common.ACTIVE,
                                                 new Object[]{userInfo.getName(), userInfo.getEmail(), passwordEncoder.encode(userInfo.getTimestamp()+""), DateUtils.getIntradayDateAndTime("")}, Locale.getDefault());
            sendEmail("有家笑话账号激活", content, userInfo.getEmail());
        } catch (Exception ex) {
            logger.error("用户注册失败：" + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 用户激活接口
     * @param userInfo
     * @param activeCode
     * @return
     */
    @Override
    public boolean active(UserInfo userInfo, String activeCode) {
        try{
            if (userInfo != null && passwordEncoder.matches(userInfo.getTimestamp()+"", activeCode)) {
                userInfo.setStatus(2); //用户状态(0:禁用 1:未激活 2:启用)
                userInfo.setInviteCode(userInfo.getUid() + "100000");//激活时生成邀请码
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
                                                 new Object[]{userInfo.getName(), userInfo.getEmail(),passwordEncoder.encode(userInfo.getTimestamp() + ""), DateUtils.getIntradayDateAndTime("")}, Locale.getDefault());
            sendEmail("有家笑话账号激活",content,userInfo.getEmail());
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
                                                     new Object[]{userInfo.getName(), userInfo.getEmail(), userInfo.getEmail(), passwordEncoder.encode(userInfo.getTimestamp() + ""), DateUtils.getIntradayDateAndTime("")}, Locale.getDefault());
                sendEmail("找回密码",content,userInfo.getEmail());
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
                if (passwordEncoder.matches(userinfo.getTimestamp() + "", resetCode)) {
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

    /**
     * 通过用户ID查找用户信息
     * @param uid 用户ID
     * @return
     */
    @Override
    public UserInfo getUserByUid(int uid) {
        return userInfoRepository.findByUid(uid);
    }

    /**
     * 保存或修改用户对象
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo){ userInfoRepository.save(userInfo); }

    @Bean
    public MailProperties jukuMailProperties() {
        return new MailProperties();
    }

    /**
     * 发送邮件
     * @param title 标题
     * @param msgBody 内容
     * @param receiver 接收人
     */
    private void sendEmail(String title, String msgBody,String receiver){
        try {
            //判断是否开启发送邮件
            if (jukuMailProperties.getIsopen()) {
                final String username = jukuMailProperties.getUsername();
                final String password = jukuMailProperties.getPassword();
                Properties props = new Properties();
                props.put("mail.smtp.auth", jukuMailProperties.getSmtp().getAuth());
                props.put("mail.smtp.host", jukuMailProperties.getSmtp().getHost());
                props.put("mail.smtp.port", jukuMailProperties.getSmtp().getPort());
                props.put("mail.transport.protocol", "smtp");

                Session session = Session.getInstance(props,
                                                      new javax.mail.Authenticator() {
                                                          @Override
                                                          protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                                                              return new javax.mail.PasswordAuthentication(username, password);
                                                          }
                                                      }
                );
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(jukuMailProperties.getFromaddress(), "有家笑话"));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver, "快乐家族成员"));
                msg.setSubject(title);
                msg.setText(msgBody);
                Transport.send(msg);
            }
        }catch (Exception ex){
            logger.error("邮件发送失败：",ex);
        }
    }

}
