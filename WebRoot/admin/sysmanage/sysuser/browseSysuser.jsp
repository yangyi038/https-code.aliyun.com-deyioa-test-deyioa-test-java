<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
 <link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css"/>
    <script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>	
   <script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>	
		<div class="frame-container">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="yz-frame-head">
							<div class="searchbox">
								<div class="row">
									<div class="col-xs-12">
										<e:yzbutton   id="add_sysuser"  name="新增"  cssClass="btn btn-success" power="3901"/>
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listSysuser.action" autowidth="true"
									dourl="delSysuser.action" id="sysuser" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview">
									<tgrid:jqGridHead headvalue="'主键id','登录名','所属角色','所属组织机构','状态','操作'"><!-- '所属运营商', -->
										<tgrid:jqcol name="id" index="id" hidden="true" />
										<%-- <tgrid:jqcol name="companyname" index="companyname" /> --%>
										<tgrid:jqcol name="loginname" index="loginname" />
										<tgrid:jqcol name="sysrole.rolename" index="rolename" />
										<tgrid:jqcol name="department.depname" index="depname" />
										<tgrid:jqcol name="isclose" index="isclose" type="isclose" formatter="select"/>
										<tgrid:jqcol name="act" /> 
									</tgrid:jqGridHead>
								</tgrid:jqGrid>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<e:yzact id="act">   
<e:yzactbutton onclick="xg" title="修改" power="3903"/> 
<e:yzactbutton onclick="logoff" title="注销"  event="function (rowdata){
if(rowdata['isclose']=='1'){
		return true;
	}else{
		return false;
	}
}"/> 
<e:yzactbutton onclick="logono" title="启用"  event="function (rowdata){
if(rowdata['isclose']=='2'){
		return true;
	}else{
		return false;
	}
}"/> 
<e:yzactbutton onclick="del" title="删除"  power="3902"/>
</e:yzact>

<script type="text/javascript">
$("#add_sysuser").click(function(){
	jQuery().yzIframeDialog({id:"add_sysuser_m",iframeurl:"<%=basepath%>/admin/sysuser/preAddSysuser.action",title:"新增系统管理员"});
	$('#add_sysuser_m').modal('show');
})
function xg(id){
	var rd =jQuery("#sysuser").getRowData(id);
	jQuery().yzIframeDialog({id:"edit_sysuser_m",iframeurl:"<%=basepath%>/admin/sysuser/editSysuser.action?id="+rd['id'],title:"编辑系统管理员"});
	$('#edit_sysuser_m').modal('show');
}
function del(id){
	var rowData =jQuery("#sysuser").getRowData(id);
	if( id != null ) jQuery("#sysuser").jqGrid('delGridRow',rowData['id'],{
		top:120,
		left:250,
		reloadAfterSubmit:true,
		jqModal: false,
		msg: "确认删除所选用户【"+rowData['loginname']+"】？",
		afterSubmit:function(response,postdata){
			var json = response.responseText;
			if(json!="")
				modalDialogAlert(json); 
			return [true];
			}
	});
	else modalDialogAlert("请先选中一行");
}
function logoff(id){
	var rowData =jQuery("#sysuser").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定注销该用户？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/sysuser/logofforno.action',{id:rowData['id'],isclose:2},function(data){
				if(data.status==0){
					refreshGrid('sysuser');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("通信错误");
				}
			});
     }},{name:"否",fn:function(){
    	 closeDialog("confim_sysuser_logoff");
     }}]);
}
function logono(id){
	var rowData =jQuery("#sysuser").getRowData(id);
	 modalDialogconfim("confim_sysuser_logono","确定启动该用户？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/sysuser/logofforno.action',{id:rowData['id'],isclose:1},function(data){
				if(data.status==0){
					refreshGrid('sysuser');
					closeDialog("confim_sysuser_logono");
				}else{
					modalDialogAlert(data.info);
				}
			});
     }},{name:"否",fn:function(){
    	 closeDialog("confim_sysuser_logono");
     }}]);
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
