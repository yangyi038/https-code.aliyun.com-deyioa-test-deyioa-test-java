<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css" />
<script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>

<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#groupbaseinfo" data-toggle="tab"><strong>组基本信息</strong></a></li>
		<li><a href="#stbinfolist" data-toggle="tab"><strong>组机顶盒列表</strong></a></li>
		<li><a href="#groupconfinfo" data-toggle="tab"><strong>组配置信息</strong></a></li>
	</ul>

	<div id="myTabContent" class="tab-content">
		<br>
		<div class="tab-pane fade in active" id="groupbaseinfo">
			<br>
			<div>
				<span>组编号：</span> <input type="text" name="groupnum" disabled="true"
					value="${t_stb_group.groupnum}" /> &nbsp;&nbsp;&nbsp; <span>组名称
					：</span> <input type="text" name="groupname" disabled="true"
					value="${t_stb_group.groupname}" />
			</div>
			<br>
			<div>
				<span>描述 ：</span> <input type="text" name="comm" disabled="true"
					value="${t_stb_group.comm}" />
			</div>
		</div>

		<!-- 机顶盒列表 -->
		<div class="tab-pane fade" id="stbinfolist">
			<br>
			<table class="table table-striped table-bordered table-hover"
				style="width: 100%">
				<!--style="width: 100%;" border="1"  -->
				<tr>
					<th>机顶盒账号</th>
					<th>所属酒店</th>
					<th>有效截止时间</th>
					<th>余额</th>
					<th>审核状态</th>
					<th>创建时间</th>
				</tr>
				<c:if test="${stbList != null}">
					<c:forEach items="${stbList}" var="p">
						<tr>
							<td>${p.stbnum}</td>
							<td>${p.hotelname}</td>
							<td>${p.validdateStr}</td>
							<td>${p.balance}</td>
							<td>${p.stbstatusStr}</td>
							<td>${p.createtime}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<!-- 用户配置信息 -->
		<div class="tab-pane fade" id="groupconfinfo">
			<div>
				<span style="color: red;"><strong>关联EPG栏目：</strong></span>
			</div>
			<div>
				<strong>EPG主栏目：</strong><select name='epgfirst' id='epgfirst' disabled="true"
					style="background-color: #DDDDDD;">
					<option value='' selected>-请选择-</option>
					<c:forEach items="${columnlist}" var="v">
						<option value="${v.id}"
							<c:if test="${v.id==t_stb_group.epgfirst}">selected="selected"</c:if>>${v.name}</option>
					</c:forEach>
				</select> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<strong>EPG次栏目：</strong><select
					name='epgsecond' id='epgsecond' disabled="true"
					style="background-color: #DDDDDD;">
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
					<td><input type="text" name="wifi" disabled="true"
						value="${t_stb_group.wifi}" /></td>
					<th><strong>热点密码：</strong></th>
					<td><input type="text" name="wifipwd" disabled="true"
						value="${t_stb_group.wifipwd}" /></td>
				</tr>

				<tr>
					<th><strong>EPG首页：</strong></th>
					<td><input type="text" name="epghome" disabled="true"
						value="${t_stb_group.epghome}" /></td>
					<th><strong>VOD地址：</strong></th>
					<td><input type="text" name="vod" value="${t_stb_group.vod}"
						disabled="true" /></td>
				</tr>

				<tr>
					<th><strong>应用升级地址：</strong></th>
					<td><input type="text" name="applicationup" disabled="true"
						value="${t_stb_group.applicationup}" /></td>
					<th><strong>应用黑名单地址：</strong></th>
					<td><input type="text" name="appblacklist" disabled="true"
						value="${t_stb_group.appblacklist}" /></td>
				</tr>

				<tr>
					<th><strong>开机图片地址：</strong></th>
					<td><input type="text" name="openpic" disabled="true"
						value="${t_stb_group.openpic}" /></td>
					<th><strong>开机动画地址：</strong></th>
					<td><input type="text" name="openvideo" disabled="true"
						value="${t_stb_group.openvideo}" /></td>
				</tr>
				<tr>
					<th><strong>开机logo：</strong></th>
					<td><input type="text" name="openlogo" disabled="true"
						value="${t_stb_group.openlogo}" /></td>
					<th><strong>rom升级地址：</strong></th>
					<td><input type="text" name="romup" disabled="true"
						value="${t_stb_group.romup}" /></td>
				</tr>

				<tr>
					<th><strong>备份服务器地址：</strong></th>
					<td><input type="text" name="backupserver"
						value="${t_stb_group.backupserver}" disabled="true" /></td>
					<th><strong>NTP地址：</strong></th>
					<td><input type="text" name="ntp" value="${t_stb_group.ntp}"
						disabled="true" /></td>
				</tr>

				<tr>
					<th><strong>路由授权号：</strong></th>
					<td><input type="text" name="routerpower" disabled="true"
						value="${t_stb_group.routerpower}" /></td>
					<th><strong>前端设备SN：</strong></th>
					<td><input type="text" name="presn" disabled="true"
						value="${t_stb_group.presn}" /></td>
				</tr>

				<tr>
					<th><strong>流媒体服务器ID：</strong></th>
					<td><input type="text" name="mediaserverid" disabled="true"
						value="${t_stb_group.mediaserverid}" /></td>
					<th><strong>日志服务器地址：</strong></th>
					<td><input type="text" name="logserver" disabled="true"
						value="${t_stb_group.logserver}" /></td>
				</tr>

				<tr>
					<th><strong>维修通知图片地址：</strong></th>
					<td><input type="text" name="noticepic" disabled="true"
						value="${t_stb_group.noticepic}" /></td>
				</tr>
			</table>

		</div>
	</div>

</div>

<%@include file="../../../common/admin_footer.jsp"%>