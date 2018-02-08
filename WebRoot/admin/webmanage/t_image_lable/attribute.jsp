<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
 <link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css"/>
    <script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>	
   <script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>	
		<div class="frame-container">
			<div class="page-content">
				<div class="yz-frame-head">
					<div class="searchbox">
						<div class="row">
							<div class="col-xs-12">
								<%@include file="./serch_attribute.jsp"%>
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listT_imageLable.action" autowidth="true"
									dourl="delPicture.action" id="attribute" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','属性名','属性描述','创建时间','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="name" index="name" width="40"/>
									<tgrid:jqcol name="lable_describe" index="lable_describe" width="50"/> 
									<tgrid:jqcol name="status" index="status" width="40"/> 
									
									<tgrid:jqcol name="act"  width="50"/>

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
<%-- <e:yzact id="act">     --%>

<%-- </e:yzact> --%>
<e:yzact id="act">  
<e:yzactbutton onclick="update" title="修改" />     
<e:yzactbutton onclick="del" title="删除"  />


</e:yzact>
<script type="text/javascript"> 
function search(){
	var grid = $("#imageLable");
	var name = $("#search_content").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}


$("#add_attribute").click(function(){
	jQuery().yzIframeDialog({id:"add_attribute_m",iframeurl:"<%=basepath%>/admin/t_imageText/showAddAttributeWindow.action",title:"添加图文信息模板"});
	$('#add_attribute_m').modal('show');
})

$("#show_attribute").click(function(){
	jQuery().yzIframeDialog({id:"add_t_imageLable_m",iframeurl:"<%=basepath%>/admin/t_imageText/showAttribute.action",title:"添加图文信息模板"});
	$('#add_t_imageLable_m').modal('show');
})

function update(id){
	var rd =jQuery("#imageLable").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_sysuser_m",iframeurl:"<%=basepath%>/admin/t_imageText/showEditWindow.action?id="+rd['id'],title:"修改字幕"});
	$('#edit_sysuser_m').modal('show');
}
function del(id){
	var rowData =jQuery("#imageLable").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/delImageLable.action',{id:rowData['id'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('imageLable');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}
function agree(id){
	var rowData =jQuery("#imageLable").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定通过审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/agreeImageLable.action',{id:rowData['id'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("审核通过");
					refreshGrid('imageLable');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("审核失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function unAgree(id){
	var rowData =jQuery("#imageLable").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定取消审核？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/unAgreeImageLable.action',{id:rowData['id'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("取消成功");
					refreshGrid('imageLable');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("取消失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
