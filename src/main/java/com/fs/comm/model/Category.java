package com.fs.comm.model;

import java.util.HashSet;
import java.util.Set;
 
public class Category {
	private String cat_id;//自增ID号
	private String cat_name;//分类名称
	private String keywords;//分类的关键字
	private String cat_desc;//分类描述
	private String parent_id;//该分类的父id，取值于该表的cat_id字段
	private Integer sort_order;//该分类在页面显示的顺序，数字越大顺序越靠后；同数字，id在前的先显示
	private String cat_path;//路径,格式：|cat_id||cat_id|
	private String pic;//图片
	private  Category parentCategory; 
	private Set<Category> childCategory = new HashSet<Category>();
	private Integer cnum;//子集合数量 

	public Integer getCnum() {
		return cnum;
	}
	public void setCnum(Integer cnum) {
		this.cnum = cnum;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getCat_desc() {
		return cat_desc;
	}
	public void setCat_desc(String cat_desc) {
		this.cat_desc = cat_desc;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getSort_order() {
		return sort_order;
	}
	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}
	public String getCat_path() {
		return cat_path;
	}
	public void setCat_path(String cat_path) {
		this.cat_path = cat_path;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Set<Category> getChildCategory() {
		return childCategory;
	}
	public void setChildCategory(Set<Category> childCategory) {
		this.childCategory = childCategory;
	}




}