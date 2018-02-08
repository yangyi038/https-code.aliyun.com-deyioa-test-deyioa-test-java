/**
* <p>标题: 通用查询组件</p>
* <p>说明: 可以通过配置文件以及传递参数等方式进行分页查询,可以返回分页结果集合</p>
* <p>公司: 浙江源中通信信息技术有限公司</p>
* <p>时间: 2005-11-3014:58:42</p>
*
* @author dyl
* @version 1.0
*/
package com.framework;

import com.framework.util.Tools;;

public class Pager {
 private int firstRow;// 起始行数
 private int listRows;// 列表每页显示行数
 private String parameter;//页数跳转时要带的参数
 private int totalPages;//分页总页面数
 private int totalRows;//总行数
 private int nowPage; //当前页数
 private int coolPages=10; //分页的栏的总页数
 private String url;//分页链接
 private String  pageText;//分页内容
 private String  pageMore;
 public Pager(int totalRows_,int listRows_,String parameter_,String url_,int nowPage_){
	 totalRows=totalRows_;
	 listRows=listRows_;
	 parameter=parameter_;
	 url=Tools.invokeRegx(url_,"&currentPage=\\d{1,}","");
	 nowPage=nowPage_;
	 firstRow=listRows*(nowPage-1);
	 if(totalRows%listRows==0){
		 totalPages=totalRows/listRows;
	 }else{
		 totalPages=totalRows/listRows+1;
	 }
	 
	 pageText=getPageText();
 }
 
 public String getPageMore(){
	 StringBuffer sb=new StringBuffer();
	 int downRow=nowPage+1;
	 
	 
	 if(downRow<=totalPages){
		 sb.append("<div "+parameter+" class=\"zone_tlt\"><a href=\""+url+"\"> 更多信息</a></div>");
	 }
	 return sb.toString();
 }

 public String getPageText(){
	 StringBuffer sb=new StringBuffer();
	 int upRow=nowPage-1;
	 int downRow=nowPage+1;
	 if (nowPage>1){
		 sb.append("<a href='javascript:"+url+"(1);' class='a2'> 首页</a>　<a href='javascript:"+url+"("+upRow+");' class='a2'>上一页</a>");
	 }
										 
	 if (nowPage!=totalPages){
		 sb.append("&nbsp;&nbsp;&nbsp;<a href='javascript:"+url+"("+downRow+")' class='a2'>下一页</a>　<a href='javascript:"+url+"("+totalPages+")' class='a2'>尾页</a>");
	 }
	 sb.append("&nbsp;&nbsp;&nbsp;当前第"+nowPage+"页　共  "+totalPages+"  页　每页显示"+listRows+"条　共"+totalRows+"条　转到");
	 sb.append("<select name='pageselect' onchange=\"if(this.options[this.selectedIndex].value != '') {"+url+"(this.options[this.selectedIndex].value) }\">");
	 for (int id=1;id<totalPages+1;id++){
		 sb.append("<option value='"+id+"'  ");
		 if (nowPage==id){
			 sb.append("selected");
		 }
		 sb.append(" >"+id+"</option>");
	 }
	 sb.append("</select>");
//	 sb.append("<div class=\"blk_page\" id=\"page\">");
//	 int upRow=nowPage-1;
//	 int downRow=nowPage+1;
//	 if(upRow>0){
//		 sb.append("<a href=\""+url+"&currentPage="+upRow+"\" class=\"a2\"> &lt;上一页 </a>");
//	 }
//	 if(totalPages>10){
//		 int i=1;//起始页数
//		 if(nowPage>5){//以第5页为中心
//			 i=nowPage-5+1;
//		 }
//		 int ii=nowPage+5-totalPages;
//		 if(ii>0){
//			 i=i-ii; 
//		 }
//		
//		 int zhend=i+9;//最后页数
//		 for(;i<=zhend;i++){
//			 if(nowPage==i){
//				 sb.append("<a class=\"nub hover\">"+i+"</a>");
//			 }else{
//				 sb.append("<a href=\""+url+"&currentPage="+i+"\" class=\"a2\">"+i+"</a>");
//			 }
//		 }
//		 if(totalPages==zhend){
//			 
//		 }else if(totalPages-zhend>=2){
//			 sb.append("<a class=\"nub hover\">...</a>");
//			 sb.append("<a href=\""+url+"&currentPage="+totalPages+"\" class=\"a2\">"+totalPages+"</a>");
//		 }else{
//			 sb.append("<a href=\""+url+"&currentPage="+totalPages+"\" class=\"a2\">"+totalPages+"</a>");
//		 }
//	 }else{
//		 for(int i=1;i<=totalPages;i++){
//			 if(nowPage==i){
//				 sb.append("<a class=\"nub hover\">"+i+"</a>");
//			 }else{
//				 sb.append("<a href=\""+url+"&currentPage="+i+"\" class=\"a2\">"+i+"</a>");
//			 }
//		 }
//	 }
//	if(downRow<=totalPages){
//		 sb.append("<a href=\""+url+"&currentPage="+downRow+"\" class=\"a2\"> 下一页 &gt;</a>");
//	}
//	sb.append("</div>");
	return sb.toString();
 }
 
public int getFirstRow() {
	return firstRow;
}
public void setFirstRow(int firstRow) {
	this.firstRow = firstRow;
}
public int getListRows() {
	return listRows;
}
public void setListRows(int listRows) {
	this.listRows = listRows;
}
public String getParameter() {
	return parameter;
}
public void setParameter(String parameter) {
	this.parameter = parameter;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}
public int getTotalRows() {
	return totalRows;
}
public void setTotalRows(int totalRows) {
	this.totalRows = totalRows;
}
public int getNowPage() {
	return nowPage;
}
public void setNowPage(int nowPage) {
	this.nowPage = nowPage;
}
public int getCoolPages() {
	return coolPages;
}
public void setCoolPages(int coolPages) {
	this.coolPages = coolPages;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = Tools.invokeRegx(url,"&currentPage=\\d{0,}","");;
}

}
