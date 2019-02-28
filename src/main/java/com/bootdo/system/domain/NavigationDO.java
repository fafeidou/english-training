package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-28 09:11:34
 */
public class NavigationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//图片名称
	private String imgName;
	//图片路径
	private String imgUrl;
	//图片排序
	private Integer order;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：图片名称
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	/**
	 * 获取：图片名称
	 */
	public String getImgName() {
		return imgName;
	}
	/**
	 * 设置：图片路径
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：图片路径
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：图片排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：图片排序
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
