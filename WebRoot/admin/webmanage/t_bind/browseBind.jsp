<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css" />
<script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>

<%
	//获取当前登录用户
	Sysuser user = ContextHolderUtils.getLoginUser();
%>

<div class="frame-container">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
					 <e:yzbutton id="add_t_bind" name="添加" cssClass="btn btn-success" power="82901" /> 
					 <e:yzbutton id="importBind" name="导入" cssClass="btn btn-success" power="82906" />
					 <e:yzbutton id="downloadTemplate" name="下载模板" cssClass="btn btn-success" power="82905"/>
						
						<!-- 机顶盒状态:
						<button class="btn btn-info btn-xs" onclick="search1()"
							style="margin-left: 10px">不限</button>
						<button class="btn btn-info btn-xs" onclick="search2()"
							style="margin-left: 10px">上线</button>
						<button class="btn btn-info btn-xs" onclick="search3()"
							style="margin-left: 10px">下线</button>
						&nbsp;&nbsp;&nbsp; -->
						<div style="float: right; margin-right: 200px;">
							<input name="content" id="search_content"
								style="height: 32px; width: 280px"
								placeholder="可输入酒店名/机顶盒账号模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
					<%-- <div class="col-xs-12">
						<e:yzbutton id="add_t_bind" name="添加" cssClass="btn btn-success" />
					</div> --%>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_bind.action" autowidth="true"
					dourl="delBind.action" id="t_bindlist" mtype="GET" search="false"
					export="true" pageid="t_bindpage" prmNames="{id:'sid'}"
					sortname="sid" del="false" ondblClickRow="preview"
					rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'SID' ,'运营商','二级运营商','经销商', '酒店名称' , '机顶盒账号','无线热点','房间号','欢迎词', '创建时间' ,'操作'">
						<tgrid:jqcol name='sid' width='50' index='sid' hidden='true' />
						<tgrid:jqcol name='companyname' width='150' index='companyname' />
						<tgrid:jqcol name='operatorname' width='100' index='operatorname'  />
						<tgrid:jqcol name='dealername' width='100' index='dealername'  />
						
						<tgrid:jqcol name='hotelname' width='150' index='hotelname'
							hidden='false' />
						<tgrid:jqcol name='stbnum' width='150' index='stbnum'
							hidden='false' />
						<tgrid:jqcol name='wifi' width='150' index='wifi' hidden='false' />
						<tgrid:jqcol name='roomnum' width='150' index='roomnum'
							hidden='false' />
						<tgrid:jqcol name='welcome' width='150' index='welcome'
							hidden='false' />
						<tgrid:jqcol name='createtimeStr' width='150' index='createtimeStr'
							hidden='false' />

						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="view" title="查看" power="82904"/>
	<e:yzactbutton onclick="xg" title="修改" power="82903" /> 
	<e:yzactbutton onclick="del" title="解绑" power="82902"/>
</e:yzact>
<script type="text/javascript">

//加载列表
$(document).ready(function(){
	var len=$("#t_bindlist").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#t_bindlist").setGridParam().hideCol("companyname");
		 $("#t_bindlist").setGridParam().hideCol("operatorname");
		 $("#t_bindlist").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#t_bindlist").setGridParam().hideCol("companyname");
		 $("#t_bindlist").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#t_bindlist").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#t_bindlist").setGridWidth(len).trigger("reloadGrid");
});

//下载模板
$("#downloadTemplate").click(function(){
	var url = basepath+"/admin/t_bind/downloadBindTemplate.action"; 
    url = encodeURI(url);
    location.href = url; 
})
//导入机顶盒
$("#importBind").click(function(){
	jQuery().yzIframeDialog({id:"import_t_bind_m",iframeurl:"<%=basepath%>/admin/t_bind/importBindPre.action",title : "终端绑定批量导入"});
		$('#import_t_bind_m').modal('show');
})

//查看
function view(sid){
	var rd =jQuery("#t_bindlist").getRowData(sid);
	jQuery().yzIframeDialog({id:"edit_t_bind_m",iframeurl:"<%=basepath%>/admin/t_bind/getBind.action?sid="+rd['sid'],title:"查看详情"});
	$('#edit_t_bind_m').modal('show');
}

//修改
function xg(sid){
var rd =jQuery("#t_bindlist").getRowData(sid);
jQuery().yzIframeDialog({id:"edit_t_bind_m",iframeurl:"<%=basepath%>/admin/t_bind/editBind.action?sid="+ rd['sid'],title : "修改信息"});
	$('#edit_t_bind_m').modal('show');
}

//添加绑定关系
$("#add_t_bind").click(function(){
	jQuery().yzIframeDialog({id:"add_t_bind_m",iframeurl:"<%=basepath%>/admin/t_bind/preAddBind.action",height:"450px",
			title : "新增酒店机顶盒绑定关系"
		});
		$('#add_t_bind_m').modal('show');
	})

	//删除
	function del(sid) {
		var rowData = jQuery("#t_bindlist").getRowData(sid);
		if (sid != null)
			jQuery("#t_bindlist").jqGrid('delGridRow', rowData['sid'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认解绑？",
				afterSubmit : function(response, postdata) {
					var json = response.responseText;
					if (json != "")
						modalDialogAlert(json);
					return [ true ];
				}
			});
		else
			modalDialogAlert("请先选中一行");
	}

	//搜索订单
	function search() {
		var grid = $("#t_bindlist");
		var content = $('#search_content').val();

		sdata = {
			content : content
		}; 
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	}
	
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
