package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 图文模板
 * @author pzj
 *
 */
@Data
public class ImageLable {

	private Integer id;
	private String name;
	private String lableDescribe;
	private String status;
	private Date createTime;
	private String isDelete;
	private String tempTime;

	private Long companyid;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLableDescribe() {
		return lableDescribe;
	}
	public void setLableDescribe(String lableDescribe) {
		this.lableDescribe = lableDescribe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTempTime() {
		return tempTime;
	}
	public void setTempTime(String tempTime) {
		this.tempTime = tempTime;
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

}