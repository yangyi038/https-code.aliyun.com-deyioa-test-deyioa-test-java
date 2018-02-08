<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
													<e:yzbutton   id="add_service"  name="新增"  cssClass="btn btn-success"/>
											 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listService.action" autowidth="true"
									dourl="delService.action" id="service" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','标签名称','备注','操作'">
										<tgrid:jqcol name="id" index="id" hidden="true" />
										<tgrid:jqcol name="ser_name" index="ser_name" /> 
										<tgrid:jqcol name="remark" index="remark" />  

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
<e:yzact id="act">    <%-- 
<e:yzactbutton onclick="view" title="查看" />   --%>
<e:yzactbutton onclick="edit" title="修改" />  
<e:yzactbutton onclick="del" title="删除" />  
</e:yzact>

  <script type="text/javascript"> 
  $("#add_service").click(function(){
		jQuery().yzIframeDialog({id:"add_service_m",iframeurl:"<%=basepath%>/admin/service/preAddService.action",title:"新增服务标签"});
		$('#add_service_m').modal('show');
	})  
  function edit(id){
		var rd =jQuery("#service").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_service_m",iframeurl:"<%=basepath%>/admin/service/editService.action?id="+rd['id'],title:"修改服务标签"});
		$('#edit_service_m').modal('show');
	}  
  function del(id){
		var rowData =jQuery("#service").getRowData(id);
		if( id != null ) jQuery("#service").jqGrid('delGridRow',rowData['id'],{
			top:120,
			left:250,
			reloadAfterSubmit:true,
			jqModal: false,
			msg: "确认删除所选服务标签？",
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
