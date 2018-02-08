<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css" />
<script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js"
	type="text/javascript"></script>

<div class="frame-container">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
							操作时间: <input id='startTime' name='startTime' cssClass='Wdate' style="height: 32px; width: 150px"
								value=''
								onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['','%y-%M-01','%y-%M-%ld']
		    		})" />
							~ <input id='endTime' name='endTime' cssClass='Wdate' value='' style="height: 32px; width: 150px"
								onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['','%y-%M-01','%y-%M-%ld']
		    		})" />

						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						<div style="float: right; margin-right: 200px;">
							<input name="content" id="search_content"
								style="height: 32px; width: 250px"
								placeholder="可根据用户名进行模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="list_sysLog.action" autowidth="true"
					dourl="delPicture.action" id="t_sysloglist" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}"
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="20,30,45">
					<tgrid:jqGridHead
						headvalue="'id','时间','用户名','操作对象','操作类型','处理结果','操作描述'">

						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="dotime" index="dotime" width="30" />
						<tgrid:jqcol name="username" index="username" width="30" />
						<tgrid:jqcol name="operObject" index="operObject" width="20" />
						<tgrid:jqcol name="operType" index="operType" width="30" />
						<tgrid:jqcol name="operResult" index="operResult" width="20" />
						<tgrid:jqcol name="operDes" index="operDes" width="80" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript"> 
//搜索
function search() {
	var grid = $("#t_sysloglist");
	var content = $("#search_content").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	sdata = {
		content : content,
		startTime : startTime,
		endTime : endTime
	};
	$.extend(grid[0].p.postData, sdata);
	grid.trigger("reloadGrid", [ {
		page : 1
	} ]);
}

</script>

<%@include file="../../../common/admin_footer.jsp"%>
