<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/good.css"/>
 <script type="text/javascript" src="<%=basepath%>/admin/js/scroll.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/yz.js"></script>
<script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"> </script>


<div class="modal-real-body">
<style>
</style>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('add_ad_m');window.parent.refreshGrid('Ad');">
							${meg}
						</e:msgdialog>
					</c:if>
 					<div class="row" style="padding-top:8px 0;margin-right:0 ">
                 		<div class="col-xs-12">
                 			<div class="searchCont">
                 				商品编号:<input type="text" id="goods_sn">
                 			</div>
                 			<div class="searchCont">
                 				商品名称:<input type="text" id="goods_name">
                 			</div>
                 			<div class="searchCont">
                 				商品分类:
                 				<select name="cat_id_f" id="cat_id_f" onchange="getCatList('cat_id_s',this);">
                 					<option value="">1级类目</option>
                 				</select>
                 				<select name="cat_id_s" id="cat_id_s" onchange="getCatList('cat_id_t',this);">
                 					<option value="">2级类目</option>
                 				</select>
                 				<select name="cat_id_t" id="cat_id_t">
                 					<option value="">3级类目</option>
                 				</select>
                 			</div>
                 			<button type="button" onclick="getGoodsList();" class="btn btn-xs btnAd btn-primary">搜索</button>
                 		</div>
                 	</div>
                 	<div class="modaltable">
                 		<div class="tableInfo">
                 			<div class="modaltitle">商品列表</div>
                 			<table><tr>
                 			<th class="bh">商品编号</th>
                 			<th class="name">商品名称</th>
                 			<th class="price">商品价格</th> 
                 			<th class="kc">库存</th>
                 			<th class="zt">操作栏</th>
                 			</tr></table>
                 			<div class="scrolltable">
                 				<table id="goodslist">
                 				<tr><td colspan="7">暂无数据</td></tr>
                 				</table>
                 			</div>
                 			
                 		<button class="btn btn-primary"  onclick="addGoods()">添加</button>
                 		</div>
                 		<div class="tableInfo"><div class="modaltitle">特卖商品</div>
                 		<div class="row" style="padding-top:8px 0;margin-right:0 ">
                 		<div class="col-xs-12">
                 			<div class="searchCont">
                 				*活动名称:<input type="text" id="act_name"  name="act_name">
                 			</div>
                 			<div class="searchCont">
                 				*商品折扣:<input type="text" id="rebates" onblur="zk()">(0.1为1折，以此类推)
                 			</div>
                 			<div class="searchCont">
                 				*活动介绍:<input type="text" id="act_desc">
                 			</div>
                 			<br> 
                 			<br> 
                 			<div class="searchCont">
                 				*活动开始时间:<input type="text" id="start_time"   name="start_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})">
                 			</div>
                 			<div class="searchCont">
                 				*活动结束时间:<input type="text" id="end_time"  name="end_time"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})">
                 			</div> 
                 			</div>
                 			</div>
                 			<br>
                 			<table><tr>
                 			<th class="bh">商品编号</th>
                 			<th class="name">商品名称</th>
                 			<th class="price">商品价格</th> 
                 			<th class="kc">库存</th>
                 			<th class="kc">折扣</th>
                 			<th class="kc">是否显示首页</th>
                 			<th class="zt">操作栏</th>
                 			</tr></table>
                 			<div class="scrolltable">
                 				<table id="ActivityGoodslist">
                 				<tr><td colspan="7">暂无数据</td></tr>
                 				</table>
                 			</div>
                 		</div>
                 		<button class="btn btn-primary" onclick="addActGoods()">保存</button>
                 		</div>
           <script>
           function zk(){
    		   var rebate = $('#rebates').val();
        	   var re = /^0\.[1-9]{0,2}$/;
        	   if(rebate=='0' || rebate=='1'){
        		   $('.rebate').val("1");$('#rebates').val("1");
        	   }else{
        		   if(re.test(rebate)){
                	   $('.rebate').val(rebate);
            	   }else{
            		   $('.rebate').val("1");$('#rebates').val("1");
            	   }
        	   } 
           }
           $(function(){
       		$(".scrolltable").panel({iWheelStep:32,top:20});
       	});
           function getCatList(cid,obj){
        		$('#'+cid).empty();
        		var info='';
        		if(cid=='cat_id_f'){
        			info='<option value="">1级类目</option>';
        		}
        		if(cid=='cat_id_s'){
        			info='<option value="">2级类目</option>';
        			$('#cat_id_t').empty();
        			var tinfo='<option value="">3级类目</option>';
        			$('#cat_id_t').append(tinfo);
        		}
        		if(cid=='cat_id_t'){
        			info='<option value="">3级类目</option>';
        		}
        		if(cid=='searchcatid_f'){
        			info='<option value="">1级类目</option>';
        		}
        		if(cid=='searchcatid_s'){
        			info='<option value="">2级类目</option>';
        			$('#searchcatid_t').empty();
        			var tinfo='<option value="">3级类目</option>';
        			$('#searchcatid_t').append(tinfo);
        		}
        		if(cid=='searchcatid_t'){
        			info='<option value="">3级类目</option>';
        		}
        		if(obj.value!=''){
        			$.post("<%=basepath%>/admin/goods/getCatList.action", {'pid':obj.value}, function(data){
        				if (data.status==1) {
        					var json=JSON.parse(data.data);
        		           	for(var i=0;i<json.length;i++){
        		           		info+='<option value="'+json[i].cat_id+'">'+json[i].cat_name+'</option>';
        		           	}
        		           	$('#'+cid).append(info);
        				}else{
        					$('#'+cid).append(info);
        				}
         			});
        		}else{
        			$('#'+cid).append(info);
        		}
        	}
function getGoodsList(){
var	goods_sn =$('#goods_sn').val();
var	goods_name =$('#goods_name').val();
var	cat_id_f =$('#cat_id_f').val();
var	cat_id_s =$('#cat_id_s').val();
var	cat_id_t =$('#cat_id_t').val(); 
 var cat_id="";
 if(cat_id_f==""){
	 cat_id="";
 }else
 if(cat_id_s==""){
	 cat_id=cat_id_f;
 }else
 if(cat_id_t==""){
	 cat_id=cat_id_s;
 }else
 if(cat_id_t!=""){
	 cat_id=cat_id_t;
 }
 $.post("<%=basepath%>/admin/activity/listGoods.action", {'goods_sn':goods_sn,'goods_name':goods_name,'cat_id':cat_id}, function(data){
		if (data.status==1) {
			var json=JSON.parse(data.data);
			var info="";
			if(json.length>0){
			for(var i=0;i<json.length;i++){
				info+='<tr>'+
							'<td   class="bh" id="goods_sn_'+json[i].goods_id+'">'+json[i].goods_sn+'</td>' +
							'<td   class="name" id="goods_name_'+json[i].goods_id+'">'+json[i].goods_name+'</td>' +
							'<td   class="price" id="shop_price_'+json[i].goods_id+'">'+json[i].shop_price+'</td>' +
							'<td   class="kc" id="goods_number_'+json[i].goods_id+'">'+json[i].goods_number+'</td>' +
							'<td   class="zt"><input type="checkbox" name="goodslist"  value="'+json[i].goods_id+'"></td>' +
					 		'</tr>';
           	}}else{
           		info+='<tr><td colspan="7">暂无数据</td></tr>';
           	}
           	$('#goodslist').html(info);
}
 });
}
           $(document).ready(function () {
           	getCatList('cat_id_f',{value:1});
           	getCatList('searchcatid_f',{value:1}); 
           });
           
           function addGoods(){
        	   var info="";
        	   $('input[name="goodslist"]:checked').each(function(){ 
        		 var goods_id = $(this).val();
        		 var goods_sn = $('#goods_sn_'+goods_id).html();
        		 var goods_name = $('#goods_name_'+goods_id).html();
        		 var shop_price = $('#shop_price_'+goods_id).html();
        		 var goods_number = $('#goods_number_'+goods_id).html();
        		 info+='<tr>'+
					'<td   class="bh" id="goods_sn'+goods_id+'">'+goods_sn+'</td>' +
					'<td   class="name" id="goods_name'+goods_id+'">'+goods_name+'</td>' +
					'<td   class="price" id="shop_price'+goods_id+'">'+shop_price+'</td>' +
					'<td   class="kc" id="goods_number'+goods_id+'">'+goods_number+'</td>' +
					'<td   class="kc" > <input type="text" id="rebate'+goods_id+'" name="rebate"  class="rebate number" style="width:50px"></td>' +
					'<td   class="kc"><select  name="isindex"  id="isindex'+goods_id+'"><option value="0">否</option><option value="1">是</option></select></td>' +
					'<td   class="zt"><input type="checkbox" name="actslist"  value="'+goods_id+'"></td>' +
			 		'</tr>'; 
        		   });  
        	   if(info!=""){
        		   $('#ActivityGoodslist').html("");
        		   $('#ActivityGoodslist').append(info);
        		   zk();
        	   }else{
        		   modalDialogAlert("请选择商品进行添加!");
        		   return false;
        	   }
        	   
           }
           function addActGoods(){
        	   var info ="";
        	   var goods_id="";
        	   var goods_name="";
        	   var act_name=$('#act_name').val();
        	   var act_desc=$('#act_desc').val();
        	   var start_time=$('#start_time').val();
        	   var end_time=$('#end_time').val();
        	   var rebates=$('#rebates').val();
        	   var rebate="";
        	   var isindex="";
        	   $('input[name="actslist"]:checked').each(function(){ 
        		var   good_id = $(this).val(); 
    		 var   good_name = $('#goods_name'+good_id).html(); 
    		 var  rebat = $('#rebate'+good_id).val(); 
     		var isinde =   $('#isindex'+good_id).val();
        		   if(goods_id==""){
        			   goods_id = $(this).val(); 
        		   }else{ 
        			   goods_id +=","+$(this).val(); 
        		   } 
        		   if(goods_name==""){
        			   goods_name = good_name; 
        		   }else{ 
        			   goods_name +=","+good_name; 
        		   } 
        		   if(rebate==""){
        			   rebate = rebat; 
        		   }else{ 
        			   rebate +=","+rebat; 
        		   } 
        		   if(isindex==""){
        			   isindex = isinde; 
        		   }else{ 
        			   isindex +=","+isinde; 
        		   } 
        	   });
        	   
        	   if(goods_id!=""){
        		   if(act_name==""){
        			   info+="活动名称不能为空！</p>";
        		   }
        		   if(act_desc==""){
        			   info+="活动描述不能为空！</p>";
        		   }
        		   if(start_time==""){
        			   info+="活动开始时间不能为空！</p>";
        		   }
        		   if(rebates==""){
        			   info+="折扣不能为空！</p>";
        		   }
        		   if(info==""){
        		   $.post("<%=basepath%>/admin/activity/addActivity.action", {'goods_id':goods_id,'goods_name':goods_name,'act_name':act_name,'act_desc':act_desc,'start_time':start_time,'end_time':end_time,'rebate':rebate,'rebates':rebates,'isindex':isindex}, function(data){
       				if (data.status==0) {
       					modalDialogconfim("add_activity_m",data.info,[{name:"是",fn:function(){
       						closeDialog("add_activity_m");
              				 window.parent.closeDialogm('add_activity_m');
               				 window.parent.refreshGrid('activity'); 
       			 
       			     }}]);  
						 
       				}
       				})
        		   }else{
        			   modalDialogAlert(data.info);
            		   return false;
        		   }
        	   }else{
        		   modalDialogAlert("请选择商品进行保存!");
        		   return false;
        	   }
           }
           </script>
<%@include file="../../../common/admin_footer.jsp"%>