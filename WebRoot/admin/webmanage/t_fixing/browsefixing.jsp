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
								<e:yzbutton id="add_t_fixing" name="添加" cssClass="btn btn-success" />
								<e:yzbutton id="import_t_fixing" name="导入" cssClass="btn btn-success" />
								<e:yzbutton id="downloadTemplate" name="下载模板" cssClass="btn btn-success" />
								<div style="float: right; margin-right: 200px;">
									<input name="order_sn" id="context"
										style="height: 32px; width: 250px" placeholder="可输入设备sn进行模糊查询" />
									<button class="btn btn-info btn-xs" onclick="search()" style="width: 60px; height: 32px;">搜索</button>
		                       </div>
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listFixing.action" autowidth="true"
									dourl="delPicture.action" id="fixing" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','设备sn','用户组','设备类型','出厂日期','开通日期','服务截止日期','ca厂家','版本','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="fixing_sn" index="fixing_sn" width="30"/>
									<tgrid:jqcol name="user_group" index="user_group" width="30"/>
									<tgrid:jqcol name="type" index="type" width="40"/>
									<tgrid:jqcol name="production_data" index="production_data" width="40"/> 
									<tgrid:jqcol name="opening_data" index="opening_data" width="40"/> 
									<tgrid:jqcol name="closing_data" index="closing_data"  width="40"/> 
									<tgrid:jqcol name="vender" index="vender"  width="20"/> 
									<tgrid:jqcol name="version" index="version"  width="20"/> 
									<tgrid:jqcol name="act"  width="50"/>

									</tgrid:jqGridHead> 
								</tgrid:jqGrid>
							</div>
						</div>
					</div>
				</div>
<%-- <e:yzact id="act">     --%>

<%-- </e:yzact> --%>
<e:yzact id="act">  
<e:yzactbutton onclick="card" title="采集卡" />
<e:yzactbutton onclick="query" title="查看" />
<e:yzactbutton onclick="update" title="修改" />     
<e:yzactbutton onclick="del" title="删除"  />

</e:yzact>
<script type="text/javascript"> 
function search(){
	var grid = $("#fixing");
	var fixingSn = $("#context").val();
	sdata = {fixingSn:fixingSn};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}


$("#add_t_fixing").click(function(){
	jQuery().yzIframeDialog({id:"add_fixing_s",iframeurl:"<%=basepath%>/admin/t_fixing/showAddFixing.action",title:"添加前端设备"});
	$('#add_fixing_s').modal('show');
})

//下载模板
$("#downloadTemplate").click(function(){
	var url = basepath+"/admin/t_fixing/downloadFixingTemplate.action"; 
    url = encodeURI(url);
    location.href = url; 
})

$("#import_t_fixing").click(function(){
	jQuery().yzIframeDialog({id:"import_fixing_s",iframeurl:"<%=basepath%>/admin/t_fixing/showImportFixing.action",title:"导入前端设备"});
	$('#import_fixing_s').modal('show');
})

function update(id){
	var rd =jQuery("#fixing").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_fixing_m",iframeurl:"<%=basepath%>/admin/t_fixing/showEditFixing.action?id="+rd['id'],title:"修改前端设备"});
	$('#edit_fixing_m').modal('show');
}

function card(id){
	var rd =jQuery("#fixing").getRowData(id);
	var id = rd['id'];
	window.location.href="<%=basepath%>/admin/t_fixing/card_manager.action?id="+id;
	
}


function query(id){
	var rd =jQuery("#fixing").getRowData(id);
	jQuery().yzIframeDialog({id:"query_fixing_m",iframeurl:"<%=basepath%>/admin/t_fixing/queryFixingInfo.action?id="+rd['id'],title:"前端设备详情"});
	$('#query_fixing_m').modal('show');
}

function del(id){
	var rowData =jQuery("#fixing").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_fixing/del_fixing.action',{id:rowData['id'],fixingSn:rowData['fixing_sn'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('fixing');
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
