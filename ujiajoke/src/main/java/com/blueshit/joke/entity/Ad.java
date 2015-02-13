package com.blueshit.joke.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    //显示位置(1:广告 2:友情链接 3:轮播)
    @Column(nullable = false)
    private int type;

    //广告分类(0：手机页面专享 1:精品专辑 2:装机必备 3:精品游戏 4:神友推荐,)
    @Column(nullable = false)
    private int style;

    //广告图片(LOGO)
    @Column(nullable = true,length = 255)
    private String adPhoto;

    //应用包名
    @Column(nullable = true,length = 255)
    private String packageName;

    /**
     * 广告链接地址
     */
    @Column(nullable = true,length = 255)
    private String adUrl;

    /**
     * 应用名称
     */
    @Column(nullable = false, length = 255)
    private String name = "";

    /**
     * 广告标题
     */
    @Column(nullable = false, length = 255)
    private String adTitle = "";

    //广告简介
    @Column(nullable = true,length = 255)
    private String content;

    //应用大小
    @Column(nullable = true,length = 255)
    private String size;

    //下载量
    @Column(nullable = false, columnDefinition = "int(11) default 107")
    private int down = (int)(Math.random()*50000);

    /**
     * 应用截图1
     */
    @Column(nullable = true, length = 255)
    private String screenshot1 = "";
    /**
     * 应用截图2
     */
    @Column(nullable = true, length = 255)
    private String screenshot2 = "";

    /**
     * 广告状态 1:下线2:暂停3：投放中
     */
    @Column(nullable = false)
    private int adStatus = 2;

    //广告权重：排序用
    @Column(nullable = false, columnDefinition = "int(11) default 1")
    private int weight = 1;

    //广告标识：1自己广告 2:其它渠道
    @Column(nullable = false, columnDefinition = "int(11) default 1")
    private int flag = 1;

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
     * 广告主
     */
    @JsonIgnore
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

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public String getScreenshot1() {
        return screenshot1;
    }

    public void setScreenshot1(String screenshot1) {
        this.screenshot1 = screenshot1;
    }

    public String getScreenshot2() {
        return screenshot2;
    }

    public void setScreenshot2(String screenshot2) {
        this.screenshot2 = screenshot2;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
