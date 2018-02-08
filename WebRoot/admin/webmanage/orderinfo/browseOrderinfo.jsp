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
订单号:<input name="order_sn" id="search_order_sn" /> 
用户名:<input name="username" id="search_username" /> 
订单状态:<select name="order_status" id="search_order_status" > 
			        			   <option value=""></option> 
			        			   <option value="0">待支付</option> 
			        			   <option value="1">已支付</option> 
			        			   <option value="2">已发货</option> 
			        			   <option value="3">已取消</option> 
			        			   <option value="4">已完成</option> 
			        			   <option value="5">已评价</option>  
			        			 </select>
			        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
										
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listOrderinfo.action" autowidth="true"
									dourl="delOrderinfo.action" id="orderinfolist" mtype="POST"
									search="false" export="true" pageid="orgippage" prmNames="{order_id:'order_id'}"
									sortname="order_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','订单号','用户名','付款时间','订单状态 ','商品总金额','实付金额','运费','支付方式','物流状态','操作'">
										<tgrid:jqcol name="order_id" index="order_id"  hidden="true" />
										<tgrid:jqcol name="order_sn" index="order_sn" /> 
										<tgrid:jqcol name="nickname" index="nickname" /> 
										<tgrid:jqcol name="addtime" index="addtime"  />
										 <tgrid:jqcol name="order_status" index="order_status"  type="order_status" formatter="select" />   
										 <tgrid:jqcol name="goods_amount" index="goods_amount" />   
										 <tgrid:jqcol name="order_amount" index="order_amount" />
										 <tgrid:jqcol name="shipping_fee" index="shipping_fee" />        
										 <tgrid:jqcol name="pay_name" index="pay_name" />         
										 <tgrid:jqcol name="shipping_status" index="shipping_status" type="shipping_status"  formatter="select"/>       
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
<e:yzactbutton onclick="fh" title="发货"   event="function (rowdata){
if(rowdata['order_status']=='1'){
		return true;
	}else{
		return false;
	}
}"/>     
<e:yzactbutton onclick="view" title="查看详情"    /> 
<e:yzactbutton onclick="viewWl" title="查看物流"    />    
</e:yzact>

  <script type="text/javascript"> 
  function view(id){
	  var rd =jQuery("#orderinfolist").getRowData(id);
		jQuery().yzIframeDialog({id:"view_orderinfo_m",iframeurl:"<%=basepath%>/admin/orderinfo/viewOrderinfo.action?order_id="+rd['order_id'],title:"查看订单详情"});
		$('#view_orderinfo_m').modal('show');
  }
  function viewWl(id){
	  var rd =jQuery("#orderinfolist").getRowData(id);
		jQuery().yzIframeDialog({id:"wl_orderinfo_m",iframeurl:"<%=basepath%>/admin/orderinfo/viewWlOrderinfo.action?order_id="+rd['order_id'],title:"查看物流详情"});
		$('#wl_orderinfo_m').modal('show');
  }
  function fh(id){
	  var rd =jQuery("#orderinfolist").getRowData(id);
		jQuery().yzIframeDialog({id:"fh_orderinfo_m",iframeurl:"<%=basepath%>/admin/orderinfo/fhOrderinfo.action?order_id="+rd['order_id'],title:"订单发货",width:200});
		$('#fh_orderinfo_m').modal('show');
  }
  function search(){
		var grid = $("#orderinfolist");
		var order_sn=$('#search_order_sn').val(); 
		var order_status=$('#search_order_status').val(); 
		var nickname=$('#search_username').val(); 
	    sdata = {order_sn:order_sn,order_status:order_status,nickname:nickname};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
   </script>
<%@include file="../../../common/admin_footer.jsp"%>
