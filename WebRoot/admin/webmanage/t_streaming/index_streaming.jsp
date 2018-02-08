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
						<e:yzbutton id="add_t_sterming" name="添加" cssClass="btn btn-success" power="98"/>
						<div style="float: right; margin-right: 200px;">
							<input name="order_sn" id="context"
								style="height: 32px; width: 250px" placeholder="可输入设备号进行模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listStreaming.action" autowidth="true"
					dourl="delPicture.action" id="streaming" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}"
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'id','运营商','设备号','固件版本号','软件版本号','出厂日期','授权起始日期','授权结束日期','EdgeIP','最后登陆时间','最后登录IP','操作'">

						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" width="40" />
						<tgrid:jqcol name="programme_num" index="programme_num" width="30" />
						<tgrid:jqcol name="firmware" index="firmware" width="20" />
						<tgrid:jqcol name="software_version" index="software_version"
							width="20" />
						<tgrid:jqcol name="production_data" index="production_data"
							width="40" />
						<tgrid:jqcol name="start_time" index="start_time" width="40" />
						<tgrid:jqcol name="over_time" index="over_time" width="40" />
						<tgrid:jqcol name="edge_ip" index="edge_ip" width="40" />

						<tgrid:jqcol name="last_time" index="last_time" width="40" />
						<tgrid:jqcol name="lats_ip" index="lats_ip" width="40" />
						<tgrid:jqcol name="act" width="50" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="query" title="查看" />
	<e:yzactbutton onclick="update" title="修改" power="11"/>
	<e:yzactbutton onclick="del" title="删除" />
</e:yzact>

<script type="text/javascript"> 
function search(){
	var grid = $("#streaming");
	var programmeNum = $("#context").val();
	sdata = {programmeNum:programmeNum};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}

$("#add_t_sterming").click(function(){
	jQuery().yzIframeDialog({id:"add_sterming_s",iframeurl:"<%=basepath%>/admin/t_streaming/showAddString.action",title:"添加流媒体配置"});
	$('#add_sterming_s').modal('show');
})

function update(id){
	var rd =jQuery("#streaming").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_streaming_m",iframeurl:"<%=basepath%>/admin/t_streaming/showEditStreaming.action?id="+rd['id'],title:"修改流媒体配置"});
	$('#edit_streaming_m').modal('show');
}

function query(id){
	var rd =jQuery("#streaming").getRowData(id);
	jQuery().yzIframeDialog({id:"query_streaming_m",iframeurl:"<%=basepath%>/admin/t_streaming/queryStreamingInfo.action?id="+rd['id'],title:"流媒体配置详情"});
	$('#query_streaming_m').modal('show');
}

function del(id){
	var rowData =jQuery("#streaming").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_streaming/del_streaming.action',
												{
													id : rowData['id'],
													programmeNum : rowData['programme_num'],
													isclose : 2
												},
												function(data) {
													if (data.status == 0) {
														modalDialogAlert("删除成功");
														refreshGrid('streaming');
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
