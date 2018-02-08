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
				<tgrid:jqGrid url="list_recoveryShop.action" autowidth="true"
					dourl="delPicture.action" id="shopText" mtype="POST" search="true"
					export="false" pageid="orgippage" prmNames="{id:'id'}" 
					sortname="tswaccount_id" del="false" ondblClickRow="preview"
					rowList="20,30,40">
					<tgrid:jqGridHead
						headvalue="'编号','行业分类','分级','店铺名称', '主旺旺','SKU','日咨询量','月销量','现有客服','上新频次','活动频次','跟进信息','负责组','操作'">
						<tgrid:jqcol name="id" index="id" width="20" hidden="true" align="left"/>
						<tgrid:jqcol name="categoryid" index="categoryid" width="60" />
						<tgrid:jqcol name='importance' width='30' index='importance'/>
						
						<tgrid:jqcol name="name" index="name" width="90" align="left"/>
						<tgrid:jqcol name='alww' width='90' index='alww' align="left"/>
						<tgrid:jqcol name="sku" index="sku" width="40" align="left"/>
						
						<tgrid:jqcol name="volume" index="volume" width="40" align="left"/>
						<tgrid:jqcol name='monthsale' width='40' index='monthsale' align="left"/>
						<tgrid:jqcol name="customers" index="customers" width="40" align="left"/>
						<tgrid:jqcol name="newspeed" index="newspeed" width="60" align="left"/>
						
						<tgrid:jqcol name="promotions" index="promotions" width="60" align="left"/>
						<tgrid:jqcol name='follows' width='250' index='follows' align="left"/>
						<tgrid:jqcol name="groupid" index="groupid" width="40" align="center"/>
						<tgrid:jqcol name="act" width='70'/>
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="recovery" title="商铺恢复" />
</e:yzact>

<script type="text/javascript">

<%--
//控制表头字段显示
$(document).ready(function(){
	var len=$("#imageText").getGridParam("width");
	 //列的权限控制
	 if(<%=user.getAdmintype()%>==4){//经销商
		 $("#imageText").setGridParam().hideCol("companyname");
		 $("#imageText").setGridParam().hideCol("operatorname");
		 $("#imageText").setGridParam().hideCol("dealername");
	 }else if(<%=user.getAdmintype()%>==3){//二级运营商
		 $("#imageText").setGridParam().hideCol("companyname");
		 $("#imageText").setGridParam().hideCol("operatorname");
	 }else if(<%=user.getAdmintype()%>==1 || <%=user.getAdmintype()%>==2){//运营商，运营商管理员
		 $("#imageText").setGridParam().hideCol("companyname");
	 }else{//0，null： 系统管理员，全部显示
	 }
	 $("#imageText").setGridWidth(len).trigger("reloadGrid");
});
 --%>

// 商铺恢复
function recovery(id){
	var rowData =jQuery("#shopText").getRowData(id);
	 modalDialogconfim("recovery_shop_m","确定恢复？",[{name:"是",fn:function(){
	 	$.post('<%=basepath%>/admin/shop/recovery_shop.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("恢复成功");
					refreshGrid('shopText');
					closeDialog("recovery_shop_m");
				}else{
					modalDialogAlert("恢复失败");
					closeDialog("recovery_shop_m");
				}
			});
     }},{name:"否",fn:function(){
   		 closeDialog("recovery_shop_m");
     }}]);
}

</script>
<%@include file="../../../common/admin_footer.jsp"%>