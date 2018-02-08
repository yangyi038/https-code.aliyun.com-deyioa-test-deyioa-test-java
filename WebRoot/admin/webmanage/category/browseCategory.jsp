<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet" type="text/css" />


</head>
<body >
	<div class="frame-container">
	<div class="frame-head">
			<div class="widget-header">
				<h4 class="lighter smaller">
					<i class="icon-comment blue"></i>
						产品分类管理
				</h4>
			</div>
		</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<div class="row">
							<div class="col-xs-12 col-sm-3">
							<s:form action="role_addRole" enctype="multipart/form-data" onsubmit="checkPrim()">
								<s:hidden id="id" name="id"/>
									<div id="tree"> </div>
								</s:form>
							</div>
						<div class="col-xs-12 col-sm-9">
							<div class="yz-admin-body">
								<div class="table-box">
								<div class="searchbox">
								<div class="row">
									<div class="col-xs-12">
										<button class="btn btn-success btn-xs"    id="showcurrent"><i class="icon icon-plus" ></i>新增</button>
										<button class="btn btn-info btn-xs"        id="edit_cat"><i class="icon icon-pencil"  ></i>编辑</button>
										<button class="btn btn-danger btn-xs"  id="del_cat"><i class="icon icon-remove" ></i>删除</button>
									</div>
								</div>
							</div>	
								<table class="table table-striped table-bordered table-hover" style="width:100%">
								
								<tbody> 
									<tr>
										<td>分类简称</td>
										<td colspan="5" id="name" width="80%"> </td>
									</tr>
									<tr>
										<td>备注</td>
										<td colspan="5"  id="remark"> </td>
									</tr>
								</tbody>
							</table>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>		
     
     <script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>

    <script type="text/javascript">
      function load() {        
            var o = { showcheck: true,url: "loadTree.action"  ,onnodeclick:function(tree, item){
            	reflashview(tree.id);
        }};
                           
            $("#tree").treeview(o);   
            $("#showcurrent").click(function(e){
            	
                var s=$("#tree").getCurrentNode();
             if(s !=null){  
                	jQuery().yzIframeDialog({id:"category_add_d",iframeurl:"<%=basepath%>/admin/category/preAddCategory.action?cat_id="+s.id,title:"分类新增"});
                	$('#category_add_d').modal('show');
             }else{
                	modalDialogAlert("请单击选择一个上级组织机构！");
                }  
             });
            $("#del_cat").click(function(e){
                var s=$("#tree").getCurrentNode();
                if(s !=null){
                	 modalDialogconfim("confim_dep","确定删除该分类目录？",[{name:"是",fn:function(){
                		 jQuery.ajax({
                             type: "get",
                 			 url: "<%=basepath%>/admin/category/delCategory.action?cat_id="+s.id,
                 			 global: false,
                 			 async:false,
                 			 dataType:"text",
                             success: function(request) {
                            	 closeDialog("confim_dep");
                             	rs(s.pid);
                           }  
                 		})
                     }},{name:"否",fn:function(){
                    	 closeDialog("confim_dep");
                     }}]);
                }
                else
                	modalDialogAlert("请单击选择一个组织机构！");
             });
            $("#edit_cat").click(function(e){
                var s=$("#tree").getCurrentNode();
                if(s !=null)
                {
                    jQuery().yzIframeDialog({id:"category_edit_d",iframeurl:"<%=basepath%>/admin/category/editCategory.action?cat_id="+s.id,title:"分类目录修改"});
                	$('#category_edit_d').modal('show');
                }
                else
                	modalDialogAlert("请单击选择一个组织机构！");
             });
            $("#reflashnode0").click(function(e) {
            	var tree=$("#tree");
            	var s=tree.getCurrentNode();
                 if(s=='undenfid'){
                	 modalDialogAlert("请单击选择一个组织机构！");
                   return false;
             }
                if(s!='undenfid'&&s !=null){
                	 $("#tree").reflash(s.id); //"root-0" is id of the node to be reloaded
                	 reflashview(s.id);
                }	
                else
                	$("#tree").reflash("");
               
            });

        }   
        $(document).ready(load);
        function rs(id){
        	$("#tree").reflash(id);
        	reflashview(id);
        }
        function reflashview(id){
        	if(id==0){
        		return false;
        	}
        	jQuery.post("<%=basepath%>/admin/category/viewCategory.action", {cat_id: id}, function(data){
        		  for (var key in data) {
        					 if(key=='cat_name'){
        					 jQuery("#name").text(data[key]);
        				 } 
        			 if(key=='cat_desc'){
        				 jQuery("#remark").text(data[key]);
        			 } 
        		 }
        	},'json');	
        }
        </script>
        					
          </body>
        <%@include file="../../../common/admin_footer.jsp"%>
        </html>
