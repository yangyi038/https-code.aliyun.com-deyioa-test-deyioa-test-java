package com.fs.comm.model;

import java.sql.Timestamp;

public class Originplace {
	private String id;//null
	private String name;//场地名称
	private Timestamp addtime;//添加时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getAddtime() {
		return addtime;
	}
	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	} 
	
}
