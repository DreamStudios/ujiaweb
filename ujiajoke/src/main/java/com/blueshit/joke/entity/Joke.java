package com.blueshit.joke.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 笑话实体
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/16 15:59
 * @description
 */
@Entity
@Table(name = "joke")
@NamedQuery(name = "Joke.findAll", query = "SELECT a FROM Joke a")
public class Joke implements Serializable {
    private static final long serialVersionUID = -914454609095586178L;

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int jid;

    //笑话类型 1:囧图 2:糗事 3:内涵图
    @Column(nullable = false)
    private int type;

    //展示方式：1:图文 2:纯文字
    @Column(nullable = false)
    private int style = 1;

    //笑话标题
    @Column(nullable = false, length = 255)
    private String title;

    //笑话内容(纯文字笑话内容)
    @Column(nullable = true, length = 255)
    private String content;

    //笑话图片
    @Column(nullable = true, length = 255)
    private String picture;

    //笑话状态(0:审核未通过 1:待审核 2:审核通过)
    @Column(nullable = false, columnDefinition = "int(11) default 1")
    private int status = 1;

    //顶数量
    @Column(nullable = false, columnDefinition = "int(11) default 107")
    private int up = (int)(Math.random()*500);

    //踩数量
    @Column(nullable = false, columnDefinition = "int(11) default 5")
    private int down = (int)(Math.random()*20);

    //创建时间
    @Column(nullable = false)
    private Date createTime = new Date();

    //更新时间
    @Column(nullable = false)
    private Date updateTime = new Date();

    //用户信息
    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    @JsonIgnore
    private UserInfo userInfo;

    //笑话分类信息
    @ManyToOne
    @JoinColumn(name = "tid", nullable = false)
    @JsonIgnore
    private TypeInfo typeInfo;

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }
}
