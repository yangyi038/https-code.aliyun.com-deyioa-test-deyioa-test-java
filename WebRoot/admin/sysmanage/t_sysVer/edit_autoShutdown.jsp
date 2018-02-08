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
	<form action="<%=basepath%>/admin/t_sysVer/updateAutoShutdown.action" enctype="multipart/form-data" method="post">
			
	
	<div style="float:left;"> <span><font style="color:red;">*</font>starttime:</span></div>	<div style="margin-left:130px;">
		<input type="text" name="starttime"  style="width: 400px" value="${model.starttime}"/> 
		
	</div> </div></div><br/>
    <div style="float:left;"> <span><font style="color:red;">*</font>endtime:</span></div>	<div style="margin-left:130px;"><input type="text" name="endtime"  style="width: 400px" value="${model.endtime}"/> </div> </div></div><br/>	
	<div style="float:left;"> <span><font style="color:red;">*</font>dur:</span></div>	<div style="margin-left:130px;"><input type="text" name="dur"  style="width: 400px" value="${model.dur}" /> </div> </div></div> 	<br/>
	
	<div style="float:left;"> <span><font style="color:red;">*</font>timersetcheckdur:</span></div>	<div style="margin-left:130px;"><input type="text" name="timersetcheckdur"  style="width: 400px" value="${model.timersetcheckdur}"/> </div> </div></div><br/>
    <div style="float:left;"> <span><font style="color:red;">*</font>countdowntime:</span></div>	<div style="margin-left:130px;"><input type="text" name="countdowntime"  style="width: 400px" value="${model.countdowntime}"/> </div> </div></div><br/>	
	<div style="float:left;"> <span><font style="color:red;">*</font>countDownTip:</span></div>	<div style="margin-left:130px;"><input type="text" name="countdowntip"  style="width: 400px" value="${model.countdowntip}"/> </div> </div></div> <br/>	
	
	<div style="float:left;"> <span><font style="color:red;">*</font>excludedate:</span></div>	<div style="margin-left:130px;"><input type="text" name="excludedate"  style="width: 400px" value="${model.excludedate}"/> </div> </div></div> 	
	<input name="id" value="${model.id}" style="visibility:hidden;" />
	
	
       	
		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
		</div>
	</form>
			        	
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_autoShutdown_m');window.parent.refreshGrid('autoShutdown');
  		
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">

function checkdep(){
	var info="";
	var select=$("#select").val();
	if(select=="no"){
		info+="图片组不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var pictureName = $("#pictureName").val();
	if(pictureName==""){
		info+="图片不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	var linkType = $("#linkType").val();
	if(linkType=="no"){
		info+="链接类型不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var linkUrl=$("input[name=linkUrl]").val();
	if(linkUrl==""){
		info+="跳转链接不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	/* if(info!=""){
		modalDialogAlert(info);
		return false;
	}  */
	
	return true;
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>
