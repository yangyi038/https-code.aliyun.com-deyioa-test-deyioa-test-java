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
	<form action="<%=basepath%>/admin/parameter/updateParameter.action"
		enctype="multipart/form-data" method="post">

		<input id="id" type="hidden" name="id" value="${parameter.id}" />

		<table>
			<tr>
				<th><span style="color: red;">*</span><strong>枚举类别：</strong></th>
				<td><input type="text" name="ptype" style="width: 200px;"
					value="${parameter.ptype}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>枚举名称：</strong></th>
				<td><input type="text" name="pname" style="width: 200px;"
					value="${parameter.pname}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>是否显示：</strong></th>
				<td><input type="text" name="isshow" style="width: 200px;"
					value="${parameter.isshow}" required="required" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>排序序号：</strong></th>
				<td><input type="text" name="porder" style="width: 200px;"
					value="${parameter.porder}" class="znumber" /></td>
			</tr>
			<tr>
				<th><span style="color: red;">*</span><strong>枚举编号：</strong></th>
				<td><input type="text" name="pcode" style="width: 200px;"
					value="${parameter.pcode}" class="znumber" /></td>
			</tr>
		</table>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_parameter_m');window.parent.refreshGrid('parameterlist');">
							${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
