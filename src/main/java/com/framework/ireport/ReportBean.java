package com.framework.ireport;

import java.util.List;
import java.util.Map;

public class ReportBean {
	private String jasperPath;
	private Map params;
	private List sourceList;
	public String getJasperPath() {
		return jasperPath;
	}
	public void setJasperPath(String jasperPath) {
		this.jasperPath = jasperPath;
	}
	public Map getParams() {
		return params;
	}
	public void setParams(Map params) {
		this.params = params;
	}
	public List getSourceList() {
		return sourceList;
	}
	public void setSourceList(List sourceList) {
		this.sourceList = sourceList;
	}
}
