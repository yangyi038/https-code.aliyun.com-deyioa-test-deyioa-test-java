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
	用户组: <select name='hotelgroup' id='search_hotelgroup'>
		<option value='' selected>-请选择-</option>
		<c:forEach items="${hotelgrouplist}" var="v">
			<option value="${v.sid}"
				<c:if test="${v.sid==t_hotel.hotelgroup}">selected="selected"</c:if>>${v.groupname}</option>
		</c:forEach>
	</select> &nbsp;&nbsp;&nbsp; 用户类型:<select name="hoteltype" id="search_hoteltype">
		<option value='' selected>-请选择-</option>
		<c:forEach items="${fns:getCodeMap(pageContext.request,'hoteltype')}"
			var='item'>
			<option value='${item.key}'>${item.value}</option>
		</c:forEach>
	</select> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp; <input name="content" id="search_content"
		style="height: 32px; width: 280px" placeholder="可输入酒店名/负责人/联系电话模糊查询" />
	<button class="btn btn-info btn-xs" onclick="search()"
		style="margin-left: 10px">搜索</button>

	<br /> <br />
	<div>
		<!-- style="width: 950px; height: 700px" -->
		<!-- class="table-box" -->
		<tgrid:jqGrid url="searchHotelList.action" autowidth="true" 
			dourl="delHotel.action" id="t_hotellist" mtype="POST" search="false"
			export="true" pageid="t_hotelpage" prmNames="{id:'sid'}"
			sortname="sid" del="false" ondblClickRow="preview" rowList="10"
			multiselect="true" multiboxonly="true"
			beforeSelectRow="beforeSelectRow">
			<tgrid:jqGridHead
				headvalue="'SID' ,'运营商名称', '用户编号' , '用户名称' ,'用户组','用户类型','联系人', '手机号' , '创建时间' ">
				<tgrid:jqcol name='sid' index='sid' hidden='true' />
				<tgrid:jqcol name='companyname' index='companyname' hidden='false' />
				<tgrid:jqcol name='hotelnum' index='hotelnum' hidden='false' />
				<tgrid:jqcol name='hotelname' index='hotelname' hidden='false' />
				<tgrid:jqcol name='hotelgroup' index='hotelgroup' hidden='false' />
				<tgrid:jqcol name='hoteltype' index='hoteltype' hidden='false'
					type='hoteltype' />
				<tgrid:jqcol name='linkman' index='linkman' hidden='false' />
				<tgrid:jqcol name='tel' index='tel' hidden='false' />
				<tgrid:jqcol name='createtime' index='createtime' hidden='false' />
			</tgrid:jqGridHead>
		</tgrid:jqGrid>
	</div>
</div>



<form action="<%=basepath%>/admin/webmanage/t_bind/addBind.jsp"
	method="post">
	<script>
			function getHotel(){
				/* 获取单个选中行的行ID */
				var rowId = jQuery("#t_hotellist").jqGrid("getGridParam", "selrow");
				/* 获取单个行数据 */
				var rowData = jQuery("#t_hotellist").jqGrid('getRowData',rowId);
				//
				<%-- jQuery().yzIframeDialog({id:"search_hotel_m",iframeurl:"<%=basepath%>/admin/t_bind/preAddBind.action?hotelsid="+ rowData['sid']+"&hotelname="+rowData['hotelname'],title : "关联酒店"});
					$('#search_hotel_m').modal('show'); --%>
				/* $('#hotelsid').val(rowData['sid']);
				$('#hotelname').val(rowData['hotelname']); */
				window.parent.document.getElementById("hotelsid").value=rowData['sid'];
				window.parent.document.getElementById("hotelname").value=rowData['hotelname'];
				window.parent.closeDialogm('search_hotel_m');
			}
		</script>

	<input id="hotelsid" type="hidden" name="hotelsid" value="" /> <input
		id="hotelname" type="hidden" name="hotelname" value="" />
	<div style="text-align: right">
		<button type="submit" class="btn btn-primary" onclick="getHotel();">确定</button>
	</div>
</form>

<script type="text/javascript">
	//列表单选
	function beforeSelectRow() {
		$("#t_hotellist").jqGrid('resetSelection');
		return (true);
	}

	//加载酒店列表
	function loadComplete(x) {
		jQuery("#t_hotellist").jqGrid("setGridHeight", 26 * $("#t_hotellist").jqGrid('getGridParam', 'rowNum'));
	}

	//搜索酒店
	function search() {
		var grid = $("#t_hotellist");
		var content = $('#search_content').val();
		var hotelgroup = $('#search_hotelgroup').val();
		var hoteltype = $('#search_hoteltype').val();

		sdata = {
			content : content,
			hotelgroup : hotelgroup,
			hoteltype : hoteltype
		}; //注意大写 
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	}
	
	
	/* $(document).ready(function(){
		doResize();
	});
	
	var t = document.documentElement.clientWidth;
	window.onresize = function() {
		if (t != document.documentElement.clientWidth) {
			t = document.documentElement.clientWidth;
			doResize();
		}
	}

	function doResize() {
		var ss = getPageSize();
		$("#t_hotellist").jqGrid('setGridWidth', ss.WinW - 100).jqGrid(
				'setGridHeight', ss.WinH - 600);
	}

	function getPageSize() {
		//http://www.blabla.cn/js_kb/javascript_pagesize_windowsize_scrollbar.html 
		var winW, winH;
		if (window.innerHeight) {// all except IE 
			winW = window.innerWidth;
			winH = window.innerHeight;
		} else if (document.documentElement
				&& document.documentElement.clientHeight) {// IE 6 Strict Mode 
			winW = document.documentElement.clientWidth;
			winH = document.documentElement.clientHeight;
		} else if (document.body) { // other 
			winW = document.body.clientWidth;
			winH = document.body.clientHeight;
		} // for small pages with total size less then the viewport  
		return {
			WinW : winW,
			WinH : winH
		};
	} */
</script>
<%@include file="../../../common/admin_footer.jsp"%>
