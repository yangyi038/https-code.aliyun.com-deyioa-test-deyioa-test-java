<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script language="javascript" type="text/javascript"	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js" type="text/javascript"></script>	
 
<div class="modal-real-body" >
	<form action="<%=basepath%>/admin/t_application/updatAppEditions.action" enctype="multipart/form-data" method="post">
	
			<div class="admin-s-body"> <span>应用列表名称:</span> <div class="admin-s"> <input type="text"  value="${appEditions.applicationName}" readonly="readonly" style="width:300px;" /> </div> </div> <br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>版本名称:</span> <div class="admin-s"> <input type="text" name="name"  value="${appEditions.name}" style="width:300px;"/> </div> </div> <br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>下载地址:</span> <div class="admin-s"> <input type="text" name="downloadUrl"  value="${appEditions.name}" style="width:300px;"/> </div> </div> <br/>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>包名:</span> <div class="admin-s"> <input type="text" name="packageName"  value="${appEditions.packageName}" style="width:300px;"/> </div> </div> <br/>
			<div class="admin-s-body"> <span>应用版本:</span> <div class="admin-s"> <input type="text" name="version"  value="${appEditions.version}" style="width:300px;" /> </div> </div><br/>  
			<div class="admin-s-body"> <span>最低系统:</span> <div class="admin-s"> <input type="text" name="lowestSystem"  value="${appEditions.lowestSystem}" style="width:300px;"/> </div> </div><br/>  
			<input type="text" name="id"  value="${appEditions.id}" style="visibility:hidden;" /><br/>
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>  
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_appEditions_m');window.parent.refreshGrid('appEditions');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var classify=$("#applicationName").val();
	if(classify=="no"){
		info+="应用列表名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var name=$("input[name=name]").val();
	if(name==""){
		info+="版本名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var downloadUrl=$("input[name=downloadUrl]").val();
	if(downloadUrl==""){
		info+="下载地址不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var packageName=$("input[name=packageName]").val();
	if(packageName==""){
		info+="包名不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
