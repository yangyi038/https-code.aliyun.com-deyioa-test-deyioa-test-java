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
										<button class="btn btn-primary btn-xs" id="query_info">
											<i class="icon icon-search"></i>查看节目
										</button>
										<button class="btn btn-success btn-xs" id="add_program">
											<i class="icon icon-plus"></i>添加节目
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
											<td>名称</td>
											<td id="name"></td>
											<td>上级分类</td>
											<td id="classify"></td>
											<td>类型</td>
											<td id="type"></td>
										</tr>
										<tr>
											<td>状态</td>
											<td id="status"></td>
											<td>优先级</td>
											<td id="priority"></td>
											<td>序号</td>
											<td id="number"></td>
										</tr>
										<tr>
											<td>栏目唯一标识</td>
											<td colspan="5" id="identifying"></td>
										</tr>
										<tr>
											<td>描述</td>
											<td colspan="5" id="describeContext"></td>
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
            jQuery().yzIframeDialog({id:"add_t_column_s",iframeurl:"<%=basepath%>/admin/t_column/showT_column.action?id="+s.id,title:"新增栏目"});
        	$('#add_t_column_s').modal('show');
        }
    	  else
        	modalDialogAlert("请单击选择一个上级组织机构！");
     });
    
    $("#del_dep").click(function(e){
        var s=$("#tree").getCurrentNode();
        if(s !=null){
        	 modalDialogconfim("confim_dep","确定删除该栏目？",[{name:"是",fn:function(){
        		 jQuery.ajax({
                     type: "get",
         			 url: "<%=basepath%>/admin/t_column/delColumn.action?id="+s.id+"&name="+s.name,
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
        	modalDialogAlert("请单击选择一个栏目！");
     });
    
    $("#edit_dep").click(function(e){
        var s=$("#tree").getCurrentNode();
        if(s !=null)
        {
            jQuery().yzIframeDialog({id:"column_edit_d",iframeurl:"<%=basepath%>/admin/t_column/showColumnWindow.action?id="+s.id,title:"栏目修改"});
        	$('#column_edit_d').modal('show');
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
	jQuery.post("<%=basepath%>/admin/t_column/viewColumn.action", {id: id}, function(data){
		 //msg = eval("("+data+")"); 
    	for ( var key in data) {
				if (key == 'parentColumn') {
					if(data[key]!=null){
						jQuery("#classify").text(data[key].classify);
					}else{
						jQuery("#classify").text("");
					}
				} else {
					jQuery("#" + key).text("");
					if(data[key]!=null){
						jQuery("#" + key).text(data[key]);
					}
					
				}
			}
		}, 'json');
	}
	
	
$("#query_info").click(function(e){
    var s=$("#tree").getCurrentNode();
    var id = s.id;
    var name=s.name;
	window.location.href="<%=basepath%>/admin/t_column/column_info.action?id="+id+"&name="+name;
 });
 
$("#add_program").click(function(e){
	query_info
    var s=$("#tree").getCurrentNode();
    var id = s.id;
    var name=s.name;
    var type = s.type;
	window.location.href="<%=basepath%>/admin/t_column/column_type.action?id="+ id + "&name=" + name + "&type=" + type;
});

</script>
<%@include file="../../../common/admin_footer.jsp"%>
