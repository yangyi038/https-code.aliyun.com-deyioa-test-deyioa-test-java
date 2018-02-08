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
	<form action="<%=basepath%>/admin/t_column/updateColumn.action" enctype="multipart/form-data" method="post">
			<input type="text" name="id"  value="${column.id}" style="visibility:hidden;" /><br/>
			
			<div class="admin-s-body"> <span><font style="color:red;">*</font>上级分类:</span> <div class="admin-s">
       			<%-- <select name="classify" id="classify">
					<c:forEach items="${columnList}" var="item">
						<option value="${item.classify}"
						<c:if test="${item.classify==column.classify}">selected="selected"</c:if>>${item.classify}</option>
					</c:forEach>
					
					
				</select> --%>
				
				<input type="text" name="classify" readonly="true" value="${column.classify}"/><input name="parentId" type="hidden" value="${column.parentId}"/>
       		 </div> </div>          
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>名称:</span> <div class="admin-s"> <input type="text" name="name"  value="${column.name}"/> </div> </div>  <br/>
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>序号:</span> <div class="admin-s"> <input type="text" name="number"  value="${column.number}"/> </div> </div>   
		    <div class="admin-s-body"> <span>类型:</span> <div class="admin-s"> 
		    	<select  name="type"  id="type"/>
		    	
		    	<option <c:if test="${column.type=='不限'}">selected="selected"</c:if>>不限</option>
 		 		<option <c:if test="${column.type=='系列片'}">selected="selected"</c:if>>系列片</option>
  				<option <c:if test="${column.type=='单片'}">selected="selected"</c:if>>单片</option>
  				<option <c:if test="${column.type=='直播节目'}">selected="selected"</c:if>>直播节目</option>
  				<option <c:if test="${column.type=='点播节目'}">selected="selected"</c:if>>点播节目</option>
  				<option <c:if test="${column.type=='点播搜索'}">selected="selected"</c:if>>点播搜索</option>
 		 		<option <c:if test="${column.type=='视频演播大厅'}">selected="selected"</c:if>>视频演播大厅</option>
  				<option <c:if test="${column.type=='图片'}">selected="selected"</c:if>>图片</option>
  				<option <c:if test="${column.type=='字母'}">selected="selected"</c:if>>字母</option>
  				<option <c:if test="${column.type=='首页推荐海报'}">selected="selected"</c:if>>首页推荐海报</option>
  				<option <c:if test="${column.type=='首页推荐视频'}">selected="selected"</c:if>>首页推荐视频</option>
 		 		<option <c:if test="${column.type=='首页推荐节目'}">selected="selected"</c:if>>首页推荐节目</option>
  				<option <c:if test="${column.type=='专题'}">selected="selected"</c:if>>专题</option>
  				<option <c:if test="${column.type=='app应用'}">selected="selected"</c:if>>app应用</option>
  				<option <c:if test="${column.type=='图片信息'}">selected="selected"</c:if>>图片信息</option>
		    </select> 
		     </div> </div>  <br/>   
		    <div class="admin-s-body"> <span>状态:</span> <div class="admin-s" > 
		    	<select  name="status"  />
		    		<option <c:if test="${column.status=='有效'}">selected="selected"</c:if>>有效</option>
 		 			<option <c:if test="${column.status=='有效'}">selected="selected"</c:if>>无效</option>
		    	</select> 
		     </div> </div>    
		    <div class="admin-s-body"> <span>图片显示:</span> <div class="admin-s">
		    	<select  name="showPicture"  placeholder=""/>
		    		<option <c:if test="${column.showPicture=='纵向'}">selected="selected"</c:if>>纵向</option>
 		 			<option <c:if test="${column.showPicture=='横向'}">selected="selected"</c:if>>横向</option>
		    	</select> 
		     </div> </div> <br/>    
		    <div class="admin-s-body"> <span>列表播放:</span> <div class="admin-s"> 
		    	<select  name="play"  placeholder=""/>
		    		<option <c:if test="${column.play=='否'}">selected="selected"</c:if>>否</option>
 		 			<option <c:if test="${column.play=='是'}">selected="selected"</c:if>>是</option>
		    	</select> 
		   </div> </div>   
		    <div class="admin-s-body"> <span><font style="color:red;">*</font>优先级:</span> <div class="admin-s"> <input type="text" name="priority"  value="${column.priority}"/> </div> </div>  <br/>   
			<div class="admin-s-body"> <span>显示类型:</span> <div class="admin-s">
				<select  name="showType"  placeholder=""  value="${column.showType}"/>
		    		<option <c:if test="${column.showType=='正常'}">selected="selected"</c:if>>正常</option>
 		 			<option <c:if test="${column.showType=='测试'}">selected="selected"</c:if>>测试</option>
		    	</select>
		    </div> </div>  
			<div class="admin-s-body"> <span>模板编码:</span> <div class="admin-s"> <input type="text" name="code" value="${column.code}" /> </div> </div>   <br/>
			<div class="admin-s-body"> <span>操作类型:</span> <div class="admin-s"> 
				<select  name="operation"  placeholder="" value="${column.operation}"/>
		    		<option <c:if test="${column.operation=='默认'}">selected="selected"</c:if>>默认</option>
 		 			<option <c:if test="${column.operation=='显示图片'}">selected="selected"</c:if>>显示图片</option>
 		 			<option <c:if test="${column.operation=='播放视频'}">selected="selected"</c:if>>播放视频</option>
 		 			<option <c:if test="${column.operation=='打开网页'}">selected="selected"</c:if>>打开网页</option>
		    	</select>
		    </div> </div>   
			
			<div class="admin-s-body"> <span><font style="color:red;">*</font>栏目唯一标示:</span> <div class="admin-s"> <input type="text" name="identifying"  value="${column.identifying}"/> </div> </div>  <br/>
			<div class="admin-s-body"> <span>UI模板:</span> <div class="admin-s"> <input type="text" name="uiModel"  value="${column.uiModel}"/> </div> </div> 
			<div class="admin-s-body"> <span>标签:</span> <div class="admin-s"> <input type="text" name="label"  value="${column.label}"/> </div> </div> <br/> 
			<div class="admin-s-body"> <span>描述:</span> <div class="admin-s"> <input type="text" name="describeContext"  value="${column.describeContext}"/> </div> </div>  
       	
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
			
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('column_edit_d');window.parent.rs('${column.parentId}');;
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var classify=$("#classify").val();
	if(classify=="no"){
		info+="上级菜单不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	var name=$("input[name=name]").val();
	if(name==""){
		info+="名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var number=$("input[name=number]").val();
	if(number==""){
		info+="序号不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var priority=$("input[name=priority]").val();
	if(priority==""){
		info+="优先级不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var identifying=$("input[name=identifying]").val();
	if(identifying==""){
		info+="栏目唯一标识不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	return true;
}
window.onload = function () { 
	var type = $("#type");
	alert(type);
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>
