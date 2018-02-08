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
								<%@include file="./serch_program.jsp"%> 
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body" >
							<input id="columnId" value="${column.id}" type="hidden"/>
							<div class="table-box">
								<tgrid:jqGrid url="list_titleColumn.action?columnId=${column.id}" autowidth="true"
									dourl="delPicture.action" id="titleColumn" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'${'#columnId'}',name:'${'#columnName'}'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','排序号码','分类名称','内容类型','内容对象','状态','节目类型','位置','显示类型','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="number" index="number" width="50"/>
									<tgrid:jqcol name="name" index="name" width="40"/>
									<tgrid:jqcol name="type" index="type" width="50"/> 
									<tgrid:jqcol name="content" index="content" width="50"/> 
									<tgrid:jqcol name="status" index="status" width="40"/> 
									
									<tgrid:jqcol name="program_type" index="program_type"  width="50"/> 
									<tgrid:jqcol name="seat" index="seat"  width="50"/> 
									<tgrid:jqcol name="show_type" index="show_type"  width="50"/> 
								    
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
	var grid = $("#titleColumn");
	var programName = $("#name").val();
	sdata = {programName:programName};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
}


function searchLine(){
	var grid = $("#titleColumn");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function searchUnline(){
	var grid = $("#titleColumn");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function searchOnlined(){
	var grid = $("#titleColumn");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function searchUnlined(){
	var grid = $("#titleColumn");
	var status = $("#button4").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function model(){
	var grid = $("#titleColumn");
	var seat = $("#button5").val();
	sdata = {seat:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function normalModel(){
	var grid = $("#titleColumn");
	var seat = $("#button6").val();
	sdata = {seat:seat};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function testModel(){
	var grid = $("#titleColumn");
	var seat = $("#button7").val();
	sdata = {seat:seat};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function setBack(){
	window.location.href="<%=basepath%>/admin/t_column/column_manager.action";

} 
$("#add_t_titleColumn").click(function(){
	<%-- jQuery().yzIframeDialog({id:"add_t_titleColumn_s",iframeurl:"<%=basepath%>/admin/t_column/showT_titleColumn.action",title:"添加栏目节目"});
	$('#add_t_titleColumn_s').modal('show'); --%>
	window.location.href="<%=basepath%>/admin/sysmanage/t_column/add_program.jsp";
})
function update(id){
	var rd =jQuery("#titleColumn").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_program_m",iframeurl:"<%=basepath%>/admin/t_column/showEditWindow.action?id="+rd['id'],title:"栏目节目修改"});
	$('#edit_program_m').modal('show');
}
function del(id){
	var rowData =jQuery("#titleColumn").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_column/del_program.action',{id:rowData['id'],name:rowData['content'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('titleColumn');
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
