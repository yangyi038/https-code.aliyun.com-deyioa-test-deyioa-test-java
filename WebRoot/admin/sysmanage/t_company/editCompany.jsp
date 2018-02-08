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
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>

<div class="modal-real-body">
	<form action="<%=basepath%>/admin/t_company/editCompany.action"
		enctype="multipart/form-data" method="post">
		<input id="sid" type="hidden" name="sid" value="${t_company.sid}" />

		<table>
			<tr>
				<th><span style="color: red;">*</span><strong>运营商名称：</strong></th>
				<td><input type="text" name="companyname" size="22"
					value="${t_company.companyname}" required="required" /></td>
				<th><span>负责人：</span></th>
				<td><input type="text" name="boss" size="22"
					value="${t_company.boss}" /></td>
			</tr>
			<tr>
				<th><span>手机号：</span></th>
				<td><input type="text" name="phone" size="22"
					value="${t_company.phone}" /></td>
				<th><span style="color: red;">*</span><strong>登录密码：</strong></th>
				<td><input type="password" name="loginpwd" size="22"
					value="${t_company.loginpwd}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>角色：</strong></th>
				<td><select name="role" required="required"
					style="width: 150px;">
						<c:forEach items="${roles}" var="item">
							<option value="${item.id}">${item.rolename}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary">确定</button>
		</div>
	</form>

</div>


<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_t_company_m');window.parent.refreshGrid('t_company');">
							${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
