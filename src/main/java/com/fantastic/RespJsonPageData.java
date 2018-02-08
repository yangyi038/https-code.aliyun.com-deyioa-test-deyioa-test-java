package com.fantastic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.framework.excelTools.DoExcel;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.Tools;

/**
 * 
 * @author lqf
 *
 */
public class RespJsonPageData {
	private int total;
	private int page;
	private String records;
	private List<?> rows;
	private Map userdata;
	
	public Map getUserdata() {
		return userdata;
	}

	public void setUserdata(Map userdata) {
		this.userdata = userdata;
	}

	public void pkgdata(List<?> datas,JqGridPager jqGridPager){
		total = jqGridPager.getTotal();
		page = jqGridPager.getPager();
		records = jqGridPager.getRecords();
		rows = datas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public com.fantastic.RespJsonPageData createFinallyResp(JqGridPager jqGridPager, Map<String, Object> p, HttpServletResponse response) {
		 if(jqGridPager.getExport()!=null&&!"".equals(jqGridPager.getExport())){
		     String filename=Tools.getRndFilename();
		     String header="";
		     if(jqGridPager.getExportheader()!=null&&!jqGridPager.getExportheader().equals("")){
		      header=jqGridPager.getExportheader();
		     }else{
		      header=(String) p.get("header");
		     }
		     String headermate="";
		     if(jqGridPager.getExportheadermate()!=null&&!jqGridPager.getExportheadermate().equals("")){
		      headermate=jqGridPager.getExportheadermate();
		     }else{
		      headermate=(String) p.get("headermate");
		     }
		    if(header!=null&&!header.equals("")&&headermate!=null&&!headermate.equals("")){
		     try {
				DoExcel.excelExport(this.rows, response,filename, header, headermate);
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		   }
		return this;
	}

}
