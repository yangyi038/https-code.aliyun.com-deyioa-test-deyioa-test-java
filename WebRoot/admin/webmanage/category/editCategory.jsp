<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/category/updateCategory.action" enctype="multipart/form-data" method="post">
      	<input type="hidden"  name="cat_id" value="${category.cat_id }">
      	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>分类名称</span>
       		<div class="admin-s"><input type="text" name="cat_name" id="cat_name" placeholder="必输项"   value="${category.cat_name }"/></input></div>
       	</div>
       	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>分类的关键字</span>
       		<div class="admin-s"><input type="text" name="keywords" id="keywords" placeholder="必输项"  value="${category.keywords }"/></input></div>
       	</div> 
       	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>分类图片（图片大小 750*358）</span>
       		<div class="admin-s"><input type="file" name="tipic"   id="tipic"/></div>
       		<a href="javascript:void(0)"    onclick="openpic()" >查看图片</a> 
       	</div> 
         
       	<div class="admin-s-body">
       		<span>上级分类</span>
       		<div class="admin-s"><input type="text" name="category.parent_id"  placeholder="必输项" value="${category.parentCategory.cat_name }"  readonly="readonly"/></input>
       		<input type="hidden"   name="parent_id"  value="${category.parent_id }">
       		</div>
       	</div> 
       	
       	<div class="admin-s-body">
       		<span>显示的顺序</span>
       		<div class="admin-s"><input type="text" name="sort_order" id="sort_order" placeholder="输入顺序"  value="${category.sort_order } "/></input></div>
       	</div> 
       	
       	
       	<div class="admin-s-body block">
       		<span class="top">分类描述</span>
       		<div class="admin-s">
       		<textarea name="cat_desc" rows="5" cols="150" style="width:100%">${category.cat_desc } </textarea>
       		</div>
       	</div> 
       	
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			        	</div>
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" callback="window.parent.closeDialogm('category_edit_d');window.parent.rs('${category.parent_id}');">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript"> 
	
function checkdep(){
	var info="";
	var cat_name=$("#cat_name").val();
	if(cat_name==""){
		info+="分类名不能为空！";
	}

	var keywords=$("#keywords").val();
	if(keywords==""){
		info+="分类的关键字不能为空！";
	} 
	if(info!=""){
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}

function openpic(){
	var pic = '${category.pic}';
	window.parent.jQuery().yzIframeDialog({id:"win_openpics",iframeurl:"<%=basepath%>/upload/cat_pic/"+pic,title:"查看图片"});
	window.parent.$('#win_openpics').modal('show');
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>