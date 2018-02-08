package com.fs.comm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 系统菜单表
 * 
 * @author MyEclipse Persistence Tools
 */
@Data
public class Sysmenu implements java.io.Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFunctioncode() {
		return functioncode;
	}

	public void setFunctioncode(String functioncode) {
		this.functioncode = functioncode;
	}

	public String getMenulink() {
		return menulink;
	}

	public void setMenulink(String menulink) {
		this.menulink = menulink;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Integer getMenuparent() {
		return menuparent;
	}

	public void setMenuparent(Integer menuparent) {
		this.menuparent = menuparent;
	}

	public Integer getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getFunctiontype() {
		return functiontype;
	}

	public void setFunctiontype(Integer functiontype) {
		this.functiontype = functiontype;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCssclass() {
		return cssclass;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Sysmenu> getChildSysmenu() {
		return childSysmenu;
	}

	public void setChildSysmenu(List<Sysmenu> childSysmenu) {
		this.childSysmenu = childSysmenu;
	}

	public Sysmenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Sysmenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Integer getCnum() {
		return cnum;
	}

	public void setCnum(Integer cnum) {
		this.cnum = cnum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2854603305819116715L;

	// Fields
	private Integer id;// id
	private String functioncode;// 功能代码
	private String menulink;// 连接
	private String menuname;// 功能名称
	private Integer menuparent;// 菜单的父节点
	private Integer menuorder;// 在同一级菜单中的序号
	private String valid;// 是否有效
	private Date addtime;// 操作时间
	private Integer functiontype;// 功能类型
	private String img;// 图标
	private String cssclass;//
	private String path;//
	
	//菜单子节点列表
	private List<Sysmenu> childSysmenu = new ArrayList<Sysmenu>();

	private Sysmenu parentMenu; // 菜单的父节点

	private Integer cnum;// 子集合数量

}