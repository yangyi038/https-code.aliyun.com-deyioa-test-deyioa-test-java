package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * Admin entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Data
public class Sysuser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3317826926104845102L;

	private Integer id;

	private Long companyid;// 运营商ID
	private String companyname;

	private Long companyadminid;// 运营商管理员ID

	private Long operatorid;//二级运营商
	private Long dealerid;//经销商

	private String loginname;
	private String loginpwd;

	private Integer privilege;
	private Integer depid;
	private String realname;
	private Date lastlogin;// 最后登录时间
	private Integer logintimes;// 登录次数
	private Integer isclose;// 账号状态（(1:正常,2:注销)）

	private Role sysrole;
	private Department department;

	private Integer admintype;// 管理员类型0：系统管理员 1：运营商 2：运营商管理员

	/** default constructor */
	public Sysuser() {
	}

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

	public Long getCompanyadminid() {
		return companyadminid;
	}

	public void setCompanyadminid(Long companyadminid) {
		this.companyadminid = companyadminid;
	}

	public Long getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	public Long getDealerid() {
		return dealerid;
	}

	public void setDealerid(Long dealerid) {
		this.dealerid = dealerid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public Integer getDepid() {
		return depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Integer getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(Integer logintimes) {
		this.logintimes = logintimes;
	}

	public Integer getIsclose() {
		return isclose;
	}

	public void setIsclose(Integer isclose) {
		this.isclose = isclose;
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

	public Integer getAdmintype() {
		return admintype;
	}

	public void setAdmintype(Integer admintype) {
		this.admintype = admintype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** full constructor */
	public Sysuser(String loginname, String loginpwd, Integer privilege) {
		this.loginname = loginname;
		this.loginpwd = loginpwd;
		this.privilege = privilege;
	}

}