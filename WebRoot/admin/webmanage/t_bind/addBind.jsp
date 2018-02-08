<!DOCTYPE html>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link type="text/css" rel="stylesheet" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basepath%>/js/jquery.formatDateTime.js"></script>

	<%  
        //获取酒店
        String hotelsid=request.getParameter("hotelsid");  
		String hotelname=request.getParameter("hotelname"); 
		if(hotelname==null){
			hotelname="";
		}
		 //获取机顶盒设备
        String stbsid=request.getParameter("stbsid");  
		String stbnum=request.getParameter("stbnum"); 
		if(stbnum==null){
			stbnum="";
		}
     %>
     
     
<div class="modal-real-body">
	<form action="<%=basepath%>/admin/t_bind/addBind.action" enctype="multipart/form-data" method="post">

		<table style="border-collapse: separate; border-spacing: 0px 10px;">
			<tr>
				<th><span style="color: red;">*</span><strong>酒店名称:</strong></th>
				<td>
					<input type="hidden" id="hotelsid" name="hotelsid" value="<%=hotelsid%>" />
					<input type="text" id="hotelname" name="hotelname" value="<%=hotelname%>" onclick="hotellist()" placeholder="请选择酒店" required="required"/>
				</td>
				
				<th><span style="color: red;">*</span><strong>机顶盒账号：</strong></th>
				<td>
					<input type="hidden" id="stbsid" name="stbsid" value="<%=stbsid%>" />
					<input type="text" id="stbnum" name="stbnum" value="<%=stbnum%>" onclick="stblist()" placeholder="请选择机顶盒设备" required="required"/>
				</td>
			</tr>

			<tr>
				<th><strong>wifi热点：</strong></th>
				<td><input type="text" name="wifi" value="${t_bind.wifi}" /></td>
				<th><strong>wifi密码：</strong></th>
				<td><input type="text" name="wifipwd" value="${t_bind.wifipwd}" /></td>
			</tr>

			<tr>
				<th><strong>客户名：</strong></th>
				<td><input type="text" name="customer" value="${t_bind.customer}" /></td>
				<th><strong>房间号：</strong></th>
				<td><input type="text" name="roomnum" value="${t_bind.roomnum}" /></td>
			</tr>

			<tr>
				<th class="top"><strong>欢迎词：</strong></th>
				<td>
					<textarea name="welcome" id="welcome" cols="20" rows="5"
							maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
					</textarea>
				</td>
			</tr>
			<tr>
				<th class="top"><strong>备注：</strong></th>
				<td>
					<textarea name="comm" id="comm" cols="20" rows="5"
							maxlength="300" style="margin: 0px; width: 200px; height: 60px;">  
					</textarea>
				</td>
			</tr>
		</table>
		<script>
			$('#welcome').val("${t_bind.welcome}");
			$('#comm').val("${t_bind.comm}");
		</script>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary"
				onclick="return checkdep();">确定</button>
		</div>
	</form>

</div>

<script>
	function hotellist(){
		jQuery().yzIframeDialog({id:"search_hotel_m",iframeurl:"<%=basepath%>/admin/t_bind/searchT_hotel.action",height:"350px",
			title : "关联用户"
		});
		$('#search_hotel_m').modal('show');
	}
	
	function stblist(){
		jQuery().yzIframeDialog({id:"search_stb_m",iframeurl:"<%=basepath%>/admin/t_bind/searchT_stb.action",height:"350px",
			title : "关联设备账号"
		});
		$('#search_stb_m').modal('show');
	}

</script>

<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('add_t_bind_m');window.parent.refreshGrid('t_bindlist');">
		${meg}
	</e:msgdialog>
</c:if>

<%@include file="../../../common/admin_footer.jsp"%>
