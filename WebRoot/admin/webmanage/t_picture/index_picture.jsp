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
						<e:yzbutton id="add_t_picture" name="添加"
							cssClass="btn btn-success" power="63901" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 审核状态:
						<button class="btn btn-info btn-xs" onclick="unlimited()"
							id="button1" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="reviewed()"
							id="button2" value="已审核" style="width: 60px; height: 30px;">已审核</button>
						<button class="btn btn-info btn-xs" onclick="unaudited()"
							id="button3" value="未审核" style="width: 60px; height: 30px;">未审核</button>

						<div style="float: right; margin-right: 200px;">
							<input name="order_sn" id="search_content"
								style="height: 32px; width: 250px"
								placeholder="输入图片名称/图片原名称 模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_picture.action" autowidth="true"
					dourl="delPicture.action" id="picture" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}"
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'id','运营商','二级运营商','经销商','图片名称','图片原名称','图片组','状态','上传时间','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name='companyname' width='50' index='companyname' />
						<tgrid:jqcol name='operatorname' width='50' index='operatorname' />
						<tgrid:jqcol name='dealername' width='50' index='dealername' />

						<tgrid:jqcol name="picture_name" index="picture_name" width="50" />
						<tgrid:jqcol name="old_picture_name" index="old_picture_name"
							width="40" />
						<tgrid:jqcol name="picture_group" index="picture_group" width="50" />
						<tgrid:jqcol name="status" index="status" width="40" />
						<tgrid:jqcol name="upload_time" index="upload_time" width="50" />

						<tgrid:jqcol name="act" width="50" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="query" title="查看" />
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

</e:yzact>


<script type="text/javascript"> 

//控制表头字段显示
$(document).ready(function(){
	var len=$("#picture").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#picture").setGridParam().hideCol("companyname");
		 $("#picture").setGridParam().hideCol("operatorname");
		 $("#picture").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#picture").setGridParam().hideCol("companyname");
		 $("#picture").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#picture").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#picture").setGridWidth(len).trigger("reloadGrid");
});

//模糊查询
function search(){
	var grid = $("#picture");
	var pictureName = $("#search_content").val();
	sdata = {pictureName:pictureName};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

//不限
function unlimited(){
	var grid = $("#picture");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

//已审核
function reviewed(){
	var grid = $("#picture");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

//未审核
function unaudited(){
	var grid = $("#picture");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

//查看
function query(id){
	var rd =jQuery("#picture").getRowData(id);
	jQuery().yzIframeDialog({id:"query_t_picture_s",iframeurl:"<%=basepath%>/admin/t_picture/query_picture.action?id="+rd['id'],title:"图片详情"});
	$('#query_t_picture_s').modal('show');
}

//添加
$("#add_t_picture").click(function(){
	jQuery().yzIframeDialog({id:"add_t_picture_s",iframeurl:"<%=basepath%>/admin/t_picture/addPicturePre.action",title:"添加图片"});
	$('#add_t_picture_s').modal('show');
})

//修改
function update(id){
	var rd =jQuery("#picture").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_channel_m",iframeurl:"<%=basepath%>/admin/t_channel/showEditChannelWindow.action?id="+rd['id'],title:"修改字幕"});
	$('#edit_channel_m').modal('show');
}

//删除
function del(id){
	var rowData =jQuery("#picture").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_picture/delPicturet.action',{id:rowData['id'],pictureName:rowData['picture_name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('picture');
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

//同意审批
function agree(id){
	var rowData =jQuery("#picture").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定通过审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_picture/agreePicturet.action',{id:rowData['id'],pictureName:rowData['picture_name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("审核通过");
					refreshGrid('picture');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("审核失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

//取消审批
function unAgree(id){
	var rowData =jQuery("#picture").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取消审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_picture/unAgreePicturet.action',
												{
													id : rowData['id'],
													pictureName : rowData['picture_name'],
													isclose : 2
												},
												function(data) {
													if (data.status == 0) {
														modalDialogAlert("取消成功");
														refreshGrid('picture');
														closeDialog("confim_sysuser_logoff");
													} else {
														modalDialogAlert("取消失败");
														closeDialog("confim_sysuser_logoff");
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
