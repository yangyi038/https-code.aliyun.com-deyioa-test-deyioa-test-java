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
	<form action="<%=basepath%>/admin/t_physical_channel/updatePhsicalChannel.action" enctype="multipart/form-data" method="post">
	
			<input type="text" name="id"  value="${physicalChannel.id}" style="visibility:hidden;" /><br/>
			<div class="admin-s-body"> <span>名称:</span> <div class="admin-s"> <input type="text" name="name" value="${physicalChannel.name}" /> </div> </div>  
			<div class="admin-s-body"> <span>cdn类型:</span> <div class="admin-s"> 
				<select  name="type"  placeholder="" value="${physicalChannel.type}"/>
		    		<option >默认</option>
  				</select>
			</div> </div><br/>
			<div class="admin-s-body"> <span>播放级别:</span> <div class="admin-s"> 
				<select  name="level" />
					<option <c:if test="${physicalChannel.level=='默认'}">selected="selected"</c:if>>默认</option>
 		 			<option <c:if test="${physicalChannel.level=='一级'}">selected="selected"</c:if>>一级</option>
 		 			<option <c:if test="${physicalChannel.level=='二级'}">selected="selected"</c:if>>二级</option>
 		 			<option <c:if test="${physicalChannel.level=='三级'}">selected="selected"</c:if>>三级</option>
 		 			<option <c:if test="${physicalChannel.level=='四级'}">selected="selected"</c:if>>四级</option>
 		 			<option <c:if test="${physicalChannel.level=='五级'}">selected="selected"</c:if>>五级</option>
			     </select>
			        			 
			      
			 </div> </div> 
       		<div class="admin-s-body"> <span>来源名称:</span> <div class="admin-s">  <input type="text" name="sourceName"  value="${physicalChannel.sourceName}"/></div> </div>  <br/>
   			<div class="admin-s-body"> <span>频道来源:</span> <div class="admin-s"> 
   				<select  name="channelSource"  placeholder="" value="${physicalChannel.channelSource}"/>
		    		<option >录制</option>
 		 			<option >网络</option>
  				</select>
   			</div> </div> 
       		<div class="admin-s-body"> <span>媒体格式:</span> <div class="admin-s">
       			<select  name="format"  placeholder="" />
		    		<option <c:if test="${physicalChannel.format=='Envelope'}">selected="selected"</c:if>>Envelope</option>
 		 			<option <c:if test="${physicalChannel.format=='Bitrate type'}">selected="selected"</c:if>>Bitrate type</option>
 		 			<option <c:if test="${physicalChannel.format=='Video Codec'}">selected="selected"</c:if>>Video Codec</option>
 		 			<option <c:if test="${physicalChannel.format=='Video Bitrate'}">selected="selected"</c:if>>Video Bitrate</option>
 		 			<option <c:if test="${physicalChannel.format=='Resolution'}">selected="selected"</c:if>>Resolution</option>
 		 			<option <c:if test="${physicalChannel.format=='Frame Rate'}">selected="selected"</c:if>>Frame Rate</option>
 		 			<option <c:if test="${physicalChannel.format=='Audio Codec'}">selected="selected"</c:if>>Audio Codec</option>
 		 			<option <c:if test="${physicalChannel.format=='Audio Bitrate'}">selected="selected"</c:if>>Audio Bitrate</option>
 		 		</select>
       		</div> </div> <br/>
       		<div class="admin-s-body"> <span>码率:</span> <div class="admin-s"> <input type="text" name="codeCheck"  value="${physicalChannel.codeCheck}"/> </div> </div> <br/>
			<div class="admin-s-body"> <span>频道URL:</span> <div class="admin-s"> <input type="text" name="channelUrl"  value="${physicalChannel.channelUrl}" style="width:480px;"/> </div> </div> 
       		
			<div class="real-body-foot">
			    <button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			</div>
		</form>
			        	
</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" 
  		callback="window.parent.closeDialogm('edit_phsicalChannel_m');window.parent.refreshGrid('phsicalChannel');
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
