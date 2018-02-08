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
			
			
			<div class="admin-s-body"> <span><font style="color:red;">*</font>设备号:</span> <div class="admin-s"> <input type="text" name="programmeNum" style="width:220px;" value="${streaming.programmeNum}"/> </div></div> 
			<div class="admin-s-body"> <span>固件版本号:</span> <div class="admin-s"> <input type="text" name="firmware" style="width:220px;" value="${streaming.firmware}" /> </div> </div> <br/>
			
   			<div class="admin-s-body"> <span>软件版本号:</span> <div class="admin-s"> <input type="text" name="softwareVersion" style="width:220px;" value="${streaming.softwareVersion}"/> </div> </div> 
       		<div class="admin-s-body"> <span>出厂日期:</span> <div class="admin-s"> 
				<input id='validdate' name="tempProductionData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${streaming.productionData}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${streaming.productionData}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			 </div> </div> <br/>
       		 
			<div class="admin-s-body"> <span>授权起始日期:</span> <div class="admin-s"> 
				<input id='validdate' name="tempStartTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${streaming.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${streaming.startTime}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			 </div> </div> 
       		<div class="admin-s-body"> <span>授权结束日期:</span> <div class="admin-s">  
       			<input id='validdate' name="tempOverTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${streaming.overTime}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${streaming.overTime}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		
       		</div> </div>  <br/>
       		
			<div class="admin-s-body"> <span>root密码:</span> <div class="admin-s"> <input type="text" name="rootPassword" style="width:220px;" value="${streaming.rootPassword}"/> </div> </div> 
			
			<div class="admin-s-body"> <span>hotel密码:</span> <div class="admin-s"> <input type="text" name="hotelPassword"  style="width:220px;" value="${streaming.hotelPassword}"/> </div> </div>  <br/>
       		
       		<div class="admin-s-body"> <span>EdgeIP:</span> <div class="admin-s"> <input type="text" name="edgeIp"  style="width:220px;" value="${streaming.edgeIp}"/> </div> </div>  
       		
       		
       		<div class="admin-s-body"> <span>最后登陆时间:</span> <div class="admin-s">  
       		
       		<input id='validdate' name="tempLastTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${streaming.lastTime}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${streaming.lastTime}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		
       		</div> </div> <br/>
       		
       		 <div class="admin-s-body"> <span>最后登录IP:</span> <div class="admin-s"> <input type="text" name="latsIp"  style="width:220px;" value="${streaming.latsIp}"/> </div> </div>  
       		
       		<input type="text" name="id" value="${streaming.id}" style="visibility:hidden;"/> 
       		
			
<%@include file="../../../common/admin_footer.jsp"%>
