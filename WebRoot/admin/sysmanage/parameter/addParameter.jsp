<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js"
	type="text/javascript"></script>

<div class="modal-real-body">
	<form action="<%=basepath%>/admin/parameter/addParameter.action"
		enctype="multipart/form-data" method="post">

		<table>
			<tr>
				<th><span style="color:red;">*</span><strong>枚举类别：</strong></th>
				<td><input type="text" name="ptype" style="width: 200px;"
					value="${parameter.ptype}" required="required"/></td>
			</tr>
			<tr>
				<th><span style="color:red;">*</span><strong>枚举名称：</strong></th>
				<td><input type="text" name="pname" style="width: 200px;"
					value="${parameter.pname}" required="required"/></td>
			</tr>
			<tr>
				<th><span style="color:red;">*</span><strong>排序序号：</strong></th>
				<td><input type="text" name="porder" style="width: 200px;"
					value="${parameter.porder}"  class="znumber"/></td>
			</tr>
			<tr>
				<th><span style="color:red;">*</span><strong>枚举编号：</strong></th>
				<td><input type="text" name="pcode" style="width: 200px;"
					value="${parameter.pcode}"  class="znumber"/></td>
			</tr>
		</table>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_parameter_m');window.parent.refreshGrid('parameterlist');">
		${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
