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
					<e:yzbutton id="add_imageText" name="添加" cssClass="btn btn-success"
								power="68901" />
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;	
						审核状态:
						<button class="btn btn-info btn-xs" onclick="search1()"
							id="button1" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="search2()"
							id="button2" value="已审核" style="width: 60px; height: 30px;">已审核</button>
						<button class="btn btn-info btn-xs" onclick="search3()"
							id="button3" value="未审核" style="width: 60px; height: 30px;">未审核</button>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 上线状态:
						<button class="btn btn-info btn-xs" onclick="searchLine()"
							id="button4" value="不限"
							style="width: 60px; height: 30px; background: green; color: red">不限</button>
						<button class="btn btn-info btn-xs" onclick="searchUnline()"
							id="button5" value="未上线" style="width: 60px; height: 30px;">未上线</button>
						<button class="btn btn-info btn-xs" onclick="searchOnlined()"
							id="button6" value="已上线" style="width: 60px; height: 30px;">已上线</button>
						<button class="btn btn-info btn-xs" onclick="searchUnlined()"
							id="button7" value="已下线" style="width: 60px; height: 30px;">已下线</button>
						
						<div class="row" style="margin-left: 0px; margin-top: 10px">
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							显示格式: <select name="showType" id="showType">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'showtype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							<input name="order_sn" id="name"
								style="height: 32px; width: 260px" placeholder="根据标题模糊查询" />
							<button class="btn btn-info btn-xs" onclick="searchContent()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_imageText.action" autowidth="true"
					dourl="delPicture.action" id="imageText" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}"
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'id','运营商','二级运营商','经销商', '标题','审核状态','上线状态','显示格式','内容','创建时间','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" width="50" />
						<tgrid:jqcol name='operatorname' width='50' index='operatorname' />
						<tgrid:jqcol name='dealername' width='50' index='dealername' />

						<tgrid:jqcol name="name" index="name" width="40" />
						<tgrid:jqcol name="status" index="status" width="50" />
						<tgrid:jqcol name="line_status" index="line_status" width="50" />
						<tgrid:jqcol name="show_type" index="show_type" width="40"
							type='showtype' />
						<tgrid:jqcol name="content" index="content" width="50" />
						<tgrid:jqcol name="create_time" index="create_time" width="40" />
						<tgrid:jqcol name="act" width="60" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<%-- <e:yzactbutton onclick="query" title="查看" /> --%>
	<e:yzactbutton onclick="update" title="修改" power="11" />
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
if(rowdata['line_status']=='已上线'||rowdata['line_status']=='已上线'){
		return false;
	}else{
		return true;
	}
}" />
	<e:yzactbutton onclick="unline" title="下线"
		event="function (rowdata){
if(rowdata['line_status']=='已下线'||rowdata['line_status']=='未上线'){
		return false;
	}else{
		return true;
	}
}" />

	<e:yzactbutton onclick="addVideo" title="视频"
		event="function (rowdata){
if(rowdata['show_type']==2){
		return true;
	}else{
		return false;
	}
}" />

	<e:yzactbutton onclick="thumbnailIndex" title="缩略图列表"
		event="function (rowdata){
if(rowdata['show_type']==2||rowdata['show_type']==1){
		return true;
	}else{
		return false;
	}
}" />
</e:yzact>

<script type="text/javascript"> 

//控制表头字段显示
$(document).ready(function(){
	var len=$("#imageText").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#imageText").setGridParam().hideCol("companyname");
		 $("#imageText").setGridParam().hideCol("operatorname");
		 $("#imageText").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#imageText").setGridParam().hideCol("companyname");
		 $("#imageText").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#imageText").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#imageText").setGridWidth(len).trigger("reloadGrid");
});

/* 添加图文信息 */
$("#add_imageText").click(function(){
	jQuery().yzIframeDialog({id:"add_t_image_m",iframeurl:"<%=basepath%>/admin/t_imageText/addImageTextPre.action",title:"添加图文信息"});
	$('#add_t_image_m').modal('show');
})

/* 搜索 */
function searchContent(){
	var grid = $("#imageText");
	var name = $("#name").val();
	var showType = $("#showType").val();
	sdata = {name:name,showType:showType};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

function search1(){
	var grid = $("#imageText");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function search2(){
	var grid = $("#imageText");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function search3(){
	var grid = $("#imageText");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function searchLine(){
	var grid = $("#imageText");
	var lineStatus = $("#button4").val();
	sdata = {lineStatus:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function searchUnline(){
	var grid = $("#imageText");
	var lineStatus = $("#button5").val();
	sdata = {lineStatus:lineStatus};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function searchOnlined(){
	var grid = $("#imageText");
	var lineStatus = $("#button6").val();
	sdata = {lineStatus:lineStatus};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

function searchUnlined(){
	var grid = $("#imageText");
	var lineStatus = $("#button7").val();
	sdata = {lineStatus:lineStatus};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

/*查看  */
<%-- function query(id){
	var rd =jQuery("#imageText").getRowData(id);
	jQuery().yzIframeDialog({id:"query_imageText_m",iframeurl:"<%=basepath%>/admin/t_imageText/queryImageInfo.action?id="+rd['id'],title:"图文信息详情"});
	$('#query_imageText_m').modal('show');
} --%>

function addVideo(id){
	var rd =jQuery("#imageText").getRowData(id);
    var id = rd['id'];
	window.location.href="<%=basepath%>/admin/t_imageText/show_pictureInfo.action?id="+rd['id'];
}


$("#show_attribute").click(function(){
	jQuery().yzIframeDialog({id:"add_t_imageLable_m",iframeurl:"<%=basepath%>/admin/t_imageText/showAttribute.action",title:"模板属性管理"});
	$('#add_t_imageLable_m').modal('show');
})

function attribute(id){
	var rd =jQuery("#imageLable").getRowData(id);
	jQuery().yzIframeDialog({id:"add_t_imageLable_m",iframeurl:"<%=basepath%>/admin/t_imageText/showAttribute.action",title:"模板属性管理"});
	$('#add_t_imageLable_m').modal('show');
}

//修改
function update(id){
	var rd =jQuery("#imageText").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_imageText_m",iframeurl:"<%=basepath%>/admin/t_imageText/editImageTextPre.action?id="+rd['id'],title:"修改图文信息"});
	$('#edit_imageText_m').modal('show');
}

function del(id){
	var rowData =jQuery("#imageText").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/del_imageText.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('imageText');
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

function agree(id){
	var rowData =jQuery("#imageText").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定通过审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/agreeImageText.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("审核通过");
					refreshGrid('imageText');
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

function unAgree(id){
	var rowData =jQuery("#imageText").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取消审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/unImageText.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("取消成功");
					refreshGrid('imageText');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("取消失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}


function online(id){
	var rowData =jQuery("#imageText").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定上线？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/onlineImageText.action',{id:rowData['id'],status:rowData['status'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("上线成功");
					refreshGrid('imageText');
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

function unline(id){
	var rowData =jQuery("#imageText").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取下线？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/unlineImageText.action',
												{
													id : rowData['id'],
													name : rowData['name'],
													isclose : 2
												},
												function(data) {
													if (data.status == 0) {
														modalDialogAlert("下线成功");
														refreshGrid('imageText');
														closeDialog("confim_sysuser_logoff");
													} else {
														modalDialogAlert("下线失败");
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
	
	
	
	/* ============ 缩略图 ==================================== */
	
/* 浏览缩略图列表 */
function thumbnailIndex(id){
	var rd =jQuery("#imageText").getRowData(id);
    var id = rd['id'];
	window.location.href="<%=basepath%>/admin/t_imageText/thumbnailIndex.action?id="+rd['id'];
}
	
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
