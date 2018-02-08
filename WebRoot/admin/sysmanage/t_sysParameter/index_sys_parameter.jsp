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
						<e:yzbutton id="add_t_sys" name="添加" cssClass="btn btn-success" power="98"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						参数类型:
						<button class="btn btn-info btn-xs" onclick="search1()"
							id="button1" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="search2()"
							id="button2" value="数字" style="width: 60px; height: 30px;">数字</button>
						<button class="btn btn-info btn-xs" onclick="search3()"
							id="button3" value="字符" style="width: 60px; height: 30px;">字符</button>

						<div style="float: right; margin-right: 200px;">
							<input name="order_sn" id="search_content"
								style="height: 32px; width: 250px"
								placeholder="可根据文本名称或内容进行模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listSysParameter.action" autowidth="true"
					dourl="delPicture.action" id="sysParameter" mtype="POST"
					search="true" export="false" pageid="orgippage"
					prmNames="{id:'id'}" sortname="tswaccount_id" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead headvalue="'id','运营商','参数名称','参数值','参数类型','描述','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" width="50" />
						<tgrid:jqcol name="name" index="name" width="50" />
						<tgrid:jqcol name="parameter_value" index="parameter_value"
							width="40" />
						<tgrid:jqcol name="parameter_type" index="parameter_type"
							width="40" />
						<tgrid:jqcol name="sys_describe" index="sys_describe" width="50" />

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
	var grid = $("#sysParameter");
	var name = $("#search_content").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

function search1(){
	var grid = $("#sysParameter");
	var parameterType = $("#button1").val();
	sdata = {parameterType:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}
function search2(){
	var grid = $("#sysParameter");
	var parameterType = $("#button2").val();
	sdata = {parameterType:parameterType};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

function search3(){
	var grid = $("#sysParameter");
	var parameterType = $("#button3").val();
	sdata = {parameterType:parameterType};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

$("#add_t_sys").click(function(){
	jQuery().yzIframeDialog({id:"add_t_sys_s",iframeurl:"<%=basepath%>/admin/t_sysParameter/show_addSysParameter.action",title:"添加系统配置"});
	$('#add_t_sys_s').modal('show');
})

function update(id){
	var rd =jQuery("#sysParameter").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_sys_m",iframeurl:"<%=basepath%>/admin/t_sysParameter/showUpdateWindow.action?id="+rd['id'],title:"修改系统配置"});
	$('#edit_sys_m').modal('show');
}

function query(id){
	var rd =jQuery("#sysParameter").getRowData(id);
	jQuery().yzIframeDialog({id:"query_sys_m",iframeurl:"<%=basepath%>/admin/t_sysParameter/querySysParameterInfo.action?id="+rd['id'],title:"系统配置详情"});
	$('#query_sys_m').modal('show');
}

function del(id){
	var rowData =jQuery("#sysParameter").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_sysParameter/del_SysParameter', {
					id : rowData['id'],
					isclose : 2
				}, function(data) {
					if (data.status == 0) {
						modalDialogAlert("删除成功");
						refreshGrid('sysParameter');
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
