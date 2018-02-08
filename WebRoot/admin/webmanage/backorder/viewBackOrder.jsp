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
        	<div class="dd-retail-title">订单状态</div>
        	<div class="dd-retail-info">
        	<div class="admin-s-body admin-s-detail">
			       		<span class="title">退换货单号:</span>
			       		<div class="admin-s">
			       			${backorder.DELIVERY_SN}
			       		</div>
			       	</div>
        		<div class="admin-s-body admin-s-detail">
			       		<span class="title">订单号:</span>
			       		<div class="admin-s">
			       			${backorder.ORDER_SN}
			       		</div>
			       	</div> 
			       	<div class="admin-s-body admin-s-detail">
			       		<span class="title">用户名:</span>
			       		<div class="admin-s">
			       			${backorder.USERNAME}
			       		</div>
			       	</div>
			       	<div class="admin-s-body admin-s-detail">
			       		<span class="title">退换货状态:</span>
			       		<div class="admin-s">
			       		${fns:getCodeName(backorder.STATUS,'back_status')} 
			       		</div>
			       	</div>
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">申请退换货时的客户附言:</span>
			       		<div class="admin-s">
			       			${backorder.POSTSCRIPT}
			       		</div>
			       	</div>
        	</div>
        </div>
        <div class="dd-retail-box">
        	<div class="dd-retail-title">退款资料</div>
        	<div class="dd-retail-info">
        	<div class="admin-s-body admin-s-detail">
			       		<span class="title">提现账号:</span>
			       		<div class="admin-s">
			       			${backorder.TOACCOUNT}
			       		</div>
			       	</div>
        		<div class="admin-s-body admin-s-detail">
			       		<span class="title">提现姓名:</span>
			       		<div class="admin-s">
			       			${backorder.TONAME}
			       		</div>
			       	</div>
			      
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">银行名称:</span>
			       		<div class="admin-s">
			       			${backorder.BANKNAME}
			       		</div>
			       	</div>
        	</div>
        </div>
      
          <div class="dd-retail-box">
        	<div class="dd-retail-title">图片</div>
        	<div class="dd-retail-info">
        	<div class="admin-s-body admin-s-detail">
			       		<span class="title">图片:</span>
			       		<div class="admin-s">
			       	 	<c:forEach items="${list}" var="item">
			        			     <img src="<%=basepath %>/upload/goods_img/${item}"/>
			        			 </c:forEach>  
			       		</div>
			       	</div>
			       	</div>
			       	</div>
        <div class="dd-retail-box">
        	<div class="dd-retail-title">商品信息</div>
        	<div class="dd-retail-table">
        		<table class="table table-striped table-bordered table-hover">
        				<tr> 
        					<th>商品货号</th>
        					<th>商品信息</th> 
        					<th>商品的退换货数量</th>  
        				</tr>
        				<c:forEach items="${backgoods }" var="item">
	        				<tr> 
	        					<td>${item.GOODS_SN }</td>
	        					<td>${item.GOODS_NAME }</td> 
	        					<td>${item.GOODS_NUMBER }</td> 
	        				</tr> 
        				</c:forEach>
        		</table>
        	</div>
        </div>
		</form>
			        	
		        	</div> 
<%@include file="../../../common/admin_footer.jsp"%>