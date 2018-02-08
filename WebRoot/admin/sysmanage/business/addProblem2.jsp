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
	<form action="<%=basepath%>/admin/problem/addproblem2.action"
		enctype="multipart/form-data" method="post">
		<input name="id" type="hidden" value="${problem.id}">
		<div class="admin-s-body">
			<span><font style="color: red;">*</font>商店列表:</span>
			<div class="admin-s">
				<select name="shopid" id="shopid" required="required">
					<option value='' selected>-请选择-</option>
						<c:forEach items="${shopList}" var='item'>
							<option value='${item.id}'>${item.name}</option>
						</c:forEach>
				</select>
			</div>
		</div>

		<div class="admin-s-body">
			<span><font style="color: red;">*</font>问题类型:</span>
			<div class="admin-s">
	 			<select name="industry" id="industry" required="required">
	 				<c:forEach
						items="${fns:getCodeMap(pageContext.request,'industry')}"
						var='item'>
						<option value='${item.key}'>${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="admin-s-body">
			<span class="top">问题分类:</span>
			<div class="admin-s">
				<input type="text" id="qcate" name="qcate" value="${problem.qcate}" size="40"/>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span>问题总体分类:</span>
			<div class="admin-s">
				<select name="categoryid" id="categoryid">
					<c:forEach
						items="${fns:getCodeMap(pageContext.request,'category')}"
						var='item'>
						<option value='${item.key}'>${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top">问题描述/原因:</span>
			<div class="admin-s">
				<textarea rows="5" cols="65" type="text" id="reason" name="reason" 
					></textarea>
			</div>
		</div>

		<div class="admin-s-body">
			<span class="top"><font style="color: red;">*</font>问题:</span>
			<div class="admin-s">
				<textarea rows="4" cols="65" type="text" id="question" name="question"
					required="required"> </textarea>
			</div>
		</div>
		<br/>

		<div class="admin-s-body">
			<span class="top">衍生问题:</span>
			<div class="admin-s">
				<textarea rows="5" cols="65" type="text" id="derive" name="derive" 
					></textarea>
			</div>
		</div>

		<div class="admin-s-body">
			<span class="top">答案:</span>
			<div class="admin-s">
				<textarea rows="3" cols="65" id="answer" name="answer" 
					></textarea>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">产品表头:</span>
			<div class="admin-s">
				<input type="text" id="titlename" name="titlename" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">答案组成:</span>
			<div class="admin-s">
				<input type="text" id="answerForm" name="answerForm" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">关联产品:</span>
			<div class="admin-s">
				<input type="text" id="relationname" name="relationname" size="40"/>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">推荐产品:</span>
			<div class="admin-s">
				<textarea rows="3" cols="65" id="recommend" name="recommend" 
					></textarea>
			</div>
		</div>
		
		<div class="admin-s-body">
			<span class="top">建议/备注:</span>
			<div class="admin-s">
				<textarea rows="3" cols="65" id="suggest" name="suggest" 
					></textarea>
			</div>
		</div>
		<br/>
		
		<div class="admin-s-body">
			<span class="top">变量标签（红色可使用）:</span>
			<div class="admin-s">
				<textarea rows="3" cols="65" id="bugs" name="bugs" 
					></textarea>
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
		callback="window.parent.closeDialogm('add_problem2_m');window.parent.refreshGrid('problemText');
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
		var problem = $("#question").val();
		var info = "";
		
		if (problem == " ") {
			info += "问题不能为空！";
			modalDialogAlert(info);
			return false;
		}
		return true;
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>