 <!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script language="javascript" type="text/javascript"  src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js" type="text/javascript"></script>	
 
<div class="modal-real-body" >
	<form action="<%=basepath%>/admin/problem/importProblem.action" enctype="multipart/form-data" method="post">
		<div class="admin-s-body">
			<span><font style="color:red;">*</font>Excel文件:</span> 
			<div class="admin-s"> 
				<input type="file" name="filePath" id="fixingFile"  style="width:300px;"/>
			</div>
		</div>
		<br/>			
       		
		<div class="real-body-foot">
		    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
		</div>
	</form>		  
</div>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('import_problem_s');window.parent.refreshGrid('problemText');">
		${meg}
	</e:msgdialog>
</c:if>
<c:if test="${errorMeg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="">
		${errorMeg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	
	var info="";
	var filePath=$("input[name=filePath]").val();
	if(filePath==""){
		info+="Excel文件不能为空！";
		modalDialogAlert(info);
		return false;
	}
	return true;
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>