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
						<e:yzbutton id="add_t_application" name="添加" cssClass="btn btn-success" power="84901"/>
						&nbsp;&nbsp;&nbsp;&nbsp;	
						&nbsp;&nbsp;&nbsp;&nbsp;
						上线状态:
						<button class="btn btn-info btn-xs" onclick="searchLine()"
							id="button1" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="searchUnline()"
							id="button2" value="未上线" style="width: 60px; height: 30px;">未上线</button>
						<button class="btn btn-info btn-xs" onclick="searchOnlined()"
							id="button3" value="已上线" style="width: 60px; height: 30px;">已上线</button>
						<button class="btn btn-info btn-xs" onclick="searchUnlined()"
							id="button4" value="已下线" style="width: 60px; height: 30px;">已下线</button>
						<button class="btn btn-info btn-xs" onclick="undercarriage()"
							id="button5" value="已下架" style="width: 60px; height: 30px;">已下架</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div style="float: right; margin-right: 200px;">
							<input name="order_sn" id="name"
								style="height: 32px; width: 260px" placeholder="根据名称模糊查询" />
							<button class="btn btn-info btn-xs" onclick="searchContent()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_appManager.action" autowidth="true"
					dourl="delApplication.action" id="appManager" mtype="POST"
					search="true" export="false" pageid="orgippage"
					prmNames="{id:'id'}" sortname="id" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'id','运营商','二级运营商','经销商','名称','参数类型','开发者','评分','应用参数','简介','上线状态','上线时间','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" width="50" />
						<tgrid:jqcol name='operatorname' width='50' index='operatorname'  />
						<tgrid:jqcol name='dealername' width='50' index='dealername'  />
						
						<tgrid:jqcol name="name" index="name" width="40" />
						<tgrid:jqcol name="parameter_type" index="parameter_type"
							width="50" />
						<tgrid:jqcol name="developer_name" index="developer_name"
							width="30" />
						<tgrid:jqcol name="grader" index="grader" width="20" />
						<tgrid:jqcol name="apply_parameter" index="apply_parameter"
							width="30" />
						<tgrid:jqcol name="synopsis" index="synopsis" width="50" />
						<tgrid:jqcol name="status" index="status" width="30" />
						<tgrid:jqcol name="online_time" index="online_time" width="40" />
						<tgrid:jqcol name="act" width="70" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="appVersion" title="应用版本" />
	<e:yzactbutton onclick="query" title="查看" power="84904"/>
	<e:yzactbutton onclick="update" title="修改" power="84903"/>
	<e:yzactbutton onclick="del" title="删除" />

	<e:yzactbutton onclick="online" title="上线"
		event="function (rowdata){
if(rowdata['status']=='未上线'||rowdata['status']=='已下线'){
		return true;
	}else{
		return false;
	}
}" />
	<e:yzactbutton onclick="unline" title="下线"
		event="function (rowdata){
if(rowdata['status']=='已上线'){
		return true;
	}else{
		return false;
	}
}" />
	<e:yzactbutton onclick="unlined" title="下架"
		event="function (rowdata){
if(rowdata['status']=='已下架'){
		return false;
	}else{
		return true;
	}
}" />
</e:yzact>

<script type="text/javascript"> 

//控制表头字段显示
$(document).ready(function(){
	var len=$("#appManager").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#appManager").setGridParam().hideCol("companyname");
		 $("#appManager").setGridParam().hideCol("operatorname");
		 $("#appManager").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#appManager").setGridParam().hideCol("companyname");
		 $("#appManager").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#appManager").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#appManager").setGridWidth(len).trigger("reloadGrid");
});





$("#add_t_application").click(function(){
	jQuery().yzIframeDialog({id:"add_t_app_m",iframeurl:"<%=basepath%>/admin/t_application/showApplication.action",title:"添加应用列表"});
	$('#add_t_app_m').modal('show');
})

/* 查询 */
function query(id){
	var rd =jQuery("#appManager").getRowData(id);
	jQuery().yzIframeDialog({id:"query_appManager_m",iframeurl:"<%=basepath%>/admin/t_application/queryAppInfo.action?id="+rd['id'],title:"应用详情"});
	$('#query_appManager_m').modal('show');
}

function update(id){
	var rd =jQuery("#appManager").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_appManager_m",iframeurl:"<%=basepath%>/admin/t_application/showEditAppWindow.action?id="+rd['id'],title:"修改应用列表"});
	$('#edit_appManager_m').modal('show');
}

function del(id){
	var rowData =jQuery("#appManager").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_application/del_appManager.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('appManager');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}


/*浏览应用版本 */
function appVersion(id){
	var rd =jQuery("#appManager").getRowData(id);
	var id = rd['id'];
	var name = rd['name'];
	window.location.href="<%=basepath%>/admin/t_application/appVersionPre.action?id="+id+"&name="+name;
}


function online(id){
	var rowData =jQuery("#appManager").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定上线？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_application/onlineAppmanager.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("上线成功");
					refreshGrid('appManager');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("上线失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function unline(id){
	var rowData =jQuery("#appManager").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定下线？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_application/unlineAppmanager.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("下线成功");
					refreshGrid('appManager');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("下线失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function unlined(id){
	var rowData =jQuery("#appManager").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定下架？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_application/undercarriageAppManager.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("下架成功");
					refreshGrid('appManager');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("下架失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

jQuery("#add_t_appEditions").click(function(){
	window.location.href="<%=basepath%> /admin/sysmanage/t_applicationManger/app_editions.jsp"
});
		

//搜索
function searchContent(){
	var grid = $("#appManager");
	var name = $("#name").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}
//不限
function searchLine(){
	var grid = $("#appManager");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 
//未上线
function searchUnline(){
	var grid = $("#appManager");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 
//已上线
function searchOnlined(){
	var grid = $("#appManager");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 
//已下线
function searchUnlined(){
	var grid = $("#appManager");
	var status = $("#button4").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 
//已下架
function undercarriage(){
	var grid = $("#appManager");
	var status = $("#button5").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 
</script>
<%@include file="../../../common/admin_footer.jsp"%>
