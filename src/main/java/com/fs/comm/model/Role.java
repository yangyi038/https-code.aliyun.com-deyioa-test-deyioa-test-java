package com.fs.comm.model;

import lombok.Data;

/**
 * 角色
 * 
 * @author pzj
 */
@Data
public class Role implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9114107590508778131L;

	private Integer id;// 主键id
	private Long companyid;// 运营商ID
	private String companyname;
	// 角色的适用对象
	private Integer touser;

	private String rolename;// 角色名称
	private String menuid;// 功能id
	private String dataarea;
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
	public Integer getTouser() {
		return touser;
	}
	public void setTouser(Integer touser) {
		this.touser = touser;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getDataarea() {
		return dataarea;
	}
	public void setDataarea(String dataarea) {
		this.dataarea = dataarea;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}