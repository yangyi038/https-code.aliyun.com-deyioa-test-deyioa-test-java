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
	<form action="<%=basepath%>/admin/user/updateVIP.action" enctype="multipart/form-data" method="post"> 
     <input type="hidden" name="id"  value="${id }">
      	<div class="admin-s-body block">
       		<span>会员等级：</span>
       		<div class="admin-s">
<div class="admin-s">  
			        			 <select name="rank_id" id="rank_id" >
			        			<c:forEach items="${hy}" var="item">
			        			 <option value="${item.RANK_ID}" >${item.RANK_NAME}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>       		</div>
       	</div> 
    	 	<div class=" admin-s-body">
       		<span class="top">备注:</span>
       		<div class="admin-s"> 
   			 <textarea rows="5" cols="100"  id="remarks"  name="remarks"></textarea>
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
							callback="window.parent.closeDialogm('edit_vip_m');window.parent.refreshGrid('user');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
   
</script>
<%@include file="../../../common/admin_footer.jsp"%>