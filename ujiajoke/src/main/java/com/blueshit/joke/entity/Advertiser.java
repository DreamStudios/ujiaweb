package com.blueshit.joke.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 广告主信息表
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/14 11:10
 * @description
 */
@Entity
@Table(name = "advertiser")
@NamedQuery(name = "Advertiser.findAll", query = "SELECT a FROM Advertiser a")
public class Advertiser implements Serializable {
    private static final long serialVersionUID = 4228786004070618566L;

    /**
     * 广告主ID：主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int advid;

    /**
     * 广告主名称(个人名或公司名)
     */
    @Column(nullable = false, length = 255)
    private String advname;

    /**
     * 广告主联系人
     */
    @Column(nullable = false, length = 255)
    private String contacts;

    /**
     * 广告主联系方式
     */
    @Column(nullable = false, length = 255)
    private String advphone;

    /**
     * 与广告主合作状态(1:待合作 2:合作中:3:暂停合作4:不再合作)
     */
    @Column(nullable = false)
    private int advstatus;

    /**
     * 该广告主在平台投放的广告
     */
    @OneToMany(mappedBy = "advertiser")
    private List<Ad> adsSet;

    /**
     * 备注信息
     */
    @Column(nullable = true, length = 255)
    private String remark;

    public int getAdvid() {
        return advid;
    }

    public void setAdvid(int advid) {
        this.advid = advid;
    }

    public String getAdvname() {
        return advname;
    }

    public void setAdvname(String advname) {
        this.advname = advname;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAdvphone() {
        return advphone;
    }

    public void setAdvphone(String advphone) {
        this.advphone = advphone;
    }

    public int getAdvstatus() {
        return advstatus;
    }

    public void setAdvstatus(int advstatus) {
        this.advstatus = advstatus;
    }

    public List<Ad> getAdsSet() {
        return adsSet;
    }

    public void setAdsSet(List<Ad> adsSet) {
        this.adsSet = adsSet;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

