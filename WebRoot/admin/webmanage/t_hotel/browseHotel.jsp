<!DOCTYPE html>
<%@page import="com.fantastic.ContextHolderUtils"%>
<%@page import="com.fs.comm.model.Sysuser"%>
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

<%
	//获取当前登录用户
	Sysuser user = ContextHolderUtils.getLoginUser();
%>

<div class="frame-container">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
						<e:yzbutton id="add_t_hotel" name="添加" cssClass="btn btn-success" power="78901"/>
						&nbsp;&nbsp;&nbsp; 
						用户组: <select name='hotelgroup'
							id='search_hotelgroup'>
							<option value='' selected>-请选择-</option>
							<c:forEach items="${hotelgrouplist}" var="v">
								<option value="${v.sid}"
									<c:if test="${v.sid==t_hotel.hotelgroup}">selected="selected"</c:if>>${v.groupname}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; 
						用户类型:<select name="hoteltype"
							id="search_hoteltype">
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'hoteltype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
						<input name="content" id="search_content"
							style="height: 32px; width: 280px" placeholder="可输入负责人/联系电话模糊查询" />
						<button class="btn btn-info btn-xs" onclick="search()"
							style="margin-left: 10px">搜索</button>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_hotel.action" autowidth="true"
					dourl="delHotel.action" id="t_hotellist" mtype="POST"
					search="false" export="true" pageid="t_hotelpage"
					prmNames="{id:'sid'}" sortname="sid" del="false"
					ondblClickRow="preview" rowList="15,30,45" >
					<tgrid:jqGridHead
						headvalue="'SID' , '用户编号' , '用户名称' ,'用户组','用户类型','联系人', '手机号' , '创建时间' ,'终端数量','操作'">
						<tgrid:jqcol name='sid' width='50' index='sid' hidden='true' />
						
						<tgrid:jqcol name='hotelnum' width='100' index='hotelnum'
							hidden='false' align="center"/>
						<tgrid:jqcol name='hotelname' width='100' index='hotelname'
							hidden='false' />
						<tgrid:jqcol name='hotelgroup' width='100' index='hotelgroup'
							hidden='false' />
						<tgrid:jqcol name='hoteltype' width='100' index='hoteltype'
							hidden='false' type='hoteltype' />
						<tgrid:jqcol name='linkman' width='100' index='linkman'
							hidden='false' />
						<tgrid:jqcol name='tel' width='100' index='tel' hidden='false' />
						<tgrid:jqcol name='createtime' width='150' index='createtime'
							hidden='false' />
						<tgrid:jqcol name='stbcount' width='100' index='stbcount'
							hidden='false' />	
						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="view" title="查看" power="78904"/>
	<e:yzactbutton onclick="xg" title="修改"  power="78903"/>
	<e:yzactbutton onclick="del" title="删除" power="78902"/>
</e:yzact>
<script type="text/javascript">

//加载酒店列表
$(document).ready(function(){
	var len=$("#t_hotellist").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#t_hotellist").setGridParam().hideCol("companyname");
		 $("#t_hotellist").setGridParam().hideCol("operatorname");
		 $("#t_hotellist").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#t_hotellist").setGridParam().hideCol("companyname");
		 $("#t_hotellist").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#t_hotellist").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#t_hotellist").setGridWidth(len).trigger("reloadGrid");
});

//查看酒店
function view(sid){
	var rd =jQuery("#t_hotellist").getRowData(sid);
	jQuery().yzIframeDialog({id:"edit_t_hotel_m",iframeurl:"<%=basepath%>/admin/t_hotel/getHotelInfo.action?sid="+rd['sid'],title:"查看用户详情"});
	$('#edit_t_hotel_m').modal('show');
}

//修改
function xg(sid){
var rd =jQuery("#t_hotellist").getRowData(sid);
jQuery().yzIframeDialog({id:"edit_t_hotel_m",iframeurl:"<%=basepath%>/admin/t_hotel/PreEditHotel.action?sid="+ rd['sid'],title : "修改用户信息"});
	$('#edit_t_hotel_m').modal('show');
}

//添加酒店
$("#add_t_hotel").click(function(){
	jQuery().yzIframeDialog({id:"add_t_hotel_m",iframeurl:"<%=basepath%>/admin/t_hotel/preAddHotel.action",
			title : "新增用户"
		});
		$('#add_t_hotel_m').modal('show');
	})

	//删除
	function del(sid) {
		var rowData = jQuery("#t_hotellist").getRowData(sid);
		if (sid != null)
			jQuery("#t_hotellist").jqGrid('delGridRow', rowData['sid'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认删除所选用户？",
				afterSubmit : function(response, postdata) {
					var json = response.responseText;
					if (json != "")
						modalDialogAlert(json);
					return [ true ];
				}
			});
		else
			modalDialogAlert("请先选中一行");
	}

	//搜索酒店
	function search() {
		var grid = $("#t_hotellist");
		var content = $('#search_content').val();
		var hotelgroup = $('#search_hotelgroup').val();
		var hoteltype = $('#search_hoteltype').val();

		sdata = {
			content : content,
			hotelgroup : hotelgroup,
			hoteltype : hoteltype
		}; //注意大写 
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
