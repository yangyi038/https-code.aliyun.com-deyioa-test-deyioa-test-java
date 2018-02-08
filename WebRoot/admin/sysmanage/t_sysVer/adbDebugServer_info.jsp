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

			
	
	<div style="float:left;"> <span><font style="color:red;">*</font>ip:</span></div>	<div style="margin-left:130px;">
		<input type="text" name="ip"  style="width: 400px" value="${model.ip}"/> 
		
	</div> </div></div><br/>
    <div style="float:left;"> <span><font style="color:red;">*</font>port:</span></div>	<div style="margin-left:130px;"><input type="text" name="port"  style="width: 400px" value="${model.port}"/> </div> </div></div><br/>	
	<div style="float:left;"> <span><font style="color:red;">*</font>key:</span></div>	<div style="margin-left:130px;"><input type="text" name="adbKey"  style="width: 400px" value="${model.adbKey}"/> </div> </div></div> 	<br/>
	<input name="id" value="${model.id}" style="visibility:hidden;" />
	
	
       	
			        	
</div>

<%@include file="../../../common/admin_footer.jsp"%>
