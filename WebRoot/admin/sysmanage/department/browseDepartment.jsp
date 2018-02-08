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
											<td>组织机构名称</td>
											<td id="depname"></td>
											<td>组织机构编号</td>
											<td id="depcode"></td>
											<td>组织机构地址</td>
											<td id="depaddress"></td>
										</tr>
										<tr>
											<td>上级组织机构</td>
											<td id="Departmentdepname"></td>
											<td>联系电话</td>
											<td id="telephone"></td>
											<td>联系人</td>
											<td id="liaisonper"></td>
										</tr>
										<tr>
											<td>排序</td>
											<td colspan="5" id="dorder"></td>
										</tr>
										<tr>
											<td>组织机构简称</td>
											<td colspan="5" id="contracted"></td>
										</tr>
										<tr>
											<td>备注</td>
											<td colspan="5" id="remark"></td>
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
            jQuery().yzIframeDialog({id:"department_add_d",iframeurl:"<%=basepath%>/admin/department/preAddDepartment.action?id="+s.id,title:"新增组织机构"});
        	$('#department_add_d').modal('show');
        }
    	  else
        	modalDialogAlert("请单击选择一个上级组织机构！");
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
	jQuery.post("<%=basepath%>/admin/department/viewDepartment.action", {
			id : id
		}, function(data) {
			//msg = eval("("+data+")"); 
			for ( var key in data) {
				if (key == 'parentDepartment') {
					if (data[key] != null) {
						jQuery("#Departmentdepname").text(data[key].depname);
					} else {
						jQuery("#Departmentdepname").text("");
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
