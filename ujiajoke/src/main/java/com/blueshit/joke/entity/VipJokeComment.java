package com.blueshit.joke.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 普通笑话评论
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 12:15
 * @description
 */
@Entity
@Table(name = "vipjokecomment")
@NamedQuery(name = "VipJokeComment.findAll", query = "SELECT a FROM VipJokeComment a")
public class VipJokeComment implements Serializable {
    private static final long serialVersionUID = -8217058407689563312L;

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int id;

    //评论内容
    @Column(nullable = true, length = 255)
    private String content;

    //评论时间
    @Column(nullable = false)
    private Date createTime = new Date();

    //用户信息
    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    @JsonIgnore
    private UserInfo userInfo;

    //笑话信息
    @ManyToOne
    @JoinColumn(name = "jid", nullable = false)
    @JsonIgnore
    private VipJoke vipJoke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public VipJoke getVipJoke() {
        return vipJoke;
    }

    public void setVipJoke(VipJoke vipJoke) {
        this.vipJoke = vipJoke;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
