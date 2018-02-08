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
<script src="<%=basepath%>/js/easyui/jquery.min.js"></script>  
<!-- http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js -->
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#groupbaseinfo" data-toggle="tab"><strong>组基本信息</strong></a></li>
		<li><a href="#userinfolist" data-toggle="tab"><strong>组用户列表</strong></a></li>
	</ul>

	<div id="myTabContent" class="tab-content">
		<br>
		<div class="tab-pane fade in active" id="groupbaseinfo">
			<br>
			<div>
				<span>组编号：</span> <input type="text" name="groupnum" disabled="true"
					value="${t_hotel_group.groupnum}" /> 
					&nbsp;&nbsp;&nbsp; 
					<span>组名称：</span> <input type="text" name="groupname" disabled="true"
					value="${t_hotel_group.groupname}" />
			</div>
			<br>
			<div>
				<span>组类型：</span> <input type="text" name="grouptype"
					disabled="true" value="${t_hotel_group.grouptypeStr}" />
				&nbsp;&nbsp;&nbsp; <span>描述 ：</span> <input type="text" name="comm"
					disabled="true" value="${t_hotel_group.comm}" />
			</div>
			<br>
			<div>
				<span>创建时间：</span> <input type="text" name="createtime"
					disabled="true" value="${t_hotel_group.createtime}" />
			</div>
		</div>

		<!-- 机顶盒账号 -->
		<div class="tab-pane fade" id="userinfolist">
			<br>
			<table class="table table-striped table-bordered table-hover"
				style="width: 100%">
				<!--style="width: 100%;" border="1"  -->
				<tr>
					<th>用户编号</th>
					<th>用户名称</th>
					<th>用户类型</th>
					<th>手机号</th>
					<th>创建时间</th>
				</tr>
				<c:if test="${hotelList != null}">
					<c:forEach items="${hotelList}" var="p">
						<tr>
							<td>${p.hotelnum}</td>
							<td>${p.hotelname}</td>
							<td>${p.hoteltypeStr}</td>
							<td>${p.tel}</td>
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
							<c:if test="${v.id==t_hotel_group.epgfirst}">selected="selected"</c:if>>${v.name}</option>
					</c:forEach>
				</select> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<strong>EPG次栏目：</strong><select
					name='epgsecond' id='epgsecond' disabled="true"
					style="background-color: #DDDDDD;">
					<option value='' selected>-请选择-</option>
					<c:forEach items="${columnlist}" var="v">
						<option value="${v.id}"
							<c:if test="${v.id==t_hotel_group.epgsecond}">selected="selected"</c:if>>${v.name}</option>
					</c:forEach>
				</select>
			</div>
			<br>

		</div>
	</div>

</div>

<%@include file="../../../common/admin_footer.jsp"%>