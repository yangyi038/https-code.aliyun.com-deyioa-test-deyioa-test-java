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
<div class="modal-real-body">
<style>

.alertmodel{
height:200px;
}
</style>
	<form action="<%=basepath%>/admin/originplace/updateOriginplace.action" enctype="multipart/form-data" method="post"> 
	<input type="hidden"  name="id" value="${originplace.id }">
      	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>产地</span>
       		<div class="admin-s"><input type="text" name="name"   id="name"  placeholder="请输入产地"  value="${originplace.name }"/></div>
       	</div>
      
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('edit_originplace_m');window.parent.refreshGrid('listOriginplace');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
function check(){
	var errorinfo="";  
var name =$('#name').val();	
if(name==null||name==''){
		errorinfo+="产地不能为空</br>";
	}  
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
</script>
<%@include file="../../../common/admin_footer.jsp"%>