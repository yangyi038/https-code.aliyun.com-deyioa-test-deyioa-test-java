<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
 <link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css"/>
    <script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>	
   <script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>	
		<div class="frame-container">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<div class="row">
					<div class="col-xs-12">
						<div class="yz-frame-head">
							<div class="searchbox">
								<div class="row">
									<div class="col-xs-12"> 
													<e:yzbutton   id="add_activity"  name="新增"  cssClass="btn btn-success"/>
											 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listActivity.action" autowidth="true"
									dourl="delActivity.action" id="activity" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{act_id:'act_id'}"
									sortname="act_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','活动名','活动开始时间','活动结束时间','活动是否结束','操作'">
										<tgrid:jqcol name="act_id" index="act_id" hidden="true" />
										<tgrid:jqcol name="act_name" index="act_name" /> 
										<tgrid:jqcol name="start_time" index="start_time" />
										<tgrid:jqcol name="end_time" index="end_time" />
										<tgrid:jqcol name="is_finished" index="is_finished"   type="is_finished"  formatter="select"/> 
										<tgrid:jqcol name="act" />
									</tgrid:jqGridHead>
								</tgrid:jqGrid>
							</div>
						</div>
					</div>
				</div>
					</div>
				</div>

			</div>
		</div>
<e:yzact id="act">    
<e:yzactbutton onclick="xg" title="编辑" /> 
<e:yzactbutton onclick="del" title="删除"  />
</e:yzact>

<script type="text/javascript">
$("#add_activity").click(function(){
	jQuery().yzIframeDialog({id:"add_activity_m",iframeurl:"<%=basepath%>/admin/activity/preAddActivity.action",title:"新增特卖活动",width:"960px"});
	$('#add_activity_m').modal('show');
}) 
function xg(id){
	var rd =jQuery("#activity").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_activity_m",iframeurl:"<%=basepath%>/admin/activity/editActivity.action?act_id="+rd['act_id'],title:"编辑特卖活动",width:"960px"});
	$('#edit_activity_m').modal('show');
}
function del(id){
	var rowData =jQuery("#activity").getRowData(id);
	if( id != null ) jQuery("#activity").jqGrid('delGridRow',rowData['act_id'],{
		top:120,
		left:250,
		reloadAfterSubmit:true,
		jqModal: false,
		msg: "确认删除所选活动及其所属商品吗？",
		afterSubmit:function(response,postdata){
			var json = response.responseText;
			if(json!="")
				modalDialogAlert(json); 
			return [true];
			}
	});
	else modalDialogAlert("请先选中一行");
} 
</script>
<%@include file="../../../common/admin_footer.jsp"%>
