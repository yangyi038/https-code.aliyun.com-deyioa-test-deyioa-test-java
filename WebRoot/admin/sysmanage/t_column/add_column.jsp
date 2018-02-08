 <!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/admin_head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script language="javascript" type="text/javascript"	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basepath%>/js/jquery.formatDateTime.js" type="text/javascript"></script>	
 
<div class="modal-real-body" >
	<form action="<%=basepath%>/admin/t_column/add_column.action" enctype="multipart/form-data" method="post">
 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>上级菜单:</span> <div class="admin-s"> 
			
				<input type="text" name="classify" readonly="true" value="${column.name}"/>
				<input name="parentId" type="hidden" value="${column.id}"/>
			 </div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>名称:</span> <div class="admin-s"> <input type="text" name="name"  /> </div> </div> <br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>序号:</span> <div class="admin-s"> <input type="text" name="number"  /></div> </div> 
       		<div class="admin-s-body"> <span>类型:</span> <div class="admin-s">  
       			<select  name="type"  placeholder=""/>
		    		<option >不限</option>
					<option >系列片</option>
					<option >单片</option>
					<option >直播节目</option>
					<option >点播节目</option>
					<option >点播搜索</option>
					<option >视频演播大厅</option>
					<option >图片</option>
					<option >字幕</option>
					<option >首页推荐海报</option>
					<option >首页推荐视频</option>
					<option >首页推荐节目</option>
					<option >专题</option>
					<option >app应用</option>
					<option >图文信息</option>
 		 		</select>
       		</div> </div>  <br/>
    
       		<div class="admin-s-body"> <span>状态:</span> <div class="admin-s">
       			<select  name="status"  placeholder=""/>
		    		<option >有效</option>
 		 			<option >无效</option>
 		 		</select>
       		</div> </div> 
       		<div class="admin-s-body"> <span>图片显示:</span> <div class="admin-s"> 
       			<select  name="showPicture"  placeholder=""/>
		    		<option >纵向</option>
 		 			<option >横向</option>
 		 		</select>
       		 
       		</div> </div> <br/>
			<div class="admin-s-body"> <span>列表播放:</span> <div class="admin-s"> 
				<select  name="play"  placeholder=""/>
		    		<option >否</option>
 		 			<option >是</option>
 		 			
 		 		</select>
			 </div> </div> 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>优先级:</span> <div class="admin-s"> 
				<input type="text" name="priority"  /> 
			</div> </div>  <br/> 
			
       		<div class="admin-s-body"> <span>显示类型:</span> <div class="admin-s">  
       			<select  name="showType"  placeholder=""/>
		    		<option >正常</option>
 		 			<option >测试</option>
				</select>
       		</div> </div>  
       		<div class="admin-s-body"> <span>模板编码:</span> <div class="admin-s">
       			<input type="text" name="code"  /> 
       		</div> </div> <br/>
       		<div class="admin-s-body"> <span>操作类型:</span> <div class="admin-s"> 
       			<select  name="operation"  placeholder=""/>
		    		<option >默认</option>
 		 			<option >显示图片</option>
 		 			<option >播放视频</option>
 		 			<option >打开网页</option>
				</select>
       		</div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>栏目唯一标识:</span> <div class="admin-s"> <input type="text" name="identifying"  /> </div> </div> <br/><br/>
			<div class="admin-s-body"> <span>UI模板:</span> <div class="admin-s"> <input type="text" name="uiModel"  /> </div> </div>  
       		<div class="admin-s-body"> <span>标签:</span> <div class="admin-s">  <input type="text" name="label"  /> </textarea> </div> </div> <br/> 
       		<div class="admin-s-body"> <span>描述:</span> <div class="admin-s"> <textarea rows="3" cols="65" type="text" name="describeContext"   > </textarea> </div> </div>    
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>    
			  
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_t_column_s');window.parent.rs('${column.parentId}');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var classify=$("#classify").val();
	if(classify=="no"){
		info+="上级菜单不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	var name=$("input[name=name]").val();
	if(name==""){
		info+="名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var number=$("input[name=number]").val();
	if(number==""){
		info+="序号不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var reg = /^[0-9]*$/;
	if(reg.test(number)==false){
		info+="序号请输入数字！";
		modalDialogAlert(info);
		return false;
	}
	
	var priority=$("input[name=priority]").val();
	if(priority==""){
		info+="优先级不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var identifying=$("input[name=identifying]").val();
	if(identifying==""){
		info+="栏目唯一标识不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	
	return true;

}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
