<!DOCTYPE html>
<%@page import="com.fantastic.ContextHolderUtils"%>
<%@page import="com.fs.comm.model.Sysuser"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<e:yzbutton id="add_dealer" name="添加"
								cssClass="btn btn-success" power="107901"/>
							<div style="float: right; margin-right: 200px;">
								<input name="content" id="search_content"
									style="height: 32px; width: 250px"
									placeholder="可输入经销商名称进行查询" />
								<button class="btn btn-info btn-xs" onclick="search()"
									style="width: 60px; height: 32px;">搜索</button>
							</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_com_dealer.action" autowidth="true"
					dourl="delDealer.action" id="t_com_dealer" mtype="POST"
					search="false" export="true" pageid="t_com_dealerpage"
					prmNames="{id:'sid'}" sortname="sid" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'SID' , '所属运营商','所属二级运营商','经销商' ,'昵称','联系方式', '创建时间','操作'">
						<tgrid:jqcol name='sid' width='50' index='sid' hidden='true' />
						<tgrid:jqcol name='companyname' width='150' index='companyname' hidden='false' />
						<tgrid:jqcol name='operatorname' width='150' index='operatorname' hidden='false' />
						<tgrid:jqcol name='name' width='150' index='name' hidden='false' />
						<tgrid:jqcol name='nick' width='150' index='nick' hidden='false' />
						<tgrid:jqcol name='phone' width='150' index='phone' hidden='false' />
						<tgrid:jqcol name='createtimeStr' width='150' index='createtimeStr' hidden='false' />
						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="xg" title="修改" power="107903" />
	<e:yzactbutton onclick="del" title="删除" power="107902"/>
</e:yzact>
<script type="text/javascript">

//加载列表
$(document).ready(function(){
	var len=$("#t_com_dealer").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#t_com_dealer").setGridParam().hideCol("companyname");
		 $("#t_com_dealer").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#t_com_dealer").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#t_com_dealer").setGridWidth(len).trigger("reloadGrid");
});

//添加
$("#add_dealer").click(function(){
	jQuery().yzIframeDialog({id:"add_t_com_dealer_m",iframeurl:"<%=basepath%>/admin/t_com_dealer/preAddDealer.action",
											title : "添加经销商"
										});
						$('#add_t_com_dealer_m').modal('show');
					})
					

//修改
function xg(sid){
var rd =jQuery("#t_com_dealer").getRowData(sid);
jQuery().yzIframeDialog({id:"edit_t_com_dealer_m",iframeurl:"<%=basepath%>/admin/t_com_dealer/PreEditDealer.action?sid="+ rd['sid'],title : "修改信息"});
	$('#edit_t_com_dealer_m').modal('show');
}


	//删除
	function del(sid) {
		var rowData = jQuery("#t_com_dealer").getRowData(sid);
		if (sid != null)
			jQuery("#t_com_dealer").jqGrid('delGridRow', rowData['sid'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认删除？",
				afterSubmit : function(response, postdata) {
					var json = response.responseText;
					if (json != "")
						modalDialogAlert(json);
					return [ true ];
				}
			});
		else
			modalDialogAlert("请先选中一行");
	}

	//搜索订单
	 function search() {
		var grid = $("#t_com_dealer");
		var content = $('#search_content').val();

		sdata = {
			content : content
		}; 
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	} 
</script>
<%@include file="../../../common/admin_footer.jsp"%>
