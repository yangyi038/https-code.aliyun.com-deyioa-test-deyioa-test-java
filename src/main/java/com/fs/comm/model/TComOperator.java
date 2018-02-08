package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

@Data
public class TComOperator {

	private Long sid;

	private Long companyid;
	// 登录名称
	private String name;
	// 登录密码
	private String pwd;

	// 昵称
	private String nick;
	// 电话
	private String phone;

	private Integer privilege;

	private Date createtime;
	private String createtimeStr;
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreatetimeStr() {
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

}