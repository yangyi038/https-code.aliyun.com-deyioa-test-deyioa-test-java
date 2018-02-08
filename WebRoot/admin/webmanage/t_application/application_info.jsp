<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script  type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script  type="text/javascript"	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js" type="text/javascript"></script>	
 
<div class="modal-real-body" >
	
			<div class="admin-s-body"> <span>应用名称:</span> <div class="admin-s"> <input type="text"   value="${appManager.name}"/> </div> </div>  
			<div class="admin-s-body"> <span>开发者:</span> <div class="admin-s"> <input type="text"  value="${appManager.developerName}"/> </div> </div> <br/>
			
			<div class="admin-s-body"> <span>评分:</span> <div class="admin-s"> <input type="text"  value="${appManager.grader}"/> </div> </div> 
			<div class="admin-s-body"> <span>参数类型:</span> <div class="admin-s"> <input type="text"  value="${appManager.parameterType}"/> </div> </div>  <br/>
			
       		<div class="admin-s-body"> <span>上线状态:</span> <div class="admin-s"><input type="text"  value="${appManager.status}"/> </div> </div> 
			<div class="admin-s-body"> <span>上线时间:</span> <div class="admin-s"><input type="text"  value="${appManager.tempTime}"/> </div> </div> <br/>
       		
       		<div class="admin-s-body"> <span>应用参数:</span> <div class="admin-s"><input type="text"  value="${appManager.applyParameter}" style="width:482px;"/> </div> </div> <br/>
       		<div class="admin-s-body"> <span>简介:</span> <div class="admin-s"> <textarea rows="5" cols="4" type="text"  style="width:482px;">${appManager.synopsis} </textarea> </div> </div><br/>   
			
			        	
</div>

<%@include file="../../../common/admin_footer.jsp"%>
