<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../common/admin_head.jsp"%>
 <link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css"/>
    <script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>	
   <script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>	
   <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"> </script>
   
		<div class="frame-container">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<div class="row">
					<div class="col-xs-12">
						<div class="yz-frame-head">
							<div class="searchbox">
								<div class="row">
									<div class="col-xs-12">  
退货单号：<input type="text"   name="delivery_sn"   id="delivery_sn"> 
订单号：<input type="text"   name="order_sn"   id="order_sn"> 
用户名：<input type="text"   name="username"   id="username"> 

														        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
												 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listBackOrder.action" autowidth="true"
									dourl="delBackOrder.action" id="backorderlist" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{back_id:'back_id'}"
									sortname="back_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'ID','退货单号','订单号','用户名','退货留言','退换货状态','退换货类型','操作'">
										<tgrid:jqcol name="BACK_ID" index="BACK_ID"   hidden="true" />
										<tgrid:jqcol name="DELIVERY_SN" index="DELIVERY_SN" /> 
										<tgrid:jqcol name="ORDER_SN" index="ORDER_SN" /> 
										<tgrid:jqcol name="USERNAME" index="USERNAME"  /> 
										<tgrid:jqcol name="POSTSCRIPT" index="POSTSCRIPT"    /> 
										<tgrid:jqcol name="STATUS" index="STATUS"  type="back_status"  formatter="select"/>   
										<tgrid:jqcol name="BACKTYPE" index="BACKTYPE"     type="backtype"  formatter="select"/>   
										<tgrid:jqcol name="act" />
									</tgrid:jqGridHead>
								</tgrid:jqGrid> 
							</div>
						</div>
					</div>
				</div>
					</div>
				</div>

			</div>
		</div>
<e:yzact id="act">
<e:yzactbutton onclick="cl" title="状态处理" event="function (rowdata){
if(rowdata['STATUS']!='10'){
		return true;
	}else{
		return false;
	}
}"/>     
<e:yzactbutton onclick="view" title="详情"  />     
</e:yzact>
  
  <script type="text/javascript">  
  function cl(id){
	  var rd =jQuery("#backorderlist").getRowData(id);
		jQuery().yzIframeDialog({id:"cl_orderinfo_m",iframeurl:"<%=basepath%>/admin/backorder/ClBackOrder.action?back_id="+rd['BACK_ID'],title:"查看退货/退款订单详情",width:200});
		$('#cl_orderinfo_m').modal('show');
  } 
   
  function view(id){
	  var rd =jQuery("#backorderlist").getRowData(id);
		jQuery().yzIframeDialog({id:"view_orderinfo_m",iframeurl:"<%=basepath%>/admin/backorder/viewBackOrder.action?back_id="+rd['BACK_ID'],title:"查看退货/退款订单详情"});
		$('#view_orderinfo_m').modal('show');
  } 

  function pay(id){
	  var rd =jQuery("#backorderlist").getRowData(id);
		jQuery().yzIframeDialog({id:"dk_orderinfo_m",iframeurl:"<%=basepath%>/admin/backorder/DkBackOrder.action?back_id="+rd['BACK_ID'],title:"查看退货/退款订单详情"});
		$('#dk_orderinfo_m').modal('show');
  } 
  function search(){
		var grid = $("#backorderlist");
		var delivery_sn=$('#delivery_sn').val(); 
		var order_sn=$('#order_sn').val(); 
		var username=$('#username').val(); 
	    sdata = {delivery_sn:delivery_sn,order_sn:order_sn,username:username};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
 </script>
<%@include file="../../../common/admin_footer.jsp"%>
