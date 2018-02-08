<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/good.css"/>
 <script type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript" src="<%=basepath%>/admin/js/scroll.js"></script>

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
<form id="goodsform" action="<%=basepath%>/admin/goods/updateGoods.action" enctype="multipart/form-data" method="post"  >
	<input type="hidden" name="goods_id" id="goods_id" value="${goods.goods_id}">
	<input type="hidden" name="attrjson" id="attrjson" value='${attrjson }'>
	<input type="hidden" name="attrtype" id="attrtype" value="old">
	<input type="hidden" name="img_head" id="img_head" value="${goods.img_head}">
	<input type="hidden" name="original_img" id="original_img" value="${goods.original_img}">
	<input type="hidden" name="img_info" id="img_info" value="${goods.img_info}">
    <div class="addGoodsContainer">
        <div class="addProsess">
            <ul class="prosessUl">
               <li class="current">商品信息</li>
               <li>图片上传</li>
               <li>销售属性</li>
               <li>商品描述</li>
            </ul>
        </div>
        <div class="addContent">
             <div class="contentInfo">
                 <div class="infoItem active">
                 	<div class="modal-real-body">
                 		<div class="admin-s-body topSec block">
                 			<span class="title">*商品名称</span>
                 			<div class="admin-s">
                 				<input type="text" name="goods_name" id="goods_name" value="${goods.goods_name }" class="bigInput">
                 			</div>
                 		</div>
                 		
                 		<div class="admin-s-body topSec">
                 			<span class="title">销量</span><div class="admin-s">
                 				<input type="text" name="total_sell" id="total_sell" class="checkznumber" value="${goods.total_sell}">
                 			</div>
                 		</div>
                 		
                 		<div class="admin-s-body topSec">
                 			<span class="title">排序级别</span><div class="admin-s">
                 				<select name="sort_order" id="sort_order">
	                 				<c:forEach var="item" begin="1" end="10">
				        			<option value="${item}" ${goods.sort_order==item?'selected="selected"':''}>${item}</option>
				        			</c:forEach>
<!--                  					<option value="1">1</option> -->
<!--                  					<option value="2">2</option> -->
<!--                  					<option value="3">3</option> -->
<!--                  					<option value="4">4</option> -->
<!--                  					<option value="5">5</option> -->
<!--                  					<option value="6">6</option> -->
<!--                  					<option value="7">7</option> -->
<!--                  					<option value="8">8</option> -->
<!--                  					<option value="9">9</option> -->
<!--                  					<option value="10">10</option> -->
                 				</select>
                 			</div>
                 		</div>
                 		<div class="admin-s-body topSec">
                 			<span class="title">产地</span><div class="admin-s">
                 				<select name="productplace" id="productplace">
                 					<option value="0">请选择</option>
                 					
                 				</select>
                 			</div>
                 		</div>
                 		
                 		<div class="admin-s-body block">
                 			<span class="title">*所属分类</span><div class="admin-s">
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
                 		</div>
                 		
<!--                  		<div class="admin-s-body"> -->
<!--                  			<span class="title">包装方式</span><div class="admin-s"> -->
<!--                  				<select name="pack" id="pack"> -->
<%--       			<c:forEach items="${fns:getCodeMap(pageContext.request,'pack')}" var="item"> --%>
<%--       			   <option value="${item.key}" ${goods.pack==item.key?'selected="selected"':''}>${item.value}</option> --%>
<%--       			 </c:forEach>   --%>
<!--       			</select> -->
<!--                  			</div> -->
<!--                  		</div> -->
                 		
                 		<div class="admin-s-body block">
                 			<span class="title">服务标签</span>
                 			<div class="admin-s" id="serlist">
                 			</div>
                 		</div>
                 		<div class="admin-s-body block">
                 			<span class="title">是否热门</span>
                 			<div class="admin-s" >
                 			<span class="kfame"><input type="radio" name="ishot" value="2" ${goods.ishot==2?'checked="checked"':''}><label for="1">否</label></span>
                 			<span class="kfame"><input type="radio" name="ishot" value="1" ${goods.ishot==1?'checked="checked"':''}><label for="1">是</label></span>
                 			</div>
                 		</div>
<!--                  		<div class="admin-s-body topSec block"> -->
<!--                  			<span class="title">备注</span> -->
<!--                  			<div class="admin-s"> -->
<%--                  				<input type="text" name="remark" id="remark" value="${goods.remark}" class="bigInput"> --%>
<!--                  			</div> -->
<!--                  		</div> -->
                 	</div>
                 </div>
                 <!-- step 1 -->
                 <div class="infoItem">
                 	<div class="picbox">
                 		<div class="picTit">前台首页图片（比例1：1，大小50KB以内）</div>
                 		<div class="picInfo">
                 			
                 			
                 			<div class="infoItem picItem">
                 				<div class="picBox">
                 					<c:set var="cimghead">${other.headpath }${goods.img_head }</c:set>
                 					<c:set var="indeximg"><%=basepath%>/admin/img/uploadDefult.jpg</c:set>
                 					<c:set var="imgpath">
                 					<c:choose><c:when test="${goods.img_head==null }">${indeximg }</c:when>
   									<c:otherwise>${cimghead }</c:otherwise></c:choose>
                 					</c:set>
                 					<div class="picImg"><img id="img_h1" src="${imgpath}"></div>
                 				</div>
                 				<div class="upboot">
                 					<input type="file" id="file_h1" name="headpicpath" class="file">
                 					<button type="button" class="choButt">上传</button><button type="button" onclick="delFile('h1');" class="removeButt">删除</button>
                 				</div>
                 			</div>
                 			
                 		</div>
                 		
                 	</div>
                 	<div class="picbox">
                 		<div class="picTit">商品详情页图片 最多可上传十张图片（比例1：1，大小50KB以内）</div>
                 		<div class="picInfo">
                 			
                 			<c:forEach var="i" begin="1" end="10">
			        			<div class="infoItem picItem">
	                 				<div class="picBox">
	                 					<div class="picImg"><img id="img_i${i }" src="<%=basepath%>/admin/img/uploadDefult.jpg"></div>
	                 				</div>
	                 				<div class="upboot">
	                 					<input type="file" id="file_i${i }" name="infopicpath" class="file">
	                 					<button type="button" class="choButt">上传</button><button type="button" onclick="delFile('i${i }');" class="removeButt">删除</button>
	                 				</div>
	                 			</div>
		        			</c:forEach>
                 			
                 		</div>
                 	</div>
                 </div>
                 <!-- step 2 -->
                 <div class="infoItem">
                 	<div class="sellSx">
                 		<div class="sxClim">
                 			<div class="climTitle">
                 				属性名称
                 			</div>
<!--                  			<input type="text" id="attrtitle" value=""> -->
							<select id="attrtitle">
								<option value=""></option>
								<option value="颜色">颜色</option>
								<option value="尺码">尺码</option>
							</select>
                 		</div>
	                   	<div class="sxPic" id="attrlist">
	                   		<div class="sxbox attrbox_1">
	                   			<div class="inp"><input type="text" id="attrvalue_1_1" class="attrvalue_1" name="attrvalue"></div>
	                   			<div class="pic"><div class="img" title="点击上传图片">
	                   			<img id="img_1_1" src="<%=basepath%>/admin/img/uploadDefult.jpg">
	                   			<input type="file" name="attrpicpath" id="file_1_1" class="sxPicFile attrpic_1 attrfile" title="点击上传图片">
	                   			</div></div>
	                   			<div class="remove" onclick="delFile('1_1');">删除</div>
	                   		</div>
	                   		
	                   	</div>
	                   	<a href="javascript:addAttr();" class="addSx">新增一个属性值</a>
	                   	<div class="modal-real-body" style="padding-top:10px">
	                   		 <button type="button" class="btn btn-success" onclick="addToTable();">添加</button>
	                   		 <button type="button" class="btn btn-success" onclick="clearTable();">清除</button>
	                   	</div>
	                   	<div class="modaltable">
                 			<div class="tableInfo">
                 				<table class="sxtable">
                 					<thead>
                 						<tr>
	                 						<th>属性名称</th>
	                 						<th class="tx">编号</th>
	                 						<th class="tx">库存</th>
	                 						<th class="tx">销售价格</th>
	                 						<th class="tx">市场价格</th>
	                 						
	                 					</tr>
                 					</thead>
                 					<tbody id="sellattrlist">
                 					
                 					<c:forEach items="${salesattrlist}" var="item">
                 						<tr>
											<td>${item.attrname}<input type="hidden" name="sellattr_id" value="${item.id}"></td>
											<td class="tx"><input type="text" name="sn" class="checknumber" value="${item.goods_sn}"></td>
											<td class="tx"><input type="text" name="goods_number" class="checknumber" value="${item.goods_number}"></td>
											<td class="tx"><input type="text" name="shop_price" class="checknumber" value="${item.shop_price}"></td>
											<td class="tx"><input type="text" name="market_price" class="checknumber" value="${item.market_price}"></td>
										</tr>
      			 					</c:forEach>
                 						
                 					</tbody>
                 				</table>
                 			</div>
                 		</div>
                  	</div>
                 	
                 </div>
                 <!-- step 3 -->
                 <div class="infoItem">
                 	<div class="picbox">
                 		<div class="picTit">最多可上传十张图片（比例1：1，大小50KB以内）</div>
                 		<div class="picInfo">
                 		
                 		<c:forEach var="i" begin="1" end="10">
			        		<div class="infoItem picItem">
                 				<div class="picBox">
                 					<div class="picImg"><img id="img_r${i }" src="<%=basepath%>/admin/img/uploadDefult.jpg"></div>
                 				</div>
                 				<div class="upboot">
                 					<input type="file" id="file_r${i }" name="remarkpicpath" class="file">
                 					<button type="button" class="choButt">上传</button><button type="button" onclick="delFile('r${i }');" class="removeButt">删除</button>
                 				</div>
                 			</div>	
	        			</c:forEach>
                 			
                 			
                 			
                 		</div>
                 		<div>
                 		
                 		
                 		
                 		</div>
                 	</div>
                 </div>
                 
                 
                 <!-- step 6 -->
             </div>
        </div>
        <div class="addButtom">
             <button type="button" class="btn btn-primary" href="javascript:void(0)" id="prevBtn" onclick="goPrev()">上一步</button>
             <button type="button" class="btn btn-primary" href="javascript:void(0)" id="nextBtn" onclick="goNext()">下一步</button>
             <button type="button" class="btn btn-primary" href="javascript:void(0)" id="saveBtn" onclick="save()">保存</button>
          </div> 
</div>
</form>
							     </div>
						    </div>
					    </div>
				    </div>
			</div>
		</div>
			</div>
		</div>

	</div>
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>">
		${meg}
	</e:msgdialog>
</c:if>
<script>
	function goNext(){
		var oMenu = $('.prosessUl');
		var oCont = $('.contentInfo');
		var oEit = oMenu.children('.current');
		var oNum = oEit.length;
		if(oNum == 1){
			if(checkInfo()){
				$('#prevBtn').show();
			}else{
				return false;
			}
		}else if(oNum == 3){
			if(!checkattr()){
				return false;
			}else{
				$('#nextBtn').hide();
				$('#saveBtn').show();	
			}
		}
		oMenu.children('li').eq(oNum).addClass('current');
		oCont.children('.infoItem').eq(oNum).addClass('active').siblings().removeClass('active');
			
	}
	function goPrev(){
		var oMenu = $('.prosessUl');
		var oCont = $('.contentInfo');
		var oEit = oMenu.children('.current');
		var oNum = oEit.length;
		if(oNum == 4){
			$('#nextBtn').show();
			$('#saveBtn').hide();
		}else if(oNum == 2){
			$('#prevBtn').hide();	
		}
		oMenu.children('li').eq(oNum-1).removeClass('current');
		oCont.children('.infoItem').eq(oNum-2).addClass('active').siblings().removeClass('active');
		
	}
	
	$('.picItem').on('mouseenter',function(){
		$(this).children('a.remove').show();
	}).on('mouseleave',function(){
		$(this).children('a.remove').hide();
	});
	
	$(function(){
		$(".scrolltable").panel({iWheelStep:32,top:20});
	});
</script>
<script type="text/javascript">
$('.checkznumber').keyup(function(){
	if(!isNaN(this.value)){
		if(Number(this.value)<0){
			this.value=0;
		}
		if(this.value.indexOf('.')>=0){
			this.value=this.value.split('.')[0];
		}
		
	}else{
		this.value=0;
	}
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

function getSerList(val){
	$.post("<%=basepath%>/admin/goods/getSerList.action", {}, function(data){
		if (data.status==1) {
			var json=JSON.parse(data.data);
			var info='';
			var idarr=val.split(',');
           	for(var i=0;i<json.length;i++){
           		var ischeck="0";
      			for(var u=0;u<idarr.length;u++){
          			var sid=repall(idarr[u],"'","");
      				if(sid==json[i].id){
          				ischeck="1";
          			}
          		}
           		if(ischeck=='0'){
               		info+='<span class="kfame"><input type="checkbox" name="service" value="'+json[i].id+'"><label for="1">'+json[i].ser_name+'</label></span>';

           		}else{
               		info+='<span class="kfame"><input type="checkbox" name="service" value="'+json[i].id+'" checked="checked"><label for="1">'+json[i].ser_name+'</label></span>';

           		}
           		
           	}
           	$('#serlist').append(info);
		}else{
			modalDialogAlert("通信错误，获取服务列表失败");
		}
	});
}

function repall(str,berep,rep){
	str=str.replace(berep,rep);
	if(str.indexOf(berep)>=0){
		return repall(str,berep,rep);
	}else{
		return str;
	}
}

function checkInfo(){
	var info="";
	var goods_name=$("#goods_name").val();
	var provider_name=$("#provider_name").val();
	var provider_phone=$("#provider_phone").val();
	var provider_address=$("#provider_address").val();
	var shelflife=$("#shelflife").val();
	var shelflifeunit=$("#shelflifeunit").val();
	var cat_id_f=$("#cat_id_f").val();
	var store=$("#store").val();
	var unit=$("#unit").val();
	var xjday=$("#xjday").val();
	if(goods_name==""){
		info+="商品名称不能为空！</p>";
	}
	if(provider_name==""){
		info+="供应商名称不能为空！</p>";
	}
	if(provider_phone==""){
		info+="供应商电话不能为空！</p>";
	}
	if(provider_address==""){
		info+="供应商地址不能为空！</p>";
	}
	if(shelflife==""){
		info+="保质期不能为空！</p>";
	}
	if(shelflifeunit==""||shelflifeunit=="0"){
		info+="保质期单位不能为空！</p>";
	}
	if(cat_id_f==""){
		info+="所属分类不能为空！</p>";
	}
	if(store==""||store=="0"){
		info+="储藏方式不能为空！</p>";
	}
	if(unit==""){
		info+="商品单位不能为空！</p>";
	}
	if(xjday==""){
		info+="自动下架时间不能为空！</p>";
	}
	if(info!=""){
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}

function checkattr(){
	var attrjson=$('#attrjson').val();
	if(attrjson==null||attrjson==''){
		modalDialogAlert("至少添加一个属性");
		return false;
	}else{
		return true;
	}
}

$('.file').change(function(){
	var idarr=this.id.split('_');
	var file = this.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        $("#img_"+idarr[1]).attr("src", this.result);
    };
});

$('.attrfile').change(function(){
	var idarr=this.id.split('_');
	var file = this.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        $("#img_"+idarr[1]+'_'+idarr[2]).attr("src", this.result);
    };
});

function delFile(type){
	$("#img_"+type).attr("src", '<%=basepath%>/admin/img/uploadDefult.jpg');
//     $("#pic_"+type).val('');
    $("#file_"+type).val('');
    if(type.indexOf('h')>=0){
    	$('#img_head').val('');
    }
    if(type.indexOf('i')>=0){
    	var original=$('#original_img').val();
    	if(original!=null&&original!=''){
    		var num=repall(type,'i','');
    		var oarr=original.split(',');
    		if(Number(num)<=oarr.length){
    			oarr[num-1]='';
    			var oval=oarr.join(',');
    			$('#original_img').val(oval);
    		}
    	}
    }
    if(type.indexOf('r')>=0){
    	var original=$('#img_info').val();
    	if(original!=null&&original!=''){
    		var num=repall(type,'r','');
    		var oarr=original.split(',');
    		if(Number(num)<=oarr.length){
    			oarr[num-1]='';
    			var oval=oarr.join(',');
    			$('#img_info').val(oval);
    		}
    	}
    }
}

var attrnum=1;
var attrnum_c=2;
function addAttr(){
	var info='<div class="sxbox attrbox_'+attrnum+'">'+
			'<div class="inp"><input type="text" id="attrvalue_'+attrnum+'_'+attrnum_c+'" class="attrvalue_'+attrnum+'" name="attrvalue"></div>'+
 			'<div class="pic"><div class="img" title="点击上传图片">'+
 			'<img id="img_'+attrnum+'_'+attrnum_c+'" src="<%=basepath%>/admin/img/uploadDefult.jpg">'+
 			'<input type="file" name="attrpicpath" id="file_'+attrnum+'_'+attrnum_c+'" class="sxPicFile attrpic_'+attrnum+' attrfile" title="点击上传图片">'+
 			'</div></div>'+
 			'<div class="remove" onclick="delFile(\''+attrnum+'_'+attrnum_c+'\');">删除</div>'+
 		'</div>';
	
	$('#attrlist').append(info);
	$('#file_'+attrnum+'_'+attrnum_c).bind("change",function(){
		var idarr=this.id.split('_');
		var file = this.files[0];
	    var reader = new FileReader();
	    reader.readAsDataURL(file);
	    reader.onload = function(e) {
	        $("#img_"+idarr[1]+'_'+idarr[2]).attr("src", this.result);
	    };
	});
	attrnum_c++;
}

var attrlist=new Array();
function clearTable(){
	$('#attrtype').val('new');
	$('#attrjson').val('');
	$('#sellattrlist').html('');
	attrlist=new Array();
}
function addToTable(){
	var ftitle=$('#attrtitle').val();
	if(ftitle==null||ftitle==''){
		modalDialogAlert("属性名称不能为空");
		return false;
	}
	
	var attrtype=$('#attrtype').val();
	if(attrtype=='old'){
		var r=confirm("添加新属性必须删除旧属性，是否确认删除？");
		if (r==true){
			$('#attrtype').val('new');
			$('#attrjson').val('');
			$('#sellattrlist').html('');
		}else{
			return false;
		}
	}
	
	var varr=$('.attrvalue_'+attrnum);
	var picarr=$('.attrpic_'+attrnum);
	var attrvaluearr=new Array();
	var anum=0;
	for(var i=0;i<varr.length;i++){
		if(varr[i].value!=''){
			anum++;
			var idarr=varr[i].id.split('_');
			if(picarr[i].value!=''){
				
				var map={'id':idarr[1]+'_'+idarr[2],'attrvalue':varr[i].value,'attrpic':1}
				attrvaluearr.push(map);
			}else{
				var map={'id':idarr[1]+'_'+idarr[2],'attrvalue':varr[i].value,'attrpic':0}
				attrvaluearr.push(map);
			}
		}else{
			picarr[i].value='';
		}
	}
	if(anum==0){
		modalDialogAlert("至少填写一个属性值名称");
		return false;
	}
	var amap={'id':attrnum,'title':ftitle,clist:attrvaluearr};
	attrlist.push(amap);
	$('#attrjson').val(JSON.stringify(attrlist));
	$('#attrtitle').val('');
	$('.attrbox_'+attrnum).hide();
	attrnum++;
	var info='<div class="sxbox attrbox_'+attrnum+'">'+
	'<div class="inp"><input type="text" id="attrvalue_'+attrnum+'_'+attrnum_c+'" class="attrvalue_'+attrnum+'" name="attrvalue"></div>'+
		'<div class="pic"><div class="img" title="点击上传图片">'+
		'<img id="img_'+attrnum+'_'+attrnum_c+'" class="attrfile" src="<%=basepath%>/admin/img/uploadDefult.jpg">'+
		'<input type="file" name="attrpicpath" id="file_'+attrnum+'_'+attrnum_c+'" class="sxPicFile attrpic_'+attrnum+' attrfile" title="点击上传图片">'+
		'</div></div>'+
		'<div class="remove" onclick="delFile(\''+attrnum+'_'+attrnum_c+'\');">删除</div>'+
	'</div>';
	$('#attrlist').append(info);
	$('#file_'+attrnum+'_'+attrnum_c).change(function(){
		var idarr=this.id.split('_');
		var file = this.files[0];
	    var reader = new FileReader();
	    reader.readAsDataURL(file);
	    reader.onload = function(e) {
	        $("#img_"+idarr[1]+'_'+idarr[2]).attr("src", this.result);
	    };
	});
	attrnum_c++;
	
	var sellattrlist=getsellattrlist(attrlist,attrlist[0],'','','',0)
	$('#sellattrlist').html(sellattrlist);
	$('.checknumber').bind('keyup',function(){
		if(!isNaN(this.value)){
			if(Number(this.value)<0){
				this.value=0;
			}
			if(this.value.indexOf('.')>=0){
				if(this.value.length>this.value.indexOf('.')+3){
					this.value=this.value.substring(0,this.value.indexOf('.')+3);
				}
			}
			
		}else{
			this.value=0;
		}
	});
}

function getsellattrlist(attrlist,amap,sellattrlist,id,name,num){
	
	for(var u=0;u<amap.clist.length;u++){
		var sid=id;
		var sname=name;
		if(id==''){
			sid=amap.clist[u].id;
			sname=amap.clist[u].attrvalue;
		}else{
			if(num==0){
				sid=amap.clist[u].id;
				sname=amap.clist[u].attrvalue;
			}else{
				sid+=','+amap.clist[u].id;
				sname+='/'+amap.clist[u].attrvalue;
			}
			
		}
		if(attrlist.length>num+1){
			sellattrlist=getsellattrlist(attrlist,attrlist[num+1],sellattrlist,sid,sname,num+1);
		}else{
			var info='<tr>'+
				'<td>'+sname+'<input type="hidden" name="sellattr_id" value="'+sid+'"></td>'+
				'<td class="tx"><input type="text" name="sn" value=""></td>'+
				'<td class="tx"><input type="text" name="goods_number" class="checknumber" value="0"></td>'+
				'<td class="tx"><input type="text" name="shop_price" class="checknumber" value="0"></td>'+
				'<td class="tx"><input type="text" name="market_price" class="checknumber" value="0"></td>'+
				'</tr>';
			
			sellattrlist+=info;
			
		}
	}
	return sellattrlist;
}

function getGoodsList(){
	var gsn=$('#gsn').val();
	var gname=$('#gname').val();
	var searchcatid_f=$('#searchcatid_f').val();
	var searchcatid_s=$('#searchcatid_s').val();
	var searchcatid_t=$('#searchcatid_t').val();
	var cat_id='';
	if(searchcatid_t!=null&&searchcatid_t!=''){
		cat_id=searchcatid_t;
	}else if(searchcatid_s!=null&&searchcatid_s!=''){
		cat_id=searchcatid_s;
	}else if(searchcatid_f!=null&&searchcatid_f!=''){
		cat_id=searchcatid_f;
	}
	
	$.post("<%=basepath%>/admin/goods/getGoodsList.action", {'goods_sn':gsn,'goods_name':gname,'cat_id':cat_id,}, function(data){
		if (data.status==1) {
			var json=JSON.parse(data.data);
			zpjson=data.data;
			var info='';
			if(json.length!=0){
				for(var i=0;i<json.length;i++){
	           		info+='<tr><td class="bh">'+json[i].GOODS_SN+'</td>'+
	          			'<td class="name">'+json[i].GOODS_NAME+'</td>'+
	          			'<td class="price">'+json[i].SHOP_PRICE+'</td>'+
	          			'<td class="xl">'+json[i].TOTAL_SELL+'</td>'+
	          			'<td class="fl">'+json[i].CAT_NAME+'</td>'+
	          			'<td class="kc">'+json[i].GOODS_NUMBER+'</td>'+
	          			'<td class="zt"><a href="javascript:addzp(\''+json[i].GOODS_ID+'\');">添加</a>　'+
	          			'<a href="javascript:view(\''+json[i].GOODS_ID+'\');">详情</a></td></tr>';
	           	}
			}else{
				info='<tr><td colspan="7">暂无数据</td></tr>';
			}
           	
           	$('#goodslist').html(info);
		}else{
			modalDialogAlert("通信错误，获取服务列表失败");
		}
	});
}
var zpjson='';
var zparr=new Array();
function addzp(goods_id){
	for(var n=0;n<zparr.length;n++){
		if(goods_id==zparr[n]){
			modalDialogAlert("已存在该赠品，添加失败");
			return false;
		}
	}
	var json=JSON.parse(zpjson);
	var info='';
	for(var i=0;i<json.length;i++){
		if(json[i].GOODS_ID==goods_id){
			info+='<tr class="zpc" id="zp_'+json[i].GOODS_ID+'"><td class="bh">'+json[i].GOODS_SN+'</td>'+
  			'<td class="name">'+json[i].GOODS_NAME+'</td>'+
  			'<td class="price">'+json[i].SHOP_PRICE+'</td>'+
  			'<td class="xl">'+json[i].TOTAL_SELL+'</td>'+
  			'<td class="fl">'+json[i].CAT_NAME+'</td>'+
  			'<td class="kc">'+json[i].GOODS_NUMBER+'</td>'+
  			'<td class="zt"><a href="javascript:delzp(\''+json[i].GOODS_ID+'\');">删除</a></td></tr>';
  			$('#zpemptytitle').hide();
			$('#zplist').append(info);
			zparr.push(json[i].GOODS_ID);
		}
   	}
}

function delzp(goods_id){
	$('#zp_'+goods_id).remove();
	for(var i=zparr.length-1;i>=0;i--){
		if(goods_id==zparr[i]){
			zparr.splice(i,1)
		}
	}
	if($('.zpc').length==0){
		$('#zpemptytitle').show();
	}
	
}

function view(goods_id){
	
}

function save(){
	var zp=zparr.join(",");
	$('#zp').val(zp);
	modalDialogWait("waitwin","数据提交中，请稍候...");
	$('#goodsform').submit();
}

function initCatList(f,s,t){
	
	doinitCatList('1',f,'cat_id_f');
	doinitCatList(f,s,'cat_id_s');
	doinitCatList(s,t,'cat_id_t');
}

function doinitCatList(val,nextval,cid){
	if(val!=''){
		var info='';
		$.post("<%=basepath%>/admin/goods/getCatList.action", {'pid':val}, function(data){
			if (data.status==1) {
				var json=JSON.parse(data.data);
	           	for(var i=0;i<json.length;i++){
	           		if(nextval==json[i].cat_id){
	           			info+='<option value="'+json[i].cat_id+'" selected="selected">'+json[i].cat_name+'</option>';
	           		}else{
	           			info+='<option value="'+json[i].cat_id+'">'+json[i].cat_name+'</option>';
	           		}
	           	}
	           	$('#'+cid).append(info);
	           	
			}else{
				$('#'+cid).append(info);
			}
		});
	}
}

function initImg(orgimg){
	var imgarr=orgimg.split(',');
	for(var i=1;i<=imgarr.length;i++){
		$('#img_i'+i).attr('src','${other.headpath }'+imgarr[i-1]);
		
	}
}

function initImgInfo(orgimg){
	var imgarr=orgimg.split(',');
	for(var i=1;i<=imgarr.length;i++){
		$('#img_r'+i).attr('src','${other.remarkpath }'+imgarr[i-1]);
		
	}
}

function getProductPlaceList(pid){
	$.post("<%=basepath%>/admin/goods/getProductPlaceList.action", {}, function(data){
		if (data.status==1) {
			var json=JSON.parse(data.data);
			var info='';
           	for(var i=0;i<json.length;i++){
           		if(json[i].id==pid){
           			info+='<option value="'+json[i].id+'" selected="selected">'+json[i].name+'</option>';
           		}else{
           			info+='<option value="'+json[i].id+'">'+json[i].name+'</option>';
           		}
           		
           	}
           	$('#productplace').append(info);
		}else{
			modalDialogAlert("通信错误，获取服务列表失败");
		}
	});
}

$(document).ready(function () {
	initCatList('${other.cat_id_f}','${other.cat_id_s}','${other.cat_id_t}');
	getCatList('searchcatid_f',{value:1});
	getSerList("${goods.service}");
	initImg('${goods.original_img}');
	initImgInfo('${goods.img_info}');
	getProductPlaceList('${goods.productplace}');
});
</script>
<%@include file="../../../common/admin_footer.jsp"%>