package com.bootdo.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-20 15:39:17
 */
public class CourseDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //课程种类名称
    private Integer trainCorseId;
    //培训课程名称
    private String name;
    //点击数
    private Integer clickCount;
    //教师id
    private Integer teacherId;
    //价格
    private BigDecimal price;
    //会员价格
    private BigDecimal vipPrice;
    //是否免费(免费就是试听)
    private Boolean isFree;
    //图片地址
    private String url;
    //视频地址
    private String videoUrl;
    //
    private Date createTime;
    //
    private Date updateTime;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：课程种类名称
     */
    public void setTrainCorseId(Integer trainCorseId) {
        this.trainCorseId = trainCorseId;
    }

    /**
     * 获取：课程种类名称
     */
    public Integer getTrainCorseId() {
        return trainCorseId;
    }

    /**
     * 设置：培训课程名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：培训课程名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：点击数
     */
    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * 获取：点击数
     */
    public Integer getClickCount() {
        return clickCount;
    }

    /**
     * 设置：教师id
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取：教师id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置：价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：会员价格
     */
    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    /**
     * 获取：会员价格
     */
    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    /**
     * 设置：是否免费(免费就是试听)
     */
    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    /**
     * 获取：是否免费(免费就是试听)
     */
    public Boolean getIsFree() {
        return isFree;
    }

    /**
     * 设置：图片地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：图片地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：视频地址
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 获取：视频地址
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
