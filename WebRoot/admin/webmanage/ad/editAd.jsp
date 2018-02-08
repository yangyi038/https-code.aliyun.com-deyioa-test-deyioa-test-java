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
<div class="modal-real-body">
<style>

.alertmodel{
height:200px;
}
</style> 
	<form action="<%=basepath%>/admin/ad/updateAd.action" enctype="multipart/form-data" method="post">
	<input type="hidden" name="ad_id" value="${ad.ad_id}"/>
      	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>轮播图片名称</span>
       		<div class="admin-s"><input type="text" name="ad_name"   id="ad_name"  placeholder="请输入轮播图片名称" value="${ad.ad_name}"/></div>
       	</div>
       		<div class="admin-s-body"  >
       		<span><b style="color: red;">*</b>轮播图片（图片大小 750*500）</span>
       		<div class="admin-s"><input type="file" name="tipic"   id="tipic" style="width: 110px"/></div>
       		 <a href="javascript:void(0)"    onclick="openpic()" >查看图片</a> 
       	
       	</div>  	
       	
       		 
       		<div class="admin-s-body "   >
       		<span><b style="color: red;">*</b>请填写广告链接</span>
       		<div class="admin-s"><input type="text" name="ad_link"  id="ad_link"  placeholder="请输入链接" value="${ad.ad_link}"   /></div>
       	</div> 
       	<div class="admin-s-body">
       		<span>状态</span>
       		<div class="admin-s"> 
       		 <select name="enabled" id="enabled" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'enabled')}" var="item">
			        			   <option value="${item.key}" ${ad.enabled==item.key?'selected="selected"':''}>${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>
       	<div class="admin-s-body">
       		<span><b style="color: red;">*</b>排序</span>
       		<div class="admin-s"><input type="text" name="oder"  id="oder"  placeholder="请输入排序" value="${ad.oder}"/></div>
       	</div>
       		<div class="admin-s-body">
       		<span><b style="color: red;">*</b>轮播类型</span>
       		<div class="admin-s"> 
       		 <select name="ad_type" id="ad_type" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'ad_type')}" var="item">
			        			   <option value="${item.key}" ${ad.ad_type==item.key?'selected="selected"':''}>${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
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
							callback="window.parent.closeDialogm('edit_ad_m');window.parent.refreshGrid('Ad');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
function check(){
	var errorinfo="";  
var ad_name =$('#ad_name').val();	
if(ad_name==null||ad_name==''){
		errorinfo+="轮播名称不能为空</br>";
	}  
	var ad_link =$('#ad_link').val();	
	if(ad_link==null||ad_link==''){
		errorinfo+="广告链接不能为空</br>";
	} 
var oder =$('#oder').val();	 
if(oder==null||oder==''){
		errorinfo+="排序不能为空</br>";
	}	
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	} 

   
function openpic(){
	var pic = '${ad.AD_CODE}';
	window.parent.jQuery().yzIframeDialog({id:"win_openpic",iframeurl:"<%=basepath%>/upload/ad_code/"+pic,title:"查看图片"});
	window.parent.$('#win_openpic').modal('show');
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>