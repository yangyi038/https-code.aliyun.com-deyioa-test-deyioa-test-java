<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="e" uri="/yz"%> 
<%@include file="../../../common/admin_head.jsp"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="admin_title"/></title>
<link href="<%=basepath%>/css/loginStyle.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="<%=basepath%>/js/depTree.js"></script>
</head>
<body class="main1bg ">

	<div class="formDiv">
	  	<s:hidden name="id"/>
		<table>
		 <COLGROUP><COL width='10%'><COL width='23%'><COL width='15%'><COL width='23%'><COL width='10%'><COL width='23%'></COLGROUP>
		
		<tr>
				<th>操作用户 :</th>
			    <td>${username}</td>
			    
		 		<th>操作用户ip</th>
			    <td>${ipAdd}</td>
		
				<th>操作类型:</th>
			    <td>${operType}</td>
		
		</tr>			 
	  		<tr>
				<th>操作对象 :</th>
			    <td>${operObject}</td>
			    
		 		<th>日志类型</th>
			    <td>${slogType}</td>
		
				<th>操作时间:</th>
			    <td>${dotime}</td>
		
		</tr>		 
	     <tr>  
	      <th>日志内容:</th>
		     <td colspan="5">${slogComment}</td>
	       </tr>
</table>
	</div>

</body>
</html>
