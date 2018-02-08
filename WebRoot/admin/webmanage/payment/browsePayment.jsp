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
													<e:yzbutton   id="add_payment"  name="新增"  cssClass="btn btn-success"/>
												 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listPayment.action" autowidth="true"
									dourl="delPayment.action" id="payment" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{pay_id:'pay_id'}"
									sortname="pay_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','支付方式名称','支付费用','支付方式描述','支付方式在页面的显示顺序','是否可用','是否货到付款','是否在线支付','支付方式代码','操作'">
										<tgrid:jqcol name="pay_id" index="pay_id" hidden="true" />
										<tgrid:jqcol name="pay_name" index="pay_name" /> 
										<tgrid:jqcol name="pay_fee" index="pay_fee" /> 
										<tgrid:jqcol name="pay_desc" index="pay_desc"  />
										 <tgrid:jqcol name="pay_order" index="pay_order" />  
										<tgrid:jqcol name="enabled" index="enabled"  type="pay_enabled"   formatter="select"/>  
										<tgrid:jqcol name="is_cod" index="is_cod"   type="is_cod"  formatter="select" />  
										<tgrid:jqcol name="is_online" index="is_online"   type="is_online"  formatter="select" />  
										<tgrid:jqcol name="pay_code" index="pay_code" />  
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
<e:yzact id="act">    <%-- 
<e:yzactbutton onclick="view" title="查看" />   --%>
<e:yzactbutton onclick="edit" title="修改" />  
<e:yzactbutton onclick="del" title="删除" />  
</e:yzact>

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
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
