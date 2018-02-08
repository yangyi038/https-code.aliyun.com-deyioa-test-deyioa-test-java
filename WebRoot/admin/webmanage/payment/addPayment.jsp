<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>

<script type="text/javascript" charset="utf-8" src="<%=basepath %>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basepath %>/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basepath %>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<div class="modal-real-body">

<style>

.alertmodel{
height:200px;
}
</style>
	<form action="<%=basepath%>/admin/payment/addPayment.action" enctype="multipart/form-data" method="post"> 
      		<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>支付方式名称</span>
       		<div class="admin-s"><input type="text" name="pay_name"   id="pay_name"/></div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>支付方式描述</span>
       		<div class="admin-s"><input type="text" name="pay_desc"   id="pay_desc"/></div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>支付方式代码</span>
       		<div class="admin-s"><input type="text" name="pay_code"   id="pay_code"/>( 汉字首字母或英文缩写)</div>
       	</div>
      	<div class="admin-s-body block">
       		<span>是否可用</span>
       		<div class="admin-s">
<div class="admin-s">  
			        			 <select name="enabled" id="enabled" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'pay_enabled')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>       		
       		</div>
       	</div>
       	 
       	<div class="admin-s-body block">
       		<span>是否货到付款</span>
       		<div class="admin-s">  
			        			 <select name="is_cod" id="is_cod" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'is_cod')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>
       	<div class="admin-s-body block">
       		<span>是否在线支付</span>
       		<div class="admin-s">  
			        			 <select name="is_online" id="is_online" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'is_online')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>排序</span>
       		<div class="admin-s"><input type="text" name="pay_order"  id="pay_order"  placeholder="请输入显示顺序"  />(数字范围为0~255，数字越大越靠前
       		)</div>
       	</div>
       	
    	 	 
			        	</div>
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('add_payment_m');window.parent.refreshGrid('payment');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript">  
function check(){
	var errorinfo="";  
var pay_name =$('#pay_name').val();	
if(pay_name==null||pay_name==''){
		errorinfo+="支付名称不能为空</br>";
	} 
var pay_desc =$('#pay_desc').val();	
if(pay_desc==null||pay_desc==''){
		errorinfo+="支付介绍不能为空</br>";
	}  
	var pay_code =$('#pay_code').val();	
	if(pay_code==null||pay_code==''){
		errorinfo+="支付代码不能为空</br>";
	} 
var pay_order =$('#pay_order').val();	 
if(pay_order==null||pay_order==''){
		errorinfo+="排序不能为空</br>";
	}	
 
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
</script>
<%@include file="../../../common/admin_footer.jsp"%>