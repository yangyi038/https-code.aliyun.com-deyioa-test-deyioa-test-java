package com.fs.comm.model;

import java.util.Date;

public class Service {
	private String id;//null
	private String ser_name;//标签名称
	private Date addtime;//添加时间
	private Date edittime;//编辑时间
	private String remark;//备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSer_name() {
		return ser_name;
	}
	public void setSer_name(String ser_name) {
		this.ser_name = ser_name;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getEdittime() {
		return edittime;
	}
	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
