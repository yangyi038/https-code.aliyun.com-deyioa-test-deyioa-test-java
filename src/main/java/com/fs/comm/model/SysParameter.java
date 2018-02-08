package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 系统参数表
 * @author pzj
 *
 */
@Data
public class SysParameter {

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getSysDescribe() {
		return sysDescribe;
	}
	public void setSysDescribe(String sysDescribe) {
		this.sysDescribe = sysDescribe;
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
	private Long id;
	//运营商ID
	private Long companyid;
	private String companyname;
	
	private String name;
	private String parameterValue;
	private String parameterType;
	private String sysDescribe;
	private Date createTime;
	private String isDelete;
	private String tempTime;

}