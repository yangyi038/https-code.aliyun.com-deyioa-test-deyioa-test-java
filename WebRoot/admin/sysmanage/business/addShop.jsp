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
	<form action="<%=basepath%>/admin/shop/addshop.action"
		enctype="multipart/form-data" method="post">
		<input name="id" type="hidden" value="">
		<div class="admin-s-body">
			<span><font style="color: red;">*</font>分级:</span>
			<div class="admin-s">
				<select name="importance" id="importance">
					<c:forEach
						items="${fns:getCodeMap(pageContext.request,'important')}"
						var='item'>
						<option value='${item.key}'>${item.value}</option>
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
				<input type="text" id="categoryid" name="categoryid" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>店铺名称:</span>
			<div class="admin-s">
				<input type="text" id="name" name="name" size="40" required="required"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>主旺旺:</span>
			<div class="admin-s">
				<input type="text" id="alww" name="alww" size="40" required="required"/>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top">地区:</span>
			<div class="admin-s">
				<input type="text" id="placeid" name="placeid" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">负责人:</span>
			<div class="admin-s">
				<input type="text" id="contact" name="contact" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">电话:</span>
			<div class="admin-s">
				<input type="text" id="phone" name="phone" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">QQ:</span>
			<div class="admin-s">
				<input type="text" id="qq" name="qq" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">微信:</span>
			<div class="admin-s">
				<input type="text" id="wechat" name="wechat" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">SKU:</span>
			<div class="admin-s">
				<input type="text" id="sku" name="sku" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">日咨询量:</span>
			<div class="admin-s">
				<input type="text" id="volume" name="volume" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">月销量:</span>
			<div class="admin-s">
				<input type="text" id="monthsale" name="monthsale" size="40"/>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top">现有客服:</span>
			<div class="admin-s">
				<input type="text" id="customers" name="customers" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">上新频次:</span>
			<div class="admin-s">
				<input type="text" id="newspeed" name="newspeed" size="40"/>
			</div>
		</div>
		
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<div class="admin-s-body">
			<span class="top">活动频次:</span>
			<div class="admin-s">
				<input type="text" id="promotions" name="promotions" size="40"/>
			</div>
		</div>
		<br/>
		

		<div class="admin-s-body">
			<span class="top">跟进信息:</span>
			<div class="admin-s">
				<textarea rows="5" cols="65" type="text" id="follows" name="follows" 
					></textarea>
			</div>
		</div>
		<br/>
		
				<div class="admin-s-body">
			<span class="top">备注:</span>
			<div class="admin-s">
				<textarea rows="5" cols="45" type="text" id="remark" name="remark" 
					></textarea>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>负责组</span>
			<div class="admin-s">
	 			<select name="groupid" id="groupid">
					<option value='' selected>-请选择-</option>
					<c:forEach items="${groupList}" var='item'>
						<option value='${item.id}'>${item.depname}</option>
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
		callback="window.parent.closeDialogm('add_shop_m');window.parent.refreshGrid('shopText');
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