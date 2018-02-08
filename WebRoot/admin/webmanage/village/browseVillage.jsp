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
													<e:yzbutton   id="add_village"  name="新增"  cssClass="btn btn-success"/>
											 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listVillage.action" autowidth="true"
									dourl="delVillage.action" id="village" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','小区名称','所属行政区域','具体地址','期数','搜索关键字','备注','操作'">
										<tgrid:jqcol name="id" index="id" hidden="true" />
										<tgrid:jqcol name="name" index="name" /> 
										<tgrid:jqcol name="districtname" index="districtname" /> 
										<tgrid:jqcol name="address" index="address" /> 
										<tgrid:jqcol name="qnum" index="qnum"  />
										 <tgrid:jqcol name="keywords" index="keywords" />  
										<tgrid:jqcol name="remark" index="remark"/>  
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
<e:yzactbutton onclick="edit" title="修改" />  
<e:yzactbutton onclick="del" title="删除" />  
</e:yzact>

  <script type="text/javascript"> 
  $("#add_village").click(function(){
		jQuery().yzIframeDialog({id:"add_village_m",iframeurl:"<%=basepath%>/admin/village/preAddVillage.action",title:"新增小区"});
		$('#add_village_m').modal('show');
	})
  function edit(id){
		var rd =jQuery("#village").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_village_m",iframeurl:"<%=basepath%>/admin/village/editVillage.action?id="+rd['id'],title:"修改小区"});
		$('#edit_village_m').modal('show');
	}  
  function del(id){
		var rowData =jQuery("#village").getRowData(id);
		if( id != null ) jQuery("#village").jqGrid('delGridRow',rowData['id'],{
			top:120,
			left:250,
			reloadAfterSubmit:true,
			jqModal: false,
			msg: "确认删除所选小区？",
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
