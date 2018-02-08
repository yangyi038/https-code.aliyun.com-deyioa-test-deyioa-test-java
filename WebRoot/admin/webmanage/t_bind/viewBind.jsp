<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@include file="../../../common/admin_head.jsp"%>
<script type="text/javascript"
	src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet"
	type="text/css" />
<div class="frame-container">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div class="yz-admin-body">
					<div class="table-box">
						<table class="table table-striped table-bordered table-hover"
							style="width: 100%">

							<tbody>
								<tr>
									<th>用户名称 :</th>
									<td>${t_bind.hotelname}</td>
									<th>机顶盒编号:</th>
									<td>${t_bind.stbnum}</td>
								</tr>
								<tr>
									<th>房间号:</th>
									<td>${t_bind.roomnum}</td>
									<th>wifi热点:</th>
									<td>${t_bind.wifi}</td>
								</tr>
								<tr>
									<th>顾客名称:</th>
									<td>${t_bind.customer}</td>
									<th>欢迎词:</th>
									<td>${t_bind.welcome}</td>
								</tr>
								<tr>
									<th>创建时间:</th>
									<td>${t_bind.createtimeStr}</td>
									<th>备注:</th>
									<td>${t_bind.comm}</td>
								</tr>

							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../../../common/admin_footer.jsp"%>
