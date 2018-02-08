<!DOCTYPE html>
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
							<e:yzbutton id="add_t_hotel_group" name="添加" cssClass="btn btn-success" power="79901" />
						
							<div style="float: right; margin-right: 200px;">
								<input name="content" id="search_content"
									style="height: 32px; width: 250px"
									placeholder="可输入组编号或组名称进行模糊查询" />
								<button class="btn btn-info btn-xs" onclick="search()"
									style="width: 60px; height: 32px;">搜索</button>
							</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_hotel_group.action" autowidth="true"
					dourl="delHotelGroup.action" id="t_hotel_grouplist" mtype="POST"
					search="false" export="true" pageid="t_hotel_grouppage"
					prmNames="{id:'sid'}" sortname="sid" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'主键id','用户组编号','用户组名称','用户组类型','创建时间','描述','操作'">
						<tgrid:jqcol name="sid" index="order_id" hidden="true" />
						<tgrid:jqcol name="groupnum" index="groupnum" />
						<tgrid:jqcol name="groupname" index="groupname" />
						<tgrid:jqcol name="grouptype" index="grouptype" type="hoteltype"/>
						<tgrid:jqcol name="createtime" index="createtime" />
						<tgrid:jqcol name="comm" index="comm" />
						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>
<e:yzact id="act">
	<e:yzactbutton onclick="view" title="查看" power="79904"/>
	<e:yzactbutton onclick="xg" title="修改" power="79903" />
	<e:yzactbutton onclick="del" title="删除" power="79902"/>
</e:yzact>

<script type="text/javascript"> 
/* 加载列表权限 */
$(document).ready(function(){
	var len=$("#t_hotel_grouplist").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#t_hotel_grouplist").setGridParam().hideCol("companyname");
		 $("#t_hotel_grouplist").setGridParam().hideCol("operatorname");
		 $("#t_hotel_grouplist").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#t_hotel_grouplist").setGridParam().hideCol("companyname");
		 $("#t_hotel_grouplist").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#t_hotel_grouplist").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#t_hotel_grouplist").setGridWidth(len).trigger("reloadGrid");
});

//查看订单
function view(sid){
	var rd =jQuery("#t_hotel_grouplist").getRowData(sid);
	jQuery().yzIframeDialog({id:"edit_t_hotel_group_m",iframeurl:"<%=basepath%>/admin/t_hotel_group/getHotelGroup.action?sid="+rd['sid'],title:"查看酒店组信息"});
	$('#edit_t_hotel_group_m').modal('show');
}

//修改订单
function xg(sid){
var rd =jQuery("#t_hotel_grouplist").getRowData(sid);
jQuery().yzIframeDialog({id:"edit_t_hotel_group_m",iframeurl:"<%=basepath%>/admin/t_hotel_group/editHotelGroup.action?sid="+ rd['sid'],title : "修改用户组信息"});
	$('#edit_t_hotel_group_m').modal('show');
}

//添加订单
$("#add_t_hotel_group").click(function(){
	// Note ,height:"1000" not need (lqf)
	jQuery().yzIframeDialog({id:"add_t_hotel_group_m",iframeurl:"<%=basepath%>/admin/t_hotel_group/preAddHotelGroup.action",
											title : "新增用户组"
										});
						$('#add_t_hotel_group_m').modal('show');
					})

	//删除订单
	function del(sid) {
		var rowData = jQuery("#t_hotel_grouplist").getRowData(sid);
		if (sid != null)
			jQuery("#t_hotel_grouplist").jqGrid('delGridRow', rowData['sid'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认删除所选组？",
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

	//搜索订单
	function search() {
		var grid = $("#t_hotel_grouplist");
		var content = $("#search_content").val();
		sdata = {
			content : content
		}; 
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
