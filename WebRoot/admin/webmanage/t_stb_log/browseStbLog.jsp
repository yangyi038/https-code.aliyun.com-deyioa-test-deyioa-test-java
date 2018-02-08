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
						<div>
							在线时间: <input id='startTime' name='startTime' cssClass='Wdate'
								value=''
								onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['','%y-%M-01','%y-%M-%ld']
		    		})" />
							~ <input id='endTime' name='endTime' cssClass='Wdate'
								value=''
								onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['','%y-%M-01','%y-%M-%ld']
		    		})" />
						</div>
						
						<br> 流播放状态:<select name="streamstatus" id="search_streamstatus">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'streamstatus')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; 
						终端类型:<select name="terminaltype"
							id="search_terminaltype">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'terminaltype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; 
						心跳状态:<select name="onlinestatus"
							id="search_onlinestatus">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'onlinestatus')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
						</select> 
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; 
						<input name="content" id="search_content"
							style="height: 32px; width: 280px"
							placeholder="可根据业务账号和token进行搜索" />
						<button class="btn btn-info btn-xs" onclick="search()"
							style="width: 60px; height: 32px;">搜索</button>

					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listT_stb_log.action" autowidth="true"
					dourl="delStbLog.action" id="t_stb_loglist" mtype="POST"
					search="false" export="true" pageid="t_stb_logpage"
					prmNames="{id:'stbtoken'}" sortname="stbtoken" del="false"
					ondblClickRow="preview" rowList="15,30,45" multiselect="true"  >
					<tgrid:jqGridHead
						headvalue=" '业务账号' ,'运营商', 'stbtoken' ,'终端类型','流播放状态','心跳状态',
						'上次心跳时间','LANIP','WANIP','STBID','MAC','WIFIMAC','登陆时间','在线时长（秒）',
						'APP版本','ROM版本','ROM固件版本','APP类型' "> <!-- ,'操作' -->
						
						<tgrid:jqcol name='stbnum'  index='stbnum' />
						<tgrid:jqcol name='companyid'   index='companyid' />
						<tgrid:jqcol name='stbtoken'   index='stbtoken' />
						<tgrid:jqcol name='terminaltype'   index='terminaltype' hidden='false' type="terminaltype"/>
						<tgrid:jqcol name='streamstatus'   index='streamstatus' hidden='false' type="streamstatus"/>
						<tgrid:jqcol name='onlinestatus'   index='onlinestatus' hidden='false' type="onlinestatus" />
						<tgrid:jqcol name='lastonlinetimeStr'   index='lastonlinetimeStr' hidden='false' />
						<tgrid:jqcol name='lanip'   index='lanip' hidden='false' />
						<tgrid:jqcol name='wanip'   index='wanip' hidden='false' />
						<tgrid:jqcol name='stbid'   index='stbid' hidden='false' />
						<tgrid:jqcol name='mac'   index='mac' hidden='false' />
						<tgrid:jqcol name='wifimac'   index='wifimac' hidden='false' />
						<tgrid:jqcol name='logintimeStr'   index='logintimeStr' hidden='false' />
						<tgrid:jqcol name='onlinetime'   index='onlinetime' hidden='false' />
						<tgrid:jqcol name='apkversion'   index='apkversion' hidden='false' />
						<tgrid:jqcol name='romversion'   index='romversion' hidden='false' />
						<tgrid:jqcol name='romfirmware'   index='romfirmware' hidden='false' />
						<tgrid:jqcol name='apptype'   index='apptype' hidden='false' />

						<%-- <tgrid:jqcol name="act" /> --%>
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<%-- <e:yzact id="act">
	<e:yzactbutton onclick="view" title="查看" />
	<e:yzactbutton onclick="del" title="删除" />
</e:yzact> --%>
<script type="text/javascript">

//加载机顶盒列表
function loadComplete(x){
	 jQuery("#t_stb_loglist").jqGrid("setGridHeight",26*$("#t_stb_loglist").jqGrid('getGridParam','rowNum')); 
}

//查看
function view(stbtoken){
	var rd =jQuery("#t_stb_loglist").getRowData(stbtoken);
	jQuery().yzIframeDialog({id:"edit_t_stb_log_m",iframeurl:"<%=basepath%>/admin/t_stb_log/getStbLog.action?sid="
									+ rd['stbtoken'],
							title : "查看终端日志信息"
						});
		$('#edit_t_stb_log_m').modal('show');
	}

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
		var grid = $("#t_stb_loglist");
		var content = $("#search_content").val();
		var terminaltype=$("#search_terminaltype").val();
		var streamstatus = $("#search_streamstatus").val();
		var onlinestatus = $("#search_onlinestatus").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		
		sdata = {
			content : content,
			terminaltype : terminaltype,
			streamstatus : streamstatus,
			onlinestatus : onlinestatus,
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
