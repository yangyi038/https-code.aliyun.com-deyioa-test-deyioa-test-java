package com.fs.comm.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class TCompanyAdmin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195146023892402543L;

	private Long sid;

	private Long companyid;
	// 管理员名称
	private String loginname;

	private String loginpwd;

	private String phone;
	// 角色
	private Integer privilege;

	private String email;

	private String qq;

	private String token;

	private Date createtime;
	private String createtimeStr;

	private Integer admintype;

	private Role sysrole;
	private Department department;

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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
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

	public Integer getAdmintype() {
		return admintype;
	}

	public void setAdmintype(Integer admintype) {
		this.admintype = admintype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getQq() {
		return qq;
	}

	public String getToken() {
		return token;
	}

	/** default constructor */
	public TCompanyAdmin() {
	}

	/** full constructor */
	public TCompanyAdmin(String loginname, String loginpwd, Integer privilege) {
		this.loginname = loginname;
		this.loginpwd = loginpwd;
		this.privilege = privilege;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd == null ? null : loginpwd.trim();
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public Role getSysrole() {
		return sysrole;
	}

	public void setSysrole(Role sysrole) {
		this.sysrole = sysrole;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}