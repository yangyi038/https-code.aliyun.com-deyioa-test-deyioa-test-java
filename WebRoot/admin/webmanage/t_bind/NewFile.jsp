<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jqgrid宽度高度自适应</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="jquery/jquery-ui/css/redmond/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="jquery/jqgrid/css/ui.jqgrid.css" />

<script src="jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
<script src="jquery/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="jquery/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<style>
<!--
.zebra-striped {
	background: #efefff 50% 50% repeat-x;
}
-->
</style>

<script language='javascript'>
	$(function() {
		$("#gridTable").jqGrid({
			url : 'userData.action',
			datatype : "json",
			autowidth : false,
			altRows : true,
			altclass : "zebra-striped",
			colNames : [ '登录名', '姓名', '性别', '邮箱' ],
			colModel : [ {
				name : 'loginName',
				index : 'loginName',
				width : 160,
				sorttype : "int"
			}, {
				name : 'realName',
				index : 'realName',
				width : 190
			}, {
				name : 'gender',
				index : 'gender',
				width : 190
			}, {
				name : 'email',
				index : 'email',
				width : 290
			} ],
			sortname : 'userId',
			sortorder : 'asc',
			viewrecords : true,
			rowNum : 20,
			rowList : [ 20, 30, 50, 100, 150 ],
			jsonReader : {
				repeatitems : false,
				id : "userId",
				total : "totalPageCount",
				page : "pageNumber",
				records : "totalRecordCount"
			},
			toolbar : [ true, "top" ],
			pager : "#gridPager",
			rownumbers : true,
			//multiselect: true, 
			pagerpos : "left"
		}).navGrid('#gridPager', {
			edit : false,
			add : false,
			del : false,
			refresh : false,
			search : false
		});

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
		$("#gridTable").jqGrid('setGridWidth', ss.WinW - 10).jqGrid(
				'setGridHeight', ss.WinH - 100);
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
	}

	//http://www.wsria.com/archives/1147有另一种算法
</script>
</head>
<body style="margin: 5px 5px">
	<table id="gridTable"></table>
	<div id="gridPager"></div>
</body>
</html>