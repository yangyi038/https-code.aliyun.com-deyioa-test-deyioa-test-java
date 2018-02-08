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
	<form action="<%=basepath%>/admin/shop/editshop.action"
		enctype="multipart/form-data" method="post">
		<input name="id" type="hidden" value="${shop.id}">
		<div class="admin-s-body">
			<span><font style="color: red;">*</font>分级:</span>
			<div class="admin-s">
				<select name="importance" id="importance">
					<c:forEach
						items="${fns:getCodeMap(pageContext.request,'important')}"
						var='item'>
						<option value='${item.key}' <c:if test="${item.key==shop.importance}">selected="selected"</c:if>>${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">行业分类:</span>
			<div class="admin-s">
				<input type="text" value="${shop.categoryid}" id="categoryid" name="categoryid" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>店铺名称:</span>
			<div class="admin-s">
				<input type="text" id="name" name="name" size="40" value="${shop.name}" required="required"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>主旺旺:</span>
			<div class="admin-s">
				<input type="text" id="alww" name="alww" value="${shop.alww}" size="40" required="required"/>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top">地区:</span>
			<div class="admin-s">
				<input type="text" id="placeid" name="placeid" value="${shop.placeid}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">负责人:</span>
			<div class="admin-s">
				<input type="text" id="contact" name="contact" value="${shop.contact}" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">电话:</span>
			<div class="admin-s">
				<input type="text" id="phone" name="phone" value="${shop.phone}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">QQ:</span>
			<div class="admin-s">
				<input type="text" id="qq" name="qq" value="${shop.qq}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">微信:</span>
			<div class="admin-s">
				<input type="text" id="wechat" name="wechat" value="${shop.wechat}" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">SKU:</span>
			<div class="admin-s">
				<input type="text" id="sku" name="sku" value="${shop.sku}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">日咨询量:</span>
			<div class="admin-s">
				<input type="text" id="volume" name="volume" value="${shop.volume}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">月销量:</span>
			<div class="admin-s">
				<input type="text" id="monthsale" name="monthsale" value="${shop.monthsale}" size="40"/>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top">现有客服:</span>
			<div class="admin-s">
				<input type="text" id="customers" name="customers" value="${shop.customers}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">上新频次:</span>
			<div class="admin-s">
				<input type="text" id="newspeed" name="newspeed" value="${shop.newspeed}" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">活动频次:</span>
			<div class="admin-s">
				<input type="text" id="promotions" name="promotions" value="${shop.promotions}" size="40"/>
			</div>
		</div>
		<br/>
		

		<div class="admin-s-body">
			<span class="top">跟进信息:</span>
			<div class="admin-s">
				<textarea rows="5" cols="65" type="text" id="follows" name="follows" 
					>${shop.follows}</textarea>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">备注:</span>
			<div class="admin-s">
				<textarea rows="5" cols="45" type="text" id="remark" name="remark" 
					>${shop.remark}</textarea>
		</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>负责组</span>
			<div class="admin-s">
	 			<select name="groupid" id="groupid">
					<option value='' selected>-请选择-</option>
					<c:forEach
						items="${groupList}"
						var='item'>
						<option value='${item.id}' <c:if test="${item.id==shop.groupid}">selected="selected"</c:if>>${item.depname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br/>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_shop_m');window.parent.refreshGrid('shopText');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	window.close = function()
	{
		parent.searchContent();
	}

	function checkdep() {
		var name = $("#name").val();
		var alww = $("#alww").val();
		var info = "";
		
		if (name == " ") {
			info += "店铺名称不能为空！";
			modalDialogAlert(info);
			return false;
		}
		
		if (alww == " ") {
			info += "主旺旺不能为空！";
			modalDialogAlert(info);
			return false;
		}

		return true;
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>