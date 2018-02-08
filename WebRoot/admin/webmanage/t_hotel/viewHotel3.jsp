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

<div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active" style="backgroud:"><a href="#userbaseinfo"
			data-toggle="tab"><strong>用户基本信息</strong></a></li>
	</ul>

	<div id="myTabContent" class="tab-content">
		<!--用户基本信息  -->
		<div class="tab-pane fade in active" id="userbaseinfo">  
			<br>
			<div class="table-box">
				<table class="table table-striped table-bordered table-hover"
					style="width: 100%">
					<tbody>
						<tr>
							<th>用户编号:</th>
							<td>${t_hotel.hotelnum}</td>
							<th>用户名称 :</th>
							<td colspan="5">${t_hotel.hotelname}</td>
						</tr>
						<tr>
							<th>用户组:</th>
							<td>${t_hotel.hotelgroup}</td>
							<th>联系人:</th>
							<td>${t_hotel.linkman}</td>
						</tr>
						<tr>
							<th>证件类型:</th>
							<td>${t_hotel.cardtypeStr}</td>
							<th>证件号码:</th>
							<td>${t_hotel.cardid}</td>
						</tr>
						<tr>
							<th>手机号:</th>
							<td>${t_hotel.tel}</td>
							<th>邮编:</th>
							<td>${t_hotel.postcode}</td>
						</tr>
						<tr>
							<th>地址:</th>
							<td>${t_hotel.address}</td>
							<th>电子邮箱:</th>
							<td>${t_hotel.email}</td>
						</tr>
						<tr>
							<th>用户类型:</th>
							<td>${t_hotel.hoteltypeStr}</td>
							<th>创建时间:</th>
							<td>${t_hotel.createtime}</td>
						</tr>
						<tr>
							<th>备注:</th>
							<td>${t_hotel.comm}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="../../../common/admin_footer.jsp"%>