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
	
			<div class="admin-s-body"> <span>频道号:</span> <div class="admin-s"> <input type="text" value="${channelInfo.number}" /> </div> </div>  
			<div class="admin-s-body"> <span>频道名称:</span> <div class="admin-s"> <input type="text"  value="${channelInfo.name}"/> </div> </div> 
			<div class="admin-s-body"> <span>频道类型:</span> <div class="admin-s"> <input type="text"  value="${channelInfo.type}"/></div> </div> 
       		<div class="admin-s-body"> <span>双语标志:</span> <div class="admin-s">  <input type="text"  value="${channelInfo.bilingualSigns}" style="width:220px;"/></div> </div>  <br/>
       		
       		<div class="admin-s-body"> <span>拷贝保护标志:</span> <div class="admin-s"><input type="text"  value="${channelInfo.protectiveEmblem}"/></div> </div> 
       		<div class="admin-s-body"> <span>语言:</span> <div class="admin-s"> <input type="text" value="${channelInfo.language}" /> </div> </div> 
			<div class="admin-s-body"> <span>视频参数:</span> <div class="admin-s"> <input type="text" value="${channelInfo.videoParameter}" /></div> </div> 
			<div class="admin-s-body"> <span>音频参数:</span> <div class="admin-s"> <input type="text" value="${channelInfo.audioParameter}" style="width:220px;"/> </div> </div>  <br/> 
			
       		<div class="admin-s-body"> <span>信号源类型:</span> <div class="admin-s">  <input type="text"  value="${channelInfo.signalSource}"/> </div> </div>  
       		<div class="admin-s-body"> <span>码流标志:</span> <div class="admin-s"><input type="text"  value="${channelInfo.bitStream}"/></div> </div> 
       		<div class="admin-s-body"> <span>频道星级:</span> <div class="admin-s"><input type="text"  value="${channelInfo.starLevel}"/></div> </div>  
			<div class="admin-s-body"> <span><font style="color:red;">*</font>台标:</span> <div class="admin-s"> <input type="text" name="tvLogo"   value="${channelInfo.tvLogo}" style="width:220px;"/> </div> </div> <br/>
			
			<div class="admin-s-body"> <span>国家:</span> <div class="admin-s"> <input type="text" name="country"  value="${channelInfo.country}" /> </div> </div>  
       		<div class="admin-s-body"> <span>州/省:</span> <div class="admin-s">  <input type="text" name="province"  value="${channelInfo.province}" /> </textarea> </div> </div>  
       		<div class="admin-s-body"> <span>城市:</span> <div class="admin-s"><input type="text" name="city"   value="${channelInfo.city}"/> </div> </div> 
       		<div class="admin-s-body"> <span>邮编:</span> <div class="admin-s"> <input type="text" name="email"   value="${channelInfo.email}" style="width:220px;"/> </div> </div> <br/>
       		 
			<div class="admin-s-body"> <span>内容提供商:</span> <div class="admin-s"> <input type="text" name="supplier"  value="${channelInfo.supplier}" /> </div> </div> 
			<div class="admin-s-body"> <span>网络频道关联:</span> <div class="admin-s"> <input type="text" name="correlation"   value="${channelInfo.correlation}"/> </div> </div> 
       		<div class="admin-s-body"> <span>内容服务平台标识:</span> <div class="admin-s">  <input type="text" name="contentDesignator"   value="${channelInfo.contentDesignator}"/></div> </div>
       		<div class="admin-s-body"> <span>创建时间:</span> <div class="admin-s">  <input type="text" name="contentDesignator"   value="${channelInfo.tempTime}" style="width:220px;"/></div> </div>  <br/>
       		
       		<div class="admin-s-body"> <span>释放状态:</span> <div class="admin-s">  <input type="text" name="contentDesignator"   value="${channelInfo.status}"/></div> </div>
       		<div class="admin-s-body"> <span>上线状态:</span> <div class="admin-s">  <input type="text" name="contentDesignator"   value="${channelInfo.lineStatus}"/></div> </div>  <br/>
       		<div class="admin-s-body"> <span>描述:</span> <div class="admin-s"> <textarea rows="5" cols="5" type="text" name="tvDescribe" style="width:482px;"> ${channelInfo.tvDescribe}</textarea> </div> </div>   
			
			        	
</div>

<%@include file="../../../common/admin_footer.jsp"%>
