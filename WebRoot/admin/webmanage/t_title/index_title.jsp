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
						<e:yzbutton id="add_t_title" name="添加" cssClass="btn btn-success" power="64901"/>
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
				<tgrid:jqGrid url="listT_title.action" autowidth="true"
					dourl="delPicture.action" id="title" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}"
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="15,30,45">
					<tgrid:jqGridHead headvalue="'id','运营商','二级运营商','经销商','文件名称','文本内容','状态','创建时间','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						
						<tgrid:jqcol name="companyname" index="companyname" width="50" />
						<tgrid:jqcol name='operatorname'  index='operatorname'  width="50"/>
						<tgrid:jqcol name='dealername'  index='dealername'  width="50"/>
						
						<tgrid:jqcol name="name" index="name" width="50" />
						<tgrid:jqcol name="content" index="content" width="40" />
						<tgrid:jqcol name="status" index="status" width="40" />
						<tgrid:jqcol name="create_time" index="create_time" width="50" />

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
	var len=$("#title").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#title").setGridParam().hideCol("companyname");
		 $("#title").setGridParam().hideCol("operatorname");
		 $("#title").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#title").setGridParam().hideCol("companyname");
		 $("#title").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#title").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#title").setGridWidth(len).trigger("reloadGrid");
});


function search(){
	var grid = $("#title");
	var context = $("#search_content").val();
	sdata = {context:context};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}

$("#add_t_title").click(function(){
	jQuery().yzIframeDialog({id:"add_t_title_s",iframeurl:"<%=basepath%>/admin/t_title/showT_title.action",title:"添加字幕"});
	$('#add_t_title_s').modal('show');
})

function update(id){
	var rd =jQuery("#title").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_title_m",iframeurl:"<%=basepath%>/admin/t_title/showUpdateWindow.action?id="+rd['id'],title:"修改字幕"});
	$('#edit_title_m').modal('show');
}

function query(id){
	var rd =jQuery("#title").getRowData(id);
	jQuery().yzIframeDialog({id:"query_title_m",iframeurl:"<%=basepath%>/admin/t_title/queryTitleInfo.action?id="+rd['id'],title:"字幕详情"});
	$('#query_title_m').modal('show');
}

function del(id){
	var rowData =jQuery("#title").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_title/delTitle.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('title');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function agree(id){
	var rowData =jQuery("#title").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定通过审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_title/agreeTitle.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("审核通过");
					refreshGrid('title');
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
	var rowData =jQuery("#title").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取消审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_title/unAgreeTitle.action', {
					id : rowData['id'],
					name : rowData['name'],
					isclose : 2
				}, function(data) {
					if (data.status == 0) {
						modalDialogAlert("取消成功");
						refreshGrid('title');
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
