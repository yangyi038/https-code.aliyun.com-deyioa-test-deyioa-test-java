<!DOCTYPE html>
<%@page import="com.fantastic.ContextHolderUtils"%>
<%@page import="com.fs.comm.model.Sysuser"%>
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
					<e:yzbutton id="add_problem" name="添加问题" cssClass="btn btn-success"/>
					&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					<e:yzbutton id="import_t_problem" name="导入" cssClass="btn btn-success" />
					&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					<e:yzbutton id="downloadTemplate" name="导出" cssClass="btn btn-success"/>
					&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  
						<div class="row" style="margin-left: 0px; margin-top: 10px">
							店铺搜索: <select name="shopid" id="shopid">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${shopList}"
								var='item'>
								<option value='${item.id}'>${item.name}</option>
							</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							问题类型: <select name="industryid" id="industryid">
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'industry')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							问题分类: <select name="categoryid" id="categoryid">
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'category')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>>
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
				<tgrid:jqGrid url="list_problem.action" autowidth="true"
					dourl="delPicture.action" id="problemText" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}" 
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="30,50,100" 
					beforeSelectRow="beforeSelectRow">
					<tgrid:jqGridHead
						headvalue="'编号','问题分类','问题类型','问题描述/原因','Q(问题)','衍生/可识别问题','产品表头','答案组成','答案','答案id','关联产品',
							'推荐产品','建议/备注','变量标签（红色可使用）','操作'">
						<tgrid:jqcol name="id" index="id" width="10" hidden="true" align="left"/>
						<tgrid:jqcol name="qcate" index="qcate" width="40" />
						<tgrid:jqcol name='industry' width='50' index='industry'/>
						<tgrid:jqcol name='reason' width='70' index='reason'/>
						<tgrid:jqcol name="question" index="question" width="150" align="left"/>
						<tgrid:jqcol name='derive' width='100' index='derive' align="left"/>
						
						<tgrid:jqcol name="titlename" index="titlename" width="60" align="left"/>
						<tgrid:jqcol name='answerForm' width='80' index='derive' align="left"/>
						<tgrid:jqcol name="answer" index="answer" width="110" align="left"/>
						
						<tgrid:jqcol name="answerid" index="answerid" width="10" hidden="true" align="left"/>
						<tgrid:jqcol name="relationname" index="relationname" width="50" align="left"/>
						<tgrid:jqcol name='recommend' width='60' index='recommend' align="left"/>
						<tgrid:jqcol name="suggest" index="suggest" width="70" align="left"/>
						<tgrid:jqcol name="bugs" index="bugs" width="90" align="left"/>
						<tgrid:jqcol name="act" width='135'/>
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="insert" title="添加" power="109901"/>
	<e:yzactbutton onclick="update" title="修改" power="109903"/>
	<e:yzactbutton onclick="delQuestion" title="删除问题" power="109902"/>
	<e:yzactbutton onclick="delAnswer" title="删除答案" power="109904"/>
</e:yzact>

<script type="text/javascript">
$(document).ready(function(){
	 if(<%=user.getPrivilege()%>!=1){
		 $("#downloadTemplate").hide();
	 }
	 if(<%=user.getPrivilege()%>!=1 && <%=user.getPrivilege()%>!=33){
		 $("#import_t_problem").hide();
	 }
	 if(<%=user.getPrivilege()%>==35){
		 $("#add_problem").hide();
	 }
	 $('#name').focus();
});

//列表单选
function beforeSelectRow() {
	$("#problemText").jqGrid('resetSelection');
	return (true);
}
 
// 搜索按钮事件
function searchContent(){
	var grid = $("#problemText");
	var name = $("#name").val();
	var shopid = $("#shopid").val();
	var categoryid = $("#categoryid").val();
	var industryid = $("#industryid").val();
	sdata = {name:name,shopid:shopid,industryid:industryid,categoryid:categoryid};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}

// 商店下拉列表选中执行
$("#shopid").change(function(){
	var grid = $("#problemText");
	var shopid = $("#shopid").val();
	sdata = {shopid:shopid};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	})

// 分类搜索下拉列表选中执行
$("#industryid").change(function(){
	var grid = $("#problemText");
	var industryid = $("#industryid").val();
	sdata = {industryid:industryid};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	})

// 问题分类下拉列表选中执行
$("#categoryid").change(function(){
	var grid = $("#problemText");
	var categoryid = $("#categoryid").val();
	sdata = {categoryid:categoryid};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	})

//下载模板
$("#downloadTemplate").click(function(){
	var shopid = $("#shopid").val();
	var url = basepath+"/admin/problem/exportExcell.action?shopid="+shopid; 
    url = encodeURI(url);
    location.href = url; 
     
})

// 表头添加问题信息 
$("#add_problem").click(function(){
	var rowId =jQuery("#problemText").jqGrid("getGridParam", "selrow");
	var rowData = jQuery("#problemText").jqGrid('getRowData',rowId);
	jQuery().yzIframeDialog({id:"add_problem2_m",iframeurl:"<%=basepath%>/admin/problem/addproblemPre2.action",title:"添加问题"});
	$('#add_problem2_m').modal('show');
})

// 添加行业包信息 
$("#add_industryid").click(function(){
	jQuery().yzIframeDialog({id:"add_industryid_m",iframeurl:"<%=basepath%>/admin/problem/addindustryPre.action",title:"添加行业包"});
	$('#add_industryid_m').modal('show');
})

// 添加问题分类信息 
$("#add_qcateid").click(function(){
	jQuery().yzIframeDialog({id:"add_qcateid_m",iframeurl:"<%=basepath%>/admin/problem/addproblemPre.action",title:"添加问题"});
	$('#add_qcateid_m').modal('show');
})

// 添加
function insert(id){
	var rd =jQuery("#problemText").getRowData(id);
	jQuery().yzIframeDialog({id:"add_problem_m",iframeurl:"<%=basepath%>/admin/problem/addproblemPre.action?id="+rd['id'],title:"添加问题信息"});
	$('#add_problem_m').modal('show');
}

// 导入按钮操作
$("#import_t_problem").click(function(){
	jQuery().yzIframeDialog({id:"import_problem_s",iframeurl:"<%=basepath%>/admin/problem/showImport.action",title:"导入Q&A表"});
	$('#import_problem_s').modal('show');
})

//修改
function update(id){
	var rd =jQuery("#problemText").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_problem_m",iframeurl:"<%=basepath%>/admin/problem/editproblemPre.action?answerid="+rd['answerid']+"&id="+rd['id'],title:"修改问题信息"});
	$('#edit_problem_m').modal('show');
}

// 删除问题
function delQuestion(id){
	var rowData =jQuery("#problemText").getRowData(id);
	modalDialogconfim("delete_problem_m","确定删除问题？",[{name:"是",fn:function(){
		$.post('<%=basepath%>/admin/problem/del_problem.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
			if(data.status==0){
				modalDialogAlert("删除成功");
				refreshGrid('problemText');
				closeDialog("delete_problem_m");
			}else{
				modalDialogAlert("删除失败");
				closeDialog("delete_problem_m");
			}
		});
	}},{name:"否",fn:function(){
   		closeDialog("delete_problem_m");
	}}]);
}

//删除答案
function delAnswer(id){
	var rowData =jQuery("#problemText").getRowData(id);
	modalDialogconfim("delete_answers_m","确定删除答案？",[{name:"是",fn:function(){
	$.post('<%=basepath%>/admin/problem/del_answer.action',{answerid:rowData['answerid'],name:rowData['name'],isclose:2},function(data){
		if(data.status==0){
			modalDialogAlert("删除成功");
			refreshGrid('problemText');
			closeDialog("delete_answers_m");
		}else{
			modalDialogAlert("删除失败");
			closeDialog("delete_answers_m");
			}
		});
	}},{name:"否",fn:function(){
   		closeDialog("delete_answers_m");
	}}]);
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>