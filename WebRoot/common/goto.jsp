<%
	//取得当前Web应用的名称
	String basepath = request.getContextPath();
%>
<script type="text/javascript">
window.location.href="<%=basepath%>/admin/index.jsp";
</script>
