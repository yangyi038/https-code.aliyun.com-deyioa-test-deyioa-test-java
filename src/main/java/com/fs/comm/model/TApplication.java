package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 应用表
 * @author pzj
 *
 */
@Data
public class TApplication {
	private Integer id;
	
	private Long companyid;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	private String name;
	private String parameterType;
	private String developerName;
	private String grader;
	private String applyParameter;
	private String synopsis;
	private String status;
	private Date onlineTime;
	private String isDelete;
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
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getDeveloperName() {
		return developerName;
	}
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	public String getGrader() {
		return grader;
	}
	public void setGrader(String grader) {
		this.grader = grader;
	}
	public String getApplyParameter() {
		return applyParameter;
	}
	public void setApplyParameter(String applyParameter) {
		this.applyParameter = applyParameter;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
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
	
}
