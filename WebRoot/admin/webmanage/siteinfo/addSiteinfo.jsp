<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>

<script type="text/javascript" charset="utf-8" src="<%=basepath %>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basepath %>/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basepath %>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<div class="modal-real-body">

<style>

.alertmodel{
height:200px;
}
</style>
	<form action="<%=basepath%>/admin/siteinfo/addSiteinfo.action" enctype="multipart/form-data" method="post"> 
      	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>文案标题</span>
       		<div class="admin-s"><input type="text" name="title"   id="title"/></div>
       	</div>
      	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>文案类别</span>
       		<div class="admin-s">
<div class="admin-s">  
			        			 <select name="sicategory" id="sicategory" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'sicategory')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>       		</div>
       	</div>
       		<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>静态页面链接</span>
       		<div class="admin-s"><input type="text" name="silink"   id="silink"/>(当填写"链接"后点击文章标题将直接跳转至链接地址，不显示文章内容。链接格式请以http://开头)</div>
       	</div> 
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>文案图片</span>
       		<div class="admin-s"><input type="file" name="pic"   id="pic"/></div>
       	</div> 
       	<div class="admin-s-body block">
       		<span>状态</span>
       		<div class="admin-s">  
			        			 <select name="isshow" id="isshow" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'isshow')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>排序</span>
       		<div class="admin-s"><input type="text" name="px"  id="px"  placeholder="请输入排序"  />(数字范围为0~255，数字越大越靠前
       		)</div>
       	</div>
       	
    	 	<div class=" ">
       		<span class="top"><b style="color: red;">*</b>文案内容:</span>
       		<div class="admin-s">
       		<input type="hidden"  id="content"    name="content">
   			 <script id="editor"  type="text/plain" style="width:100%;height:300px;"></script>
          	 </div>
       	</div>
			        	</div>
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('add_siteinfo_m');window.parent.refreshGrid('siteinfo');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
var ue = UE.getEditor('editor');  
function check(){
	var errorinfo="";  
	
	var title =$('#title').val();	
	if(title==null||title==''){
			errorinfo+="标题不能为空</br>";
		} 
var sicategory =$('#sicategory').val();	
if(sicategory==null||sicategory=='0'){
		errorinfo+="文案内别不能为空</br>";
	} 
var silink =$('#silink').val();	
if(silink==null||silink==''){
		errorinfo+="静态页面链接不能为空</br>";
	}  
	var isshow =$('#isshow').val();	
	if(isshow==null||isshow==''){
		errorinfo+="前台显示规则不能为空</br>";
	} 
	var pic =$('#pic').val();	
	if(pic==null||pic==''){
			errorinfo+="图片不能为空</br>";
		} 
var px =$('#px').val();	 
if(px==null||px==''){
		errorinfo+="排序不能为空</br>";
	}	

var content=ue.getContent(); 
if(content==null||content==''){
		errorinfo+="文案内容不能为空</br>";
	}	
	$('#content').val(content);
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
</script>
<%@include file="../../../common/admin_footer.jsp"%>