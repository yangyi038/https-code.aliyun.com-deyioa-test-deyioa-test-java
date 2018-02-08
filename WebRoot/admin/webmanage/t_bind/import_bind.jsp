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
	<form action="<%=basepath%>/admin/t_bind/importBind.action"
		enctype="multipart/form-data" method="post">

		<div class="admin-s-body">
			<span><font style="color: red;">*</font><strong>Excel文件:</strong></span>
			<div class="admin-s">
				<input type="file" id="bindfile" name="filePath"  style="width: 300px;" required="required"/>
			</div>
		</div>
		<br />

		<table style="border-collapse: separate; border-spacing: 0px 10px;">
			<tr>
				<th><strong>wifi热点：</strong></th>
				<td><input type="text" name="wifi" value="${t_bind.wifi}" /></td>
				<th><strong>wifi密码：</strong></th>
				<td><input type="text" name="wifipwd" value="${t_bind.wifipwd}" /></td>
			</tr>

			<tr>
				<th><strong>客户名：</strong></th>
				<td><input type="text" name="customer"
					value="${t_bind.customer}" /></td>
				<th><strong>房间号：</strong></th>
				<td><input type="text" name="roomnum" value="${t_bind.roomnum}" /></td>
			</tr>

			<tr>
				<th class="top"><strong>欢迎词：</strong></th>
				<td><textarea name="welcome" id="welcome" cols="20" rows="5"
						maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
					</textarea></td>
				<th class="top"><strong>备注：</strong></th>
				<td><textarea name="comm" id="comm" cols="20" rows="5"
						maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
					</textarea></td>
			</tr>
		</table>
		<script>
			$('#welcome').val("${t_bind.welcome}");
			$('#comm').val("${t_bind.comm}");
		</script>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>
</div>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('import_t_bind_m');window.parent.refreshGrid('t_bindlist');">
		${meg}
	</e:msgdialog>
</c:if>

<c:if test="${errorMeg!=null}">
	<e:msgdialog basepath="<%=basepath%>" callback="">
		${errorMeg}
	</e:msgdialog>
</c:if>

<script type="text/javascript">
	function checkdep() {
		var info = "";
		var bindfile = $("#bindfile").val();

		if (bindfile == "") {
			info += "Excel文件不能为空！";
			modalDialogAlert(info);
			return false;
		}

		if (bindfile.indexOf(".xlsx") > 0 || bindfile.indexOf(".xls") > 0) {
			return true;
		} else {
			info += "请上传Excel文件！";
			modalDialogAlert(info);
			return false;
		}

		return true;
	}
</script>

<%@include file="../../../common/admin_footer.jsp"%>
