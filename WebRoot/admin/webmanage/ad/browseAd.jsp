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
													<e:yzbutton   id="add_ad"  name="新增"  cssClass="btn btn-success"/>
											 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listAd.action" autowidth="true"
									dourl="delAd.action" id="Ad" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{ad_id:'ad_id'}"
									sortname="ad_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','轮播名','状态','排序','点击量','操作'">
										<tgrid:jqcol name="ad_id" index="ad_id" hidden="true" />
										<tgrid:jqcol name="ad_name" index="ad_name" /> 
										<tgrid:jqcol name="enabled" index="enabled"  type="enabled"  formatter="select"/>
										<tgrid:jqcol name="oder" index="oder" />
										<tgrid:jqcol name="click_count" index="click_count" /> 
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
$("#add_ad").click(function(){
	jQuery().yzIframeDialog({id:"add_ad_m",iframeurl:"<%=basepath%>/admin/ad/preAddAd.action",title:"新增轮播图片"});
	$('#add_ad_m').modal('show');
}) 
function xg(id){
	var rd =jQuery("#Ad").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_ad_m",iframeurl:"<%=basepath%>/admin/ad/editAd.action?ad_id="+rd['ad_id'],title:"编辑首页轮播"});
	$('#edit_ad_m').modal('show');
}
function del(id){
	var rowData =jQuery("#Ad").getRowData(id);
	if( id != null ) jQuery("#Ad").jqGrid('delGridRow',rowData['ad_id'],{
		top:120,
		left:250,
		reloadAfterSubmit:true,
		jqModal: false,
		msg: "确认删除所选轮播图片【"+rowData['ad_name']+"】？",
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
