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
												  发票状态:
								 <select name="isinv" id="isinv" >
			        			   <option value="3">全部</option>
			        			   <option value="0">未开票</option>
			        			   <option value="1">已开票</option>
			        			  </select>
														        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
												 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listInvoice.action" autowidth="true"
									dourl="delInvoice.action" id="invoicelist" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{pay_id:'pay_id'}"
									sortname="pay_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'订单编号','发票抬头','发票内容','发票金额','发票状态','用户ID','订单ID','操作'">
										<tgrid:jqcol name="order_sn" index="order_sn"  />
										<tgrid:jqcol name="inv_payee" index="inv_payee" /> 
										<tgrid:jqcol name="inv_content" index="inv_content" /> 
										<tgrid:jqcol name="order_amount" index="order_amount"  /> 
										<tgrid:jqcol name="isinv" index="isinv"  type="isinv"  formatter="select" /> 
										<tgrid:jqcol name="openid" index="openid"  hidden="true"/> 
										<tgrid:jqcol name="order_id" index="order_id"  hidden="true"/> 
										
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
<e:yzactbutton onclick="kp" title="已开票" event="function (rowdata){
if(rowdata['isinv']=='0'){
		return true;
	}else{
		return false;
	}
}"/>     
</e:yzact>

  <script type="text/javascript">  
  function search(){
		var grid = $("#invoicelist");
		var isinv=$('#isinv').val(); 
	    sdata = {isinv:isinv};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
  
  function kp(id){
		var rowData =jQuery("#invoicelist").getRowData(id);
		 modalDialogconfim("confim_user_KT","开票时间<input type='text' onclick='WdatePicker()' name='invtime' id='invtime'><br>开票备注<input type='text' name='remark'  id='remark' >",[{name:"是",fn:function(){
			 var invtime=$('#invtime').val();
			 if(invtime==''){
				 modalDialogAlert("开票时间不能为空");
			 } var remark=$('#remark').val();
			 $.post('<%=basepath%>/admin/invoice/addInvoice.action',{order_sn:rowData['order_sn'],isinv:1,inv_payee:rowData['inv_payee'],inv_content:rowData['inv_content'],order_amount:rowData['order_amount'],order_id:rowData['order_id'],invtime:invtime,remark:remark,openid:rowData['openid']},function(data){
					if(data.status==0){
						refreshGrid('invoicelist');
						closeDialog("confim_user_KT");
					}else{
						modalDialogAlert(data.info);
					}
				});
	     }},{name:"否",fn:function(){
	    	 closeDialog("confim_user_KT");
	     }}]);
	}
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
