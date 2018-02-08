<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<script type="text/javascript"
	src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/tree/tree_data.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>

<div class="modal-real-body">
	<form action="<%=basepath%>/admin/t_company/updateYunAdmin.action"
		enctype="multipart/form-data" method="post">
		<input id="sid" type="hidden" name="sid" value="${t_company_admin.sid}" />

		<table>
			<tr>
				<th><span style="color: red;">*</span><strong>管理员名称：</strong></th>
				<td><input type="text" name="loginname"
					value="${t_company_admin.loginname}" required="required" /></td>
				<th><span style="color: red;">*</span><strong>登录密码：</strong></th>
				<td><input type="password" name="loginpwd"
					value="${t_company_admin.loginpwd}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>角色：</strong></th>
				<td><select name="privilege" style="width: 163px"
					required="required">
						<c:forEach items="${roles}" var="item">
							<option value="${item.id}">${item.rolename}</option>
						</c:forEach>
				</select></td>
				<th><strong>手机：</strong></th>
				<td><input type="text" name="phone"
					value="${t_company_admin.phone}" /></td>
			</tr>
			<tr>
				<th><strong>Email：</strong></th>
				<td><input type="text" name="Email"
					value="${t_company_admin.email}" /></td>
				<th><strong>QQ：</strong></th>
				<td><input type="text" name="qq" value="${t_company_admin.qq}" /></td>
			</tr>
		</table>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary">确定</button>
		</div>
	</form>

</div>


<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_t_company_admin_m');window.parent.refreshGrid('t_company_admin');">
							${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
