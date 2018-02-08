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
								<e:yzbutton id="add_t_card" name="添加" cssClass="btn btn-success" />
								<e:yzbutton id="import_t_card" name="导入" cssClass="btn btn-success" />
								<div style="float:right;margin-right:200px;">
									<button class="btn btn-info btn-xs" onclick="setBack()"  style="height:32px;width:100px">返回前端设备</button>
								</div>
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listCard.action?fixingId=${id}" autowidth="true"
									dourl="delPicture.action" id="card" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','节目组号','CA号','设备账号组','固件版本','Rom版本','LastTime','LastIP','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="programme_code" index="programme_code" width="30"/>
									<tgrid:jqcol name="ca_code" index="ca_code" width="40"/>
									<tgrid:jqcol name="stb_group" index="stb_group" width="40"/> 
									<tgrid:jqcol name="firmware" index="firmware" width="20"/> 
									<tgrid:jqcol name="romversion" index="romversion"  width="20"/> 
									<tgrid:jqcol name="last_time" index="last_time"  width="40"/> 
									<tgrid:jqcol name="last_ip" index="last_ip"  width="20"/> 
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
<e:yzactbutton onclick="query" title="查看" />
<e:yzactbutton onclick="update" title="修改" />     
<e:yzactbutton onclick="del" title="删除"  />

</e:yzact>
<script type="text/javascript"> 
function search(){
	var grid = $("#channel");
	var name = $("#context").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}


function setBack(){
	window.location.href="<%=basepath%>/admin/t_fixing/fixing_manage.action";
} 



$("#add_t_card").click(function(){
	var id = ${id};
	jQuery().yzIframeDialog({id:"add_card_s",iframeurl:"<%=basepath%>/admin/t_fixing/showAddCard.action?fixingId="+id,title:"添加采集卡"});
	$('#add_card_s').modal('show');
})


$("#import_t_card").click(function(){
	var id = ${id};
	jQuery().yzIframeDialog({id:"import_card_s",iframeurl:"<%=basepath%>/admin/t_fixing/showImportCard.action?fixingId="+id,title:"导入采集卡"});
	$('#import_card_s').modal('show');
})

function update(id){
	var rd =jQuery("#card").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_card_m",iframeurl:"<%=basepath%>/admin/t_fixing/showEditCard.action?id="+rd['id'],title:"修改采集卡"});
	$('#edit_card_m').modal('show');
}



function query(id){
	var rd =jQuery("#card").getRowData(id);
	jQuery().yzIframeDialog({id:"query_card_m",iframeurl:"<%=basepath%>/admin/t_fixing/querycardInfo.action?id="+rd['id'],title:"采集卡详情"});
	$('#query_card_m').modal('show');
}

function del(id){
	var rowData =jQuery("#card").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_fixing/del_card.action',{id:rowData['id'],programmeCode:rowData['programme_code'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('card');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>
