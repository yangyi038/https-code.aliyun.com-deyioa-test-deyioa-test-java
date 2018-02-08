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

       		 
			<input type="text" name="id"  value="${imageText.id}" style="visibility:hidden;" /><br/>
			<div class="admin-s-body"> <span>标题:</span> <div class="admin-s"> <input type="text"  value="${imageText.name}"/> </div> </div>  
			<div class="admin-s-body"> <span>显示格式:</span> <div class="admin-s"> <input type="text"  value="${imageText.showType}"/> </div> </div>  <br/>
			<div class="admin-s-body"> <span>图文模板:</span> <div class="admin-s"> <input type="text"   value="${imageText.templet}"/> </div> </div>
			<div class="admin-s-body"> <span>创建时间:</span> <div class="admin-s"> <input type="text"   value="${imageText.tempDate}"/> </div> </div><br/>
			
			<div class="admin-s-body"> <span>审核状态:</span> <div class="admin-s"> <input type="text"   value="${imageText.status}"/> </div> </div>
			<div class="admin-s-body"> <span>上线状态:</span> <div class="admin-s"> <input type="text"   value="${imageText.lineStatus}"/> </div> </div><br/>
			
			<div class="admin-s-body"> <span>内容:</span> <div class="admin-s"> 
				<textarea rows="3" cols="65" type="text" name="content"   > ${imageText.content}</textarea>
			</div> </div> <br/>
			<div class="admin-s-body"> <span>标签属性:</span> <div class="admin-s"> <input type="text" name="lableAttribute"  style="width:482px;" value="${imageText.lableAttribute}"/> </div> </div>  
			
			        	
</div>


<%@include file="../../../common/admin_footer.jsp"%>
