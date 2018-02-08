<%@page import="org.springframework.dao.DataIntegrityViolationException"%>
<%@page import="java.sql.SQLException"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title><s:text name="exception_title"/></title>
</head>
<body style="padding:10px;background-color:#D6D3CE;">
<center><h2><s:text name="exception_title"/></h2></center>
<font color="#FF0000"><b><s:text name="exception_prompt"/></b></font><br/>
<%
Exception exception=(Exception)request.getAttribute("exception");
if(exception instanceof DataIntegrityViolationException){
	if(exception.getCause().getCause() instanceof SQLException){

	    SQLException sqlE = (SQLException)exception.getCause().getCause();

	    if(sqlE.getErrorCode()==1){//ORACLE主键冲突异常代码

	    	out.println("信息已存在！");
	    	%>	    	
	   <a href="javascript:history.back(-1)">返回上一页</a>
	    		    	
	    	<%
	    }

	}else{%>	
		<s:property value="exception.message"/>
		<%
	}
}else{%>
	<s:property value="exception.message"/>
<%}

%>
</body>
</html>