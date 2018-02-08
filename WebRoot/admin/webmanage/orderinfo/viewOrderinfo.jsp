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
			       		<span class="title">订单号:</span>
			       		<div class="admin-s">
			       			${order_info.order_sn}
			       		</div>
			       	</div>
			       	<div class="admin-s-body admin-s-detail">
			       		<span class="title">订单状态:</span>
			       		<div class="admin-s"> 
			       		${fns:getCodeName(order_info.order_status,'order_status')} 
			       		</div>
			       	</div>
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">订单金额:</span>
			       		<div class="admin-s">
			       			${order_info.order_amount}
			       		</div>
			       	</div>
        	</div>
        </div>
        
        <!-- 订单信息 -->
           <div class="dd-retail-box">
        	<div class="dd-retail-title">订单信息</div>
        	<div class="dd-retail-info">
        		<div class="admin-s-body admin-s-detail">
			       		<span class="title">用户名:</span>
			       		<div class="admin-s"> 
			       			${order_info.nickname}
			       		</div>
			       	</div> 
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">支付方式:</span>
			       		<div class="admin-s">
			       			${order_info.pay_name}
			       		</div>
			       	</div> 
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">下单时间:</span>
			       		<div class="admin-s">
			       			${order_info.addtime}
			       		</div>
			       	</div> 
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">支付通道:</span>
			       		<div class="admin-s">
			       			${order_info.pay_name}
			       		</div>
			       	</div>
        	</div>
        </div>
        
        
         <!-- 收货信息 -->
           <div class="dd-retail-box">
        	<div class="dd-retail-title">收货信息</div>
        	<div class="dd-retail-info">
        		<div class="admin-s-body admin-s-detail">
			       		<span class="title">收货人姓名:</span>
			       		<div class="admin-s">
			       			${order_info.consignee}
			       		</div>
			       	</div>
			       	<div class="admin-s-body admin-s-detail">
			       		<span class="title">收货人手机:</span>
			       		<div class="admin-s">
			       			${order_info.mobile}
			       		</div>
			       	</div>
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">邮编:</span>
			       		<div class="admin-s">
			       			${order_info.zipcode}
			       		</div>
			       	</div> 
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">收货地区</span>
			       		<div class="admin-s">
			       			${order_info.provincename}-${order_info.cityname}-${order_info.districtname}
			       		</div>
			       	</div> 
			       		<div class="admin-s-body admin-s-detail">
			       		<span class="title">具体地址:</span>
			       		<div class="admin-s">
			       			${order_info.address}
			       		</div>
			       	</div>
        	</div>
        </div>  
        <!-- 发票 -->
         <div class="dd-retail-box">
        	<div class="dd-retail-title">发票信息</div>
        	<div class="dd-retail-info">
        		<div class="admin-s-body admin-s-detail">
			       		<span class="title">开票抬头:</span>
			       		<div class="admin-s">
			       			${order_info.inv_payee}
			       		</div>
			       	</div>
			       	<div class="admin-s-body admin-s-detail">
			       		<span class="title">开票内容:</span>
			       		<div class="admin-s">
			       			${order_info.inv_content}
			       		</div>
			       	</div> 
        	</div>
        </div>
        
        <div class="dd-retail-box">
        	<div class="dd-retail-title">商品信息</div>
        	<div class="dd-retail-table">
        		<table class="table table-striped table-bordered table-hover">
        				<tr>
        					<th></th>
        					<th>商品名称</th>
        					<th>单价</th>
        					<th>数量</th>
        					<th>小计</th>
        				</tr>
        				<c:forEach items="${goods_info }" var="item">
	        				<tr>
	        					<td><img src="<%=basepath%>/upload/goods/${item.goods_id }/attr/${item.attr_img }" ></td>
	        					<td>${item.goods_name }</td>
	        					<td>${item.goods_price }</td>
	        					<td>${item.goods_number }</td>
	        					<td>${item.xj }</td>
	        				</tr> 
        				</c:forEach>
        		</table>
        	</div>
        </div>
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" callback="window.parent.closeDialogm('category_edit_d');window.parent.rs('${category.parent_id}');">
		${meg}
	</e:msgdialog>
</c:if> 
<%@include file="../../../common/admin_footer.jsp"%>