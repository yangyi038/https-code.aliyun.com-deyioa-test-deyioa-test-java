/**
* <p>标题: 通用查询组件</p>
* <p>说明: 可以通过配置文件以及传递参数等方式进行分页查询,可以返回分页结果集合</p>
* <p>公司: 浙江源中通信信息技术有限公司</p>
* <p>版权: Copyright 2005 .Zhejiang Top Thinking information technology CO.,Ltd.</p>
* <p>时间: 2005-11-3014:58:42</p>
*
* @author zqb
* @version 1.0
*/
package com.framework.jqgrid;

import javax.servlet.http.HttpServletRequest;

public class JqGridPager {
	private String id;//行id
	private int pager;//当前页数
	private int total;//总页数
	private String records;//总共的数据
	private int rows;//包含实际数据的数组
	private String pgButton;//翻页动作first,last,prev,next
	private String sord;//排序类别
	private String sidx;//排序字段
	private int startRow; //当前页在数据库中的起始行
	private String where;//查询条件
	private String export;//是否导出excel，excel：当前页，allexcel：全部页
	private String exportheader;//自定义excel头显示文本，以字符串格式，逗号分开
	private String exportheadermate;//自定义excel头显示对应字段，以字符串格式，逗号分开
	public JqGridPager(String id_,int pager_,int total_,String records_,int rows_,String pgButton_,String sord_,String sidx_){
		 id=id_;
		 pager=pager_;
		 total=total_;
		 records=records_;
		 rows=rows_;
		 pgButton=pgButton_;
		 sord=sord_;
		 sidx=sidx_;
	 }
	public JqGridPager(HttpServletRequest request){
		
		this.setId(request.getParameter("id"));
		this.setPager(Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page")))	;
		this.setTotal(Integer.valueOf(request.getParameter("total")==null?"0":request.getParameter("total")));
		this.setRows(Integer.valueOf(request.getParameter("rows")==null?"0":request.getParameter("rows")));
		this.setPgButton(request.getParameter("pgButton"));
		this.setSord( request.getParameter("sord"));
		this.setSidx(request.getParameter("sidx"));
		this.setExport(request.getParameter("export"));
		this.setPage();
		
	}
	public void setRecords(String records) {
		this.records = records;
		try {
			if(Integer.valueOf(records)%rows>0){
				setTotal(Integer.valueOf(records)/rows+1);	
			}else{
				setTotal(Integer.valueOf(records)/rows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPage(){
		if(pgButton!=null){
		if(pgButton.equals("next")&&pgButton!=null){//下一页
		     if (pager < total) {
		    	 pager++;
		       }
		       startRow = (pager - 1) * rows;
	   }else if(pgButton.equals("prev")){//上一页
		   if (pager == 0) {	      
		     }else{
		     startRow = (pager - 1) * rows;
		     }
	   }else if(pgButton.equals("last")){//最后一页
		   //pager = total;
		     startRow = (pager - 1) * rows;
	   }else if(pgButton.equals("first")){//第一页
		   pager = 1;
		     startRow = 0;
	   }else if(pager>1){
		   //pager = 1;
		   startRow = (pager - 1) * rows;
	    }else{
	 	   pager = 1;
		     startRow = 0;
	    }
		}else{
			pager = 1;
		     startRow = 0;
	   }	
	}
	//public String getRecordsSql(String sql){
	//}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPager() {
		return pager;
	}
	public void setPager(int pager) {
		this.pager = pager;
	}
	public String getPgButton() {
		return pgButton;
	}
	public void setPgButton(String pgButton) {
		this.pgButton = pgButton;
	}
	public String getRecords() {
		return records;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getExport() {
		return export;
	}
	public void setExport(String export) {
		this.export = export;
	}
	public String getExportheader() {
		return exportheader;
	}
	public void setExportheader(String exportheader) {
		this.exportheader = exportheader;
	}
	public String getExportheadermate() {
		return exportheadermate;
	}
	public void setExportheadermate(String exportheadermate) {
		this.exportheadermate = exportheadermate;
	}
}
