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
<script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>
<script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"> </script>

<div class="modal-real-body">
<style>

.alertmodel{
height:200px;
}
</style>
	<form action="<%=basepath%>/admin/coupon/updateCoupon.action" enctype="multipart/form-data" method="post"> 
      	<div class="admin-s-body block">
      	<input type="hidden" name="id"  value="${coupon.id }">
       		<span><b style="color: red;">*</b>优惠券名称</span>
       		<div class="admin-s">
       		
       		<input type="text" name="cou_name"   id="cou_name"  placeholder="请输入优惠券名称"  value="${coupon.cou_name }"/> 
       	</div>
       	</div>
       		<div class="admin-s-body">
       		<span><b style="color: red;">*</b>优惠券类型</span>
       		<div class="admin-s"> 
       		 <select name="ctype" id="ctype" onchange="yhtype('99')">
			        			<c:forEach items="${fns:getCodeMap(pageContext.request,'ctype')}" var="item">
			        			   <option value="${item.key}" ${coupon.ctype==item.key?'selected="ctype"':''}>${item.value}</option>
			        			 </c:forEach>  
			        			 </select>
       		</div>
       	</div>
      	<div class="admin-s-body block">
       		<span><b style="color: red;">*</b>发行数量</span>
       		<div class="admin-s">
       		 
       		<input type="text" name="issuenum"   id="issuenum"  placeholder="请输入发行数量"  value="${coupon.issuenum }"/> 
       	</div>
       	</div>
       	
       	<div class="admin-s-body block none" id="dk"  >
       		<span><b style="color: red;">*</b>抵扣金额</span>
       		<div class="admin-s"> 
       		<input type="text" name="cou_money_dk"   id="cou_money"  placeholder="请输入优惠所值金额"    value="${coupon.cou_money }"/>
       		 </div>
       		</div>
       		
       		
       		<div class="admin-s-body block none" id="mj">
       		<span><b style="color: red;">*</b>优惠幅度</span>
       		<div class="admin-s">
       		满
       		<input type="text" name="min_goods_amount"   id="min_goods_amount"  placeholder="请输入使用金额限制" value="${coupon.min_goods_amount }"   style="width:145px;margin:0 8px" />
       		减
       		<input type="text" name="cou_money"   id="cou_money"  placeholder="请输入优惠所值金额" value="${coupon.cou_money }"    style="width:144px;margin:0 8px"/>
       		
       		</div>
       		</div>
       		
       		
       		<div class="admin-s-body block none" id="zk"  >
       		<span><b style="color: red;">*</b>折扣</span>
       		<div class="admin-s"> 
       		<input type="text" name="rebate"   id="rebate"  placeholder="请输入使用金额限制"  value="${coupon.rebate }"     onblur="zk()"/>(0.1为1折，以此类推)
       		 </div>
       		</div>
    	 	<div class="admin-s-body block"  >
       		<span><b style="color: red;">*</b>有效日期</span> 
       		<div class="admin-s"><input type="text" name="use_start_date"  id="use_start_date"  placeholder="请输入开始时间"   onclick="WdatePicker()" value="${coupon.use_start_date }"/></div>---
       		<div class="admin-s"><input type="text" name="use_end_date"  id="use_end_date"  placeholder="请输入结束时间"  onclick="WdatePicker()"  value="${coupon.use_end_date }" /></div>
       	</div>   
       	<div class="admin-s-body block"  >
       	<input type="hidden"  name="coup_goods_category" id="coup_goods_category">
       	<div class="admin-s">
                 				<span>商品分类:</span> 
                 				<select name="cat_id_f" id="cat_id_f" onchange="getCatList('cat_id_s',this,'');">
                 					<option value="">1级类目</option>
                 				</select>
                 				<select name="cat_id_s" id="cat_id_s" onchange="getCatList('cat_id_t',this,'');">
                 					<option value="">2级类目</option>
                 				</select>
                 				<select name="cat_id_t" id="cat_id_t">
                 					<option value="">3级类目</option>
                 				</select>
                 			</div>
   </div>
       	 
       	<div class="admin-s-body  block"  >
       		<span class="top"> 备注内容</span>
       		<div class="admin-s"><textarea rows="5" cols="50" placeholder="请输入备注"  name="remarks"   id="remarks">${coupon.remarks }</textarea>
       	</div> 	 
       	</div>
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return check();">确定</button>
			        	</div>
			        	</form>
			        	
		        	</div>
					<c:if test="${meg!=null}">
						<e:msgdialog basepath="<%=basepath%>"
							callback="window.parent.closeDialogm('edit_coupon_m');window.parent.refreshGrid('coupon');">
							${meg}
						</e:msgdialog>
					</c:if>
<script type="text/javascript"> 
function check(){
	var errorinfo="";
	
	var	cat_id_f =$('#cat_id_f').val();
	var	cat_id_s =$('#cat_id_s').val();
	var	cat_id_t =$('#cat_id_t').val(); 
	 var coup_goods_category="";
	 if(cat_id_f==""){
		 coup_goods_category="";
	 }else
	 if(cat_id_s==""){
		 coup_goods_category=cat_id_f;
	 }else
	 if(cat_id_t==""){
		 coup_goods_category=cat_id_s;
	 }else
	 if(cat_id_t!=""){
		 coup_goods_category=cat_id_t;
	 }
	 $('#coup_goods_category').val(coup_goods_category);
var cou_name =$('#cou_name').val();	
if(cou_name==null||cou_name==''){
		errorinfo+="优惠券名称不能为空</br>";
	}   
var issuenum =$('#issuenum').val();	
if(issuenum==null||issuenum==''){
		errorinfo+="发行数量不能为空</br>";
	}  
var ctype =$('#ctype').val();	
if(ctype==null||ctype=='0' || ctype==''){
		errorinfo+="优惠券类型不能为空</br>";
	}
var ctype =$('#ctype').val();
if(ctype=='1'){
	var cou_money =$('#cou_money').val();	
	if(cou_money==null||cou_money==''){
			errorinfo+="抵扣金额不能为空</br>";
		}  
}else if(ctype=='2'){ 
	var min_goods_amount =$('#min_goods_amount').val();	
	if(min_goods_amount==null||min_goods_amount==''){
			errorinfo+="金额限制不能为空</br>";
		}  
	var cou_money =$('#cou_money').val();	
	if(cou_money==null||cou_money==''){
			errorinfo+="优惠所值金额不能为空</br>";
		}  
}else if(ctype=='3'){ 
	var rebate =$('#rebate').val();	
	if(rebate==null||rebate==''){
			errorinfo+="折扣金额不能为空</br>";
		}  
}

var use_start_date =$('#use_start_date').val();	
if(use_start_date==null||use_start_date==''){
		errorinfo+="优惠开始时间不能为空</br>";
	}  

var use_end_date =$('#use_end_date').val();	
if(use_end_date==null||use_end_date==''){
		errorinfo+="优惠结束时间不能为空</br>";
	}  
	if(errorinfo!=''){
		modalDialogAlert(errorinfo); 
		return false;
	} 
	}  
	var ajax="";
function getCatList(cid,obj,paths){ 
	 var arr=new Array();
	if(paths!=""){
		var path =	'${category.CAT_PATH}';	
	    var cat_path =   path.substring(1,path.length-1); 
   	arr=cat_path.split('||');
	}
	  
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
	if(obj.value!=''){
		$.post("<%=basepath%>/admin/goods/getCatList.action", {'pid':obj.value}, function(data){
			if (data.status==1) {
				var json=JSON.parse(data.data);
	           	for(var i=0;i<json.length;i++){
	           		info+='<option value="'+json[i].cat_id+'">'+json[i].cat_name+'</option>';
	           	}
	           	$('#'+cid).append(info); 
	           	if(paths!=""){
	           		if(arr.length==2){
	           			$('#cat_id_f').val(arr[1]);  
	           		}else if(arr.length==3){
	           			$('#cat_id_f').val(arr[1]); 
	    	           	getCatLists('cat_id_s',{value:arr[1]},arr[2]); 
	           		}else if(arr.length==4){
	           			$('#cat_id_f').val(arr[1]); 
	    	           	getCatLists('cat_id_s',{value:arr[1]},arr[2]);  
	           			$('#cat_id_s').val(arr[2]); 
	    	           	getCatLists('cat_id_t',{value:arr[2]},arr[3]);  
	           			$('#cat_id_t').val(arr[3]); 
	           		} 
	           	}
			}else{
				$('#'+cid).append(info);
			}
			});
	}else{
		$('#'+cid).append(info);
	}  
} 
function getCatLists(cid,obj,path){
 var info="";
	if(obj.value!=''){
		$.post("<%=basepath%>/admin/goods/getCatList.action", {'pid':obj.value}, function(data){
			if (data.status==1) {
				var json=JSON.parse(data.data);
	           	for(var i=0;i<json.length;i++){
	           		info+='<option value="'+json[i].CAT_ID+'">'+json[i].CAT_NAME+'</option>';
	           	}
	           	$('#'+cid).append(info);  
	        	$('#'+cid).val(path);
			}else{
				$('#'+cid).append(info);
			}
			});
	}else{
		$('#'+cid).append(info);
	} 

}  
$(document).ready(function () {  
   	getCatList('cat_id_f',{value:1},"1"); 
   	yhtype('${coupon.ctype}');
   });

	
   function cat (){ 	 
	 
	      	if(arr.length==2){
	      		$('#cat_id_f').val(arr[1]);  
	      	   	getCatList('cat_id_s',{value:arr[1]}); 
	      	}else if(arr.length==3){
	      		$('#cat_id_f').val(arr[1]);  
	      	   	getCatList('cat_id_s',{value:arr[1]}); 
	      		$('#cat_id_s').val(arr[2]);  
	      	   	getCatList('cat_id_t',{value:arr[2]}); 
	      	}else  if(arr.length==4){
	      		$('#cat_id_f').val(arr[1]);  
	      	   	getCatList('cat_id_s',{value:arr[1]}); 
	      		$('#cat_id_s').val(arr[2]);  
	      	   	getCatList('cat_id_t',{value:arr[2]}); 
	      		$('#cat_id_t').val(arr[3]);  
	      	}
   }
   
   function zk(){
	   var rebate = $('#rebate').val();
	   var re = /^0\.[1-9]{0,2}$/;
	   if(rebate=='0' || rebate=='1'){
		   $('#rebate').val("1");
	   }else{
			   if(re.test(rebate)){
	     	   $('#rebate').val(rebate);
	 	   }else{
	 		    $('#rebate').val("1");
	 	   }
	   } 
}
function yhtype(obj){
	var ctype ='';
	if(obj!='99'){
		ctype =obj;
	}else{
		ctype =$('#ctype').val();
		
	}
	if(ctype=='1'){
		$("#dk").removeClass("none"); 
		$("#mj").addClass("none"); 
		$("#zk").addClass("none"); 
	}else if(ctype=='2'){ 
		$("#dk").addClass("none"); 
		$("#mj").removeClass("none"); 
		$("#zk").addClass("none"); 
	}else if(ctype=='3'){ 
		$("#dk").addClass("none"); 
		$("#mj").addClass("none"); 
		$("#zk").removeClass("none"); 
	}else if(ctype=='0' || ctype==''){ 
		$("#dk").addClass("none"); 
		$("#mj").addClass("none"); 
		$("#zk").addClass("none"); 
	}
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>