package com.fs.comm.model;

import lombok.Data;

/**
 * 枚举参数表
 * @author pzj
 *
 */
@Data
public class Parameter implements java.io.Serializable {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getIsshow() {
		return isshow;
	}
	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}
	public Integer getPorder() {
		return porder;
	}
	public void setPorder(Integer porder) {
		this.porder = porder;
	}
	public Integer getPcode() {
		return pcode;
	}
	public void setPcode(Integer pcode) {
		this.pcode = pcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7242492948024871836L;

	private Integer id;// 主键id
	private String ptype;// 参数类别
	private String pname;// 参数名称
	private Integer isshow;// 是否显示
	private Integer porder;// 排序序号
	private Integer pcode;// 参数编码

}
