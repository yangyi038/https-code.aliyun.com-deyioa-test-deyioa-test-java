package com.framework.jqgrid;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.excelTools.DoExcel;
import com.framework.util.JsonUtil;
import com.framework.util.Tools;

public class JqGridTool {
	/**
	 *jqGrid组件统一查询方法
	 */
	public static String jqGridQuery(HttpServletRequest request,HttpServletResponse response,List<?> listdata,
			JqGridPager jqGridPager) throws Exception {
		try {
			if(jqGridPager.getExport()!=null&&!"".equals(jqGridPager.getExport())){
				 String filename=Tools.getRndFilename();
				 String header="";
				 if(jqGridPager.getExportheader()!=null&&!jqGridPager.getExportheader().equals("")){
					 header=jqGridPager.getExportheader();
				 }else{
					 header=request.getParameter("header");
				 }
				 String headermate="";
				 if(jqGridPager.getExportheadermate()!=null&&!jqGridPager.getExportheadermate().equals("")){
					 headermate=jqGridPager.getExportheadermate();
				 }else{
					 headermate=request.getParameter("headermate");
				 }
				if(header!=null&&!header.equals("")&&headermate!=null&&!headermate.equals("")){
					DoExcel.excelExport(listdata, response,filename, header, headermate);
				}
			}else{
		    response.setContentType("text/xml; charset=UTF-8");//传输xml时要用html 
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(JsonUtil.spanJson(listdata,jqGridPager));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null; 

	}
}
