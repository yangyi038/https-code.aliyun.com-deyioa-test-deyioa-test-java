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
						审核状态:
						<button class="btn btn-info btn-xs" onclick="status()"
							id="button1" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="agreeStatus()"
							id="button2" value="已审核" style="width: 60px; height: 30px;">已审核</button>
						<button class="btn btn-info btn-xs" onclick="unAgreeStatus()"
							id="button3" value="未审核" style="width: 60px; height: 30px;">未审核</button>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						&nbsp;&nbsp;&nbsp;&nbsp; 
						上线状态:
						<button class="btn btn-info btn-xs" onclick="line()" id="button4"
							value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="unline()"
							id="button5" value="未上线" style="width: 60px; height: 30px;">未上线</button>
						<button class="btn btn-info btn-xs" onclick="onlined()"
							id="button6" value="已上线" style="width: 60px; height: 30px;">已上线</button>
						<button class="btn btn-info btn-xs" onclick="unlined()"
							id="button7" value="已下线" style="width: 60px; height: 30px;">已下线</button>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="row" style="margin-left: 0px; margin-top: 10px">
							<e:yzbutton id="add_t_channel" name="添加" cssClass="btn btn-success" power="86901"/>
							<div style="float: right; margin-right: 200px;">
								<input name="order_sn" id="context"
									style="height: 32px; width: 260px"
									placeholder="可根据频道名称或台标信息进行模糊查询" />
								<button class="btn btn-info btn-xs" onclick="search()"
									style="width: 60px; height: 32px;">搜索</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listChannel.action" autowidth="true"
					dourl="delPicture.action" id="channel" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}"
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'id','运营商','二级运营商','经销商','频道号','频道名称','台标','释放状态','状态','创建时间','版权方','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" width="50" />
						<tgrid:jqcol name='operatorname' width='50' index='operatorname'  />
						<tgrid:jqcol name='dealername' width='50' index='dealername'  />
						
						<tgrid:jqcol name="number" index="number" width="30" />
						<tgrid:jqcol name="name" index="name" width="40" />
						<tgrid:jqcol name="tv_logo" index="tv_logo" width="50" />
						<tgrid:jqcol name="status" index="status" width="20" />
						<tgrid:jqcol name="line_status" index="line_status" width="20" />
						<tgrid:jqcol name="create_date" index="create_date" width="50" />
						<tgrid:jqcol name="supplier" index="supplier" width="20" />
						<tgrid:jqcol name="act" width="80" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="phsical" title="物理频道" />
	<e:yzactbutton onclick="query" title="查看" />
	<e:yzactbutton onclick="update" title="修改" power="11"/>
	<e:yzactbutton onclick="del" title="删除" />
	<e:yzactbutton onclick="agree" title="审核"
		event="function (rowdata){
if(rowdata['status']=='已审核'){
		return false;
	}else{
		return true;
	}
}" />
	<e:yzactbutton onclick="unAgree" title="取消审核"
		event="function (rowdata){
if(rowdata['status']=='未审核'){
		return false;
	}else{
		return true;
	}
}" />
	<e:yzactbutton onclick="online" title="上线"
		event="function (rowdata){
if(rowdata['line_status']=='已上线'){
		return false;
	}else{
		return true;
	}
}" />
	<e:yzactbutton onclick="unLine" title="下线"
		event="function (rowdata){
if(rowdata['line_status']=='未上线'||rowdata['line_status']=='已下线'){
		return false;
	}else{
		return true;
	}
}" />

</e:yzact>


<script type="text/javascript"> 

//动态加载表头字段
$(document).ready(function(){
	var len=$("#channel").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#channel").setGridParam().hideCol("companyname");
		 $("#channel").setGridParam().hideCol("operatorname");
		 $("#channel").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#channel").setGridParam().hideCol("companyname");
		 $("#channel").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#channel").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#channel").setGridWidth(len).trigger("reloadGrid");
});

function search(){
	var grid = $("#channel");
	var name = $("#context").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

function line(){
	var grid = $("#channel");
	var line_status = $("#button4").val();
	sdata = {line_status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function unline(){
	var grid = $("#channel");
	var line_status = $("#button5").val();
	sdata = {line_status:line_status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function onlined(){
	var grid = $("#channel");
	var line_status = $("#button6").val();
	sdata = {line_status:line_status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function unlined(){
	var grid = $("#channel");
	var line_status = $("#button7").val();
	sdata = {line_status:line_status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function status(){
	var grid = $("#channel");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function agreeStatus(){
	var grid = $("#channel");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function unAgreeStatus(){
	var grid = $("#channel");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

$("#add_t_channel").click(function(){
	jQuery().yzIframeDialog({id:"add_t_channel_s",iframeurl:"<%=basepath%>/admin/t_channel/showChannel.action",title:"添加直播频道信息"});
	$('#add_t_channel_s').modal('show');
})

function update(id){
	var rd =jQuery("#channel").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_channel_m",iframeurl:"<%=basepath%>/admin/t_channel/showEditChannelWindow.action?id="+rd['id'],title:"修改直播频道信息"});
	$('#edit_channel_m').modal('show');
}

function phsical(id){
	var rd =jQuery("#channel").getRowData(id);
	var id = rd['id'];
	var number = rd['number'];
	window.location.href="<%=basepath%>/admin/t_physical_channel/physicalChannel_manager.action?id="+id+"&number="+number;
}

function query(id){
	var rd =jQuery("#channel").getRowData(id);
	jQuery().yzIframeDialog({id:"query_channel_m",iframeurl:"<%=basepath%>/admin/t_channel/queryChannelInfo.action?id="+rd['id'],title:"直播频道详情"});
	$('#query_channel_m').modal('show');
}

function del(id){
	var rowData =jQuery("#channel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_channel/del_channel.action',{id:rowData['id'],number:rowData['number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('channel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}



function online(id){
	var rowData =jQuery("#channel").getRowData(id);
	modalDialogconfim("confim_sysuser_logoff","确定上线？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_channel/onlineChannel.action',{id:rowData['id'],number:rowData['number'],status:rowData['status'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("上线成功");
					refreshGrid('channel');
					closeDialog("confim_sysuser_logoff");
				}else if(data.status==1){
					modalDialogAlert("未审核不能上线");
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

function unLine(id){
	var rowData =jQuery("#channel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定下线？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_channel/unlineChannel.action',{id:rowData['id'],number:rowData['number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("下线成功");
					refreshGrid('channel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("下线失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function agree(id){
	var rowData =jQuery("#channel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定通过审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_channel/agreeChannel.action',{id:rowData['id'],number:rowData['number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("审核通过");
					refreshGrid('channel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("审核失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function unAgree(id){
	var rowData =jQuery("#channel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取消审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_channel/unAgreeChannel.action', {
					id : rowData['id'],
					number : rowData['number'],
					isclose : 2
				}, function(data) {
					if (data.status == 0) {
						modalDialogAlert("取消成功");
						refreshGrid('channel');
						closeDialog("confim_sysuser_logoff");
					} else {
						modalDialogAlert("取消失败");
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
