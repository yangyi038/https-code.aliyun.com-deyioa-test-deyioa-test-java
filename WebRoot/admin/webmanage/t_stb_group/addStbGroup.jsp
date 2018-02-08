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
	<form action="<%=basepath%>/admin/t_stb_group/addStbGroup.action"
		enctype="multipart/form-data" method="post">

		<div>
			<span style="color: red;"><strong>基础信息：</strong></span>
		</div>
		<table>
			<tr>
				<th><span style="color:red;">*</span><strong>组名称：</strong></th>
				<td><input type="text" name="groupname" style="width: 200px;"
					value="${t_stb_group.groupname}" required="required"/></td>
			</tr>
			<tr>
				<th class="top"><strong>描述：</strong></th>
				<td><textarea name="comm" id="comm" cols="20" rows="5"
						maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
			</textarea></td>
			</tr>
		</table>
		<script>
			$('#comm').val("${t_stb_group.comm}");
		</script>

		<!-- ======== 关联EPG栏目 ========================= -->
		<div>
			<span style="color: red;"><strong>关联EPG栏目：</strong></span>
		</div>
		&nbsp;&nbsp;&nbsp;
		<div>
			<strong>EPG主栏目：</strong><select name='epgfirst' id='epgfirst'>
				<option value='' selected>-请选择-</option>
				<c:forEach items="${columnlist}" var="v">
					<option value="${v.id}"
						<c:if test="${v.id==t_stb_group.epgfirst}">selected="selected"</c:if>>${v.name}</option>
				</c:forEach>
			</select> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <strong>EPG次栏目：</strong><select name='epgsecond'
				id='epgsecond'>
				<option value='' selected>-请选择-</option>
				<c:forEach items="${columnlist}" var="v">
					<option value="${v.id}"
						<c:if test="${v.id==t_stb_group.epgsecond}">selected="selected"</c:if>>${v.name}</option>
				</c:forEach>
			</select>
		</div>
		<br>

		<!-- ======== 设置信息 ========================= -->
		<div>
			<span style="color: red;"><strong>设置信息：</strong></span>
		</div>
		<table>
			<tr>
				<th><strong>热点名称：</strong></th>
				<td><input type="text" name="wifi" value="${t_stb_group.wifi}" /></td>
				<th><strong>热点密码：</strong></th>
				<td><input type="text" name="wifipwd"
					value="${t_stb_group.wifipwd}" /></td>
			</tr>

			<tr>
				<th><strong>EPG首页：</strong></th>
				<td><input type="text" name="epghome"
					value="${t_stb_group.epghome}" /></td>
				<th><strong>欢迎词：</strong></th>
				<td><input type="text" name="welcome" value="${t_stb_group.welcome}" /></td>
			</tr>

			<tr>
				<th><strong>应用升级地址：</strong></th>
				<td><input type="text" name="applicationup"
					value="${t_stb_group.applicationup}" /></td>
				<th><strong>应用黑名单地址：</strong></th>
				<td><input type="text" name="appblacklist"
					value="${t_stb_group.appblacklist}" /></td>
			</tr>

			<tr>
				<th><strong>开机图片地址：</strong></th>
				<td><input type="text" name="openpic"
					value="${t_stb_group.openpic}" /></td>
				<th><strong>开机动画地址：</strong></th>
				<td><input type="text" name="openvideo"
					value="${t_stb_group.openvideo}" /></td>
			</tr>
			<tr>
				<th><strong>开机logo：</strong></th>
				<td><input type="text" name="openlogo"
					value="${t_stb_group.openlogo}" /></td>
				<th><strong>rom升级地址：</strong></th>
				<td><input type="text" name="romup"
					value="${t_stb_group.romup}" /></td>
			</tr>

			<tr>
				<th><strong>备份服务器地址：</strong></th>
				<td><input type="text" name="backupserver"
					value="${t_stb_group.backupserver}" /></td>
				<th><strong>NTP地址：</strong></th>
				<td><input type="text" name="ntp" value="${t_stb_group.ntp}" /></td>
			</tr>

			<tr>
				<th><strong>路由授权号：</strong></th>
				<td><input type="text" name="routerpower"
					value="${t_stb_group.routerpower}" /></td>
				<th><strong>前端设备SN：</strong></th>
				<td><input type="text" name="presn"
					value="${t_stb_group.presn}" /></td>
			</tr>

			<tr>
				<th><strong>流媒体服务器ID：</strong></th>
				<td><input type="text" name="mediaserverid"
					value="${t_stb_group.mediaserverid}" /></td>
				<th><strong>日志服务器地址：</strong></th>
				<td><input type="text" name="logserver"
					value="${t_stb_group.logserver}" /></td>
			</tr>

			<tr>
				<th><strong>维修通知图片地址：</strong></th>
				<td><input type="text" name="noticepic"
					value="${t_stb_group.noticepic}" /></td>
				<th><strong>VOD地址：</strong></th>
				<td><input type="text" name="vod" value="${t_stb_group.vod}" /></td>
			</tr>
		</table>


		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_t_stb_group_m');window.parent.refreshGrid('t_stb_grouplist');">
		${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
