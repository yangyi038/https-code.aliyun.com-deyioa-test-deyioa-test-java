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
	<form action="<%=basepath%>/admin/t_imageText/addImageTextLable.action" enctype="multipart/form-data" method="post">

       		 
			
			<div class="admin-s-body"> <span><font style="color:red;">*</font>模板名:</span> <div class="admin-s"> <input type="text" name="name"  style="width:220px;"/> </div> </div>  <br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>模板描述:</span> <div class="admin-s"> <textarea rows="5" cols="5" type="text" name="lableDescribe" id="lableDescribe" style="width:220px;"> </textarea> </div> </div> <br/>
			
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>  
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_t_imageLable_m');window.parent.refreshGrid('imageLable');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var name=$("input[name=name]").val();
	if(name==""){
		info+="模板名不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var lableDescribe=$("#lableDescribe").val();
	if(lableDescribe==""){
		info+="模板描述不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
