<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
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
昵称:<input name="nickname" id="search_nickname" /> 
			        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
										
									</div>
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listTswaccount.action" autowidth="true"
									dourl="delTswaccount.action" id="tswaccount" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{tswaccount_id:'tswaccount_id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'头像','openid','昵称','性别','是否关注','关注时间'">
									<tgrid:jqcol name="pic" index="pic" width="50"/>
										<tgrid:jqcol name="openid" index="openid" width="200"/>
										 <tgrid:jqcol name="nickname" index="nickname" width="100"/> 
										 <tgrid:jqcol name="sex" index="sex" type="sex"  formatter="select" width="50"/> 
										<tgrid:jqcol name="subscribe" index="subscribe" type="subscribe"  formatter="select" width="50"/> 
										<tgrid:jqcol name="addtime" index="addtime" width="100"/> 
<%-- 								  		<tgrid:jqcol name="act" /> --%>

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

<%-- </e:yzact> --%>

<script type="text/javascript"> 
function search(){
	var grid = $("#tswaccount");
	var nickname=$('#search_nickname').val();
    sdata = {nickname:nickname};
	$.extend(grid[0].p.postData,sdata);
	grid.trigger("reloadGrid",[{page:1}]);
} 

</script>
<%@include file="../../../common/admin_footer.jsp"%>
