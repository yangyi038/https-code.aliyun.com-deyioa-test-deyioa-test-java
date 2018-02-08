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
							<div class="searchboxInfo">
								<div class="row">
									<div class="col-xs-12">
										<div class="searchbox">
											<div class="row">
											<div class="col-xs-12"> 
											用户名:<input name="username" id="username" />
											 回访状态:
											 <select name="is_visit" id="is_visit" >
						        			<c:forEach items="${fns:getCodeMap(pageContext.request,'is_visit')}" var="item">
						        			   <option value="${item.key}">${item.value}</option>
						        			 </c:forEach>  
						        			 </select>  
											 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
								 				
												</div>
											</div>
											<div class="addRock"><i class="arrow icon-angle-up"></i></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listSug.action" autowidth="true"
									dourl="delSug.action" id="sug" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','留言时间','用户名','等级积分','留言内容','反馈平台','回访人','回访状态','操作'">
										<tgrid:jqcol name="ID" index="ID" hidden="true" />
										<tgrid:jqcol name="DOTIME" index="DOTIME" /> 
										<tgrid:jqcol name="USERNAME" index="USERNAME"   />
										<tgrid:jqcol name="RANK_POINTS" index="RANK_POINTS" />
										<tgrid:jqcol name="CONTENT" index="CONTENT" />
										<tgrid:jqcol name="STYPE" index="STYPE"   type="stype"  formatter="select"/>
										<tgrid:jqcol name="VISIT" index="VISIT" />
										<tgrid:jqcol name="IS_VISIT" index="IS_VISIT"  type="is_visit"  formatter="select"/>
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
<e:yzactbutton onclick="hf" title="已回访" />  
</e:yzact>

  <script type="text/javascript"> 
   function search(){
		var grid = $("#sug");
		var username=$('#username').val();
		var is_visit=$('#is_visit').val();    
	    sdata = {username:username,is_visit:is_visit};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
		
function hf(id){
	var rowData =jQuery("#sug").getRowData(id);
	 modalDialogconfim("confim_sug_hf","回访人:<input type='text' name='visit'   id='visit'/><br><br>回访备注:<input type='text' name='remarks'   id='remarks'/>",[{name:"提交",fn:function(){
		 var visit = $('#visit').val();
		 var remarks = $('#remarks').val();
		 if(visit==''){ 
				modalDialogAlert("回访人员姓名不能为空！");
				return false;
		 }
		 $.post('<%=basepath%>/admin/sug/Sughf.action',{id:rowData['ID'],is_visit:1,visit:visit,remarks:remarks},function(data){
				if(data.status==0){
					refreshGrid('sug');
					closeDialog("confim_sug_hf");
				}else{
					modalDialogAlert("通信错误");
				}
			});
     }},{name:"否",fn:function(){
    	 closeDialog("confim_sug_hf");
     }}]);
} 
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
