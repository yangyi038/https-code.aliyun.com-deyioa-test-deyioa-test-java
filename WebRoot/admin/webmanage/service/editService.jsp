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
	<form action="<%=basepath%>/admin/service/updateService.action" enctype="multipart/form-data" method="post"> 
      		<input type="hidden"  name="id"  value="${service.id }">
      		<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>标签名称</span>
       		<div class="admin-s"><input type="text" name="ser_name"   id="ser_name"  value="${service.ser_name }"/></div>
       	</div>
       	<div class="admin-s-body block ">
       		<span class="top"><b style="color: red;">*</b>标签描述:</span>
       		<div class="admin-s"><textarea rows="5" cols="90"  id="remark"  name="remark" >${service.remark }</textarea></div>
       	</div> 
    	 	 
			        	</div>
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('edit_service_m');window.parent.refreshGrid('service');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript">  
function check(){
	var errorinfo="";  
var ser_name =$('#ser_name').val();	
if(ser_name==null||ser_name==''){
		errorinfo+="标签名称不能为空</br>";
	} 
var remark =$('#remark').val();	
if(remark==null||remark==''){
		errorinfo+="标签备注不能为空</br>";
	}   
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
</script>
<%@include file="../../../common/admin_footer.jsp"%>