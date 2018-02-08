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
										  是否回复:
								 <select name="is_hf" id="is_hf" >
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'is_hf')}" var="item">
			        			   <option value="${item.key}">${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
			        			  评论星级:
								 <select name="comment_rank" id="comment_rank" > 
			        			   <option value=""></option> 
			        			   <option value="1">一星</option> 
			        			   <option value="2">二星</option> 
			        			   <option value="3">三星</option> 
			        			   <option value="4">四星</option> 
			        			   <option value="5">五星</option> 
			        			 </select>
			        			  <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listComment.action" autowidth="true"
									dourl="delComment.action" id="comment" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{comment_id:'comment_id'}"
									sortname="comment_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','用户名','评论内容','评论时间','评论星级','是否通过审核','回复内容','是否已回复','操作'">
										<tgrid:jqcol name="comment_id" index="comment_id" hidden="true" />
										 <tgrid:jqcol name="nickname" index="nickname" /> 
										 <tgrid:jqcol name="content" index="content" /> 
										<tgrid:jqcol name="add_time" index="add_time" /> 
										<tgrid:jqcol name="comment_rank" index="comment_rank" /> 
										<tgrid:jqcol name="comment_status" index="comment_status"  type="comment_status"  formatter="select"/>
										<tgrid:jqcol name="kf_content" index="kf_content"  />
										<tgrid:jqcol name="is_hf" index="is_hf"  type="is_hf"  formatter="select"/>
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
<e:yzactbutton onclick="commentsh" title="是否推送贝粉秀"  event="function (rowdata){
if(rowdata['comment_status']=='3'){
		return true;
	}else{
		return false;
	}
}"/>   
<e:yzactbutton onclick="commenthf" title="回复"  />  
</e:yzact>

<script type="text/javascript"> 
function search(){
	var grid = $("#comment");
	var is_hf=$('#is_hf').val();
	var comment_rank=$('#comment_rank').val();    
    sdata = {is_hf:is_hf,comment_rank:comment_rank};
		$.extend(grid[0].p.postData,sdata);
		grid.trigger("reloadGrid",[{page:1}]);
	} 
function commenthf(id){
	var rowData =jQuery("#comment").getRowData(id);
	 modalDialogconfim("confim_comment_commentsh","回复：<input  type='text'  name='kf_content' id='kf_content'>",[{name:"提交",fn:function(){
		var kf_content = $('#kf_content').val();
		if(kf_content==null||kf_content==''){
			modalDialogAlert("回复不能为空!");
			return false;
		}
		 $.post('<%=basepath%>/admin/comment/Commenthf.action',{kf_content:kf_content,comment_id:rowData['comment_id']},function(data){
				if(data.status==0){
					refreshGrid('comment');
					closeDialog("confim_comment_commentsh");
				}else{
					modalDialogAlert("通信错误");
				}
			});
     }},{name:"取消",fn:function(){
    	 closeDialog("confim_comment_commentsh");
     }}]);
} 
function commentsh(id){
	var rowData =jQuery("#comment").getRowData(id);
	 modalDialogconfim("confim_comment_commentsh","确定同意该评论通过审核？",[{name:"同意",fn:function(){
		 $.post('<%=basepath%>/admin/comment/commentsh.action',{comment_id:rowData['comment_id'],comment_status:1},function(data){
				if(data.status==0){
					refreshGrid('comment');
					closeDialog("confim_comment_commentsh");
				}else{
					modalDialogAlert("通信错误");
				}
			});
     }},{name:"不同意",fn:function(){
    	 closeDialog("confim_comment_commentsh");
     }}]);
} 
</script>
<%@include file="../../../common/admin_footer.jsp"%>
