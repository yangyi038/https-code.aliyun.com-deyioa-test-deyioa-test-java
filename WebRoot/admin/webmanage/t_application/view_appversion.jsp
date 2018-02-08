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
	<div class="admin-s-body">
		<span>所属应用:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.applicationName}" />
		</div>
	</div>
	<br />
	<div class="admin-s-body">
		<span>版本名称:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.name}" />
		</div>
	</div>
	<br />
	<div class="admin-s-body">
		<span>下载地址:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.downloadUrl}" />
		</div>
	</div>
	<br />
	<div class="admin-s-body">
		<span>包名:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.packageName}" />
		</div>
	</div>
	<br />
	<div class="admin-s-body">
		<span>应用版本:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.version}" />
		</div>
	</div>
	<br />
	<div class="admin-s-body">
		<span>最低系统:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.lowestSystem}" />
		</div>
	</div>
	<br />
	<div class="admin-s-body">
		<span>创建时间:</span>
		<div class="admin-s">
			<input type="text" value="${appEditions.tempTime}" />
		</div>
	</div>
	<br />
</div>

<!-- <script type="text/javascript">
	$(document).ready(function() {
		document.getElementByTag('input').disabled=true;
	});
</script>
 -->
<%@include file="../../../common/admin_footer.jsp"%>
