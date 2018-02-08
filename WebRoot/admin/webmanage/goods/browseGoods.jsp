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
							<div class="searchbox">
								<div class="row">
									<div class="col-xs-12">
商品名称:<input name="goods_name" id="search_goods_name" /> 
			        			 <button class="btn btn-info btn-xs" onclick="search()" style="margin-left:10px">搜索</button>
										
									</div>
								</div>
							</div>
						</div>
						<div class="yz-frame-body">
							<div class="table-box">
								<tgrid:jqGrid url="listGoods.action" autowidth="true"
									dourl="delGoods.action" id="goodslist" mtype="POST"
									search="false" export="true" pageid="orgippage" prmNames="{id:'id'}"
									sortname="id" del="false" ondblClickRow="preview"  rowList="15,30,45">
									<tgrid:jqGridHead headvalue="'主键id','添加时间','商品货号','商品名称 ','分类','是否上架','好评率','操作'">
										<tgrid:jqcol name="goods_id" index="goods_id"  hidden="true" />
										<tgrid:jqcol name="addtime" index="addtime" /> 
										<tgrid:jqcol name="goods_sn" index="goods_sn" /> 
										<tgrid:jqcol name="goods_name" index="goods_name"  />
										 <tgrid:jqcol name="cat_name" index="cat_name" />   
										 <tgrid:jqcol name="is_on_sale" index="is_on_sale"  hidden="true" />
										 <tgrid:jqcol name="ghpl" index="ghpl" />    
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
<e:yzactbutton onclick="edit" title="修改" />   
  
<e:yzactbutton onclick="XJ" title="下架"   event="function (rowdata){
if(rowdata['is_on_sale']=='1'){
		return true;
	}else{
		return false;
	}
}"/>   
<e:yzactbutton onclick="SJ" title="上架"   event="function (rowdata){
if(rowdata['is_on_sale']=='2'){
		return true;
	}else{
		return false;
	}
}"/>     
</e:yzact>

  <script type="text/javascript"> 
function edit(id){
	var rowData =jQuery("#goodslist").getRowData(id);
	creatIframe("<%=basepath%>/admin/goods/editGoods.action?goods_id="+rowData['goods_id'],"修改商品",rowData['goods_id']);
<%-- 	jQuery().yzIframeDialog({id:"edit_goods_m",iframeurl:"<%=basepath%>/admin/goods/editGoods.action?goods_id="+rowData['GOODS_ID'],title:"修改商品"}); --%>
// 	$('#edit_goods_m').modal('show');
}
  
  
  
  function search(){
		var grid = $("#goodslist");
		var goods_name=$('#search_goods_name').val(); 
	    sdata = {goods_name:goods_name};
			$.extend(grid[0].p.postData,sdata);
			grid.trigger("reloadGrid",[{page:1}]);
		}
  
  function XJ(id){
		var rowData =jQuery("#goodslist").getRowData(id);
		 modalDialogconfim("confim_goods_xj","确定下架该商品？",[{name:"是",fn:function(){
			 
			 $.post('<%=basepath%>/admin/goods/updateGoodsType.action',{goods_id:rowData['goods_id'],is_on_sale:2},function(data){
					if(data.status==0){
						refreshGrid('goodslist');
						closeDialog("confim_goods_xj");
					}else{
						modalDialogAlert(data.info);
					}
				});
	     }},{name:"否",fn:function(){
	    	 closeDialog("confim_user_KT");
	     }}]);
	}
  function SJ(id){
		var rowData =jQuery("#goodslist").getRowData(id);
		 modalDialogconfim("confim_goods_sj","确定上架该商品？",[{name:"是",fn:function(){
			 
			 $.post('<%=basepath%>/admin/goods/updateGoodsType.action',{goods_id:rowData['goods_id'],is_on_sale:1},function(data){
					if(data.status==0){
						refreshGrid('goodslist');
						closeDialog("confim_goods_sj");
					}else{
						modalDialogAlert(data.info);
					}
				});
	     }},{name:"否",fn:function(){
	    	 closeDialog("confim_user_ZX");
	     }}]);
	}
</script>  
<%@include file="../../../common/admin_footer.jsp"%>
