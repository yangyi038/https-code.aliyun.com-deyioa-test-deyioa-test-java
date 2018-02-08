
<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>
 <%
	//取得当前Web应用的名称
	String basepath = request.getContextPath(); 
%>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script><!--/admin/js/jquery.js  -->
<script type="text/javascript" src="<%=basepath%>/js/jquery.cookie.js"></script>
<link href="<%=basepath%>/resources/css/login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basepath%>/resources/css/font-awesome.min.css" />
<title><spring:message code="title"></spring:message></title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header" style="background:#426374;font-size:22px;color:#fff;line-height: 60px;">
	<span style="padding-left: 40px">
		<spring:message code="title"></spring:message>
	</span>
</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="index.html" method="post">
      <div class="row cl">
        <label class="form-label col-3"><i class="icon-user" style="font-size: 22px;"></i></label>
        <div class="formControls col-8">
          <input id="loginname" name="loginname" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="icon-lock" style="font-size: 22px;"></i></label>
        <div class="formControls col-8">
          <input id="loginpwd" name="loginpwd" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" style="width:150px;" name="rand">
          <img src="<%=basepath%>/common/rand.jsp?tmp=<%=System.currentTimeMillis()%>"> <a id="kanbuq" href="javascript:location.reload();">看不清，换一张</a> </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" name="rmbUser" id="rmbUser" value="1">
           是否记住用户名</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="admin_login_login_submit" type="button" style="width:360px" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
		  <!--  <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;"> -->
        </div>
      </div>
      <div class="error-msg" >
      	
      </div>
    </form>
  </div>
</div>
<div class="footer">得易文化传播有限公司  by H-ui.admin.v2.3</div>


</body>
<script type="text/javascript">
jQuery("#admin_login_login_submit").click(function(){
	$(".error-msg").text("登录中，请稍后......");
	var loginname=$("input[name=loginname]").val();
	var loginpwd=$("input[name=loginpwd]").val();
	
	if ($("#rmbUser").prop('checked')==true) {
		$.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie 
		$.cookie("loginname", loginname, { expires: 7 }); 
		
	}else{ 
		$.cookie("rmbUser", "false", { expire: -1 }); 
		$.cookie("loginname", "", { expires: -1 }); 
	}
	
	
	var rmbUser=$("#rmbUser").val();
	var rand=$("input[name=rand]").val();
	var postdata={loginname:loginname,loginpwd:loginpwd,rand:rand};
   $.post('<%=basepath %>/user/login.action',postdata, function(data){
		if(data.status==0){
			if ($("#rmbUser").prop('checked')==true) {
				//$.cookie("loginpwd", data.data, { expires: 7 });
			}
			window.location.href="<%=basepath%>/admin/index.jsp";
		}else{
			$(".error-msg").text(data.info);
		}
		
	});	
});

$(document).ready(function(){ 
	if ($.cookie("rmbUser") == "true") { 
		$("#rmbUser").attr("checked", true); 
		$("input[name=loginname]").val($.cookie("loginname")); 
	} 
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	$("#admin_login_login_submit").trigger("click");
	     }
	}
	}); 
</script>
</html>

