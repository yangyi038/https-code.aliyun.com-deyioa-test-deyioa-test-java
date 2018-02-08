package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 用户操作日志表
 * @author pzj
 */
@Data
public class SysLog {

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public Date getDotime() {
		return dotime;
	}
	public void setDotime(Date dotime) {
		this.dotime = dotime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperobject() {
		return operobject;
	}
	public void setOperobject(String operobject) {
		this.operobject = operobject;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getOperresult() {
		return operresult;
	}
	public void setOperresult(String operresult) {
		this.operresult = operresult;
	}
	public String getOperdes() {
		return operdes;
	}
	public void setOperdes(String operdes) {
		this.operdes = operdes;
	}
	private String id;
	
	//运营商ID
	private Long companyid;
	private String companyname;
	
	private Date dotime;
	private String username;
	private String operobject;
	private String opertype;
	private String operresult;
	private String operdes;

}