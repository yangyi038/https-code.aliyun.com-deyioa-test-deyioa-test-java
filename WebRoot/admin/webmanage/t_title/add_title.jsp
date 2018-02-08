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
	<form action="<%=basepath%>/admin/t_title/addT_title.action" enctype="multipart/form-data" method="post">

       		
	
	
		        
	
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>文本名称:</span> <div class="admin-s"> 
		    	 <input type="text" name="name"  id="name" style="width:300px;"/>
		    </div> </div>  <br/>        
	
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>文本内容</span> <div class="admin-s"> 
		    	<textarea rows="7" cols="5" type="text" name="content" id="content" style="width:300px;"></textarea>
		
		    </div> </div>          
       	
			<div class="real-body-foot">
			     <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_t_title_s');window.parent.refreshGrid('title');
  		
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">

function checkdep(){
	var info="";
	var name=$("#name").val();
	if(name==""){
		info+="文件名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var content=$("#content").val();
	if(content==""){
		info+="文本内容不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
