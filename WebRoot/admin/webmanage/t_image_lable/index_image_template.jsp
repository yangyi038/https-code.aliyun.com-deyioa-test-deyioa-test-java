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
						<e:yzbutton id="add_t_imageLable" name="添加" cssClass="btn btn-success" power="69901" />
						&nbsp;&nbsp;&nbsp;&nbsp; 
						&nbsp;&nbsp;&nbsp;&nbsp; 
						审核状态:
						<button class="btn btn-info btn-xs" onclick="search1()"
							id="button1" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="search2()"
							id="button2" value="已审核" style="width: 60px; height: 30px;">已审核</button>
						<button class="btn btn-info btn-xs" onclick="search3()"
							id="button3" value="未审核" style="width: 60px; height: 30px;">未审核</button>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div style="float: right; margin-right: 200px;">
							<input name="order_sn" id="search_content"
								style="height: 32px; width: 260px" placeholder="根据模板名称模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_imageLable.action" autowidth="true"
					dourl="delPicture.action" id="imageLable" mtype="POST"
					search="true" export="false" pageid="orgippage"
					prmNames="{id:'id'}" sortname="tswaccount_id" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead headvalue="'id','运营商','二级运营商','经销商','模板名','模板描述','状态','创建时间','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" width="50" />
						<tgrid:jqcol name='operatorname' width='50' index='operatorname'  />
						<tgrid:jqcol name='dealername' width='50' index='dealername'  />
						
						<tgrid:jqcol name="name" index="name" width="40" />
						<tgrid:jqcol name="lable_describe" index="lable_describe"
							width="50" />
						<tgrid:jqcol name="status" index="status" width="40" />
						<tgrid:jqcol name="create_time" index="create_ime" width="50" />

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
	<%-- <e:yzactbutton onclick="attribute" title="模板属性"  /> --%>
</e:yzact>
<script type="text/javascript"> 

//控制表头字段显示
$(document).ready(function(){
	var len=$("#imageLable").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#imageLable").setGridParam().hideCol("companyname");
		 $("#imageLable").setGridParam().hideCol("operatorname");
		 $("#imageLable").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#imageLable").setGridParam().hideCol("companyname");
		 $("#imageLable").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#imageLable").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#imageLable").setGridWidth(len).trigger("reloadGrid");
});

function search(){
	var grid = $("#imageLable");
	var name = $("#search_content").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

function search1(){
	
	var grid = $("#imageLable");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function search2(){
	var grid = $("#imageLable");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function search3(){
	var grid = $("#imageLable");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 


$("#add_t_imageLable").click(function(){
	jQuery().yzIframeDialog({id:"add_t_imageLable_m",iframeurl:"<%=basepath%>/admin/t_imageText/showImageTextLable.action",title:"添加图文信息模板"});
	$('#add_t_imageLable_m').modal('show');
})

$("#show_attribute").click(function(){
	alert("sssssssssssssssssss");
	jQuery().yzIframeDialog({id:"add_t_imageLable_m",iframeurl:"<%=basepath%>/admin/t_imageText/showAttribute.action",title:"模板属性管理"});
	$('#add_t_imageLable_m').modal('show');
})

function attribute(id){
	var rd =jQuery("#imageLable").getRowData(id);
	jQuery().yzIframeDialog({id:"add_t_imageLable_m",iframeurl:"<%=basepath%>/admin/t_imageText/showAttribute.action",title:"模板属性管理"});
	$('#add_t_imageLable_m').modal('show');
}

function update(id){
	var rd =jQuery("#imageLable").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_sysuser_m",iframeurl:"<%=basepath%>/admin/t_imageText/showEditWindow.action?id="+rd['id'],title:"修改图文信息模板"});
	$('#edit_sysuser_m').modal('show');
}

function query(id){
	var rd =jQuery("#imageLable").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_sysuser_m",iframeurl:"<%=basepath%>/admin/t_imageText/queryInageLableInfo.action?id="+rd['id'],title:"图文信息模板详情"});
	$('#edit_sysuser_m').modal('show');
}

function del(id){
	var rowData =jQuery("#imageLable").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/delImageLable.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('imageLable');
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
	var rowData =jQuery("#imageLable").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定通过审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/agreeImageLable.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("审核通过");
					refreshGrid('imageLable');
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
	var rowData =jQuery("#imageLable").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取消审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/unAgreeImageLable.action',
												{
													id : rowData['id'],
													name : rowData['name'],
													isclose : 2
												},
												function(data) {
													if (data.status == 0) {
														modalDialogAlert("取消成功");
														refreshGrid('imageLable');
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
