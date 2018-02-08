<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 

<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/category/addCategory.action" enctype="multipart/form-data" method="post">
      	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>分类名称</span>
       		<div class="admin-s"><input type="text" name="cat_name" id="cat_name" placeholder="必输项" /></input></div>
       	</div>
       	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>分类的关键字</span>
       		<div class="admin-s"><input type="text" name="keywords" id="keywords" placeholder="必输项"/></input></div>
       	</div> 
       	 	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>分类图片（图片大小 750*358）</span>
       		<div class="admin-s"><input type="file" name="tipic"   id="tipic"/></div> 
       	</div> 
       	<div class="admin-s-body">
       		<span>上级分类</span>
       		<div class="admin-s"><input type="text" name="category.parent_id"  placeholder="必输项" value="${category.cat_name }"  readonly="readonly"/></input>
       		<input type="hidden"   name="parent_id"  value="${category.cat_id }">
       		</div>
       	</div> 
       	
       	
       	<div class="admin-s-body">
       		<span>显示的顺序</span>
       		<div class="admin-s"><input type="text" name="sort_order" id="sort_order" placeholder="输入顺序"/></input></div>
       	</div> 
       	
       	
       	
       	<div class="textareaBody">
					<span class="title">分类描述</span>
					<div class="textarea">
						<textarea name="cat_desc" rows="5" cols="150"></textarea>
					</div>
				</div>
       	
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			        	</div>
		</form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" callback="window.parent.closeDialogm('category_add_d');window.parent.rs('${category.parent_id}');">
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



</script>

<%@include file="../../../common/admin_footer.jsp"%>