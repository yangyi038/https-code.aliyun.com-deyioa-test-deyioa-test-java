<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<script type="text/javascript"
	src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/sysuser/addSysuser.action"
		enctype="multipart/form-data" method="post">
		<div class="admin-s-body">
			<span>登录名</span>
			<div class="admin-s">
				<input type="text" name="loginname" size="22" placeholder="请输入登录名" />
			</div>
		</div>
		<div class="admin-s-body">
			<span>密码</span>
			<div class="admin-s">
				<input type="text" name="loginpwd" size="22" placeholder="请输入密码" />
			</div>
		</div>
		<div class="admin-s-body">
			<span>角色</span>
			<div class="admin-s">
				<select name="privilege">
					<c:forEach items="${roles}" var="item">
						<option value="${item.id}">${item.rolename}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="admin-s-body">
			<span>选择组织机构</span>
			<div class="admin-s">
				<input type="text" id="input_tree" name="department.depname">
				<div class="tree-box tree-open" id="tree-box">
					<div id="tree"></div>
					<input name="depid" id="depid" type="hidden" />
				</div>
			</div>
		</div>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return check();">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_sysuser_m');window.parent.refreshGrid('sysuser');">
							${meg}
						</e:msgdialog>
</c:if>
<script type="text/javascript">
function load() {        
    var o = { showcheck: true,url: "<%=basepath%>/admin/department/loadTree.action?isall=<%="false"%>&level=<%=2%>",
			onnodeclick : function(tree, item) {
				var s = $("#tree").getCurrentNode();
				$("#depid").val(s.id);
				$("#input_tree").val(s.text);
				$("#tree-box").hide();
			}

		};
		$("#tree").treeview(o);

	}
	$(document).ready(load);
	function check() {
		var errorinfo = "";
		var loginname = $("input[name=loginname]").val();
		if (loginname == "") {
			errorinfo += "登录名不能为空</br>";
		}
		var loginpwd = $("input[name=loginpwd]").val();
		if (loginpwd == "") {
			errorinfo += "密码不能为空</br>";
		}
		var privilege = $("select[name=privilege]").val();
		if (privilege == "") {
			errorinfo += "角色不能为空</br>";
		}
		
		/* var depid = $("input[name=depid]").val();
		if (depid == "") {
			errorinfo += "组织机构不能为空</br>";
		} */
		
		if (errorinfo != "") {
			modalDialogAlert(errorinfo);
			return false;
		}
		return true;
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>