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
	<form action="<%=basepath%>/admin/t_streaming/add_Streaming.action" enctype="multipart/form-data" method="post">
			
			
			<div class="admin-s-body"> <span><font style="color:red;">*</font>设备号:</span> <div class="admin-s"> <input type="text" name="programmeNum" style="width:220px;" /> </div></div> 
			<div class="admin-s-body"> <span>固件版本号:</span> <div class="admin-s"> <input type="text" name="firmware" style="width:220px;" /> </div> </div> <br/>
			
   			<div class="admin-s-body"> <span>软件版本号:</span> <div class="admin-s"> <input type="text" name="softwareVersion" style="width:220px;" /> </div> </div> 
       		<div class="admin-s-body"> <span>出厂日期:</span> <div class="admin-s"> 
				<input id='validdate' name="tempProductionData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			 </div> </div> <br/>
       		 
			<div class="admin-s-body"> <span>授权起始日期:</span> <div class="admin-s"> 
				<input id='validdate' name="tempStartTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			 </div> </div> 
       		<div class="admin-s-body"> <span>授权结束日期:</span> <div class="admin-s">  
       			<input id='validdate' name="tempOverTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		
       		</div> </div>  <br/>
       		
			<div class="admin-s-body"> <span>root密码:</span> <div class="admin-s"> <input type="text" name="rootPassword" style="width:220px;" /> </div> </div> 
			
			<div class="admin-s-body"> <span>hotel密码:</span> <div class="admin-s"> <input type="text" name="hotelPassword"  style="width:220px;"/> </div> </div>  <br/>
       		
       		<div class="admin-s-body"> <span>EdgeIP:</span> <div class="admin-s"> <input type="text" name="edgeIp"  style="width:220px;"/> </div> </div>  
       		
       		
       		<div class="admin-s-body"> <span>最后登陆时间:</span> <div class="admin-s">  
       		
       		<input id='validdate' name="tempLastTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		
       		</div> </div> <br/>
       		
       		 <div class="admin-s-body"> <span>最后登录IP:</span> <div class="admin-s"> <input type="text" name="latsIp"  style="width:220px;"/> </div> </div>  
       		
       		
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>    
			  
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_sterming_s');window.parent.refreshGrid('streaming');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	
	var info="";
	var programmeNum=$("input[name=programmeNum]").val();
	if(programmeNum==""){
		info+="设备号不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}

$('#validdate').val($.formatDateTime('yy-mm-dd hh:ii:ss', new Date()));
</script>
<%@include file="../../../common/admin_footer.jsp"%>
