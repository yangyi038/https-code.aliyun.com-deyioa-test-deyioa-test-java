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
								<%@include file="./serch_physical_channel.jsp"%>
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box"> 
								<tgrid:jqGrid url="listPhsicalChannel.action?channel_id=${id}+&channel_number=${number}" autowidth="true"
									dourl="delPicture.action" id="phsicalChannel" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','频道号','名称','cdn类型','码率','播放URL','激活状态','URL状态','创建时间','操作'">
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="channel_number" index="channel_number" width="20"/>
									<tgrid:jqcol name="name" index="name" width="50"/>
									<tgrid:jqcol name="type" index="type" width="20"/> 
									<tgrid:jqcol name="code_check" index="code_check" width="20"/> 
									<tgrid:jqcol name="channel_url" index="channel_url"  width="60"/> 
									<tgrid:jqcol name="activated_state" index="activated_state"  width="20"/> 
								    <tgrid:jqcol name="status" index="status"  width="20"/> 
									<tgrid:jqcol name="create_time" index="create_time" width="50"/>
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
<e:yzactbutton onclick="activate" title="激活"  event="function (rowdata){
if(rowdata['activated_state']=='激活'){
		return false;
	}else{
		return true;
	}
}"/> 
<e:yzactbutton onclick="stop" title="停止"  event="function (rowdata){
if(rowdata['activated_state']=='停止'){
		return false;
	}else{
		return true;
	}
}"/> 
<e:yzactbutton onclick="effective" title="有效"  event="function (rowdata){
if(rowdata['status']=='有效'){
		return false;
	}else{
		return true;
	}
}"/> 
<e:yzactbutton onclick="unEffective" title="无效"  event="function (rowdata){
if(rowdata['status']=='无效'){
		return false;
	}else{
		return true;
	}
}"/> 

<e:yzactbutton onclick="scrap" title="废弃"  />

</e:yzact>
<script type="text/javascript"> 
function search(){
	var grid = $("#channel");
	var name = $("#searchContext").val();
	sdata = {name:name};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
	
}




function setlineState(){
	var grid = $("#phsicalChannel");
	var status = $("#button4").val();
	sdata = {status:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function setEffective(){
	var grid = $("#phsicalChannel");
	var status = $("#button5").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function setUnEffective(){
	var grid = $("#phsicalChannel");
	var status = $("#button6").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function setScrap(){
	var grid = $("#phsicalChannel");
	var status = $("#button7").val();
	sdata = {status:status};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function activateStatus(){
	var grid = $("#phsicalChannel");
	var activated_state = $("#button1").val();
	sdata = {activated_state:null};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 
function setActivate(){
	var grid = $("#phsicalChannel");
	var activated_state = $("#button2").val();
	sdata = {activated_state:activated_state};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 

function setStop(){
	var grid = $("#phsicalChannel");
	var activated_state = $("#button3").val();
	sdata = {activated_state:activated_state};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);

} 


$("#add_physical_channel").click(function(){
	var number = ${number};
	var id = ${id};
	
	jQuery().yzIframeDialog({id:"add_t_channel_s",iframeurl:"<%=basepath%>/admin/t_physical_channel/showPhysicalChannel.action?channelNumber="+number+"&channelId="+id,title:"添加物理频道信息"});
	$('#add_t_channel_s').modal('show');
})
function update(id){
	
	var rd =jQuery("#phsicalChannel").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_phsicalChannel_m",iframeurl:"<%=basepath%>/admin/t_physical_channel/showPhsicalChannelWindow.action?id="+rd['id'],title:"修改物理频道信息"});
	$('#edit_phsicalChannel_m').modal('show');
}

function query(id){
	
	var rd =jQuery("#phsicalChannel").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_channel_m",iframeurl:"<%=basepath%>/admin/t_physical_channel/queryPhysicalChannel.action?id="+rd['id'],title:"物理频道详情"});
	$('#edit_channel_m').modal('show');
}

function setBack(){
	window.location.href="<%=basepath%>/admin/t_channel/channel_manager.action";
} 

function del(id){
	var rowData =jQuery("#phsicalChannel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_physical_channel/del_phsicalChannel.action',{id:rowData['id'],channelNumber:rowData['channel_number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('phsicalChannel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}



function effective(id){
	var rowData =jQuery("#phsicalChannel").getRowData(id);
	
	 modalDialogconfim("confim_sysuser_logoff","确定设定有效？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_physical_channel/effective.action',{id:rowData['id'],channelNumber:rowData['channel_number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("设定成功");
					refreshGrid('phsicalChannel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("设定失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function unEffective(id){
	var rowData =jQuery("#phsicalChannel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定设定无效？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_physical_channel/unEffective.action',{id:rowData['id'],channelNumber:rowData['channel_number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("设定成功");
					refreshGrid('phsicalChannel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("设定失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function activate(id){
	var rowData =jQuery("#phsicalChannel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定激活？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_physical_channel/activateChannel.action',{id:rowData['id'],channelNumber:rowData['channel_number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("激活成功");
					refreshGrid('phsicalChannel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("激活失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function stop(id){
	var rowData =jQuery("#phsicalChannel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定停止？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_physical_channel/stopChannel.action',{id:rowData['id'],channelNumber:rowData['channel_number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("停止成功");
					refreshGrid('phsicalChannel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("停止失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function scrap(id){
	var rowData =jQuery("#phsicalChannel").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定废弃？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_physical_channel/scrapChannel.action',{id:rowData['id'],channelNumber:rowData['channel_number'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("废弃成功");
					refreshGrid('phsicalChannel');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("废弃失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
