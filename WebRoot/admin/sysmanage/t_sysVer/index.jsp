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
								<%@include file="./serch_ver.jsp"%>
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listSysVer.action" autowidth="true"
									dourl="delPicture.action" id="sysVer" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','参数','名称','组名','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="code" index="code" width="50"/>
									<tgrid:jqcol name="name" index="name" width="40"/>
									<tgrid:jqcol name="allow_group" index="allow_group" width="50"/> 
									
								   
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
    <e:yzactbutton onclick="query" title="查看"  />  
	<e:yzactbutton onclick="del" title="删除"  />
 	<e:yzactbutton onclick="update" title="编辑"  />
 	<e:yzactbutton onclick="add" title="添加" event="function (rowdata){
if(rowdata['code']=='autoShutdown'||rowdata['code']=='layoutSelectors'||rowdata['code']=='uiPwd'||rowdata['code']=='adbDebugServer'||rowdata['code']=='realTimeControl'){
		return true;
	}else{
		return false;
	}
}"/> 


</e:yzact>
<script type="text/javascript"> 
function search(){
	var grid = $("#sysVer");
	var code = $("#search_content").val();
	sdata = {code:code};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}


//查看
function query(id){
	var rd =jQuery("#sysVer").getRowData(id);
	jQuery().yzIframeDialog({id:"query_t_sysVer_s",iframeurl:"<%=basepath%>/admin/t_sysVer/query_sysVer.action?id="+rd['id'],title:"参数详情"});
	$('#query_t_sysVer_s').modal('show');
}

//添加
$("#add_t_sysVer").click(function(){
	jQuery().yzIframeDialog({id:"add_t_sysver_s",iframeurl:"<%=basepath%>/admin/t_sysVer/show_addSysVer.action",title:"添加参数"});
	$('#add_t_sysver_s').modal('show');
})

//修改
function update(id){
	var rd =jQuery("#sysVer").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_sysVer_m",iframeurl:"<%=basepath%>/admin/t_sysVer/showEditSysVerWindow.action?id="+rd['id'],title:"修改参数"});
	$('#edit_sysVer_m').modal('show');
}

//删除
function del(id){
	var rowData =jQuery("#sysVer").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_sysVer/del_SysVer.action',{id:rowData['id'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('sysVer');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}


function add(id){
	var rd =jQuery("#sysVer").getRowData(id);
	var id = rd['id'];
	var code = rd['code'];
	window.location.href="<%=basepath%>/admin/t_sysVer/autoshutdown_manager.action?id="+id+"&code="+code;
	
}

</script>
<%@include file="../../../common/admin_footer.jsp"%>
