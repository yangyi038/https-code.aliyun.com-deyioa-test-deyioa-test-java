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
	<form action="<%=basepath%>/admin/backorder/addBackLog.action" enctype="multipart/form-data" method="post"> 
      	<div class="admin-s-body block">
      	<input type="hidden" name="back_id"  value="${backorder.BACK_ID }">
       		<span><b style="color: red;">*</b>退换货状态:</span>
       		<div class="admin-s">
       		<c:if test="${backorder.BACKTYPE==1 }">
       		<select  name="status" id="status" onchange="getKdg()">
       				<option value="1">客服审核中</option> 
       				<option value="2">退回中</option> 
       				<option value="3">已取回</option> 
       				<option value="4">退货处理中</option> 
       				<option value="5">打款中</option> 
       				<option value="6">打款成功</option> 
       				<option value="10">完成</option> 
       				<option value="11">失败</option> 
       		</select>
       		 </c:if> 
       		 <c:if test="${backorder.BACKTYPE==2 }">
       		<select  name="status" id="status" onchange="getKdg()">
       				<option value="1">客服审核中</option> 
       				<option value="2">退回中</option> 
       				<option value="3">已取回</option> 
       				<option value="7">换货处理中</option> 
       				<option value="8">发货中</option> 
       				<option value="9">取货</option> 
       				<option value="10">完成</option> 
       				<option value="11">失败</option> 
       		</select>
       		 </c:if>
       	</div>
       	</div>
       		<div class="admin-s-body block noneImport"    id="kdg">
       		<span><b style="color: red;">*</b>请选择快递柜号:</span>
       		<div class="admin-s"> 
       		<input type="text" name="issuenum"   id="issuenum"  placeholder="请选择快递柜号"  /> 
       	</div>
       	</div>
     
       	 
       	<div class="admin-s-body  block"  >
       		<span class="top"> 备注内容</span>
       		<div class="admin-s"><textarea rows="5" cols="50" placeholder="请输入备注"  name="retremark"   id="retremark"></textarea>
       	</div> 	 
       	</div>  
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('cl_orderinfo_m');window.parent.refreshGrid('backorderlist');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
function getKdg(){
	var status = $('#status').val();
	if(status==2){
		$('#kdg').removeClass('noneImport');
	}else{ 
		$('#kdg').addClass('noneImport');
	}
}
function check(){
	var errorinfo="";
	var status = $('#status').val();
	if(status==2){ 
		var status = $('#status').val();
		if(issuenum==null||issuenum==''){
			errorinfo+="快递柜号不能为空</br>";
		}  
	}
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>