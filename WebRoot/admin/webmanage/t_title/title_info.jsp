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

       		<div class="admin-s-body"> <span>文本名称:</span> <div class="admin-s"> <input type="text"  value="${title.name}" style="width:300px;"/> </div> </div> <br/>
       		
       		<div class="admin-s-body"> <span>审核状态:</span> <div class="admin-s"> <input type="text"  value="${title.status}" style="width:300px;"/> </div> </div> <br/>
       		
       		<div class="admin-s-body"> <span>创建时间:</span> <div class="admin-s"> <input type="text"  value="${title.tempTime}" style="width:300px;"/> </div> </div> <br/>
	
		    <div class="admin-s-body"> <span>文本内容:</span> <div class="admin-s"> <textarea rows="7" cols="5" type="text" style="width:300px;">${title.content}</textarea></div> </div>         
</div>

<%@include file="../../../common/admin_footer.jsp"%>
