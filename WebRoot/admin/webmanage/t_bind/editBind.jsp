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
	<form action="<%=basepath%>/admin/t_bind/updateBindById.action"
		enctype="multipart/form-data" method="get">

		<input id="sid" type="hidden" name="sid" value="${t_bind.sid}" />

		<table style="border-collapse: separate; border-spacing: 0px 10px;">
			<tr>
				<th><span style="color: red;">*</span><strong>酒店名称:</strong></th>
				<td><select name='hotelsid' id='hotelsid' required="required" disabled="true" style="background-color: #DDDDDD;">
						<option value='' selected>-请选择-</option>
						<c:forEach items="${hotellist}" var="v">
							<option value="${v.sid}"
								<c:if test="${v.sid==t_bind.hotelsid}">selected="selected"</c:if>>${v.hotelname}</option>
						</c:forEach>
				</select></td>
				<th><span style="color: red;">*</span><strong>机顶盒账号：</strong></th>
				<td><select name='stbsid' id='stbsid' required="required" disabled="true" style="background-color: #DDDDDD;">
						<option value='' selected>-请选择-</option>
						<c:forEach items="${stbList}" var="v">
							<option value="${v.sid}"
								<c:if test="${v.sid==t_bind.stbsid}">selected="selected"</c:if>>${v.stbnum}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<th><strong>wifi热点：</strong></th>
				<td><input type="text" name="wifi" value="${t_bind.wifi}" /></td>
				<th><strong>wifi密码：</strong></th>
				<td><input type="text" name="wifipwd" value="${t_bind.wifipwd}" /></td>
			</tr>

			<tr>
				<th><strong>客户名：</strong></th>
				<td><input type="text" name="customer" value="${t_bind.customer}" /></td>
				<th><strong>房间号：</strong></th>
				<td><input type="text" name="roomnum" value="${t_bind.roomnum}" /></td>
			</tr>

			<tr>
				<th class="top"><strong>欢迎词：</strong></th>
				<td>
					<textarea name="welcome" id="welcome" cols="20" rows="5"
							maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
					</textarea>
				</td>
			</tr>
			<tr>
				<th class="top"><strong>备注：</strong></th>
				<td>
					<textarea name="comm" id="comm" cols="20" rows="5"
							maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
					</textarea>
				</td>
			</tr>
		</table>
		<script>
			$('#welcome').val("${t_bind.welcome}");
			$('#comm').val("${t_bind.comm}");
		</script>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_t_bind_m');window.parent.refreshGrid('t_bindlist');">
							${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
