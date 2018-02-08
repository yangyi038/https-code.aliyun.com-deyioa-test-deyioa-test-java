<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
	
<script type="text/javascript"
	src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/tree/tree_data.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet"
	type="text/css" />
	
<%
	//获取当前登录用户
	Sysuser user = ContextHolderUtils.getLoginUser();
%>
	
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/role/addRole.action"
		enctype="multipart/form-data" method="post" onsubmit="checkPrim()">
		<!-- 菜单ID -->
		<input id="menuid" type="hidden" name="menuid" />
		<!-- 增删改查操作权限 -->
		
		
		<div class="admin-s-body">
			<span>角色名称</span>
			<div class="admin-s">
				<input type="text" name="rolename" />
			</div>
			<!--placeholder="输入管理员名称"  -->
		</div>
		
		<div class="admin-s-body">
			<span>数据权限</span>
			<div class="admin-s">
				<select name="dataarea">
					<c:forEach
						items="${fns:getCodeMap(pageContext.request,'dataarea')}"
						var="item">
						<option value="${item.key}">${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br>
		
		<div class="admin-s-body" id="touser">
			<span>适用对象：</span>
			<div class="admin-s">
				<select name="touser" >
				  <option value="-1">--请选择--</option>
				  <option value="3">二级运营商</option>
				  <option value="4">经销商</option>
				</select>
			</div>
		</div>
		
		<div class="admin-s-body block">
			<span class="top">权限列表</span>
			<div class="admin-s">
				<div class="tree-box">
					<div id="tree"></div>
				</div>
			</div>
		</div>
		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary">确定</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</form>

</div>


<script type="text/javascript">

//加载表单
$(document).ready(function(){
	if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		$('#touser').show();
	 }else{
		$('#touser').hide();
	 }
});

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
				var checkstate = s[i].checkstate;
				teipprim = teipprim + s[i].id + "(" + checkstate + ")" + "|";
			}
			$("#menuid").val(teipprim);
		} else {
			$("#menuid").val("");
		}
		return false;
	};
</script>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_role_m');window.parent.refreshGrid('rolelist');">
							${meg}
						</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>