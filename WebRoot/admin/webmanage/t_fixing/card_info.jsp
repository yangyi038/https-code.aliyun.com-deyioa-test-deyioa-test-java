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
 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>节目组号:</span> <div class="admin-s"> <input type="text" name="programmeCode" style="width:220px;" value="${card.programmeCode}"/> </div></div> 
			<div class="admin-s-body"> <span>CA号:</span> <div class="admin-s"> <input type="text" name="caCode" style="width:220px;" value="${card.caCode}"/> </div> </div> <br/>
			
    
       		
       		<div class="admin-s-body"> <span>设备帐号组:</span> <div class="admin-s"> 
       			<input type="text" name="stbGroup" style="width:220px;" value="${card.stbGroup}"/>
       		</div> </div> 
			<div class="admin-s-body"> <span>固件版本:</span> <div class="admin-s"> 
				<input type="text" name="firmware" style="width:220px;" value="${card.firmware}"/>
			 </div> </div> <br/>
			
			
       		<div class="admin-s-body"> <span>Rom版本:</span> <div class="admin-s">  
       			<input type="text" name="romversion" style="width:220px;" value="${card.romversion}"/>
       		</div> </div>  
       		
			<div class="admin-s-body"> <span>LastTime:</span> <div class="admin-s"> 
				<input id='' name="tempLastTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${card.lastTime}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${card.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			</div> </div> <br/>
			
			<div class="admin-s-body"> <span>LastIP:</span> <div class="admin-s"> <input type="text" name="lastIp"  style="width:220px;" value="${card.lastIp}"/> </div> </div>  
       		
       		
			  
<%@include file="../../../common/admin_footer.jsp"%>
