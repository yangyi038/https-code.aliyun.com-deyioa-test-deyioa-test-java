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
	<form action="<%=basepath%>/admin/t_sysVer/addT_sysVer.action" enctype="multipart/form-data" method="post">
			
	
	<div style="float:left;"> <span><font style="color:red;">*</font>参数:</span></div>	<div style="margin-left:112px;"><input type="text" name="code"  style="width: 400px" /> </div> </div></div><br/>
    <div style="float:left;"> <span>名称:</span></div>	<div style="margin-left:112px;"><input type="text" name="name"  style="width: 400px" /> </div> </div></div><br/>	
	<div style="float:left;"> <span>组名:</span></div>	<div style="margin-left:112px;"><input type="text" name="allowGroup"  style="width: 400px" /> </div> </div></div> 	
	
	
	
	
       	
		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
		</div>
	</form>
			        	
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_t_sysver_s');window.parent.refreshGrid('sysVer');
  		
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">

function checkdep(){
	var info="";

	var code=$("input[name=code]").val();
	if(code==""){
		info+="参数不能为空！";
		modalDialogAlert(info);
		return false;
	}

	return true;
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>
