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
								<e:yzbutton id="add_t_adbDebugServer" name="添加" cssClass="btn btn-success" />
								<button class="btn btn-info btn-xs" onclick="setBack()"  style=";height:40px;width:70px;float:right;margin-right:200px;">返回</button>
								
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listadbDebugServer.action" autowidth="true"
									dourl="delPicture.action" id="adbDebugServer" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','ip','port','key','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="ip" index="ip" width="50"/>
									<tgrid:jqcol name="port" index="port" width="50"/>
									<tgrid:jqcol name="adb_key" index="adb_key" width="50"/>
								   
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
 	


</e:yzact>
<script type="text/javascript"> 



//查看
function query(id){
	var rd =jQuery("#adbDebugServer").getRowData(id);
	jQuery().yzIframeDialog({id:"query_t_uipwd_s",iframeurl:"<%=basepath%>/admin/t_sysVer/showAdbDebugServerInfo.action?id="+rd['id'],title:"详情"});
	$('#query_t_uipwd_s').modal('show');
}

//添加
$("#add_t_adbDebugServer").click(function(){
	jQuery().yzIframeDialog({id:"add_t_adbDebugServer_s",iframeurl:"<%=basepath%>/admin/t_sysVer/show_addadbDebugServer.action",title:"添加"});
	$('#add_t_adbDebugServer_s').modal('show');
})

//修改
function update(id){
	var rd =jQuery("#adbDebugServer").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_adbDebugServer_m",iframeurl:"<%=basepath%>/admin/t_sysVer/showEditnAdbDebugServerWindow.action?id="+rd['id'],title:"修改"});
	$('#edit_adbDebugServer_m').modal('show');
}

//删除
function del(id){
	var rowData =jQuery("#adbDebugServer").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_sysVer/del_adbDebugServer.action',{id:rowData['id'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('adbDebugServer');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function setBack(){
	window.location.href="<%=basepath%>/admin/t_sysVer/sysVer_manager.action";
} 
</script>
<%@include file="../../../common/admin_footer.jsp"%>
