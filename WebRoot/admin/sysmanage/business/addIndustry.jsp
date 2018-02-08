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
	<form action="<%=basepath%>/admin/problem/addindustry.action"
		enctype="multipart/form-data" method="post">

		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>行业包名称:</span>
			<div class="admin-s">
				<input type="text" id="name" name="name" size="60"/>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>行业包备注:</span>
			<div class="admin-s">
				<textarea rows="5" cols="40" type="text" id="remark" name="remark" required="required"> </textarea>
			</div>
		</div>
		<br/>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_industryid_m');window.parent.refreshGrid('industryid');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	function checkdep() {
		var problem = $("#question").val();
		var derive = $("#derive").val();
		var answer = $("#answer").val();
		var titlename = $("#titlename").val();
		var info = "";
		
		if (problem == " ") {
			info += "问题不能为空！";
			modalDialogAlert(info);
			return false;
		}
		
		if (derive == " ") {
			info += "衍生问题不能为空！";
			modalDialogAlert(info);
			return false;
		}
		
		if (answer == " ") {
			info += "答案不能为空！";
			modalDialogAlert(info);
			return false;
		}
		
		if (titlename == "") {
			info += "标题名称不能为空！";
			modalDialogAlert(info);
			return false;
		}
		return true;
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>