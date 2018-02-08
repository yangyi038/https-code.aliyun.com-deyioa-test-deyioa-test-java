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
	<form action="<%=basepath%>/admin/t_column/updateProgram.action" enctype="multipart/form-data" method="post">
			<input type="text" name="id"  value="${propgram.id}" style="visibility:hidden;" /><br/>
			
			<div class="admin-s-body"> <span><font style="color:red;">*</font>排序号码:</span> <div class="admin-s"> <input type="text" name="number"  value="${propgram.number}" /> </div> </div>      
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>位子:</span> <div class="admin-s"> <input type="text" name="seat"  value="${propgram.seat}" /> </div> </div><br/>  
		    <div class="admin-s-body"> <span>显示类型:</span> <div class="admin-s"> <input type="text" name="showType"  value="${propgram.showType}"/> </div> </div>   
		    
			<div class="admin-s-body"> <span>播放垫片:</span> <div class="admin-s"> 
				<select  name="shim"  placeholder="" value="${propgram.shim}"/>
		    		<option  <c:if test="${propgram.shim=='无'}">selected="selected"</c:if>>无</option>
 		 			<option <c:if test="${propgram.shim=='视频'}">selected="selected"</c:if>>视频</option>
 		 			<option <c:if test="${propgram.shim=='图片'}">selected="selected"</c:if>>图片</option>
 		 			<option <c:if test="${propgram.shim=='网页'}">selected="selected"</c:if>>网页</option>
		    	</select>
		    	
		    	
		    </div> </div>   <br/> 
			
			<div class="admin-s-body"> <span>垫片URL:</span> <div class="admin-s"> <input type="text" name="shimUrl"  value="${propgram.shimUrl}"/> </div> </div> 
			<div class="admin-s-body"> <span>参数配置:</span> <div class="admin-s"> <input type="text" name="preferences"  value="${propgram.preferences}"/> </div> </div> 
			
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
			
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_program_m');window.parent.refreshGrid('titleColumn');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	
	var seat=$("input[name=seat]").val();
	if(seat==""){
		info+="位子不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	
	
	return true;
}
window.onload = function () { 
	var type = $("#type");
	alert(type);
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>
