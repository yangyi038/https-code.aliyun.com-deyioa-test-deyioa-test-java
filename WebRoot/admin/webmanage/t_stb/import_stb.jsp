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
	<form action="<%=basepath%>/admin/t_stb/importStb.action"
		enctype="multipart/form-data" method="post">

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>Excel文件:</span>
			<div class="admin-s">
				<input type="file" name="filePath" id="stb" style="width: 300px;" required="required"/>
			</div>
		</div>
		<br />
		<div class="admin-s-body">
			<span>组名称:</span>
			<div class="admin-s">
				<select name="stbgroupname" style="width: 300px">
					<option value="no">--请选择--</option>
					<c:forEach items="${list}" var="item">
						<option>${item.groupname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br />
		<div class="admin-s-body">
			<span><font style="color: red;">*</font>机顶盒类型：</span>
			<div class="admin-s">
				<select name="stbtype" id="stbtype" required="required"
					style="width: 300px;">
					<option value='' selected>-请选择-</option>
					<c:forEach
						items="${fns:getCodeMap(pageContext.request,'terminaltype')}"
						var='item'>
						<option value='${item.key}'>${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br />
		<div class="admin-s-body">
			<span>应用版本号:</span>
			<div class="admin-s">
				<input type="text" name="romversion" style="width: 300px;" />
			</div>
		</div>
		<br />
		<div class="admin-s-body">
			<span>固件版本号:</span>
			<div class="admin-s">
				<input type="text" name="romfirmware" style="width: 300px;" />
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
		callback="window.parent.closeDialogm('import_t_stb_m');window.parent.refreshGrid('t_stblist');">
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
		var stb = $("#stb").val();

		if (stb == "") {
			info += "Excel文件不能为空！";
			modalDialogAlert(info);
			return false;
		}

		if (stb.indexOf(".xlsx") > 0 || stb.indexOf(".xls") > 0) {
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
