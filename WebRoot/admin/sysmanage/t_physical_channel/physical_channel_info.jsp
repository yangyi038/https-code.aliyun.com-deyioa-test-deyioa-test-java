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
	
			
    
		
			<div class="admin-s-body"> <span>名称:</span> <div class="admin-s"> <input type="text" name="name" value="${physicalChannel.name}" /> </div> </div>  
			<div class="admin-s-body"> <span>cdn类型:</span> <div class="admin-s"> 
				<input type="text"  value="${physicalChannel.type}" />
			</div> </div><br/>
			
			<div class="admin-s-body"> <span>播放级别:</span> <div class="admin-s"> <input type="text"  value="${physicalChannel.level}" /></div> </div> 
				
			 
       		<div class="admin-s-body"> <span>来源名称:</span> <div class="admin-s">  <input type="text" name="sourceName"  value="${physicalChannel.sourceName}"/></div> </div>  <br/>
   			<div class="admin-s-body"> <span>频道来源:</span> <div class="admin-s"> <input type="text"   value="${physicalChannel.channelSource}"/></div> </div> 
   			<div class="admin-s-body"> <span>媒体格式:</span> <div class="admin-s"><input type="text"  value="${physicalChannel.format}"/></div> </div> <br/>
   			
       		<div class="admin-s-body"> <span>码率:</span> <div class="admin-s"> <input type="text" name="codeCheck"  value="${physicalChannel.codeCheck}"/> </div> </div> 
       		<div class="admin-s-body"> <span>创建时间:</span> <div class="admin-s"> <input type="text" name="codeCheck"  value="${physicalChannel.tempTime}"/> </div> </div> <br/>
       		<div class="admin-s-body"> <span>激活状态:</span> <div class="admin-s"> <input type="text" name="codeCheck"  value="${physicalChannel.activatedState}"/> </div> </div> 
       		<div class="admin-s-body"> <span>URL状态:</span> <div class="admin-s"> <input type="text" name="codeCheck"  value="${physicalChannel.status}"/> </div> </div> <br/>
			<div class="admin-s-body"> <span>频道URL:</span> <div class="admin-s"> <input type="text" name="channelUrl"  value="${physicalChannel.channelUrl}" style="width:480px;"/> </div> </div> 
       		
			
			        	
</div>

<%@include file="../../../common/admin_footer.jsp"%>
