<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<e:yzbutton id="add_parameter" name="添加"
							cssClass="btn btn-success" />
						<div style="float: right; margin-right: 200px;">
							<input name="content" id="search_content"
								style="height: 32px; width: 250px"
								placeholder="可输入组编号或组名称进行模糊查询" />
							<button class="btn btn-info btn-xs" onclick="search()"
								style="width: 60px; height: 32px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="yz-frame-body">
			<div class="table-box">
				<tgrid:jqGrid url="listParameter.action" autowidth="true"
					dourl="delParameter.action" id="parameterlist" mtype="POST"
					search="false" export="true" pageid="parameterpage"
					prmNames="{id:'id'}" sortname="id" del="false"
					ondblClickRow="preview" rowList="15,30,45">
					<tgrid:jqGridHead
						headvalue="'ID' ,'枚举类别','枚举编号', '枚举名称' , '是否显示' ,'排序序号','操作'">
						<tgrid:jqcol name='id' width='50' index='id' hidden='true' />
						<tgrid:jqcol name='ptype' width='150' index='ptype' />
						<tgrid:jqcol name='pcode' width='150' index='pcode' hidden='false' />
						<tgrid:jqcol name='pname' width='150' index='pname' hidden='false' />
						<tgrid:jqcol name='isshow' width='150' index='isshow'
							hidden='false' />
						<tgrid:jqcol name='porder' width='150' index='porder'
							hidden='false' />
						<tgrid:jqcol name="act" />
					</tgrid:jqGridHead>
				</tgrid:jqGrid>
			</div>
		</div>
	</div>
</div>

<e:yzact id="act">
	<e:yzactbutton onclick="xg" title="修改" />
	<e:yzactbutton onclick="del" title="删除" />
</e:yzact>
<script type="text/javascript">

//加载列表
function loadComplete(x){
	 jQuery("#parameterlist").jqGrid("setGridHeight",26*$("#parameterlist").jqGrid('getGridParam','rowNum')); 
}

//修改
function xg(id){
var rd =jQuery("#parameterlist").getRowData(id);
jQuery().yzIframeDialog({id:"edit_parameter_m",iframeurl:"<%=basepath%>/admin/parameter/preEditParameter.action?id="+ rd['id'],title : "修改枚举配置"});
	$('#edit_parameter_m').modal('show');
}

//添加订单
$("#add_parameter").click(function(){
	jQuery().yzIframeDialog({id:"add_parameter_m",iframeurl:"<%=basepath%>/admin/parameter/preAddParameter.action",
			title : "新增枚举配置"
		});
		$('#add_parameter_m').modal('show');
	})

	//删除
	function del(id) {
		var rowData = jQuery("#parameterlist").getRowData(id);
		if (id != null)
			jQuery("#parameterlist").jqGrid('delGridRow', rowData['id'], {
				top : 120,
				left : 250,
				reloadAfterSubmit : true,
				jqModal : false,
				msg : "确认删除该枚举？",
				afterSubmit : function(response, postdata) {
					var json = response.responseText;
					if (json != "")
						modalDialogAlert(json);
					return [ true ];
				}
			});
		else
			modalDialogAlert("请先选中一行");
	}

	//搜索订单
	function search() {
		var grid = $("#parameterlist");
		var content = $('#search_content').val();

		sdata = {
			content : content
		};
		$.extend(grid[0].p.postData, sdata);
		grid.trigger("reloadGrid", [ {
			page : 1
		} ]);
	}
</script>
<%@include file="../../../common/admin_footer.jsp"%>
