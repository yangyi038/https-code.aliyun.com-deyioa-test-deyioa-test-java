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
	<form action="<%=basepath%>/admin/t_physical_channel/add_PhysicalChannel.action" enctype="multipart/form-data" method="post">
			
			<div class="admin-s-body"> <span>名称:</span> <div class="admin-s"> <input type="text" name="name"  /> </div> </div>  
			<div class="admin-s-body"> <span>cdn类型:</span> <div class="admin-s"> 
				<select  name="type"  placeholder=""/>
		    		<option >默认</option>
  				</select>
			</div> </div><br/>
			<div class="admin-s-body"> <span>播放级别:</span> <div class="admin-s"> 
				<select  name="level"  placeholder=""/>
		    		<option >默认</option>
 		 			<option >一级</option>
 		 			<option >二级</option>
 		 			<option >三级</option>
 		 			<option >四级</option>
 		 			<option >五级</option>
  				</select>
			 </div> </div> 
       		<div class="admin-s-body"> <span>来源名称:</span> <div class="admin-s">  <input type="text" name="sourceName"  /></div> </div>  <br/>
   			<div class="admin-s-body"> <span>频道来源:</span> <div class="admin-s"> 
   				<select  name="channelSource"  placeholder=""/>
		    		<option >录制</option>
 		 			<option >网络</option>
 		 			
  				</select>
   			</div> </div>  
       		<div class="admin-s-body"> <span>媒体格式:</span> <div class="admin-s">
       			<select  name="format"  placeholder=""/>
		    		<option >Envelope</option>
 		 			<option >Bitrate type</option>
 		 			<option >Video Codec</option>
 		 			<option >Video Bitrate</option>
 		 			<option >Resolution</option>
 		 			<option >Frame Rate</option>
 		 			<option >Audio Codec</option>
 		 			<option >Audio Bitrate</option>
 		 		</select>
       		</div> </div> <br/>
       		<div class="admin-s-body"> <span>码率:</span> <div class="admin-s"> <input type="text" name="codeCheck"  /> </div> </div> <br/><br/>
			<div class="admin-s-body"> <span>频道URL:</span> <div class="admin-s"> <input type="text" name="channelUrl" style="width:480px;" /> </div> </div><br/> 
			
			<input type="text" name="channelId" value="${model.channelId}" style="visibility:hidden;"/> 
			<input type="text" name="channelNumber" value="${model.channelNumber}" style="visibility:hidden;"/>
			
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>    
			  
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('add_t_channel_s');window.parent.refreshGrid('phsicalChannel');
	">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
/*	var depname=$("#depname").val();
	if(depname==""){
		info+="组织机构名称不能为空！";
	}
	var depparent=$("input[name=depparent]").val();
	if(depparent==""){
		info+="上级组织机构不能为空！";
	}
	if(info!=""){
		modalDialogAlert(info);
		return false;
	}*/
	
	return true;
}



</script>
<%@include file="../../../common/admin_footer.jsp"%>
