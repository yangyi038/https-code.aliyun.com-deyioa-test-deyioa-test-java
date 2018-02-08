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
账号名:<input name="username" id="search_username" />
推荐人账号:<input name="tjrname" id="search_tjrname" />
所在小区:<input name="village" id="search_village" />
								 账号状态:
								 <select name="isclose" id="search_isclose" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'isclose')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
			        			  会员等级:
								 <select name="rank_id" id="search_rank_id" >
								 <option value=""></option>
			        			<c:forEach items="${hy}" var="item">
			        			   <option value="${item.RANK_ID}">${item.RANK_NAME}</option>
			        			 </c:forEach>  
			        			 </select>
			        			 排序方式:
								 <select name="order" id="search_order" >
								 <option value=""></option>
			        			  <option value="addtime">注册时间</option>
			        			    <option value="rank_id_s">会员等级</option>
			        			      <option value="pay_points">会员积分</option>
			        			 </select>
			        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
										
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listUser.action" autowidth="true"
									dourl="delUser.action" id="user" mtype="POST"
									search="false" export="true" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','注册时间','用户名','推荐人 ','会员等级','积分','所在小区','最后登入时间','状态','操作'">
										<tgrid:jqcol name="ID" index="ID" hidden="true" />
										<tgrid:jqcol name="ADDTIME" index="ADDTIME" /> 
										<tgrid:jqcol name="USERNAME" index="USERNAME" /> 
										<tgrid:jqcol name="TJRNAME" index="TJRNAME"  />
										 <tgrid:jqcol name="RANK_NAME" index="RANK_NAME" />   
										 <tgrid:jqcol name="RANK_POINTS" index="RANK_POINTS" />   
										 <tgrid:jqcol name="VILLAGE" index="VILLAGE" />   
										 <tgrid:jqcol name="LASTLOGIN" index="LASTLOGIN" />   
										 <tgrid:jqcol name="ISCLOSE" index="ISCLOSE"    type="isclose" formatter="select"/>   
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
  function edit(id){
		var rd =jQuery("#userrank").getRowData(id);
		jQuery().yzIframeDialog({id:"edit_userrank_m",iframeurl:"<%=basepath%>/admin/userrank/editUserRank.action?rank_id="+rd['rank_id'],title:"修改积分规则"});
		$('#edit_userrank_m').modal('show');
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
