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
													<e:yzbutton   id="add_siteinfo"  name="新增"  cssClass="btn btn-success"/>
												  
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listSiteinfo.action" autowidth="true"
									dourl="delSiteinfo.action" id="siteinfo" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'ID'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','文案类别','是否显示','添加时间','修改时间','排序','操作'">
										<tgrid:jqcol name="id" index="id" hidden="true" />
										<tgrid:jqcol name="sicategory" index="sicategory"  type="sicategory"  formatter="select"/> 
										<tgrid:jqcol name="isshow" index="isshow"   type="isshow"  formatter="select"/>
										 <tgrid:jqcol name="addtime" index="addtime" />  
										<tgrid:jqcol name="edittime" index="edittime" />  
										<tgrid:jqcol name="px" index="px" />  
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
  $("#add_siteinfo").click(function(){
		jQuery().yzIframeDialog({id:"add_siteinfo_m",iframeurl:"<%=basepath%>/admin/siteinfo/preAddSiteinfo.action",title:"新增文案"});
		$('#add_siteinfo_m').modal('show');
	})  
  function edit(id){
		var rd =jQuery("#siteinfo").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_siteinfo_m",iframeurl:"<%=basepath%>/admin/siteinfo/editSiteinfo.action?id="+rd['id'],title:"修改文案"});
		$('#edit_siteinfo_m').modal('show');
	}  
  function del(id){
		var rowData =jQuery("#siteinfo").getRowData(id);
		if( id != null ) jQuery("#siteinfo").jqGrid('delGridRow',rowData['id'],{
			top:120,
			left:250,
			reloadAfterSubmit:true,
			jqModal: false,
			msg: "确认删除所选文案？",
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
