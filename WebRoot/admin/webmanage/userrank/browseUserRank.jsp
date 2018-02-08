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
													<e:yzbutton   id="add_userrank"  name="新增"  cssClass="btn btn-success"/>
								 
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listUserRank.action" autowidth="true"
									dourl="delUserRank.action" id="userrank" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{rank_id:'rank_id'}"
									sortname="rank_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','等级名称','最低积分','最高积分 ','商品折扣','是否显示','等级类型','操作'">
										<tgrid:jqcol name="rank_id" index="rank_id" hidden="true" />
										<tgrid:jqcol name="rank_name" index="rank_name" /> 
										<tgrid:jqcol name="min_points" index="min_points" /> 
										<tgrid:jqcol name="max_points" index="max_points"  />
										 <tgrid:jqcol name="discount" index="discount" />  
										<tgrid:jqcol name="show_price" index="show_price"  type="show_price"   formatter="select"/>  
										<tgrid:jqcol name="usertype" index="usertype"   type="usertype"  formatter="select" />    
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
  $("#add_userrank").click(function(){
		jQuery().yzIframeDialog({id:"add_userrank_m",iframeurl:"<%=basepath%>/admin/userrank/preAddUserRank.action",title:"新增积分规则"});
		$('#add_userrank_m').modal('show');
	})  
  function edit(id){
		var rd =jQuery("#userrank").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_userrank_m",iframeurl:"<%=basepath%>/admin/userrank/editUserRank.action?rank_id="+rd['rank_id'],title:"修改积分规则"});
		$('#edit_userrank_m').modal('show');
	}  
  function del(id){
		var rowData =jQuery("#userrank").getRowData(id);
		if( id != null ) jQuery("#userrank").jqGrid('delGridRow',rowData['rank_id'],{
			top:120,
			left:250,
			reloadAfterSubmit:true,
			jqModal: false,
			msg: "确认删除所选会员积分规则？",
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
