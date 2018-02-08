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
	<form action="<%=basepath%>/admin/t_sysVer/updateRealTimeControl.action" enctype="multipart/form-data" method="post">
			
	
    <div style="float:left;"> <span>admin:</span></div>	<div style="margin-left:112px;"><input type="text" name="name"  style="width: 400px" value="${model.name}"/> </div> </div></div><br/>	
	<input name="id" value="${model.id}" style="visibility:hidden;" />
		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
		</div>
	</form>
			        	
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_realTimeControl_m');window.parent.refreshGrid('realTimeControl');
  		
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">

</script>
<%@include file="../../../common/admin_footer.jsp"%>
