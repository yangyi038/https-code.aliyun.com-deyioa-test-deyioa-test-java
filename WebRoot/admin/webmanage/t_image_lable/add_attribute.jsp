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
	<form action="<%=basepath%>/admin/t_imageText/addAttribute.action" enctype="multipart/form-data" method="post">

       		 
			<input type="text" name=""  value="${imageLable.id}" style="visibility:hidden;" /><br/>
			<div class="admin-s-body"> <span>属性名:</span> <div class="admin-s"> <input type="text" name="name"  /> </div> </div>  <br/>
			<div class="admin-s-body"> <span>属性描述:</span> <div class="admin-s"> <textarea rows="3" cols="2" type="text" name="lableDescribe"></textarea> </div> </div> <br/>
			
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>  
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_attribute_m');window.parent.refreshGrid('attribute');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	alert("ddddddddddd");
/*	var depname=$("#depname").val();
	if(depname==""){
		info+="组织机构名称不能为空！";
	}
	var depparent=$("input[name=depparent]").val();
	if(depparent==""){
		info+="上级组织机构不能为空！";
	}
	if(info!=""){
		modalDialogAlert(info);
		return false;
	}*/
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
