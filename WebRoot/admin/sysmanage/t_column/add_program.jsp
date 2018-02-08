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
								<%@include file="./serch_type.jsp"%> 
								
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<input id="columnID" value="${column.id}" type="hidden"/>
							<input id="columnName" value="${column.name}" type="hidden"/> 
							<input name="columnType" value="${column.type}" type="hidden"/>
							<div class="table-box">
								<tgrid:jqGrid url="list_columnType.action" autowidth="true"
									dourl="delPicture.action" id="columnType" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','内容对象','具体内容','状态','创建时间','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="name" index="name" width="50"/>
									<tgrid:jqcol name="content" index="content" width="40"/>
									<tgrid:jqcol name="status" index="status" width="50"/> 
									<tgrid:jqcol name="create_time" index="create_time" width="50"/> 
									<tgrid:jqcol name="act"  width="20"/>

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
<e:yzactbutton onclick="add" title="添加" />     

</e:yzact>
<script type="text/javascript"> 


function search(){
	var grid = $("#columnType");
	var name = $("#name").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}

function searchType(){
	var grid = $("#columnType");
	var type = $("#column_type").val();
	sdata = {type:type};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}


function searchLine(){
	var grid = $("#columnType");
	var status = $("#button1").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function searchUnline(){
	var grid = $("#columnType");
	var status = $("#button2").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function searchOnlined(){
	var grid = $("#columnType");
	var status = $("#button3").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function searchUnlined(){
	var grid = $("#columnType");
	var status = $("#button4").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function check(){
	var grid = $("#columnType");
	var check = $("#button5").val();
	sdata = {check:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function checked(){
	var grid = $("#columnType");
	var check = $("#button6").val();
	sdata = {check:check};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function unCheck(){
	var grid = $("#columnType");
	var seat = $("#button7").val();
	sdata = {check:check};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 




function add(id){
	var columnId = $("#columnID").val();
	var columnName = $("#columnName").val();
	var columnType = $("input[name=columnType]").val();
	
	var rowData =jQuery("#columnType").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定添加？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_column/addProgram.action',{id:rowData['id'],name:rowData['name'],columnId:columnId,columnName:columnName,columnType:columnType,isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("添加成功");
					refreshGrid('columnType');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("添加失败");
					closeDialog("confim_sysuser_logoff");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function setBack(){
	window.location.href="<%=basepath%>/admin/t_column/column_manager.action";

} 


</script>
<%@include file="../../../common/admin_footer.jsp"%>
