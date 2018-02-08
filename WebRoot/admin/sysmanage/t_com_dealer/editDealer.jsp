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
	<form action="<%=basepath%>/admin/t_com_dealer/updateDealer.action"
		enctype="multipart/form-data" method="post">
		<input id="sid" type="hidden" name="sid" value="${t_com_dealer.sid}" />

		<table>
			<tr>
				<th><span style="color: red;">*</span><strong>经销商名称：</strong></th>
				<td><input type="text" name="name"
					value="${t_com_dealer.name}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>登录密码：</strong></th>
				<td><input type="password" name="pwd"
					value="${t_com_dealer.pwd}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>昵称：</strong></th>
				<td><input type="text" name="nick"
					value="${t_com_dealer.nick}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>联系方式：</strong></th>
				<td><input type="text" name="phone"
					value="${t_com_dealer.phone}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>角色：</strong></th>
				<td><select name="privilege" style="width: 163px"
					required="required">
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
		callback="window.parent.closeDialogm('edit_t_com_dealer_m');window.parent.refreshGrid('t_com_dealer');">
							${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
