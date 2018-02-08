<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css" />
<script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>

<div class="frame-container">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
						<e:yzbutton id="add_t_app_editions" name="添加应用版本" cssClass="btn btn-success" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="order_sn" id="name"
							style="height: 32px; width: 260px" placeholder="根据名称模糊查询" />
						<button class="btn btn-info btn-xs" onclick="search()"
							style="width: 60px; height: 32px;">搜索</button>
						
						<button class="btn btn-info btn-xs" onclick="setBack()"
							style="height: 32px; width: 120px; float: right; margin-right: 200px;">返回应用列表</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="yz-frame-body">
			<div class="table-box">   <!-- /hotelPro/admin/t_application/ -->
				<tgrid:jqGrid
					url="listAppVersion.action?applicationId=${id}"
					autowidth="true" dourl="delPicture.action" id="appEditions"
					mtype="POST" search="true" export="false" pageid="orgippage"
					prmNames="{id:'id'}" sortname="tswaccount_id" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'id','版本名称','所属应用','包名','应用版本','大小(字节)','下载次数','下载地址','最低系统 ','操作'">

						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="name" index="name" width="40" />
						<tgrid:jqcol name="application_name" index="application_name"
							width="40" />
						<tgrid:jqcol name="package_name" index="package_name" width="50" />
						<tgrid:jqcol name="version" index="version" width="20" />
						<tgrid:jqcol name="app_size" index="app_size" width="20" />
						<tgrid:jqcol name="download_times" index="download_times"
							width="20" />
						<tgrid:jqcol name="download_url" index="download_url" width="70" />
						<tgrid:jqcol name="lowest_system" index="lowest_system" width="50" />
						<tgrid:jqcol name="act" width="50" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
				<input id="applicationName" value="${name}"
					style="visibility: hidden;" />
			</div>
		</div>
	</div>
</div>
<%-- <e:yzact id="act">     --%>

<%-- </e:yzact> --%>
<e:yzact id="act">
	<e:yzactbutton onclick="query" title="查看" />
	<e:yzactbutton onclick="update" title="修改" />
	<e:yzactbutton onclick="del" title="删除" />


</e:yzact>
<script type="text/javascript"> 

/* 模糊查询 */
function search(){
	var grid = $("#appEditions");
	var name = $("#name").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

/* 添加应用版本 */
$("#add_t_app_editions").click(function(){
	var applicationId = ${id};
	var applicationName = document.getElementById("applicationName").value;
	jQuery().yzIframeDialog({id:"add_t_appEditions_m",iframeurl:"<%=basepath%>/admin/t_application/addAppVersionPre.action?applicationId="+applicationId+"&applicationName="+applicationName,title:"添加应用版本信息"});
	$('#add_t_appEditions_m').modal('show'); 
})

/* 返回应用列表 */
function setBack(){
	window.location.href="<%=basepath%>/admin/t_application/application_manager.action";
} 

/* 查看版本 */
function query(id){
	var rd =jQuery("#appEditions").getRowData(id);
	jQuery().yzIframeDialog({id:"query_appEditions_m",iframeurl:"<%=basepath%>/admin/t_application/getAppVersionInfo.action?id="+rd['id'],title:"应用版本详情"});
	$('#query_appEditions_m').modal('show');
}

function update(id){
	var rd =jQuery("#appEditions").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_appEditions_m",iframeurl:"<%=basepath%>/admin/t_application/showEditAppEditionsWindow.action?id="+rd['id'],title:"修改应用版本信息"});
	$('#edit_appEditions_m').modal('show');
}

function del(id){
	var rowData =jQuery("#appEditions").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_application/del_appEditions.action',
												{
													id : rowData['id'],
													name : rowData['name'],
													isclose : 2
												},
												function(data) {
													if (data.status == 0) {
														modalDialogAlert("删除成功");
														refreshGrid('appEditions');
														closeDialog("confim_sysuser_logoff");
													} else {
														modalDialogAlert("删除失败");
													}
												});
							}
						}, {
							name : "否",
							fn : function() {
								closeDialog("confim_sysuser_logoff");
							}
						} ]);
	}
</script>

<%@include file="../../../common/admin_footer.jsp"%>
