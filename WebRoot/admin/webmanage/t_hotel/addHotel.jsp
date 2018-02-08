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
	
</script>

<body>
	<div class="modal-real-body">
		<form action="<%=basepath%>/admin/t_hotel/addHotel.action"
			enctype="multipart/form-data" method="post">
			
			<div>
				<span style="color: red;"><strong>基本信息：</strong></span>
			</div>
			<table>
				<tr>
					<th><span style="color:red;">*</span><strong>用户名称:</strong></th>
					<td><input type="text" name="hotelname"
						 required="required" /></td>
					<th><strong>用户简称:</strong></th>
					<td><input type="text" name="hotelshort"
						value="${t_hotel.hotelshort}" /></td>
				</tr>
				<tr>
					<th><span style="color:red;">*</span><strong>用户组:</strong></th>
					<td><select name='hotelgroup' id='hotelgroup' required="required" >
							<option value='' selected>-请选择-</option>
							<c:forEach items="${hotelgrouplist}" var="v">
								<option value="${v.sid}"
									<c:if test="${v.sid==t_hotel.hotelgroup}">selected="selected"</c:if>>${v.groupname}</option>
							</c:forEach>
					</select></td>
					<th><strong>用户类型:</strong></th>
					<td><select name="hoteltype" id="hoteltype">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'hoteltype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<th><span style="color:red;">*</span><strong>负责人:</strong></th>
					<td><input type="text" name="linkman"
						value="${t_hotel.linkman}" required="required" /></td>
					<th><span style="color:red;">*</span><strong>联系电话：</strong></th>
					<td><input type="text" id="tel" name="tel" value="${t_hotel.tel}" required="required"/></td>
				</tr>

				<tr>
					<th><strong>证件类型:</strong></th>
					<td><select name="cardtype" id="cardtype">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'cardtype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
					</select></td>
					<th><strong>证件号码：</strong></th>
					<td><input type="text" name="cardid" value="${t_hotel.cardid}" /></td>
				</tr>

				<tr>
					<th><span style="color:red;">*</span><strong>省/市/区县：</strong></th>
					<td>
						<select id="seachprov" name="seachprov" onChange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');" ></select>
						<select id="seachcity" name="homecity" onChange="changeCity(this.value,'seachdistrict','seachdistrict');" ></select>
						<span id="seachdistrict_div"><select id="seachdistrict" name="seachdistrict" ></select></span>
						
						<input id="province" name="province" type="hidden"  /> 
						<input id="city" name="city" type="hidden"  /> 
						<input id="area" name="area" type="hidden"  />
					</td>
					<th><span style="color:red;">*</span><strong>具体地址:</strong></th>
					<td><input type="text" name="address"
						value="${t_hotel.address}" required="required"/></td>
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
					onclick="showAreaID();return checkdep();">确定</button><!-- return checkdep(); -->
			</div>
		</form>
	</div>


<script type="text/javascript">
	function checkdep(){   
	    var phone = document.getElementById('tel').value;  
	    if(!(/^1[34578]\d{9}$/.test(phone))){   
	        alert("手机号码格式有误，请重填");    
	        return false;   
	    }   
	}
</script>

	<c:if test="${meg!=null}">
		<e:msgdialog basepath="<%=basepath%>"
			callback="window.parent.closeDialogm('add_t_hotel_m');window.parent.refreshGrid('t_hotellist'); 
		">
		${meg}
	</e:msgdialog>
	</c:if>

</body>

<%@include file="../../../common/admin_footer.jsp"%>
