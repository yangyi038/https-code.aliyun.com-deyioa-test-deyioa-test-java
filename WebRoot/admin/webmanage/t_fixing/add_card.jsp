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
	<form action="<%=basepath%>/admin/t_fixing/add_card.action" enctype="multipart/form-data" method="post">
 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>节目组号:</span> <div class="admin-s"> <input type="text" name="programmeCode" style="width:220px;" /> </div></div> 
			<div class="admin-s-body"> <span>CA号:</span> <div class="admin-s"> <input type="text" name="caCode" style="width:220px;" /> </div> </div> <br/>
			
    
       		
       		<div class="admin-s-body"> <span>设备帐号组:</span> <div class="admin-s"> 
       			<input type="text" name="stbGroup" style="width:220px;" />
       		</div> </div> 
			<div class="admin-s-body"> <span>固件版本:</span> <div class="admin-s"> 
				<input type="text" name="firmware" style="width:220px;" />
			 </div> </div> <br/>
			
			
       		<div class="admin-s-body"> <span>Rom版本:</span> <div class="admin-s">  
       			<input type="text" name="romversion" style="width:220px;" />
       		</div> </div>  
       		
			<div class="admin-s-body"> <span>LastTime:</span> <div class="admin-s"> 
				<input id='validdate' name="tempLastTime" cssClass='Wdate' style="width:220px;"
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" />
			</div> </div> <br/>
			
			<div class="admin-s-body"> <span>LastIP:</span> <div class="admin-s"> <input type="text" name="lastIp"  style="width:220px;"/> </div> </div>  
       		<input type="text" name="fixingId" value="${fixingId}" style="visibility:hidden;"/> 
       		
       		
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>    
			  
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_card_s');window.parent.refreshGrid('card');
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
