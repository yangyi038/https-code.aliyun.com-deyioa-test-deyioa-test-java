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
 										支付交易号:<input type="text"  name="trade_no"    id="trade_no">
 									<button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
 										
 									 </div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listPaylist.action" autowidth="true"
									dourl="delPaylist.action" id="paylist" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','流水号','金额','支付方式名称','支付交易号','状态','生成流水时间'">
										<tgrid:jqcol name="id" index="id" hidden="true" />
										<tgrid:jqcol name="payno" index="payno" /> 
										<tgrid:jqcol name="amount" index="amount" /> 
										<tgrid:jqcol name="pay_name" index="pay_name"  />
										 <tgrid:jqcol name="trade_no" index="trade_no" />  
										<tgrid:jqcol name="tpaystate" index="tpaystate"  type="tpaystate"   formatter="select"/>  
										<tgrid:jqcol name="dotime" index="dotime"    />  

<%-- 										<tgrid:jqcol name="act" /> --%>
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
<%-- <e:yzact id="act">     --%>
<%-- <e:yzactbutton onclick="view" title="查看" />   --%>
<%-- <e:yzactbutton onclick="edit" title="修改" />   --%>
<%-- <e:yzactbutton onclick="del" title="删除" />   --%>
<%-- </e:yzact> --%>

  <script type="text/javascript"> 
  $("#add_payment").click(function(){
		jQuery().yzIframeDialog({id:"add_payment_m",iframeurl:"<%=basepath%>/admin/payment/preAddPayment.action",title:"新增支付方式"});
		$('#add_payment_m').modal('show');
	})  
  function edit(id){
		var rd =jQuery("#payment").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_payment_m",iframeurl:"<%=basepath%>/admin/payment/editPayment.action?pay_id="+rd['pay_id'],title:"修改支付方式"});
		$('#edit_payment_m').modal('show');
	}  
  function del(id){
		var rowData =jQuery("#payment").getRowData(id);
		if( id != null ) jQuery("#payment").jqGrid('delGridRow',rowData['pay_id'],{
			top:120,
			left:250,
			reloadAfterSubmit:true,
			jqModal: false,
			msg: "确认删除所选支付方式？",
			afterSubmit:function(response,postdata){
				var json = response.responseText;
				if(json!="")
					modalDialogAlert(json); 
				return [true];
				}
		});
		else modalDialogAlert("请先选中一行");
	}
  
  function search(){
		var grid = $("#paylist");
		var trade_no=$('#trade_no').val();
	    sdata = {trade_no:trade_no};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
