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
	<form action="<%=basepath%>/admin/t_hotel_group/addHotelGroup.action"
		enctype="multipart/form-data" method="post">

		<div>
			<span style="color: red;"><strong>基础信息：</strong></span>
		</div>

		<table>
			<tr>
				<th><span style="color:red;">*</span><strong>组名称：</strong></th>
				<td><input type="text" name="groupname" style="width: 200px;"
					value="${t_hotel_group.groupname}" required="required"/></td>
				<th><span style="color:red;">*</span><strong>组类型:</strong></th>
				<td><select name='grouptype' id='grouptype' required="required"> 
						<option value='' selected>-请选择-</option>
						<c:forEach
							items="${fns:getCodeMap(pageContext.request,'hoteltype')}"
							var='item'>
							<option value='${item.key}'>${item.value}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th class="top"><strong>描述：</strong></th>
				<td><textarea name="comm" id="comm" cols="20" rows="5"
						maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
			</textarea></td>
			</tr>
		</table>
		<script>
			$('#comm').val("${t_hotel_group.comm}");
		</script>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_t_hotel_group_m');window.parent.refreshGrid('t_hotel_grouplist');">
		${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
