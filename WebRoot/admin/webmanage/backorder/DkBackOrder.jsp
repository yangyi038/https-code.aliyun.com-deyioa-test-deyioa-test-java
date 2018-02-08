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
<script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"> </script>

<div class="modal-real-body">
<style>
.noneImport{
display: none!important;}
.alertmodel{
height:200px;
}
</style>
	<form action="<%=basepath%>/admin/backorder/transfer.action" enctype="multipart/form-data" method="post"> 
      	<input type="hidden"   name="user_id"  value="${backinfo.USER_ID }">
      	  	<input type="hidden"   name="back_id"  value="${backinfo.BACK_ID }">
      	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>支付方式:</span>
       		<div class="admin-s"> 
       		<select name="pay_id"  id="pay_id"> 
	 				<option value="3">余额支付</option> 
       		 </select>
       	</div>
       	</div>
       		<div class="admin-s-body block  "    >
       		<span><b style="color: red;">*</b>请输入退款金额:</span>
       		<div class="admin-s"> 
       		<input type="text" name="amount"   id="amount"  placeholder="请输入退款金额"  /> 
       	</div>
       	</div>
     <div class="admin-s-body block  "    >
       		<span>请输入账号:</span>
       		<div class="admin-s"> 
       		<input type="text" name="toaccount"   id="toaccount"  placeholder="请输入账号"  /> 
       	</div>
       	</div>
       	 
     <div class="admin-s-body block  "    >
       		<span>请输入姓名:</span>
       		<div class="admin-s"> 
       		<input type="text" name="toname"   id="toname"  placeholder="请输入姓名"  /> 
       	</div>
       	</div> 
       	<div class="admin-s-body  block"  >
       		<span class="top"> 备注内容</span>
       		<div class="admin-s"><textarea rows="5" cols="50" placeholder="请输入备注"  name="remark"   id="remark"></textarea>
       	</div> 	 
       	</div>  
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('dk_orderinfo_m');window.parent.refreshGrid('backorderlist');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
 
function check(){
	var errorinfo=""; 
		var amount = $('#amount').val();
		if(amount==null||amount==''){
			errorinfo+="退款金额不能为空</br>";
		}    
		
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>