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
<script src="<%=basepath%>/js/jquery.formatDateTime.js"
	type="text/javascript"></script>


<div class="admin-s-body">
	<span>图片名称:</span>
	<div class="admin-s">
		<input type="text" style="width: 165px" value="${picture.pictureName}"
			readonly="readonly" />
	</div>
	<span>图片原名称:</span>
	<div class="admin-s">
		<input type="text" style="width: 165px"
			value="${picture.oldPictureName}" readonly="readonly" />
	</div>
</div>
<br />

<div class="admin-s-body">
	<span>链接类型</span>
	<div class="admin-s">
		<input type="text" style="width: 165px" value="${picture.linkType}"
			readonly="readonly" />
	</div>
	<span>点击跳转链接</span>
	<div class="admin-s">
		<input type="text" style="width: 165px" value="${picture.linkUrl}"
			readonly="readonly" />
	</div>
</div>
<br />

<div class="admin-s-body">
	<span>状态</span>
	<div class="admin-s">
		<input type="text" style="width: 165px" value="${picture.status}"
			readonly="readonly" />
	</div>
	<span>上传时间</span>
	<div class="admin-s">
		<input type="text" style="width: 165px" value="${picture.tempTime}"
			readonly="readonly" />
	</div>
</div>
<br />

<div class="admin-s-body">
	<span>图片组:</span>
	<div class="admin-s">
		<input type="text" style="width: 165px"
			value="${picture.pictureGroup}" readonly="readonly" />
	</div>
	<span>图片路径：</span>
	<div class="admin-s">
		<div class="admin-s">
			<input type="text" id="picurl" style="width: 165px"
				value="${picture.pic_url}" readonly="readonly" />
		</div>
	</div>
</div>
<br />

<div class="admin-s-body">
	<span>图片预览：</span>
	<div class="admin-s">
		<div class="admin-s">
			<img id="showimg" src="" style="width: 200px; height: 200px;">
		</div>
	</div>
</div>
<br />


<script type="text/javascript">
	$(document).ready(function() {
		var url = $("#picurl").val();
		alert(url);
		$("#showimg").src = url;
	});
</script>
<%@include file="../../../common/admin_footer.jsp"%>
