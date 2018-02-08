package com.fs.comm.model;

public class City  implements java.io.Serializable {
	private String dm;//代码
	private String mc;//名称
	private String yxzt;//有效标记
	private String isleaf;//是否叶子节点
	private String path;//路径
	private String described;
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getYxzt() {
		return yxzt;
	}
	public void setYxzt(String yxzt) {
		this.yxzt = yxzt;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescribed() {
		return described;
	}
	public void setDescribed(String described) {
		this.described = described;
	}
	
}
