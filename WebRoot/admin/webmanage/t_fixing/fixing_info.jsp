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
 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>设备sn:</span> <div class="admin-s"> <input type="text" name="fixingSn" value="${fixing.fixingSn}" style="width:220px;" /> </div></div> 
			<div class="admin-s-body"> <span>设备类型:</span> <div class="admin-s"> <input type="text" name="type" style="width:220px;" value="${fixing.type}"/> </div> </div> <br/>
			<div class="admin-s-body"> <span>用户组:</span> <div class="admin-s"> <input type="text" name="userGroup" style="width:220px;" value="${fixing.userGroup}"/> </div> </div> 
    
       		
       		<div class="admin-s-body"> <span>出厂日期:</span> <div class="admin-s"> 
       			<input id='validdate' name="tempProductionData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${fixing.productionData}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${fixing.productionData}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		 </div> </div> <br/>
			<div class="admin-s-body"> <span>开通日期:</span> <div class="admin-s"> 
				<input id='validdate' name="tempOpeningData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${fixing.openingData}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${fixing.openingData}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			 </div> </div>
			
			
       		<div class="admin-s-body"> <span>服务截至日期:</span> <div class="admin-s">  
       		
       		<input id='validdate' name="tempClosingData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${fixing.closingData}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${fixing.closingData}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		
       		</div> </div>  <br/>
       		
			<div class="admin-s-body"> <span>ca厂家:</span> <div class="admin-s"> <input type="text" name="vender" style="width:220px;" value="${fixing.vender}"/> </div> </div> 
			
			<div class="admin-s-body"> <span>版本号:</span> <div class="admin-s"> <input type="text" name="version"  style="width:220px;"value="${fixing.version}"/> </div> </div>  <br/>
       		
       		<input type="text" name="id"  value="${fixing.id}" style="visibility:hidden;" /><br/>
       		
		
			  
