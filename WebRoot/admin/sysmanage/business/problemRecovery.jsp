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
<style>
.ui-jqgrid tr.jqgrow td {
	white-space: normal !important;
	height:auto;
	vertical-align:text-top;
	padding-top:2px;
}
</style>
<%
	//获取当前登录用户
	Sysuser user = ContextHolderUtils.getLoginUser();
%>

<div class="frame-container" onload="">
	<div class="page-content">
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="list_problemRecovery.action" autowidth="true"
					dourl="delPicture.action" id="problemRecoveryText" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}" 
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="20,30,40" multiselect="true" multiboxonly="true"
					beforeSelectRow="beforeSelectRow">
					<tgrid:jqGridHead
						headvalue="'编号','问题分类','行业包/自定义','问题', '衍生问题','答案','答案id','关联产品','推荐产品','建议/备注','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" align="left"/>
						<tgrid:jqcol name="qcate" index="qcate" width="50" />
						<tgrid:jqcol name='industry' width='60' index='industry'/>
						<tgrid:jqcol name="question" index="question" width="230" align="left"/>
						<tgrid:jqcol name='derive' width='210' index='derive' align="left"/>
						<tgrid:jqcol name="answer" index="answer" width="120" align="left"/>
						
						<tgrid:jqcol name="answerid" index="answerid" width="20" hidden="true" align="left"/>
						<tgrid:jqcol name="relationname" index="relationname" width="70" align="left"/>
						<tgrid:jqcol name='recommend' width='70' index='recommend' align="left"/>
						<tgrid:jqcol name="suggest" index="suggest" width="60" align="left"/>
						<tgrid:jqcol name="act" width='80'/>
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="problemRecovery" title="问题恢复"/>
	<e:yzactbutton onclick="answerRecovery" title="答案恢复"/>
</e:yzact>

<script type="text/javascript">

//列表单选
function beforeSelectRow() {
	$("#problemText").jqGrid('resetSelection');
	return (true);
}

// 恢復问题
function problemRecovery(id){
	var rowData =jQuery("#problemRecoveryText").getRowData(id);
	 modalDialogconfim("recovery_problem_m","确定恢复问题？",[{name:"是",fn:function(){
	 	$.post('<%=basepath%>/admin/problem/recovery_problem.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("恢复成功");
					refreshGrid('problemRecoveryText');
					closeDialog("recovery_problem_m");
				}else{
					modalDialogAlert("恢复失败");
					closeDialog("recovery_problem_m");
				}
			});
     }},{name:"否",fn:function(){
   		 closeDialog("recovery_problem_m");
     }}]);
}

//恢復答案
function answerRecovery(id){
	var rowData =jQuery("#problemRecoveryText").getRowData(id);
	 modalDialogconfim("recovery_answer_m","确定恢复答案？",[{name:"是",fn:function(){
	 	$.post('<%=basepath%>/admin/problem/recovery_answer.action',{id:rowData['answerid'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("恢复成功");
					refreshGrid('problemRecoveryText');
					closeDialog("recovery_answer_m");
				}else{
					modalDialogAlert("恢复失败");
					closeDialog("recovery_answer_m");
				}
			});
     }},{name:"否",fn:function(){
   		 closeDialog("recovery_answer_m");
     }}]);
}

</script>
<%@include file="../../../common/admin_footer.jsp"%>