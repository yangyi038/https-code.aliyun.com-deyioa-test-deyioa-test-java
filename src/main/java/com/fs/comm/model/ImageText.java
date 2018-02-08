package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 图文信息表
 * 
 * @author pzj
 *
 */
@Data
public class ImageText {

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public String getShowTypeStr() {
		return showTypeStr;
	}

	public void setShowTypeStr(String showTypeStr) {
		this.showTypeStr = showTypeStr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

	public String getImageAttribute() {
		return imageAttribute;
	}

	public void setImageAttribute(String imageAttribute) {
		this.imageAttribute = imageAttribute;
	}

	public String getLastImageText() {
		return lastImageText;
	}

	public void setLastImageText(String lastImageText) {
		this.lastImageText = lastImageText;
	}

	public String getLableAttribute() {
		return lableAttribute;
	}

	public void setLableAttribute(String lableAttribute) {
		this.lableAttribute = lableAttribute;
	}

	public String getTempDate() {
		return tempDate;
	}

	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}

	private Integer id;

	private Long companyid;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	private String name;

	private String status;

	private String lineStatus;
	// 显示格式
	private Integer showType;
	private String showTypeStr;

	private String content;

	private Date createTime;

	private String isDelete;

	private String templet;

	private String imageAttribute;

	private String lastImageText;

	private String lableAttribute;

	private String tempDate;

}