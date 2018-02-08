<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/admin_head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css">
<title>温馨提示</title>
</head>
<body>
<br/><br/><br/>
<table width="560" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img height="117" src="<%=basepath%>/images/admin/sorry.gif" width="174"></td>
    <td width="360"><p align="left" class="titleText">对不起，您的登录已经过期!<br>
    <span id="num" onclick="flushTime()" style="cursor:pointer;">请重新登录</span>,谢谢! </p>
    </td>
  </tr>
</table>
<script language="javascript">
	function flushTime(){
		window.top.location="<%=basepath%>/admin/login.jsp";		
	}

</script>
</body>
</html>