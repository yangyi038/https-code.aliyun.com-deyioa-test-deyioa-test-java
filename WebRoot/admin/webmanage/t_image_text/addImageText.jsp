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
	<form action="<%=basepath%>/admin/problem/addproblem.action.action"
		enctype="multipart/form-data" method="post">

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>标题:</span>
			<div class="admin-s">
				<input type="text" name="name" />
			</div>
		</div>

		<div class="admin-s-body">
			<span>显示格式:</span>
			<div class="admin-s">
				<select name='showType' id='showType' required="required">
					<option value='' selected>-请选择-</option>
					<c:forEach
						items="${fns:getCodeMap(pageContext.request,'showtype')}"
						var='item'>
						<option value='${item.key}'>${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br />

		<div class="admin-s-body">
			<span>图文模板:</span>
			<div class="admin-s">
				<select name='templet' id='templet'>
					<option value='' selected>-请选择-</option>
					<c:forEach items="${templateList}" var="v">
						<option value="${v.id}"
							<c:if test="${v.id==templet}">selected="selected"</c:if>>${v.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br />

		<div class="admin-s-body">
			<span class="top">内容:</span>
			<div class="admin-s">
				<textarea rows="5" cols="65" type="text" name="content"> </textarea>
			</div>
		</div>
		<br />

		<div class="admin-s-body">
			<span>标签属性:</span>
			<div class="admin-s">
				<input type="text" name="lableAttribute" style="width: 482px;" />
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
		callback="window.parent.closeDialogm('add_t_image_m');window.parent.refreshGrid('imageText');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	function checkdep() {
		var info = "";
		var name = $("input[name=name]").val();
		if (name == "") {
			info += "标题不能为空！";
			modalDialogAlert(info);
			return false;
		}

		return true;
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
