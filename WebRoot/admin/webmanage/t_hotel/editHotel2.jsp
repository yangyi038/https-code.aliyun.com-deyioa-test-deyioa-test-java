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
<!-- 省市区三级联动 -->
<%-- <script src="<%=basepath%>/admin/provinceCity_js/jquery-1.7.min.js" type="text/javascript"></script> --%>
<script src="<%=basepath%>/admin/provinceCity_js/Area.js" type="text/javascript"></script>
<script src="<%=basepath%>/admin/provinceCity_js/AreaData_min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function (){
		initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '0', '0', '0');
	});
	
	//得到地区码
	function getAreaID(){
		var area = 0;          
		if($("#seachdistrict").val() != "0"){
			area = $("#seachdistrict").val();                
		}else if ($("#seachcity").val() != "0"){
			area = $("#seachcity").val();
		}else{
			area = $("#seachprov").val();
		}
		//alert("县："+$("#seachdistrict").val() +"市："+ $("#seachcity").val() +"省:"+  $("#seachprov").val()); 
		//alert("省文本："+$("#seachprov").find("option:selected").text());
		return area;
	}
	
	function showAreaID() {
		//地区码
		var areaID = getAreaID();
		//地区名
		var areaName = getAreaNamebyID(areaID) ;
		//alert("您选择的地区码：" + areaID + "      地区名：" + areaName);            
		
		var province=$("#seachprov").find("option:selected").text();
		var city=$("#seachcity").find("option:selected").text();
		var area=$("#seachdistrict").find("option:selected").text();
		if(province=="请选择"){
			province=${t_hotel.province}
		}
		if(city=="请选择"){
			city=${t_hotel.city}
		}
		if(area=="请选择"){
			area=${t_hotel.area}
		}
		$("#province").val(province);
		$("#city").val(city);
		$("#area").val(area);
	}
	
	//根据地区码查询地区名
	function getAreaNamebyID(areaID){
		var areaName = "";
		if(areaID.length == 2){
			areaName = area_array[areaID];
		}else if(areaID.length == 4){
			var index1 = areaID.substring(0, 2);
			areaName = area_array[index1] + " " + sub_array[index1][areaID];
		}else if(areaID.length == 6){
			var index1 = areaID.substring(0, 2);
			var index2 = areaID.substring(0, 4);
			areaName = area_array[index1] + " " + sub_array[index1][index2] + " " + sub_arr[index2][areaID];
		}
		return areaName;
	}
	
	$(document).ready(function(){
		$("#seachprov").find("option[text='${t_hotel.province}']").attr("selected",true);
		$("#seachcity").find("option[text='${t_hotel.city}']").attr("selected",true);
		$("#seachdistrict").find("option[text='${t_hotel.area}']").attr("selected",true);
	});
</script>

<body>
	<div class="modal-real-body">
		<form  action="<%=basepath%>/admin/t_hotel/updateHotelBySid.action"
			enctype="multipart/form-data" method="post">

			<input id="sid" type="hidden" name="sid" value="${t_hotel.sid}" />

			<div>
				<span style="color: red;"><strong>基本信息：</strong></span>
			</div>
			<table>
				<tr>
					<th><span style="color:red;">*</span><strong>用户名称:</strong></th>
					<td><input type="text" name="hotelname" 
						value="${t_hotel.hotelname}" required="required"/></td>
					<th><strong>用户简称:</strong></th>
					<td><input type="text" name="hotelshort"
						value="${t_hotel.hotelshort}" /></td>
				</tr>
				<tr>
					<th><span style="color:red;">*</span><strong>用户组:</strong></th>
					<td>
						<select name='hotelgroup' id='hotelgroup' required="required">
								<option value='' selected>-请选择-</option>
								<c:forEach items="${hotelgrouplist}" var="v">
									<option value="${v.sid}"
										<c:if test="${v.sid==t_hotel.hotelgroup}">selected="selected"</c:if>>${v.groupname}</option>
								</c:forEach>
						</select>
					</td>
					<th><strong>用户类型:</strong></th>
					<td>
						<select  name="hoteltypeStr" id="hoteltypeStr"  value="${t_hotel.hoteltypeStr}">
				    		<option <c:if test="${t_hotel.hoteltype=='1'}">selected="selected"</c:if>>经理</option>
				    		<option <c:if test="${t_hotel.hoteltype=='2'}">selected="selected"</c:if>>人事</option>
		 		 			<option <c:if test="${t_hotel.hoteltype=='3'}">selected="selected"</c:if>>普通职员</option>
		  					<option <c:if test="${t_hotel.hoteltype=='4'}">selected="selected"</c:if>>业务职员</option>
		  					<option <c:if test="${t_hotel.hoteltype=='5'}">selected="selected"</c:if>>技术职员</option>
			   			</select>
					</td>
				</tr>

				<tr>
					<th><span style="color:red;">*</span> <strong>负责人:</strong></th>
					<td><input type="text" name="linkman"
						value="${t_hotel.linkman}"  required="required" /></td>
					<th><span style="color:red;">*</span><strong>手机号码：</strong></th>
					<td><input type="text" name="tel" value="${t_hotel.tel}" required="required"/></td>
				</tr>

				<tr>
					<th><strong>证件类型:</strong></th>
					<td>
					<select  name="cardtypeStr" id="cardtypeStr"  value="${t_hotel.cardtypeStr}">
			    		<option <c:if test="${t_hotel.cardtype=='1'}">selected="selected"</c:if>>身份证</option>
			    		<option <c:if test="${t_hotel.cardtype=='2'}">selected="selected"</c:if>>护照</option>
	 		 			<option <c:if test="${t_hotel.cardtype=='3'}">selected="selected"</c:if>>驾驶证</option>
	  					<option <c:if test="${t_hotel.cardtype=='4'}">selected="selected"</c:if>>军官证</option>
	  					<option <c:if test="${t_hotel.cardtype=='5'}">selected="selected"</c:if>>营业执照</option>
		   			</select>
					
					</td>
					<th><strong>证件号码：</strong></th>
					<td><input type="text" name="cardid" value="${t_hotel.cardid}" /></td>
				</tr>

				<tr>
					<th><span style="color:red;">*</span><strong>省/市/区县：</strong></th>
					<td>
						<select id="seachprov" name="seachprov" onChange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');" >
						</select>
						<select id="seachcity" name="homecity" onChange="changeCity(this.value,'seachdistrict','seachdistrict');" >
						</select>
						<span id="seachdistrict_div"><select id="seachdistrict" name="seachdistrict" >
						</select></span>
						
						<input id="province" name="province" type="hidden"  /> 
						<input id="city" name="city" type="hidden"  /> 
						<input id="area" name="area" type="hidden"  />
					</td>
						
					<th><span style="color:red;">*</span><strong>具体地址:</strong></th>
					<td><input type="text" name="address" required="required"
						value="${t_hotel.address}" /></td>
				</tr>

				<tr>
					<th><strong>邮编:</strong></th>
					<td><input type="text" name="postcode"
						value="${t_hotel.postcode}" class="znumber"/></td>
					<th><strong>电子邮箱：</strong></th>
					<td><input type="text" name="email" value="${t_hotel.email}" /></td>
				</tr>

				<tr>
					<th><strong>头像:</strong></th>
					<td><input type="text" name="logo" value="${t_hotel.logo}" /></td>
					<th><strong>备注:</strong></th>
					<td><input type="text" name="comm" value="${t_hotel.comm}" /></td>
				</tr>
			</table>
			<br>

			<div class="real-body-foot">
				<button type="submit" class="btn btn-primary"
					onclick="showAreaID()">确定</button>
			</div>
		</form>
	</div>

</body>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" callback="window.parent.closeDialogm('edit_t_hotel_m');window.parent.refreshGrid('t_hotellist'); ">
		${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>