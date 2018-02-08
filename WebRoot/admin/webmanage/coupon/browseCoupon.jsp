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
										<e:yzbutton   id="add_coupon"  name="新增"  cssClass="btn btn-success"/>
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listCoupon.action" autowidth="true"
									dourl="delCoupon.action" id="coupon" mtype="POST"
									search="false" export="true" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','优惠券名称','优惠券类型','开始时间','结束时间 ','发行量','已领','领取率','操作'">
										<tgrid:jqcol name="id" index="id" hidden="true" />
										<tgrid:jqcol name="cou_name" index="cou_name" /> 
										<tgrid:jqcol name="ctype" index="ctype" type="ctype" formatter="select" /> 
										<tgrid:jqcol name="use_start_date" index="use_start_date" /> 
										<tgrid:jqcol name="use_end_date" index="use_end_date"  />
										 <tgrid:jqcol name="issuenum" index="issuenum" />    
										 <tgrid:jqcol name="receiveds" index="receiveds" />    
										 <tgrid:jqcol name="lql" index="lql" />    

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
<e:yzactbutton onclick="edit" title="修改"    />     
<e:yzactbutton onclick="del" title="删除"    />     
</e:yzact>

  <script type="text/javascript"> 
  function search(){
		var grid = $("#user");
		var username=$('#search_username').val();
		var tjrname=$('#search_tjrname').val();   
		var village=$('#search_village').val();   
		var isclose=$('#search_isclose').val();   
		var rank_id=$('#search_rank_id').val();   
		var order=$('#search_order').val();   
	    sdata = {username:username,tjrname:tjrname,village:village,isclose:isclose,rank_id:rank_id,order:order};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
  $("#add_coupon").click(function(){
		jQuery().yzIframeDialog({id:"add_coupon_m",iframeurl:"<%=basepath%>/admin/coupon/preAddCoupon.action",title:"新增优惠券"});
		$('#add_coupon_m').modal('show');
	}) 
  function edit(id){
		var rd =jQuery("#coupon").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_coupon_m",iframeurl:"<%=basepath%>/admin/coupon/editCoupon.action?id="+rd['id'],title:"修改优惠券"});
		$('#edit_coupon_m').modal('show');
	}   
  function del(id){
		var rowData =jQuery("#coupon").getRowData(id);
		if( id != null ) jQuery("#coupon").jqGrid('delGridRow',rowData['id'],{
			top:120,
			left:250,
			reloadAfterSubmit:true,
			jqModal: false,
			msg: "确认删除所选优惠券？",
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
