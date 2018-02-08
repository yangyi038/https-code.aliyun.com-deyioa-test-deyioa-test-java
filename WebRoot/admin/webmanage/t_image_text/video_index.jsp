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
			<div class="yz-frame-head">
					<div class="searchbox">
						<div class="row">
							<div class="col-xs-12">
								<e:yzbutton id="add_t_video" name="添加" cssClass="btn btn-success" />
								<button class="btn btn-info btn-xs" onclick="setBack()"  style="height:32px;width:120px;float:right;margin-right:200px;">返回</button>
							</div>
						</div>
					</div>
				</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listT_imageVideo.action?imagetextId=${id}" autowidth="true"
									dourl="delPicture.action" id="imageVideo" mtype="POST"
									search="true" export="false" pageid="orgippage" prmNames="{id:'id'}"
									sortname="tswaccount_id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'id','名称','播放地址','操作'">
									
									<tgrid:jqcol name="id" index="id" width="20" hidden="true"/>
									<tgrid:jqcol name="name" index="name" width="40"/>
									<tgrid:jqcol name="url" index="url"  width="50"/> 
									<tgrid:jqcol name="act"  width="60"/>

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
<e:yzact id="act"> 
	<e:yzactbutton onclick="update" title="修改" />     
	<e:yzactbutton onclick="del" title="删除"  />
</e:yzact>
  
<script type="text/javascript"> 
$("#add_t_video").click(function(){
	var rd =jQuery("#imageVideo").getRowData(id);
	var id = ${id};
	jQuery().yzIframeDialog({id:"add_t_video_m",iframeurl:"<%=basepath%>/admin/t_imageText/show_addVideo.action?id="+id,title:"添加视频"});
	$('#add_t_video_m').modal('show');
})

function update(id){
	var rd =jQuery("#imageVideo").getRowData(id);
	jQuery().yzIframeDialog({id:"add_t_video_m",iframeurl:"<%=basepath%>/admin/t_imageText/show_editVideo.action?id="+rd['id'],title:"修改视频"});
	$('#add_t_video_m').modal('show');
}



function del(id){
	var rowData =jQuery("#imageVideo").getRowData(id);
	 modalDialogconfim("confim_sysuser_logoff","确定删除？",[{name:"是",fn:function(){
		 $.post('<%=basepath%>/admin/t_imageText/del_video.action',{id:rowData['id'],name:rowData['name'],isclose:2},function(data){
				if(data.status==0){
					modalDialogAlert("删除成功");
					refreshGrid('imageVideo');
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
