<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="e" uri="/yz"%> 
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/category/updateCategory.action" enctype="multipart/form-data" method="post">
      　
        <div class="dd-retail-box">
        	<div class="dd-retail-title">物流信息</div>
        	<div class="dd-retail-table">
        		<table class="table table-striped table-bordered table-hover">
        				<tr> 
        					<th>时间</th>
        					<th>快递公司编号</th>
        					<th>运单号</th>
        					<th>内容</th>
        					<th>备注</th>
        				</tr>
        				<c:forEach items="${wuliulist }" var="item">
	        				　<tr>
	        					<td>${item.accepttime }</td>
	        					<td>${item.comcode }</td>
	        					<td>${item.ydh }</td>
	        					<td>${item.acceptstation }</td>
	        					<td>${item.remark }</td> 
	        				  </tr>
        				</c:forEach>
        		</table>
        	</div>
        </div>
		</form>
			        	
 </div>
<%@include file="../../../common/admin_footer.jsp"%>