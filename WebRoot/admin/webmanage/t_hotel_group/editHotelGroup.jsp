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
	<form
		action="<%=basepath%>/admin/t_hotel_group/updateHotelGroupById.action"
		enctype="multipart/form-data" method="post">

		<input id="sid" type="hidden" name="sid" value="${t_hotel_group.sid}" />
		<div>
			<span style="color: red;"><strong>基础信息：</strong></span>
		</div>

		<table>
			<tr>
				<th><span style="color:red;">*</span><strong>组名称：</strong></th>
				<td><input type="text" name="groupname" style="width: 200px;"
					value="${t_hotel_group.groupname}" required="required"/></td>
				<th><span style="color:red;">*</span><strong>组类型:</strong></th>
				<td>
				
				<select  name="grouptypeStr" id="grouptypeStr"  value="${t_hotel_group.grouptypeStr}" required="required">
			    		<option <c:if test="${t_hotel.hoteltype=='1'}">selected="selected"</c:if>>经理</option>
			    		<option <c:if test="${t_hotel.hoteltype=='2'}">selected="selected"</c:if>>人事</option>
	 		 			<option <c:if test="${t_hotel.hoteltype=='3'}">selected="selected"</c:if>>普通职员</option>
	  					<option <c:if test="${t_hotel.hoteltype=='4'}">selected="selected"</c:if>>业务职员</option>
	  					<option <c:if test="${t_hotel.hoteltype=='5'}">selected="selected"</c:if>>技术职员</option>
		   		</select>
				</td>
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
			<button type="submit" class="btn btn-primary">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_t_hotel_group_m');window.parent.refreshGrid('t_hotel_grouplist');">
							${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
