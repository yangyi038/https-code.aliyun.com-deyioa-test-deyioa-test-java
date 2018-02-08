package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 图片管理
 * 
 * @author pzj
 *
 */
@Data
public class T_picture {

	private Integer id;

	// 运营商ID
	private Long companyid;
	private String companyname;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	//图片名称
	private String pictureName;
	private String oldPictureName;
	private String pictureGroup;
	
	//图片存放路径
	private String pic_url;
	
	private String status;
	private Date uploadTime;
	private String linkType;
	private String isDelete;
	private String context;
	private String linkUrl;
	private String newPictureGroup;
	private String tempTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Long getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	public Long getDealerid() {
		return dealerid;
	}
	public void setDealerid(Long dealerid) {
		this.dealerid = dealerid;
	}
	public String getDealername() {
		return dealername;
	}
	public void setDealername(String dealername) {
		this.dealername = dealername;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getOldPictureName() {
		return oldPictureName;
	}
	public void setOldPictureName(String oldPictureName) {
		this.oldPictureName = oldPictureName;
	}
	public String getPictureGroup() {
		return pictureGroup;
	}
	public void setPictureGroup(String pictureGroup) {
		this.pictureGroup = pictureGroup;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getNewPictureGroup() {
		return newPictureGroup;
	}
	public void setNewPictureGroup(String newPictureGroup) {
		this.newPictureGroup = newPictureGroup;
	}
	public String getTempTime() {
		return tempTime;
	}
	public void setTempTime(String tempTime) {
		this.tempTime = tempTime;
	}
	
	

}