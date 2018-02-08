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
													<e:yzbutton   id="add_originplace"  name="新增"  cssClass="btn btn-success"/>
											 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listOriginplace.action" autowidth="true"
									dourl="delOriginplace.action" id="listOriginplace" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','产地','添加时间','操作'">
										<tgrid:jqcol name="ID" index="ID" hidden="true" />
										<tgrid:jqcol name="NAME" index="NAME" />  
										<tgrid:jqcol name="ADDTIME" index="ADDTIME" /> 
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
<e:yzactbutton onclick="edit" title="编辑" /> 
<e:yzactbutton onclick="del" title="删除"  />
</e:yzact>

<script type="text/javascript">
$("#add_originplace").click(function(){
	jQuery().yzIframeDialog({id:"add_originplace_m",iframeurl:"<%=basepath%>/admin/originplace/preAddOriginplace.action",title:"新增产地"});
	$('#add_originplace_m').modal('show');
});
function edit(id){
	var rd =jQuery("#listOriginplace").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_originplace_m",iframeurl:"<%=basepath%>/admin/originplace/editOriginplace.action?id="+rd['ID'],title:"编辑产地"});
	$('#edit_originplace_m').modal('show');
}
function del(id){
	var rowData =jQuery("#listOriginplace").getRowData(id);
	if( id != null ) jQuery("#listOriginplace").jqGrid('delGridRow',rowData['ID'],{
		top:120,
		left:250,
		reloadAfterSubmit:true,
		jqModal: false,
		msg: "确认删除所选产地？",
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
