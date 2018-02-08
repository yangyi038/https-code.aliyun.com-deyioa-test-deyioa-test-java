<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@include file="../../../common/admin_head.jsp"%>
<script type="text/javascript"
	src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet"
	type="text/css" />
<div class="frame-container">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-xs-12 col-sm-3">
						<div id="tree"></div>
					</div>
					<div class="col-xs-12 col-sm-9">
						<div class="yz-admin-head">
							<div class="searchbox">
								<div class="row">
									<div class="col-xs-12">
										<button class="btn btn-success btn-xs" id="add_dep">
											<i class="icon icon-plus"></i>新增
										</button>
										<button class="btn btn-info btn-xs" id="edit_dep">
											<i class="icon icon-pencil"></i>编辑
										</button>
										<button class="btn btn-danger btn-xs" id="del_dep">
											<i class="icon icon-remove"></i>删除
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="yz-admin-body">
							<div class="table-box">
								<table class="table table-striped table-bordered table-hover"
									style="width: 100%">

									<tbody>
										<tr>
											<td>菜单编号</td>
											<td id="functioncode"></td>
											<td>菜单链接</td>
											<td id="menulink"></td>
											<td>菜单名称</td>
											<td id="menuname"></td>
										</tr>
										<tr>
											<td>上级菜单</td>
											<td id="menuparentStr"></td>
											<td>菜单排序</td>
											<td id="menuorder"></td>
											<td>有效性</td>
											<td id="valid"></td>
										</tr>
										<tr>
											<td>添加时间</td>
											<td colspan="5" id="addtime"></td>
										</tr>
										<tr>
											<td>菜单类型</td>
											<td colspan="5" id="functiontype"></td>
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

<script type="text/javascript">
function load() {        
	
    var o = { showcheck: true,url: "loadTree.action"  ,onnodeclick:function(tree, item){
    	reflashview(tree.id);
	}};
                   
    $("#tree").treeview(o);   
    
    $("#add_dep").click(function(e){
        var s=$("#tree").getCurrentNode();
        if(s !=null){
            jQuery().yzIframeDialog({id:"sysmenu_add_d",iframeurl:"<%=basepath%>/admin/sysmenu/preAddSysmenu.action?id="+s.id,title:"新增菜单"});
        	$('#sysmenu_add_d').modal('show');
        }
    	  else
        	modalDialogAlert("请单击选择一个主菜单！");
     });
    
    $("#del_dep").click(function(e){
        var s=$("#tree").getCurrentNode();
        if(s !=null){
        	 modalDialogconfim("confim_dep","确定删除该机构？",[{name:"是",fn:function(){
        		 jQuery.ajax({
                     type: "get",
         			 url: "<%=basepath%>/admin/department/delDepartment.action?id="+s.id,
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
    
    $("#edit_dep").click(function(e){
        var s=$("#tree").getCurrentNode();
        if(s !=null)
        {
            jQuery().yzIframeDialog({id:"department_edit_d",iframeurl:"<%=basepath%>/admin/department/editDepartment.action?id="+s.id,title:"组织机构修改"});
        	$('#department_edit_d').modal('show');
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
	jQuery.post("<%=basepath%>/admin/sysmenu/viewSysmenu.action", {
			id : id
		}, function(data) {
			//msg = eval("("+data+")"); 
			for ( var key in data) {
				if (key == 'menuEntity') {
					if (data[key] != null) {
						jQuery("#menuparentStr").text(data[key].menuparent);
					} else {
						jQuery("#menuparentStr").text("");
					}
				} else {
					jQuery("#" + key).text("");
					if (data[key] != null) {
						jQuery("#" + key).text(data[key]);
					}
				}
			}
		}, 'json');
	}
</script>

<%@include file="../../../common/admin_footer.jsp"%>
