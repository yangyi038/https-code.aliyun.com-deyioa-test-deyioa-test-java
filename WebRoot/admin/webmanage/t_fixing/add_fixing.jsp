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
	<form action="<%=basepath%>/admin/t_fixing/add_Fixing.action" enctype="multipart/form-data" method="post">
 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>设备sn:</span> <div class="admin-s"> <input type="text" name="fixingSn" style="width:220px;" /> </div></div> 
			<div class="admin-s-body"> <span>设备类型:</span> <div class="admin-s"> <input type="text" name="type" style="width:220px;" /> </div> </div> <br/>
			<div class="admin-s-body"> <span>用户组:</span> <div class="admin-s"> <input type="text" name="userGroup" style="width:220px;" /> </div> </div> 
    
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
			<div class="admin-s-body"> <span>开通日期:</span> <div class="admin-s"> 
				<input id='validdate' name="tempOpeningData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			 </div> </div> 
			
			
       		<div class="admin-s-body"> <span>服务截至日期:</span> <div class="admin-s">  
       		<input id='validdate' name="tempClosingData" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
       		
       		</div> </div>  <br/>
       		
			<div class="admin-s-body"> <span>ca厂家:</span> <div class="admin-s"> <input type="text" name="vender" style="width:220px;" /> </div> </div> 
			
			<div class="admin-s-body"> <span>版本号:</span> <div class="admin-s"> <input type="text" name="version"  style="width:220px;"/> </div> </div>  <br/>
       		
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>    
			  
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_fixing_s');window.parent.refreshGrid('fixing');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	
	var info="";
	var fixingSn=$("input[name=fixingSn]").val();
	if(fixingSn==""){
		info+="设备sn不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}

$('#validdate').val($.formatDateTime('yy-mm-dd hh:ii:ss', new Date()));
</script>
<%@include file="../../../common/admin_footer.jsp"%>
