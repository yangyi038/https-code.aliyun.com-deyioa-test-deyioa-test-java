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
			
	
    <div style="float:left;"> <span>name:</span></div>	<div style="margin-left:112px;"><input type="text" name="name"  style="width: 400px" value="${model.name}"/> </div> </div></div><br/>	
	<div style="float:left;"> <span>val:</span></div>	<div style="margin-left:112px;"><input type="text" name="val"  style="width: 400px" value="${model.val}"/> </div> </div></div> 	
	<input name="id" value="${model.id}" style="visibility:hidden;" />
	

</div>



<%@include file="../../../common/admin_footer.jsp"%>
