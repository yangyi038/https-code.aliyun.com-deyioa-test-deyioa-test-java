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
	<form action="<%=basepath%>/admin/t_imageText/addThumbnail.action"
		enctype="multipart/form-data" method="post">

		<input type="text" name="imagetextId" style="visibility: hidden;"
			value="${id}" /><br />
		<div class="admin-s-body">
			<span><font style="color: red;">*</font>缩略图名称:</span>
			<div class="admin-s">
				<input type="text" name="name" style="width: 200px;" />
			</div>
		</div>
		<br />

		<div class="admin-s-body" id="radio_text">
			<span><font style="color: red;">*</font>选择缩略图:</span>
			<div class="admin-s">
				<div class="admin-s">
					<input type="file" name="tipic" id="imageUrl" style="width: 200px;" />
				</div>
				<br /> <img id="showimg" src=""
					style="width: 200px; height: 200px;">
			</div>
		</div>
		<br />

		<div class="admin-s-body">
			<span>图片类型:</span>
			<div class="admin-s">
				<select name="type" style="width: 200px;">
					<option>缩略图</option>
				</select>
			</div>
		</div>
		<br />
		<div class="admin-s-body">
			<span>描述:</span>
			<div class="admin-s">
				<textarea rows="3" cols="2" name="imageDesc" id="lableDescribe"
					style="width: 200px;"></textarea>
			</div>
		</div>
		<br />

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_t_image_m');window.parent.refreshGrid('image');
	">
		${meg}
	</e:msgdialog>
</c:if>

<script type="text/javascript">
	/* 预览图片 */
	var fileInput = document.querySelector('input[type=file]'), previewImg = document.querySelector('img');
	fileInput.addEventListener('change', function() {
		var file = this.files[0];
		var reader = new FileReader();
		// 监听reader对象的的onload事件，当图片加载完成时，把base64编码賦值给预览图片
		reader.addEventListener("load", function() {
			previewImg.src = reader.result;
		}, false);
		// 调用reader.readAsDataURL()方法，把图片转成base64
		reader.readAsDataURL(file);
	}, false);

	function checkdep() {
		var info = "";
		var name = $("input[name=name]").val();
		if (name == "") {
			info += "名称不能为空！";
			modalDialogAlert(info);
			return false;
		}

		var imageUrl = $("input[name=imageUrl]").val();
		if (imageUrl == "") {
			info += "图片URL不能为空！";
			modalDialogAlert(info);
			return false;
		}
		return true;
	}

	function radioFile() {
		alert("ddddddddddd");
		$("#radio_file").show();
		var value = $("#imageUrl").val();
		alert(value);
		value = empty();
		alert(value);
		$("#radio_text").hide();
	}

	function radioText() {
		$("#radio_text").show();
		$("#radio_file").val().empty();
		$("#radio_file").hide();
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
