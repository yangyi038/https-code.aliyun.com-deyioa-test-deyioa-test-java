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
						所属酒店:<select name='hotelsid'
							id='search_hotelsid'>
							<option value='' selected>-请选择-</option>
							<c:forEach items="${hotellist}" var="v">
								<option value="${v.sid}"
									<c:if test="${v.sid==t_stb.hotelsid}">selected="selected"</c:if>>${v.hotelname}</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp; 
						审核状态:<select name="stbstatus"
							id="search_stbstatus">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'stbstatus')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; 
						账户类型:<select name="accounttype"
							id="search_accounttype">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'accounttype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; 
						机顶盒组: <select name='stbgroup'
							id='search_stbgroup'>
							<option value='' selected>-请选择-</option>
							<c:forEach items="${stbgrouplist}" var="v">
								<option value="${v.sid}"
									<c:if test="${v.sid==t_stb.stbgroup}">selected="selected"</c:if>>${v.groupname}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; 
						<input name="content" id="search_content"
							style="height: 32px; width: 280px"
							placeholder="可输入机顶盒账号进行模糊查询" />
						<button class="btn btn-info btn-xs" onclick="search()"
							style="width: 60px; height: 32px;">搜索</button>

						<br>
						<e:yzbutton id="add_t_stb" name="添加" cssClass="btn btn-success" power="80901" />
						<e:yzbutton id="importStb" name="导入" cssClass="btn btn-success" power="80906" />
						<e:yzbutton id="downloadTemplate" name="下载模板" cssClass="btn btn-success" power="80905" />
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_stb.action" autowidth="true" 
					dourl="delStb.action" id="t_stblist" mtype="POST" search="false"
					export="true" pageid="t_stbpage" prmNames="{id:'sid'}"
					sortname="sid" del="false" ondblClickRow="preview"
					rowList="15,30,45" > 
					<tgrid:jqGridHead
						headvalue="'SID' ,'运营商','二级运营商','经销商', '机顶盒账号' , '所属酒店','所属机顶盒组','余额','审核状态','创建时间','操作'"> <%-- '有效截止时间', --%>
						<tgrid:jqcol name='sid' width='50' index='sid' hidden='true' />
						<tgrid:jqcol name='companyname' width='150' index='companyname'  />
						<tgrid:jqcol name='operatorname' width='100' index='operatorname'  />
						<tgrid:jqcol name='dealername' width='100' index='dealername'  />
						<tgrid:jqcol name='stbnum' width='150' index='stbnum'
							hidden='false' />
						<tgrid:jqcol name='hotelname' width='150' index='orderstatus'
							hidden='false' />
						<tgrid:jqcol name='stbgroup' width='150' index='stbgroup'
							hidden='false' />
						<%-- <tgrid:jqcol name='validdateStr' width='150' index='validdateStr'
							hidden='false' /> --%>
						<tgrid:jqcol name='balance' width='150' index='balance'
							hidden='false' />
						<tgrid:jqcol name='stbstatus' width='150' index='stbstatus'
							hidden='false' type="stbstatus"/>
						<tgrid:jqcol name='createtime' width='150' index='createtime'
							hidden='false' />
						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="view" title="查看" power="80904"/>
	<e:yzactbutton onclick="xg" title="修改" power="80903" />
	<e:yzactbutton onclick="del" title="删除" power="80902" />
</e:yzact>
<script type="text/javascript">

/* 加载列表权限 */
$(document).ready(function(){
	var len=$("#t_stblist").getGridParam("width");
	//列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#t_stblist").setGridParam().hideCol("companyname");
		 $("#t_stblist").setGridParam().hideCol("operatorname");
		 $("#t_stblist").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#t_stblist").setGridParam().hideCol("companyname");
		 $("#t_stblist").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#t_stblist").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
		 
	 }
	
	 $("#t_stblist").setGridWidth(len).trigger("reloadGrid");
});


//导入机顶盒
$("#importStb").click(function(){
	jQuery().yzIframeDialog({id:"import_t_stb_m",iframeurl:"<%=basepath%>/admin/t_stb/importStbPre.action",title : "机顶盒批量导入"});
		$('#import_t_stb_m').modal('show');
})

//下载模板
$("#downloadTemplate").click(function(){
	var url = basepath+"/admin/t_stb/downloadStbTemplate.action"; 
    url = encodeURI(url);
    location.href = url; 
})

//查看
function view(sid){
	var rd =jQuery("#t_stblist").getRowData(sid);
	jQuery().yzIframeDialog({id:"edit_t_stb_m",iframeurl:"<%=basepath%>/admin/t_stb/getStb.action?sid="+rd['sid'],title:"查看机顶盒信息"});
	$('#edit_t_stb_m').modal('show');
}

//修改
function xg(sid){
var rd =jQuery("#t_stblist").getRowData(sid);
jQuery().yzIframeDialog({id:"edit_t_stb_m",iframeurl:"<%=basepath%>/admin/t_stb/editStb.action?sid="+ rd['sid'],title : "修改机顶盒信息"});
	$('#edit_t_stb_m').modal('show');
}

//添加
$("#add_t_stb").click(function(){
	jQuery().yzIframeDialog({id:"add_t_stb_m",iframeurl:"<%=basepath%>/admin/t_stb/preAddStb.action",
			title : "新增机顶盒"
		});
		$('#add_t_stb_m').modal('show');
	})

	//删除
	function del(sid) {
		var rowData = jQuery("#t_stblist").getRowData(sid);
		if (sid != null)
			jQuery("#t_stblist").jqGrid('delGridRow', rowData['sid'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认删除？",
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

	//搜索
	function search() {
		var grid = $("#t_stblist");
		var hotelsid = $("#search_hotelsid").val();//所属酒店
		var stbstatus = $("#search_stbstatus").val();//审核状态
		var accounttype = $("#search_accounttype").val();//账户类型
		var stbgroup = $("#search_stbgroup").val();//机顶盒组
		var content = $("#search_content").val();
		sdata = {
			hotelsid : hotelsid,
			stbstatus : stbstatus,
			accounttype : accounttype,
			stbgroup : stbgroup,
			content : content
		};
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	}
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
