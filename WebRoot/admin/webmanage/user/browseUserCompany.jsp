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
							  
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listUserCompany.action" autowidth="true"
									dourl="delUser.action" id="user" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','注册时间','公司名称','人数','联系人','职位','联系方式','会员等级','公司地址','最后登入时间','状态','备注','操作'">
										<tgrid:jqcol name="ID" index="ID" hidden="true" />
										<tgrid:jqcol name="ADDTIME" index="ADDTIME" /> 
										<tgrid:jqcol name="CNAME" index="CNAME" /> 
										<tgrid:jqcol name="PERNUM" index="PERNUM" /> 
										<tgrid:jqcol name="CONPER" index="CONPER" /> 
										<tgrid:jqcol name="DEPNAME" index="DEPNAME" /> 
										<tgrid:jqcol name="CONTEL" index="CONTEL" /> 
										 <tgrid:jqcol name="RANK_NAME" index="RANK_NAME" />    
										 <tgrid:jqcol name="ADDRESS" index="ADDRESS" />    
										 <tgrid:jqcol name="LASTLOGIN" index="LASTLOGIN" />   
										 <tgrid:jqcol name="ISCLOSE" index="ISCLOSE"    type="isclose" formatter="select"/>   
										 <tgrid:jqcol name="REMARKS" index="REMARKS" />   
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
<e:yzactbutton onclick="KT" title="开通"   event="function (rowdata){
if(rowdata['ISCLOSE']=='2'){
		return true;
	}else{
		return false;
	}
}"/>   
<e:yzactbutton onclick="ZX" title="注销"   event="function (rowdata){
if(rowdata['ISCLOSE']=='1'){
		return true;
	}else{
		return false;
	}
}"/>     
<e:yzactbutton onclick="VIP" title="提升会员等级"    />     
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
  function VIP(id){
		var rd =jQuery("#user").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_vip_m",iframeurl:"<%=basepath%>/admin/user/editVIP.action?id="+rd['ID'],title:"修改会员等级"});
		$('#edit_vip_m').modal('show');
	}  
  function KT(id){
		var rowData =jQuery("#user").getRowData(id);
		 modalDialogconfim("confim_user_KT","确定开通该用户？",[{name:"是",fn:function(){
			 
			 $.post('<%=basepath%>/admin/user/updateUsertype.action',{id:rowData['ID'],isclose:1},function(data){
					if(data.status==0){
						refreshGrid('user');
						closeDialog("confim_user_KT");
					}else{
						modalDialogAlert(data.info);
					}
				});
	     }},{name:"否",fn:function(){
	    	 closeDialog("confim_user_KT");
	     }}]);
	}
  function ZX(id){
		var rowData =jQuery("#user").getRowData(id);
		 modalDialogconfim("confim_user_ZX","确定注销该用户？",[{name:"是",fn:function(){
			 
			 $.post('<%=basepath%>/admin/user/updateUsertype.action',{id:rowData['ID'],isclose:2},function(data){
					if(data.status==0){
						refreshGrid('user');
						closeDialog("confim_user_ZX");
					}else{
						modalDialogAlert(data.info);
					}
				});
	     }},{name:"否",fn:function(){
	    	 closeDialog("confim_user_ZX");
	     }}]);
	}
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
