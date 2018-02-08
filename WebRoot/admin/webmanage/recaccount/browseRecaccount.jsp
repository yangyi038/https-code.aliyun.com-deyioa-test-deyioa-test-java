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
订单号:<input name="orderno" id="orderno" />  
用户名:<input name="username" id="username" /> 
打款账号:<input name="toaccount" id="toaccount" /> 
打款姓名:<input name="toname" id="toname" /> 
			        			 
			        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
										
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listRecaccount.action" autowidth="true"
									dourl="delRecaccount.action" id="recaccountlist" mtype="POST"
									search="false" export="true" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','订单号','完成时间','申请人 ','开户银行','姓名','账号','金额','操作员备注','状态'">
										<tgrid:jqcol name="ID" index="ID" hidden="true" />
										<tgrid:jqcol name="ORDERNO" index="ORDERNO" /> 
										<tgrid:jqcol name="DOTIME" index="DOTIME" /> 
										<tgrid:jqcol name="USERNAME" index="USERNAME	"  /> 
										<tgrid:jqcol name="BANKNAME" index="BANKNAME"  /> 
										 <tgrid:jqcol name="TONAME" index="TONAME" />   
										 <tgrid:jqcol name="TOACCOUNT" index="TOACCOUNT" />    
										 <tgrid:jqcol name="AMOUNT" index="AMOUNT"    />   
										 <tgrid:jqcol name="ADMIN_REMARK" index="ADMIN_REMARK"    />   
										 
										 <tgrid:jqcol name="PAYSTATUS" index="PAYSTATUS"  type="rec_paystatus"   formatter="select"/>   
								
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

  <script type="text/javascript"> 
  function search(){
		var grid = $("#recaccountlist");
		var orderno=$('#orderno').val();
		var username=$('#username').val();
		var toaccount=$('#toaccount').val();
		var toname=$('#toname').val(); 
	    sdata = { orderno:orderno,username:username,toaccount:toaccount,toname:toname};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		} 
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
