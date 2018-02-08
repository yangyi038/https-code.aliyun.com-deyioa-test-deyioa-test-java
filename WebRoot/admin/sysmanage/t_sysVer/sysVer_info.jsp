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
	<form action="<%=basepath%>/admin/t_sysVer/addT_sysVer.action" enctype="multipart/form-data" method="post">
			
	 <div style="float:left;"> <span>参数:</span></div>	<div style="margin-left:112px;"><input type="text" value="${model.code}" style="width: 400px"/> </div> </div></div><br/><br/> 
	
    <div style="float:left;"> <span>名称:</span></div>	<div style="margin-left:112px;"><input type="text" value="${model.name}"  style="width: 400px"/> </div> </div></div><br/><br/> 	
	
	<div style="float:left;"> <span>组名:</span></div>	<div style="margin-left:112px;"><input type="text" value="${model.allowGroup}"  style="width: 400px"/> </div> </div></div><br/><br/> 	
	
	</form>
			        	
</div>

</script>
<%@include file="../../../common/admin_footer.jsp"%>
