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
	<form action="<%=basepath%>/admin/userrank/addUserRank.action" enctype="multipart/form-data" method="post"> 
      		<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>积分名称</span>
       		<div class="admin-s"><input type="text" name="rank_name"   id="rank_name"/></div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>最低积分</span>
       		<div class="admin-s"><input type="text" name="min_points"   id="min_points"/></div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>最高积分 </span>
       		<div class="admin-s"><input type="text" name="max_points"   id="max_points"/> </div>
       	</div>
       	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>商品折扣 </span>
       		<div class="admin-s"><input type="text" name="discount"   id="discount"/> </div>
       	</div>
      	<div class="admin-s-body block">
       		<span>是否显示该会员等级的折扣价格</span>
       		<div class="admin-s">
<div class="admin-s">  
			        			 <select name="show_price" id="show_price" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'show_price')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>       		
       		</div>
       	</div>
       	 
       	<div class="admin-s-body block">
       		<span>是否是特殊会员等级组</span>
       		<div class="admin-s">  
			        			 <select name="special_rank" id="special_rank" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'special_rank')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>   
       		<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>会员类型</span>
       		<div class="admin-s">  
			        			 <select name="usertype" id="usertype" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'usertype')}" var="item">
 									<option value="${item.key}">${item.value}</option>
 			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>   
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('add_userrank_m');window.parent.refreshGrid('userrank');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript">  
function check(){
	var errorinfo="";  
var rank_name =$('#rank_name').val();	
if(rank_name==null||rank_name==''){
		errorinfo+="积分等级名称不能为空</br>";
	} 
var min_points =$('#min_points').val();	
if(min_points==null||min_points==''){
		errorinfo+="最低积分不能为空</br>";
	}  
	var max_points =$('#max_points').val();	
	if(max_points==null||max_points==''){
		errorinfo+="最高积分 不能为空</br>";
	} 
var discount =$('#discount').val();	 
if(discount==null||discount==''){
		errorinfo+="商品折扣不能为空</br>";
	}	
var usertype =$('#usertype').val();	 
if(usertype==null||usertype==''){
		errorinfo+="会员类型不能为空</br>";
	}	
 
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
</script>
<%@include file="../../../common/admin_footer.jsp"%>