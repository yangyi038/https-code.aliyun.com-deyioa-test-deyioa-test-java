<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="admin_title"/></title>
    <link rel="stylesheet" type="text/css" media="screen"
          href="<%=basepath%>/css/jqgrid/ui.jqgrid.css"/>
        <link href="<%=basepath%>/css/loginStyle.css" rel="stylesheet" type="text/css" />
    <script src="<%=basepath%>/js/jqgrid/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script src="<%=basepath%>/js/jqgrid/jquery.jqGrid.min.js"
            type="text/javascript"></script>
                                           <script src="<%=basepath%>/js/datePick.js"
            type="text/javascript" charset="UTF-8"></script>
</head>
<body >
	<div class="seBox">
		<ul class="tit">
		
			<a href="javascript:void(0)" id="searchexpand" class="fold"></a>
			<li ><span class="dh">信息查询</span></li>
	</ul>
		<ul class="txt" id="searchtxt" style="display:none">
		<form id="searchform">
			<table>
			<COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'></COLGROUP>
			<tr>
				<th>操作类型：</th>
				<td><s:textfield name="operType" id="search_operType" /></td>
				<th>操作信息：</th>
				<td><s:textfield name="slogComment" id="search_slogComment" /></td>
				<th></th>
				<td></td>
			</tr> 
			
			</table>
		</form>	
			<div class="lineBtn"><input type="button"  value="重置" class="btn" id="valuereset"/><input type="button" name="label_submit" value="查 询" class="btn" id="label_submit"/></div>
			
		</ul>
		<ul class="clear"></ul>		
	</div>
	   <tgrid:jqGrid name="系统日志列表" url="admin/systemLog_systemLoglist.action" rowList="10,20,30" autowidth="true" 
                   id="systemLog" mtype="POST" search="true" export="false"
                  pageid="orgippage" prmNames="id" sortname="id" del="false" ondblClickRow="viewLog">
        <tgrid:jqGridHead headvalue="'主键id','操作用户','操作类型','操作时间'">
            <tgrid:jqcol name="id" index="id" hidden="true" />
            <tgrid:jqcol name="username" index="username" sopt="'eq','ne','cn'" width="40"/>
            <tgrid:jqcol name="operType" index="operType" sopt="'eq','ne','cn'" width="45"/>
            <tgrid:jqcol name="dotime" index="dotime" stype="date" width="50"/>
        </tgrid:jqGridHead>
    </tgrid:jqGrid>

<s:if test="hasActionMessages()">
	<e:msgdialog basepath="<%=basepath%>">
		<s:actionmessage/>
	</e:msgdialog>
</s:if>
  </body>
  <script type="text/javascript">
  jQuery("#searchexpand").click(function(){
		if(jQuery("#searchtxt").is(":hidden")){
			jQuery("#searchtxt").attr("style", "display: ");
		}else{
			jQuery("#searchtxt").attr("style", "display: none");
		}
		gridLayout("systemLog");
	});
  jQuery("#label_submit").click(function(){
		var grid = $("#sysuser");
		var operType=document.getElementById("search_operType").value;
		var slogComment=document.getElementById("search_slogComment").value;
		  sdata = {operType:operType,slogComment:slogComment};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
			return false;

		});
  jQuery("#valuereset").click(function(){
		jQuery("#searchform")[0].reset();

	}) 
		function viewLog(id){
		if( id != null ) {
			jQuery().yzIframeDialog({id:"systemLog_view_d",url:"<%=basepath%>/admin/systemLog_viewSystemLog.action?id="+id,title:"日志详情"});
		}
		else
			modalDialogAlert("请先选中一行");
		return false;

}
  </script>
</html>