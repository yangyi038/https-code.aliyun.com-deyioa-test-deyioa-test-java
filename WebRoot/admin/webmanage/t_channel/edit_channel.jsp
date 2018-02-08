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
	<form action="<%=basepath%>/admin/t_channel/updateChannel.action" enctype="multipart/form-data" method="post">
	
			<input type="text" name="id"  value="${channelInfo.id}" style="visibility:hidden;" /><br/>
			<div class="admin-s-body"> <span><font style="color:red;">*</font>频道号:</span> <div class="admin-s"> <input type="text" name="number"  value="${channelInfo.number}" /> </div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>频道名称:</span> <div class="admin-s"> <input type="text" name="name"   value="${channelInfo.name}"/> </div> </div> 
			<div class="admin-s-body"> <span>频道类型:</span> <div class="admin-s"> 
				<select  name="type"  placeholder=""/>
		    		<option <c:if test="${channelInfo.type=='直播频道'}">selected="selected"</c:if>>直播频道</option>
 		 			<option <c:if test="${column.type=='WEB频道'}">selected="selected"</c:if>>WEB频道</option>
  				</select>
			
			</div> </div> 
       		<div class="admin-s-body"> <span>双语标志:</span> <div class="admin-s">  
       			<select  name="bilingualSigns"  placeholder="" style="width:220px;"/>
		    		<option <c:if test="${channelInfo.bilingualSigns=='无双语'}">selected="selected"</c:if>>无双语</option>
 		 			<option <c:if test="${channelInfo.bilingualSigns=='双语'}">selected="selected"</c:if>>双语</option>
 		 		</select>
       		 </div> </div>  <br/>
       		
       		<div class="admin-s-body"> <span>拷贝保护标志:</span> <div class="admin-s">
       			<select  name="protectiveEmblem"  placeholder=""/>
		    		<option <c:if test="${channelInfo.protectiveEmblem=='无拷贝保护'}">selected="selected"</c:if>>无拷贝保护</option>
 		 			<option <c:if test="${channelInfo.protectiveEmblem=='有拷贝保护'}">selected="selected"</c:if>>有拷贝保护</option>
 		 		</select>
       			
       		</div> </div> 
       		<div class="admin-s-body"> <span>语言:</span> <div class="admin-s"> <input type="text" name="language"  value="${channelInfo.language}" /> </div> </div> 
			<div class="admin-s-body"> <span>视频参数:</span> <div class="admin-s"> 
				<select  name="videoParameter"  placeholder=""/>
		    		<option <c:if test="${channelInfo.videoParameter=='MPEG2'}">selected="selected"</c:if>>MPEG2</option>
 		 			<option <c:if test="${channelInfo.videoParameter=='MPEG4'}">selected="selected"</c:if>>MPEG4</option>
 		 			<option <c:if test="${channelInfo.videoParameter=='MPEG6'}">selected="selected"</c:if>>MPEG6</option>
 		 			<option <c:if test="${channelInfo.videoParameter=='VC1'}">selected="selected"</c:if>>VC1</option>
 		 			<option <c:if test="${channelInfo.videoParameter=='H.264'}">selected="selected"</c:if>>H.264</option>
 		 		</select>
			</div> </div> 
			<div class="admin-s-body"> <span>音频参数:</span> <div class="admin-s"> 
				<select  name="audioParameter"  placeholder="" style="width:220px;"/>
		    		<option <c:if test="${channelInfo.audioParameter=='信号来源自live(即直播频道)'}">selected="selected"</c:if>>信号来源自live(即直播频道)</option>
 		 			<option <c:if test="${channelInfo.audioParameter=='信号来源自virtual(即虚拟频道)'}">selected="selected"</c:if>>信号来源自virtual(即虚拟频道)</option>
 		 			
				</select>
			
			</div> </div>  <br/> 
			
       		<div class="admin-s-body"> <span>信号源类型:</span> <div class="admin-s">  <input type="text" name="signalSource"   value="${channelInfo.signalSource}"/> </textarea> </div> </div>  
       		<div class="admin-s-body"> <span>码流标志:</span> <div class="admin-s">
       			<select  name="bitStream"  placeholder=""/>
		    		<option <c:if test="${channelInfo.bitStream=='PS'}">selected="selected"</c:if>>PS</option>
 		 			<option <c:if test="${channelInfo.bitStream=='TS'}">selected="selected"</c:if>>TS</option>
 		 			<option <c:if test="${channelInfo.bitStream=='MP4_FILE'}">selected="selected"</c:if>>MP4_FILE</option>
 		 			<option <c:if test="${channelInfo.bitStream=='WM9'}">selected="selected"</c:if>>WM9</option>
				</select>
       		</div> </div> 
       		<div class="admin-s-body"> <span>频道星级:</span> <div class="admin-s">
       			<select  name="starLevel"  placeholder=""/>
		    		<option <c:if test="${channelInfo.starLevel=='1'}">selected="selected"</c:if>>1</option>
 		 			<option <c:if test="${channelInfo.starLevel=='2'}">selected="selected"</c:if>>2</option>
 		 			<option <c:if test="${channelInfo.starLevel=='3'}">selected="selected"</c:if>>3</option>
 		 			<option <c:if test="${channelInfo.starLevel=='4'}">selected="selected"</c:if>>4</option>
 		 			<option <c:if test="${channelInfo.starLevel=='5'}">selected="selected"</c:if>>5</option>
 		 			<option <c:if test="${channelInfo.starLevel=='6'}">selected="selected"</c:if>>6</option>
 		 			<option <c:if test="${channelInfo.starLevel=='7'}">selected="selected"</c:if>>7</option>
 		 			<option <c:if test="${channelInfo.starLevel=='8'}">selected="selected"</c:if>>8</option>
 		 			<option <c:if test="${channelInfo.starLevel=='9'}">selected="selected"</c:if>>9</option>
 		 			<option <c:if test="${channelInfo.starLevel=='10'}">selected="selected"</c:if>>10</option>
				</select> 
       		</div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>台标:</span> <div class="admin-s"> <input type="text" name="tvLogo"   value="${channelInfo.tvLogo}" style="width:220px;"/> </div> </div> <br/>
			
			<div class="admin-s-body"> <span>国家:</span> <div class="admin-s"> <input type="text" name="country"  value="${channelInfo.country}" /> </div> </div>  
       		<div class="admin-s-body"> <span>州/省:</span> <div class="admin-s">  <input type="text" name="province"  value="${channelInfo.province}" /> </textarea> </div> </div>  
       		<div class="admin-s-body"> <span>城市:</span> <div class="admin-s"><input type="text" name="city"   value="${channelInfo.city}"/> </div> </div> 
       		<div class="admin-s-body"> <span>邮编:</span> <div class="admin-s"> <input type="text" name="email"   value="${channelInfo.email}" style="width:220px;"/> </div> </div> <br/>
       		 
			<div class="admin-s-body"> <span>内容提供商:</span> <div class="admin-s"> <input type="text" name="supplier"  value="${channelInfo.supplier}" /> </div> </div> 
			<div class="admin-s-body"> <span>网络频道关联:</span> <div class="admin-s"> <input type="text" name="correlation"   value="${channelInfo.correlation}"/> </div> </div> 
       		<div class="admin-s-body"> <span>内容服务平台标识:</span> <div class="admin-s">  <input type="text" name="contentDesignator"   value="${channelInfo.contentDesignator}"/> </textarea> </div> </div>  <br/>
       		<div class="admin-s-body"> <span>描述:</span> <div class="admin-s"> <textarea rows="5" cols="5" type="text" name="tvDescribe" style="width:800px;"> ${channelInfo.tvDescribe}</textarea> </div> </div>   
			
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>
			        	
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_channel_m');window.parent.refreshGrid('channel');
	">
		${meg}
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
