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
<style>
.ui-jqgrid tr.jqgrow td {
	white-space: normal !important;
	height:auto;
	vertical-align:text-top;
	padding-top:2px;
}
</style>
<%
	//获取当前登录用户
	Sysuser user = ContextHolderUtils.getLoginUser();
%>

<div class="frame-container" onload="">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
					<e:yzbutton id="add_shop" name="添加商铺" cssClass="btn btn-success"/>
						<div class="row" style="margin-left: 0px; margin-top: 10px">
							分级搜索: <select name="importantid" id="importantid">
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'important')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							负责组搜索: <select name="groupid" id="groupid">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${groupList}"
								var='item'>
								<option value='${item.id}'>${item.depname}</option>
							</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							<input name="content" id="name"
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
				<tgrid:jqGrid url="list_shop.action" autowidth="true"
					dourl="delPicture.action" id="shopText" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}" 
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="20,30,40">
					<tgrid:jqGridHead
						headvalue="'编号','行业分类','分级','店铺名称', '主旺旺','SKU','日咨询量','月销量','现有客服','上新频次','活动频次','跟进信息','负责组','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" align="left"/>
						<tgrid:jqcol name="categoryid" index="categoryid" width="60" />
						<tgrid:jqcol name='importance' width='30' index='importance'/>
						
						<tgrid:jqcol name="name" index="name" width="90" align="left"/>
						<tgrid:jqcol name='alww' width='90' index='alww' align="left"/>
						<tgrid:jqcol name="sku" index="sku" width="40" align="left"/>
						
						<tgrid:jqcol name="volume" index="volume" width="40" align="left"/>
						<tgrid:jqcol name='monthsale' width='40' index='monthsale' align="left"/>
						<tgrid:jqcol name="customers" index="customers" width="40" align="left"/>
						<tgrid:jqcol name="newspeed" index="newspeed" width="70" align="left"/>
						
						<tgrid:jqcol name="promotions" index="promotions" width="70" align="left"/>
						<tgrid:jqcol name='follows' width='250' index='follows' align="left"/>
						<tgrid:jqcol name="groupid" index="groupid" width="40" align="center"/>
						<tgrid:jqcol name="act" width='60'/>
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="update" title="修改" power="110903"/>
	<e:yzactbutton onclick="del" title="删除" power="110902"/>
</e:yzact>

<script type="text/javascript">

// 搜索按钮事件
function searchContent(){
	var grid = $("#shopText");
	var shopname = $("#name").val();
	var importance = $("#importantid").val();
	var groupid = $("#groupid").val();
	sdata = {shopname:shopname,importance:importance,groupid:groupid};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

// 分级下拉列表选中执行
$("#importantid").change(function(){ 
	var grid = $("#shopText");
	var importance = $("#importantid").val();
	sdata = {importance:importance};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	})

// 组名称下拉列表选中执行
$("#groupid").change(function(){
	var grid = $("#shopText");
	var groupid = $("#groupid").val();
	sdata = {groupid:groupid};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	})

// 添加商铺信息 
$("#add_shop").click(function(){
	jQuery().yzIframeDialog({id:"add_shop_m",iframeurl:"<%=basepath%>/admin/shop/addshopPre.action",title:"添加商铺"});
	$('#add_shop_m').modal('show');
})

//修改
function update(id){
	var rd =jQuery("#shopText").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_shop_m",iframeurl:"<%=basepath%>/admin/shop/editshopPre.action?id="+rd['id'],title:"修改商铺信息"});
	$('#edit_shop_m').modal('show');
}

// 删除
function del(id){
	var rowData =jQuery("#shopText").getRowData(id);
	 modalDialogconfim("delete_shop_m","确定删除？",[{name:"是",fn:function(){
	 	$.post('<%=basepath%>/admin/shop/del_shop.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('shopText');
					closeDialog("delete_shop_m");
				}else{
					modalDialogAlert("删除失败");
					closeDialog("delete_shop_m");
				}
			});
     }},{name:"否",fn:function(){
   		 closeDialog("delete_shop_m");
     }}]);
}

</script>
<%@include file="../../../common/admin_footer.jsp"%>