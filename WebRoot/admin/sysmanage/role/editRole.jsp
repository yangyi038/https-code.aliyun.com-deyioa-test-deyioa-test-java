<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/tree/tree_data.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet" type="text/css" />
<div class="modal-real-body">
<form action="<%=basepath%>/admin/role/updateRole.action" enctype="multipart/form-data" method="post" onsubmit="checkPrim()">
<input id="id" type="hidden" name="id" value="${role.id}"/>
<input id="menuid" type="hidden" name="menuid" value="${role.menuid}"/>
		        		<div class="admin-s-body">
			        		<span>管理员名称</span>
			        		<div class="admin-s"><input type="text" name="rolename" placeholder="输入管理员名称" value="${role.rolename}"/></div>
			        	</div>
			        	<div class="admin-s-body">
			        		<span>数据权限</span>
			        		<div class="admin-s">
			        			<select name="dataarea">
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'dataarea')}" var="item">
			        			   <option value="${item.key}" ${role.dataarea==item.key?'selected="selected"':''}>${item.value}</option>
			        			 </c:forEach>  
			        			</select>
			        		</div>
			        	</div>
			        	<div class="admin-s-body block">
			        		<span>权限列表</span>
			        		<div class="admin-s">
			        			 <div class="tree-box">
			        			 	<div id="tree"> </div>
			        			 	</div>
			        		</div>
			        	</div>
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary">确定</button>
			        		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('edit_role_m');window.parent.refreshGrid('rolelist');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript">
	function load(data) {
		var o = {
			showcheck : true

		};
		o.data = data;
		$("#tree").treeview(o);
	}
	var teipprim = "";
	var teiparper = "";
	//构造权限字符串
	function checkPrim() {
		var s = $("#tree").getTSNs(true);
		if (s != null) {
			for (var i = 0; i < s.length; i++) {
				var checkstate=s[i].checkstate;
				teipprim = teipprim + s[i].id +"("+checkstate+")"+ "|";
			}
			$("#menuid").val(teipprim);
		} else {
			$("#menuid").val("");
		}
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>