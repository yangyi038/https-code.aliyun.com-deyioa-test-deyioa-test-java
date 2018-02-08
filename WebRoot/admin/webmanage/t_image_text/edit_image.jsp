<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script  type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script  type="text/javascript"	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js" type="text/javascript"></script>	
 
<div class="modal-real-body" >
	<form action="<%=basepath%>/admin/t_imageText/updatetImageWindow.action" enctype="multipart/form-data" method="post">

       		 
			<input type="text" name="id"  value="${imageText.id}" style="visibility:hidden;" /><br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>标题:</span> <div class="admin-s"> <input type="text" name="name"  value="${imageText.name}"/> </div> </div>  
			
			<div class="admin-s-body"> <span>显示格式:</span> <div class="admin-s"> 
				<select  name="showTypeStr" id="showTypeStr"  value="${imageText.showTypeStr}">
		    		<option <c:if test="${imageText.showType=='0'}">selected="selected"</c:if>>文字</option>
		    		<option <c:if test="${imageText.showType=='1'}">selected="selected"</c:if>>图片</option>
 		 			<option <c:if test="${imageText.showType=='2'}">selected="selected"</c:if>>视频</option>
  					<option <c:if test="${imageText.showType=='3'}">selected="selected"</c:if>>HTML页面</option>
  					<option <c:if test="${imageText.showType=='4'}">selected="selected"</c:if>>关联展示</option>
		   		</select>
			</div> </div> <br/>
			<div class="admin-s-body"> <span>图文模板:</span> <div class="admin-s"> 
				<select  name="templet" value="${imageText.templet}">
		    		<option >无模板</option>
		   		</select>
			</div> </div> <br/>
			
			<div class="admin-s-body"> <span>内容:</span> <div class="admin-s"> 
				<textarea rows="3" cols="65" type="text" name="content"   > ${imageText.content}</textarea>
			</div> </div> <br/>
			<div class="admin-s-body"> <span>标签属性:</span> <div class="admin-s"> <input type="text" name="lableAttribute"  style="width:482px;" value="${imageText.lableAttribute}"/> </div> </div>  
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>  
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_imageText_m');window.parent.refreshGrid('imageText');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var name=$("input[name=name]").val();
	if(name==""){
		info+="标题不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
