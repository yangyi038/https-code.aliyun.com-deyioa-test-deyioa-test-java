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
	<form action="<%=basepath%>/admin/t_channel/add_channel.action" enctype="multipart/form-data" method="post">
			
			

 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>频道号:</span> <div class="admin-s"> <input type="text" name="number"  id="number" /> </div></div> 
			<div class="admin-s-body"> <span><font style="color:red;">*</font>频道名称:</span> <div class="admin-s"> <input type="text" name="name"  /> </div> </div> 
			<div class="admin-s-body"> <span>频道类型:</span> <div class="admin-s"> 
				<select  name="type"  placeholder=""/>
		    		<option >直播频道</option>
 		 			<option >WEB频道</option>
  				</select>
			 </div> </div> 
       		<div class="admin-s-body"> <span>双语标志:</span> <div class="admin-s">  
       			<select  name="bilingualSigns"  placeholder="" style="width:220px;"/>
		    		<option >无双语</option>
 		 			<option >双语</option>
 		 		</select>
       		</div> </div>  <br/>
    
       		<div class="admin-s-body"> <span>拷贝保护标志:</span> <div class="admin-s">
       			<select  name="protectiveEmblem"  placeholder=""/>
		    		<option >无拷贝保护</option>
 		 			<option >有拷贝保护</option>
 		 		</select>
       		</div> </div> 
       		<div class="admin-s-body"> <span>语言:</span> <div class="admin-s"> <input type="text" name="language"  /> </div> </div> 
			<div class="admin-s-body"> <span>视频参数:</span> <div class="admin-s"> 
				<select  name="videoParameter"  placeholder=""/>
		    		<option >MPEG2</option>
 		 			<option >MPEG4</option>
 		 			<option >MPEG6</option>
 		 			<option >VC1</option>
 		 			<option >H.264</option>
 		 		</select>
			 </div> </div> 
			<div class="admin-s-body"> <span>音频参数:</span> <div class="admin-s"> 
				<select  name="audioParameter"  placeholder="" style="width:220px;"/>
		    		<option >信号来源自live(即直播频道)</option>
 		 			<option >信号来源自virtual(即虚拟频道)</option>
 		 			
				</select>
			</div> </div>  <br/> 
			
       		<div class="admin-s-body"> <span>信号源类型:</span> <div class="admin-s">  
       		
       		<input type="text" name="signalSource"  /> 
       		
       		</div> </div>  
       		<div class="admin-s-body"> <span>码流标志:</span> <div class="admin-s">
       			<select  name="bitStream"  placeholder=""/>
		    		<option >PS</option>
 		 			<option >TS</option>
 		 			<option >MP4_FILE</option>
 		 			<option >WM9</option>
				</select>
       		
       		
       		</div> </div> 
       		<div class="admin-s-body"> <span>频道星级:</span> <div class="admin-s"> 
       			<select  name="starLevel"  placeholder=""/>
		    		<option >1</option>
 		 			<option >2</option>
 		 			<option >3</option>
 		 			<option >4</option>
 		 			<option >5</option>
 		 			<option >6</option>
 		 			<option >7</option>
 		 			<option >8</option>
 		 			<option >9</option>
 		 			<option >10</option>
				</select>
       		
       		</div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>台标:</span> <div class="admin-s"> <input type="text" name="tvLogo" style="width:220px;" /> </div> </div> <br/>
			
			<div class="admin-s-body"> <span>国家:</span> <div class="admin-s"> <input type="text" name="country"  /> </div> </div>  
       		<div class="admin-s-body"> <span>州/省:</span> <div class="admin-s">  <input type="text" name="province"  /> </textarea> </div> </div>  
       		<div class="admin-s-body"> <span>城市:</span> <div class="admin-s"><input type="text" name="city"  /> </div> </div> 
       		<div class="admin-s-body"> <span>邮编:</span> <div class="admin-s"> <input type="text" name="email" style="width:220px;" /> </div> </div> <br/>
       		 
			<div class="admin-s-body"> <span>内容提供商:</span> <div class="admin-s"> <input type="text" name="supplier"  /> </div> </div> 
			<div class="admin-s-body"> <span>网络频道关联:</span> <div class="admin-s"> <input type="text" name="correlation"  /> </div> </div> 
       		<div class="admin-s-body"> <span>内容服务平台标识:</span> <div class="admin-s">  <input type="text" name="contentDesignator"  /> </textarea> </div> </div>  <br/>
       		<div class="admin-s-body"> <span>描述:</span> <div class="admin-s"> <textarea rows="5" cols="5" type="text" name="tvDescribe" style="width:800px;"> </textarea> </div> </div>    
       		
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>    
			  
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_t_channel_s');window.parent.refreshGrid('channel');
	">
		${meg}
	</e:msgdialog>
</c:if>
<c:if test="${errorMeg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		>
		${errorMeg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	
	var info="";
	var number=$("input[name=number]").val();
	if(number==""){
		info+="频道号不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var reg = /^(?!0)\d{1,3}$/;
	if(reg.test(number)==false){
		info+="请输入1~3位有效数字且首位不能为0！";
		modalDialogAlert(info);
		return false;
	}
	
	var name=$("input[name=name]").val();
	if(name==""){
		info+="频道名称不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	var tvLogo=$("input[name=tvLogo]").val();
	if(tvLogo==""){
		info+="台标不能为空！";
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}


</script>
<%@include file="../../../common/admin_footer.jsp"%>
