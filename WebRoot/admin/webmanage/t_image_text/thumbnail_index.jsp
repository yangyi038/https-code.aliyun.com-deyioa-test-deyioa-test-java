<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css" />
<script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>
<div class="frame-container">
	<div class="page-content">
		<div class="yz-frame-head">
			<div class="searchbox">
				<div class="row">
					<div class="col-xs-12">
						<e:yzbutton id="add_thumbnail" name="添加缩略图" cssClass="btn btn-success" />
						<button class="btn btn-info btn-xs" onclick="setBack()"
							style="height: 32px; width: 120px; float: right; margin-right: 200px;">返回图文列表</button>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listThumbnail.action?imagetextId=${id}"
					autowidth="true" dourl="delPicture.action" id="image" mtype="POST"
					search="true" export="false" pageid="orgippage"
					prmNames="{id:'id'}" sortname="tswaccount_id" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead headvalue="'id','名称','原名称','图片路径','图片类型','描述','操作'">

						<tgrid:jqcol name="id" index="id" width="20" hidden="true" />
						<tgrid:jqcol name="name" index="name" width="40" />
						<tgrid:jqcol name="file_name" index="file_name" width="40" />
						<tgrid:jqcol name="image_url" index="image_url" width="60" />
						<tgrid:jqcol name="type" index="type" width="40" />
						<tgrid:jqcol name="image_desc" index="image_desc" width="50" />
						<tgrid:jqcol name="act" width="40" />

					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<%-- <e:yzact id="act">     --%>

<%-- </e:yzact> --%>
<e:yzact id="act">
	<e:yzactbutton onclick="del" title="删除" />
</e:yzact>

<script type="text/javascript"> 

/* 添加缩略图 */
$("#add_thumbnail").click(function(){
	var rd =jQuery("#image").getRowData(id);
	var id = ${id};
	jQuery().yzIframeDialog({id:"add_t_image_m",iframeurl:"<%=basepath%>/admin/t_imageText/addThumbnailPre.action?id="+id,title:"添加缩略图"});
	$('#add_t_image_m').modal('show');
})


function del(id){
	var rowData =jQuery("#image").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/del_image.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('image');
					closeDialog("confim_sysuser_logoff");
				}else{
					modalDialogAlert("删除失败");
				}
			});
    }},{name:"否",fn:function(){
   	 closeDialog("confim_sysuser_logoff");
    }}]);
}

function setBack(){
	window.location.href="<%=basepath%>/admin/t_imageText/imageText_manager.action";
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
