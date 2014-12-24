package com.blueshit.joke.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/14 14:20
 * @description
 */
@Entity
@Table(name = "userinfo")
@NamedQuery(name = "UserInfo.findAll", query = "SELECT a FROM UserInfo a")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -637324516765199733L;

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int uid;

    //邮箱：用户登陆账号
    @Column(nullable = false, length = 255)
    private String email;

    //登陆密码
    @Column(nullable = false, length = 255)
    private String password;

    //用户状态(0:禁用 1:未激活 2:启用)
    @Column(nullable = false, columnDefinition = "int(11) default 1")
    private int status = 1;

    //姓名(昵称)
    @Column(nullable = false, length = 255)
    private String name;

    //用户头像
    @Column(nullable = false, length = 255)
    private String photo = "/img/login/person.jpg"; //默认头像

    //性别(0:男性 1:女性)
    @Column(nullable = false, columnDefinition = "int(11) default 0")
    private int sex = 0;

    //手机号
    @Column(nullable = true, length = 255)
    private String phone;

    //QQ号码
    @Column(nullable = true, length = 255)
    private String qq;

    //积分(U币 1U币=1角，注册送5元，邀请得两元)
    @Column(nullable = false, columnDefinition = "int(11) default 50")
    private int integral = 50;

    //经验值
    @Column(nullable = false, columnDefinition = "int(11) default 0")
    private int experience = 0;

    //快乐家族级别
    @Column(nullable = false, columnDefinition = "varchar(255) default '实习生'")
    private String level = "实习生";

    //创建时间
    @Column(nullable = false)
    private Date createTime = new Date();

    //更新时间
    @Column(nullable = false)
    private Date updateTime = new Date();

    //自己邀请码(分享奖励标识)
    @Column(nullable = true, length = 255)
    private String inviteCode;

    //父类邀请码，邀请人
    @Column(nullable = true, length = 255)
    private String parentInviteCode;

    //最后一次登陆时间，默认为0
    @Column(nullable = false)
    private long lastLoginTime = 0;

    //该用户的普通笑话
    @OneToMany(mappedBy = "userInfo") //对应Joke实体中的属性名
    private List<Joke> jokeSet;

    //该用户的会员笑话
    @OneToMany(mappedBy = "userInfo") //对应VipJoke实体中的属性名
    private List<VipJoke> vipJokeSet;

    //是否是VIP 1:是 0:否
    @Column(nullable = false, columnDefinition = "int(11) default 0")
    private int isVip = 0;

    //VIP开始日期
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date vipStartDate;

    //VIP结束日期
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date vipEndDate;

    //最后一次签到时间
    @Column(nullable = false)
    private long LastSign = 0;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getParentInviteCode() {
        return parentInviteCode;
    }

    public void setParentInviteCode(String parentInviteCode) {
        this.parentInviteCode = parentInviteCode;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Joke> getJokeSet() {
        return jokeSet;
    }

    public void setJokeSet(List<Joke> jokeSet) {
        this.jokeSet = jokeSet;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public Date getVipStartDate() {
        return vipStartDate;
    }

    public void setVipStartDate(Date vipStartDate) {
        this.vipStartDate = vipStartDate;
    }

    public Date getVipEndDate() {
        return vipEndDate;
    }

    public void setVipEndDate(Date vipEndDate) {
        this.vipEndDate = vipEndDate;
    }

    public List<VipJoke> getVipJokeSet() {
        return vipJokeSet;
    }

    public void setVipJokeSet(List<VipJoke> vipJokeSet) {
        this.vipJokeSet = vipJokeSet;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getLastSign() {
        return LastSign;
    }

    public void setLastSign(long lastSign) {
        LastSign = lastSign;
    }
}

