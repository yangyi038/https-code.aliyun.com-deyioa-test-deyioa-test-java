<!DOCTYPE html>
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

<div class="frame-container">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
					 <e:yzbutton id="add_company" name="添加" cssClass="btn btn-success" power="96901"/>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listCompany.action" autowidth="true"
					dourl="delCompany.action" id="t_company" mtype="POST" search="true"
					export="false" pageid="t_companypage" prmNames="{id:'sid'}"
					sortname="sid" del="false" ondblClickRow="preview">
					<tgrid:jqGridHead
						headvalue="'主键id','运营商名称','负责人','手机号','是否开通','支付时间','有效期截止时间','创建时间','操作'">
						<tgrid:jqcol name="sid" index="sid" hidden="true" />
						<tgrid:jqcol name="companyname" index="companyname" />
						<tgrid:jqcol name="boss" index="boss" />
						<tgrid:jqcol name="phone" index="phone" />
						<tgrid:jqcol name="isopen" index="isopen" />
						<tgrid:jqcol name="paytime" index="paytime" />
						<tgrid:jqcol name="validtime" index="validtime" />
						<tgrid:jqcol name="createtime" index="createtime" />
						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<%-- <e:yzactbutton onclick="view" title="查看" /> --%>
	<e:yzactbutton onclick="xg" title="修改"  power="96903"/>
	<e:yzactbutton onclick="del" title="删除" power="96902"/>
</e:yzact>
<script type="text/javascript">

//加载列表
function loadComplete(x){
	 jQuery("#t_company").jqGrid("setGridHeight",26*$("#t_company").jqGrid('getGridParam','rowNum')); 
}

//查看
<%-- function view(sid){
	var rd =jQuery("#t_company").getRowData(sid);
	jQuery().yzIframeDialog({id:"edit_t_company_m",iframeurl:"<%=basepath%>/admin/t_company/getCompanyInfo.action?sid="+rd['sid'],title:"查看详情"});
	$('#edit_t_company_m').modal('show');
} --%>

//修改
function xg(sid){
var rd =jQuery("#t_company").getRowData(sid);
jQuery().yzIframeDialog({id:"edit_t_company_m",iframeurl:"<%=basepath%>/admin/t_company/preEditCompany.action?sid="+ rd['sid'],title : "修改运营商"});
	$('#edit_t_company_m').modal('show');
}

//添加运营商
$("#add_company").click(function(){
	jQuery().yzIframeDialog({id:"add_company_m",iframeurl:"<%=basepath%>/admin/t_company/preAddCompany.action",title:"新增运营商"});
	$('#add_company_m').modal('show');
})

	//删除
	function del(sid) {
		var rowData = jQuery("#t_company").getRowData(sid);
		if (sid != null)
			jQuery("#t_company").jqGrid('delGridRow', rowData['sid'], {
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
	/* function search() {
		var grid = $("#t_bindlist");
		var content = $('#search_content').val();

		sdata = {
			content : content
		}; 
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	} */
	
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
