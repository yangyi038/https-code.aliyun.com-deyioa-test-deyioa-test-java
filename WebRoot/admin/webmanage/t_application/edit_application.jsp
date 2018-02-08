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
	<form action="<%=basepath%>/admin/t_application/updatAppManager.action" enctype="multipart/form-data" method="post">
	
			<input type="text" name="id"  value="${appManager.id}" style="visibility:hidden;" /><br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>应用名称:</span> <div class="admin-s"> <input type="text" name="name"  value="${appManager.name}"/> </div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>开发者:</span> <div class="admin-s"> <input type="text" name="developerName"  value="${appManager.developerName}"/> </div> </div> <br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>评分:</span> <div class="admin-s"> <input type="text" name="grader"  value="${appManager.grader}"/> </div> </div>  
       		<div class="admin-s-body"> <span>参数类型:</span> <div class="admin-s"> 
       			<select name="parameterType" id="select">
					<c:forEach items="${list}" var="item">
						<option>${item.parameterType}</option>
					</c:forEach>
				</select>
       		</div> </div>  <br/>
       		<div class="admin-s-body"> <span>应用参数:</span> <div class="admin-s"><input type="text" name="applyParameter"  value="${appManager.applyParameter}" style="width:482px;"/> </div> </div> <br/>
       		<div class="admin-s-body"> <span>简介:</span> <div class="admin-s"> <textarea rows="5" cols="4" type="text" name="synopsis"  style="width:482px;">${appManager.synopsis} </textarea> </div> </div>   
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
			
		</form>
			        	
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_appManager_m');window.parent.refreshGrid('appManager');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var name=$("input[name=name]").val();
	if(name==""){
		info+="应用名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var developerName=$("input[name=developerName]").val();
	if(developerName==""){
		info+="开发者不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var grader=$("input[name=grader]").val();
	if(grader==""){
		
		info+="评分不能为空！";
		modalDialogAlert(info);
		return false;
	}
	var reg=/^\d*\.{0,1}\d{0,1}$/;
		
	if(reg.test(grader)==true){
		if(grader<=10){
			return true;
		}else{
			info+="评分只能输入1到10 的数字！";
			modalDialogAlert(info);
			return false;
		}
		
	}else{
		info+="评分输入有误，只能输入数字！";
		modalDialogAlert(info);
		return false;
	}
	
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
