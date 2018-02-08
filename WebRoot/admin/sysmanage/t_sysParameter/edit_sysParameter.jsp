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
	<form action="<%=basepath%>/admin/t_sysParameter/updatSysParameter.action" enctype="multipart/form-data" method="post">
			<input type="text" name="id"  value="${model.id}" style="visibility:hidden;" /><br/>
       		<div class="admin-s-body"> <span><font style="color:red;">*</font>参数名称:</span> <div class="admin-s"> 
		    	 <input type="text" name="name"  id="name" style="width:260px;"  value="${model.name}"/>
		    </div> </div>  <br/>        
	
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>参数值:</span> <div class="admin-s"> 
		    	<input type="text" name="parameterValue"  id="parameterValue" style="width:260px;"  value="${model.parameterValue}"/>
		    </div> </div> <br/>
		    
		     <div class="admin-s-body"> <span>参数类型:</span> <div class="admin-s"> 
		    	<select  name="parameterType" id="parameterType" style="width:260px;" value="${model.parameterType}"/>
		    		<option <c:if test="${model.parameterType=='字符'}">selected="selected"</c:if>>字符</option>
 		 			<option <c:if test="${model.parameterType=='数字'}">selected="selected"</c:if>>数字</option>
		   		</select> 
		    </div> </div> <br/> 
		    
		    <div class="admin-s-body"> <span>描述:</span> <div class="admin-s"> 
		   		 <textarea rows="3" cols="2" type="text" name="sysDescribe" style="width:260px;">${model.sysDescribe}</textarea> 
		    </div> </div> <br/>
		     <div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>     
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_sys_m');window.parent.refreshGrid('sysParameter');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var name=$("#name").val();
	if(name==""){
		info+="参数名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var parameterValue=$("#parameterValue").val();
	if(parameterValue==""){
		info+="参数值不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
