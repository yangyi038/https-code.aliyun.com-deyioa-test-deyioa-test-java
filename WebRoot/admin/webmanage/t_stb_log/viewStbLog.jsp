<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="e" uri="/yz"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/t_stb/getStb.action"
		enctype="multipart/form-data" method="post">

		<div>
			<span style="color: red;"><strong>配置信息：</strong></span>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>机顶盒账号:</strong></span>
			<div class="admin-s">${t_stb.stbnum}</div>
		</div>
		<br>
		<div class="admin-s-body admin-s-detail">
			<strong>所属机顶盒组：</strong><select name='stbgroup' id='stbgroup'
				disabled="true" style="background-color: #DDDDDD;">
				<option value='' selected>-请选择-</option>
				<c:forEach items="${stbgrouplist}" var="v">
					<option value="${v.sid}"
						<c:if test="${v.sid==t_stb.stbgroup}">selected="selected"</c:if>>${v.groupname}</option>
				</c:forEach>
			</select>
		</div>
		<div class="admin-s-body admin-s-detail">
			<strong>所属酒店:</strong><select name='hotelsid' id='hotelsid'
				disabled="true" style="background-color: #DDDDDD;">
				<option value='' selected>-请选择-</option>
				<c:forEach items="${hotellist}" var="v">
					<option value="${v.sid}"
						<c:if test="${v.sid==t_stb.hotelsid}">selected="selected"</c:if>>${v.hotelname}</option>
				</c:forEach>
			</select>
		</div>
		<br>
		<div class="admin-s-body admin-s-detail">
			<strong>EPG栏目主根节点:</strong><select name='epgroot' id='epgroot'
				disabled="true" style="background-color: #DDDDDD;">
				<option value='' selected>-请选择-</option>
				<c:forEach items="${columnlist}" var="v">
					<option value="${v.id}"
						<c:if test="${v.id==t_stb.epgroot}">selected="selected"</c:if>>${v.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="admin-s-body admin-s-detail">
			<strong>EPG定制栏目根节点:</strong><select name='epgprivate' id='epgprivate'
				disabled="true" style="background-color: #DDDDDD;">
				<option value='' selected>-请选择-</option>
				<c:forEach items="${columnlist}" var="v">
					<option value="${v.id}"
						<c:if test="${v.id==t_stb.epgprivate}">selected="selected"</c:if>>${v.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="admin-s-body admin-s-detail">
			<strong>手机栏目根节点:</strong><select name='telroot' id='telroot'
				disabled="true" style="background-color: #DDDDDD;">
				<option value='' selected>-请选择-</option>
				<c:forEach items="${columnlist}" var="v">
					<option value="${v.id}"
						<c:if test="${v.id==t_stb.telroot}">selected="selected"</c:if>>${v.name}</option>
				</c:forEach>
			</select>
		</div>
		<br>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>账户类型:</strong></span>
			<div class="admin-s">${t_stb.accounttypeStr}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>支付类型:</strong></span>
			<div class="admin-s">${t_stb.paytypeStr}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>余额:</strong></span>
			<div class="admin-s">${t_stb.balance}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>押金:</strong></span>
			<div class="admin-s">${t_stb.deposit}</div>
		</div>
		<br>
		
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>机顶盒状态:</strong></span>
			<div class="admin-s">${t_stb.stbstatusStr}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>有效期:</strong></span>
			<div class="admin-s">${t_stb.validdateStr}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>安装地址:</strong></span>
			<div class="admin-s">${t_stb.installaddress}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>创建时间:</strong></span>
			<div class="admin-s">${t_stb.createtime}</div>
		</div>



		<br> <br>
		<div>
			<span style="color: red;"><strong>固件信息：</strong></span>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>机顶盒ID:</strong></span>
			<div class="admin-s">${t_stb.stbid}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>机顶盒Mac:</strong></span>
			<div class="admin-s">${t_stb.mac}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>应用包版本:</strong></span>
			<div class="admin-s">${t_stb.apkversion}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>ROM版本:</strong></span>
			<div class="admin-s">${t_stb.romversion}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>ROM固件版本:</strong></span>
			<div class="admin-s">${t_stb.romfirmware}</div>
		</div>
		<div class="admin-s-body admin-s-detail">
			<span class="title"><strong>APP类型:</strong></span>
			<div class="admin-s">${t_stb.apptype}</div>
		</div>

	</form>
</div>

<%@include file="../../../common/admin_footer.jsp"%>