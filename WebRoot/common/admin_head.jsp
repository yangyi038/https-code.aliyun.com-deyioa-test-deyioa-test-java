<%@page import="com.fantastic.ContextHolderUtils"%>
<%@page import="com.fs.comm.model.Sysuser"%>
<%@page import="com.framework.BrowseTool"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	//取得当前Web应用的名称
	String basepath = request.getContextPath();
%>

<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title><spring:message code="title"></spring:message></title>

<%-- jquery --%>
<script src="<%=basepath%>/admin/js/jquery.js"></script>

<!-- Bootstrap -->
<link href="<%=basepath%>/admin/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basepath%>/admin/js/bootstrap.min.js" rel="stylesheet">
<%-- <script src="<%=basepath%>/js/bootstrap/js/bootstrap.min.js"></script> --%>
<link rel="stylesheet" href="<%=basepath%>/admin/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=basepath%>/admin/css/ace.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/js/jqgrid/css/jquery-ui-1.10.3.full.min.css" />

<script src="<%=basepath%>/admin/js/ace-extra.min.js"></script>
<script src="<%=basepath%>/js/yz.js"></script>
<script src="<%=basepath%>/js/modalDialog.js"></script>



<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
    var basepath='<%=basepath%>';
</script>
</head>
<body>