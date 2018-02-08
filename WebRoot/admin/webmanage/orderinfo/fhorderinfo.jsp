<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="e" uri="/yz"%> 
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/orderinfo/updateForderinfo.action" enctype="multipart/form-data" method="post">
	<input type="hidden" name="order_id" id="order_id" value="${order_id }">
        <div class="dd-retail-box">
        	<div class="dd-retail-title">订单发货:</div>
        	<div class="dd-retail-info">
        		<div class="admin-s-body admin-s-detail">
			       		<span class="title">运单号:</span>
			       		<div class="admin-s">
			       			<input type="text" id="ydh" name="ydh">
			       		</div>
			       	</div>
			       	<div class="admin-s-body admin-s-detail">
			       		<span class="title">快递公司:</span>
			       		<div class="admin-s">
			       			<select name="comcode" id="comcode" >
			        			<c:forEach items="${getShipping}" var="item">
			        			 <option value="${item.shipping_id}">${item.shipping_name}</option>
			        			 </c:forEach>  
			        			 </select>
			       		</div>
			       	</div> 
        	</div>
        </div> 
        <div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
		</form>  	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('fh_orderinfo_m');window.parent.refreshGrid('orderinfolist');">
							${meg}
						</e:msgdialog>
</c:if> 
<%@include file="../../../common/admin_footer.jsp"%>
<script>
function check(){
	var errorinfo="";   
	var ydh =$('#ydh').val();	
	if(ydh==null||ydh==''){
			errorinfo+="标题不能为空</br>";
		} 
 
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
}  
</script>