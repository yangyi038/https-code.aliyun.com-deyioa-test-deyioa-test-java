package com.fs.comm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields
	private Integer id;//部门主键
	private String depcode;//部门编号
	private String depaddress;//部门地址
	private String telephone;//联系电话
	private String liaisonper;//联系人
	private String contracted;//部门简称
	private Integer flag;//有效标记  
	private Date operatetime;//操作时间  
	private String operator;//操作人  
	private String remark;//备注  
	private String depname;//部门名称
	private Integer depparent;//上级部门id
	private Integer dorder;//排序
	private Integer levels;//部门级别
	private String deppath;//部门级别
	private Integer depyear;
	private Integer cnum;//子集合数量
	private  Department parentDepartment; 
	private Set<Department> childDepartment = new HashSet<Department>();
	
	
	
	public Set<Department> getChildDepartment() {
		return childDepartment;
	}

	public void setChildDepartment(Set<Department> childDepartment) {
		this.childDepartment = childDepartment;
	}

	public Integer getDepparent() {
		return depparent;
	}

	public void setDepparent(Integer depparent) {
		this.depparent = depparent;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	/** default constructor */
	public Department() {
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepcode() {
		return depcode;
	}

	public void setDepcode(String depcode) {
		this.depcode = depcode;
	}

	public String getDepaddress() {
		return depaddress;
	}

	public void setDepaddress(String depaddress) {
		this.depaddress = depaddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLiaisonper() {
		return liaisonper;
	}

	public void setLiaisonper(String liaisonper) {
		this.liaisonper = liaisonper;
	}

	public String getContracted() {
		return contracted;
	}

	public void setContracted(String contracted) {
		this.contracted = contracted;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public Integer getDorder() {
		return dorder;
	}

	public void setDorder(Integer dorder) {
		this.dorder = dorder;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getDeppath() {
		return deppath;
	}

	public void setDeppath(String deppath) {
		this.deppath = deppath;
	}

	public Integer getDepyear() {
		return depyear;
	}

	public void setDepyear(Integer depyear) {
		this.depyear = depyear;
	}

	public Integer getCnum() {
		return cnum;
	}

	public void setCnum(Integer cnum) {
		this.cnum = cnum;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

}