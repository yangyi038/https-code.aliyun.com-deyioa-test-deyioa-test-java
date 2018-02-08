<!DOCTYPE html>
<%@page import="com.fantastic.ContextHolderUtils"%>
<%@page import="com.fs.comm.model.Sysuser"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
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
		<div class="row">
			<div class="col-xs-12">
				<div class="yz-frame-head">
					<div class="searchbox">
						<e:yzbutton id="add_role" name="添加" cssClass="btn btn-success" power="2901"/>
					</div>
				</div>
				<div class="yz-frame-body">
					<div class="table-box">
						<tgrid:jqGrid url="listRole.action" autowidth="true"
							dourl="delRole.action" id="rolelist" mtype="POST" search="true"
							pageid="rolelistpage" prmNames="{id:'id'}" rowList="15,30,45"
							sortname="id" del="false" export="false">
							<tgrid:jqGridHead headvalue="'主键id','所属运营商','角色名称','数据域','操作'">
								<tgrid:jqcol name="id" index="id" hidden="true" />
								<tgrid:jqcol name="companyname" index="companyname" />
								<tgrid:jqcol name="rolename" index="rolename" />
								<tgrid:jqcol name="dataarea" index="dataarea" type="dataarea"
									formatter="select" />
								<tgrid:jqcol name="act" />
							</tgrid:jqGridHead>
						</tgrid:jqGrid>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="xg" title="修改" power="2903"/>
	<e:yzactbutton onclick="del" title="删除" power="2902"/>
</e:yzact>

<script type="text/javascript">

//加载角色列表
$(document).ready(function(){
	var len=$("#rolelist").getGridParam("width");
	//列的权限控制
	if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#rolelist").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#rolelist").setGridWidth(len).trigger("reloadGrid");
});

/* 添加 */
$("#add_role").click(function(){
	jQuery().yzIframeDialog({id:"add_role_m",iframeurl:"<%=basepath%>/admin/role/preAddRole.action",title:"新增角色"});
	$('#add_role_m').modal('show');
})

/* 修改 */
function xg(id){
	var rd =jQuery("#rolelist").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_role_m",iframeurl:"<%=basepath%>/admin/role/editRole.action?id="
							+ rd['id'],
					title : "编辑角色"
				});
		$('#edit_role_m').modal('show');
	}

	/* 删除 */
	function del(id) {
		var rowData = jQuery("#rolelist").getRowData(id);
		if (id != null)
			jQuery("#rolelist").jqGrid('delGridRow', rowData['id'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认删除所选角色【" + rowData['rolename'] + "】？",
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
</script>
<%@include file="../../../common/admin_footer.jsp"%>
