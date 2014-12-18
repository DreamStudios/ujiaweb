package com.blueshit.joke.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 反馈意见实体
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/18 14:38
 * @description
 */
@Entity
@Table(name = "proposal")
@NamedQuery(name = "Proposal.findAll", query = "SELECT a FROM Proposal a")
public class Proposal implements Serializable {
    private static final long serialVersionUID = 7385190509769651929L;

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int pid;

    //反馈内容
    @Column(nullable = false, length = 255)
    private String content;

    //QQ号码
    @Column(nullable = false, length = 255)
    private String qq;

    //创建时间
    @Column(nullable = false)
    private Date createTime = new Date();

    @Transient
    private String kaptcha;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }
}
