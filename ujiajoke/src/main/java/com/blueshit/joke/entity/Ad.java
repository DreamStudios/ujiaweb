package com.blueshit.joke.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 广告实体：存储站内自有广告(主要面向C类广告)
 * 淘宝、百度类第三方广告不存储，直接在页面引用
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/14 11:05
 * @description
 */
@Entity
@Table(name = "ad")
@NamedQuery(name = "Ad.findAll", query = "SELECT a FROM Ad a")
public class Ad implements Serializable {

    private static final long serialVersionUID = 6045350823060238201L;
    /**
     * 广告ID：下发广告的唯一ID，统计计费的唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int adid;

    //广告类型(1:广告 2:友情链接)
    @Column(nullable = false)
    private int type;

    //广告图片
    @Column(nullable = true,length = 255)
    private String adPhoto;

    /**
     * 广告链接地址
     */
    @Column(nullable = true,length = 255)
    private String adUrl;

    /**
     * 广告标题
     */
    @Column(nullable = false, length = 255)
    private String adTitle = "";

    //广告简介
    @Column(nullable = true,length = 255)
    private String content;

    /**
     * 广告创建时间
     */
    @Column(nullable = false)
    private Date createTime = new Date();

    /**
     * 广告更新时间
     */
    @Column(nullable = false)
    private Date updateTime = new Date();

    /**
     * 广告状态 1:下线2:暂停3：投放中
     */
    @Column(nullable = false)
    private int adStatus = 2;

    //广告权重：排序用
    @Column(nullable = false, columnDefinition = "int(11) default 1")
    private int weight = 1;

    /**
     * 广告主
     */
    @ManyToOne
    @JoinColumn(name = "advid", nullable = false)
    private Advertiser advertiser;

    public int getAdid() {
        return adid;
    }

    public void setAdid(int adid) {
        this.adid = adid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdPhoto() {
        return adPhoto;
    }

    public void setAdPhoto(String adPhoto) {
        this.adPhoto = adPhoto;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
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

    public int getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(int adStatus) {
        this.adStatus = adStatus;
    }

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
