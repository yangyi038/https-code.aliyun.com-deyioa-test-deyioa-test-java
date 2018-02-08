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
	<form action="<%=basepath%>/admin/t_picture/addT_picture.action"
		enctype="multipart/form-data" method="post">

		<div style="float: left;">
			<span><font style="color: red;">*</font>图片组</span>
		</div>
		<div style="position: relative; float: left; margin-left: 80px;">
			<span style="margin-left: 300px; width: 18px; overflow: hidden;">
				<select style="width: 168px; margin-left: -300px"
				onchange="groupChange()" name="pictureGroup" id="select">
					<c:forEach items="${picList}" var="item">
						<option>${item}</option>
					</c:forEach>
			</select>
			</span> <input name="newPictureGroup" id="_input"
				style="width: 150px; height: 30px; position: absolute; left: 0px;">
		</div>
		<br /> <br />

		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>选择图片</span>
			<div class="admin-s">
				<div class="admin-s">
					<input type="file" name="tipic" id="pictureName" onchange="test()"
						style="width: 165px" />
				</div>
				<br /> <img id="showimg" src=""
					style="width: 200px; height: 200px;">
			</div>
		</div>
		<br />

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>链接类型</span>
			<div class="admin-s">
				<select name="linkType" id="linkType">
					<option selected="selected" value="no">--请选择--</option>
					<option>图片</option>
					<option>视频</option>
					<option>HTML页面</option>
				</select>
			</div>
		</div>
		<br />

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>点击跳转链接</span>
			<div class="admin-s">
				<input type="text" name="linkUrl" />
			</div>
		</div>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_t_picture_s');window.parent.refreshGrid('picture');
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

	/* input选择事件 */
	function groupChange() {
		$("#_input").val($("#select option:selected").text());
	}

	function checkdep() {
		var info = "";
		var select = $("#select").val();
		if (select == "no") {
			info += "图片组不能为空！";
			modalDialogAlert(info);
			return false;
		}

		var pictureName = $("#pictureName").val();
		var linkUrl = $("input[name=linkUrl]").val();
		if (pictureName == "") {
			info += "图片不能为空！";
			modalDialogAlert(info);
			return false;
		}

		var linkType = $("#linkType").val();
		if (linkType == "no") {
			info += "链接类型不能为空！";
			modalDialogAlert(info);
			return false;
		}

		var linkUrl = $("input[name=linkUrl]").val();
		if (linkUrl == "") {
			info += "跳转链接不能为空！";
			modalDialogAlert(info);
			return false;
		}

		/* if(info!=""){
			modalDialogAlert(info);
			return false;
		}  */

		return true;
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
