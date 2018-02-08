<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script src="<%=basepath%>/js/cache/cache_city.js"></script>
  <script src="<%=basepath%>/js/threeCity.js"></script>
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/village/addVillage.action"
		enctype="multipart/form-data" method="post">
		<div class="admin-s-body">
			<span>省</span>
			<div class="admin-s">
				<select name="province"></select>
			</div>
		</div>
		<div class="admin-s-body">
			<span>市</span>
			<div class="admin-s">
				<select name="city"></select>
			</div>
		</div>
		<div class="admin-s-body">
			<span>*县/区/街道</span>
			<div class="admin-s">
				<select name="district"></select>
			</div>
		</div>
		<div class="textareaBody">
			<span class="title">详细地址</span>
			<div class="textarea">
				<textarea name="address" rows="3"></textarea>
			</div>
		</div>
		<div class="admin-s-body">
			<span>*小区编号</span>
			<div class="admin-s">
				<input type="text" name="vcode" />
			</div>
		</div>
		<div class="admin-s-body">
			<span>*小区名称</span>
			<div class="admin-s">
				<input type="text" name="name" />
			</div>
		</div>
		<div class="admin-s-body">
			<span>期数</span>
			<div class="admin-s">
				<input type="text" name="qnum" />
			</div>
		</div>
		<div class="admin-s-body">
			<span>经度</span>
			<div class="admin-s">
				<input type="text" name="latitude" />
			</div>
		</div>
		<div class="admin-s-body">
			<span>纬度</span>
			<div class="admin-s">
				<input type="text" name="longitude" />
			</div>
		</div>
		<div class="textareaBody">
			<span class="title">备注</span>
			<div class="textarea">
				<textarea name="remark" rows="3"></textarea>
			</div>
		</div>
		<div class="textareaBody">
			<span class="title">搜索关键字</span>
			<div class="textarea">
				<textarea name="keywords" rows="3"></textarea>
			</div>
		</div>
		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return check();">确定</button>
		</div>
		</form>
		</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('add_village_m');window.parent.refreshGrid('village');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript">  
var defaults_ge = {
		s1:'select[name=province]',
		s2:'select[name=city]',
		s3:'select[name=district]'
	};
$(document).ready(function(){
	threeSelect(defaults_ge);
});
function check(){
	var errorinfo="";  
var district =$('select[name=district]').val();	
if(district==null||district==''){
		errorinfo+="县/区/街道不能为空</br>";
	} 
var vcode =$('input[name=vcode]').val();	
if(vcode==null||vcode==''){
		errorinfo+="小区编号不能为空</br>";
	}  
	var name =$('input[name=name]').val();	
	if(name==null||name==''){
		errorinfo+="小区名称不能为空</br>";
	} 
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
</script>
<%@include file="../../../common/admin_footer.jsp"%>