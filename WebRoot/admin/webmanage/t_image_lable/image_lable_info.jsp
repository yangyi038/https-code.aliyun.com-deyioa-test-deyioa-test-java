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


       		<div class="admin-s-body"> <span>模板名:</span> <div class="admin-s"> <input type="text"   value="${imageLable.name}" style="width:220px;"/> </div> </div> <br/>
	
			<div class="admin-s-body"> <span>审核状态:</span> <div class="admin-s"> <input type="text"   value="${imageLable.status}" style="width:220px;"/></div> </div><br/>
			<div class="admin-s-body"> <span>创建时间:</span> <div class="admin-s"> <input type="text"   value="${imageLable.tempTime}" style="width:220px;"/></div> </div> <br/>
		    <div class="admin-s-body"> <span>模板描述:</span> <div class="admin-s"> <textarea rows="5" cols="5" type="text" style="width:220px;">${imageLable.lableDescribe}</textarea></div> </div>         
			
		        	


<%@include file="../../../common/admin_footer.jsp"%>
