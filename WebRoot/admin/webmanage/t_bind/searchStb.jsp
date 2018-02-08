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

<div>
	 审核状态:<select name="stbstatus" id="search_stbstatus">
		<option value='' selected>-请选择-</option>
		<c:forEach items="${fns:getCodeMap(pageContext.request,'stbstatus')}"
			var='item'>
			<option value='${item.key}'>${item.value}</option>
		</c:forEach>
	</select> 
	&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
	机顶盒组: <select name='stbgroup' id='search_stbgroup'>
		<option value='' selected>-请选择-</option>
		<c:forEach items="${stbgrouplist}" var="v">
			<option value="${v.sid}"
				<c:if test="${v.sid==t_stb.stbgroup}">selected="selected"</c:if>>${v.groupname}</option>
		</c:forEach>
	</select> 
	&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
	<input name="content" id="search_content"
		style="height: 32px; width: 280px" placeholder="可输入机顶盒账号进行模糊查询" />
	<button class="btn btn-info btn-xs" onclick="search()"
		style="width: 60px; height: 32px;">搜索</button>

	<br /> <br />
	<div>
		<tgrid:jqGrid url="searchStbList.action" autowidth="true" 
			dourl="delStb.action" id="t_stblist" mtype="POST" search="false"
			export="true" pageid="t_stbpage" prmNames="{id:'sid'}" sortname="sid"
			del="false" ondblClickRow="preview" rowList="10" multiselect="true"
			multiboxonly="true" beforeSelectRow="beforeSelectRow">
			<tgrid:jqGridHead
				headvalue="'SID' ,'运营商', '机顶盒账号' , '所属酒店','所属机顶盒组','有效截止时间','余额','审核状态','创建时间' ">
				<tgrid:jqcol name='sid' width='50' index='sid' hidden='true' />
				<tgrid:jqcol name='companyname' width='150' index='companyname' />
				<tgrid:jqcol name='stbnum' width='150' index='stbnum' hidden='false' />
				<tgrid:jqcol name='hotelname' width='150' index='orderstatus'
					hidden='false' />
				<tgrid:jqcol name='stbgroup' width='150' index='stbgroup'
					hidden='false' />
				<tgrid:jqcol name='validdateStr' width='150' index='validdateStr'
					hidden='false' />
				<tgrid:jqcol name='balance' width='150' index='balance'
					hidden='false' />
				<tgrid:jqcol name='stbstatus' width='150' index='stbstatus'
					hidden='false' type="stbstatus" />
				<tgrid:jqcol name='createtime' width='150' index='createtime'
					hidden='false' />
			</tgrid:jqGridHead>
		</tgrid:jqGrid>
	</div>
</div>


<form action="<%=basepath%>/admin/webmanage/t_bind/addBind.jsp"
	method="post">
	<script>
		function getStb() {
			/* 获取单个选中行的行ID */
			var rowId = jQuery("#t_stblist").jqGrid("getGridParam", "selrow");
			/* 获取单个行数据 */
			var rowData = jQuery("#t_stblist").jqGrid('getRowData', rowId);

			window.parent.document.getElementById("stbsid").value = rowData['sid'];
			window.parent.document.getElementById("stbnum").value = rowData['stbnum'];
			window.parent.closeDialogm('search_stb_m');
		}
	</script>

	<input id="stbsid" type="hidden" name="stbsid" value="" /> <input
		id="stbnum" type="hidden" name="stbnum" value="" />
	<div style="text-align: right">
		<button type="submit" class="btn btn-primary" onclick="getStb();">确定</button>
	</div>
</form>

<script type="text/javascript">

/* var tableHeight =  30;
$("#t_stblist").css(
	"cssText",
	"height: " + tableHeight+ "px!important;"
); */

	//列表单选
	function beforeSelectRow() {
		$("#t_stblist").jqGrid('resetSelection');
		return (true);
	}

	//加载机顶盒列表
	function loadComplete(x) {
		jQuery("#t_stblist").jqGrid("setGridHeight",
				26 * $("#t_stblist").jqGrid('getGridParam', 'rowNum'));
	}

	//搜索
	function search() {
		var grid = $("#t_stblist");
		var stbstatus = $("#search_stbstatus").val();//审核状态
		var stbgroup = $("#search_stbgroup").val();//机顶盒组
		var content = $("#search_content").val();//机顶盒账号
		sdata = {
			stbstatus : stbstatus,
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
