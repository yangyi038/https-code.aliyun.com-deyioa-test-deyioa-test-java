<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>
<div class="modal-real-body">
<form action="<%=basepath%>/admin/sysuser/updatePsw.action" enctype="multipart/form-data" method="post">
      	<div class="page-content">
      		<div class="admin-s-body block">
       		<div>输入新密码</div>
       		<div class="admin-s"><input type="text" name="loginpwd" size="22" id="loginpwd"/></div>
       	</div>
       	<div class="admin-s-body block">
       		<div>确定新密码</div>
       		<div class="admin-s"><input type="text" name="loginpwd1" size="22" id="loginpwd1"/></div>
       	</div>
			        	<div class="">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">提交</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="">
							${meg}
						</e:msgdialog>
					</c:if>
      	</div>
      	<script type="text/javascript">
function check(){
	var info="";
	var loginpwd=$("#loginpwd").val();
	if(loginpwd==""){
		info+="请输入密码！";
	}
	var loginpwd1=$("#loginpwd1").val();
	if(loginpwd1==""){
		info+="请确定密码！";
	}
	
	if(info!=""){
		modalDialogAlert(info);
		return false;
	}
    if(loginpwd!=loginpwd1){
    	modalDialogAlert("前后密码输入不一致！");
		return false;
	}
	return true;
}



</script>
      	
      	
<%@include file="../../../common/admin_footer.jsp"%>