<!DOCTYPE html>
<%@page import="com.fantastic.ContextHolderUtils"%>
<%@page import="com.fs.comm.model.Sysuser"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/admin_head.jsp"%>

<%
	//获取当前登录用户
	Sysuser user = ContextHolderUtils.getLoginUser();
%>

		<div class="navbar navbar-default" id="navbar">

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="homelogo">
								<img src="img/yz.png" width="40" height="40"/>
							</i>
							<spring:message code="title"></spring:message>
						</small>
					</a>
					<!-- /.brand -->
				</div>
				<!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation" >
					<ul class="nav ace-nav">
					
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="img/yz.png" width="40" height="40" alt="Jason's Photo" />
								<span class="user-info">
									<small>Welcome,</small>
									<%=user.getLoginname()%>
									<!-- 超级管理员 -->
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="javascript:void(0);" id="updatepwd">
										<i class="icon-cog"></i>
										密码设置
									</a>
								</li>
								<li class="divider"></li>

								<li>
									<a href="<%=basepath %>/user/logout.action?tmp=<%=System.currentTimeMillis()%>">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div>
			<!-- /.container -->
		</div>
		<div class="main-container" id="main-container"  style="overflow:auto">
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar"> 
					<div class="sidebar-shortcuts" id="sidebar-shortcuts" >
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->
					<ul class="nav nav-list">
						
					</ul>
					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>
				</div>
				
				<div class="main-content">
					<!--选项卡-->
					<div class="breadcrumbs" id="breadcrumbs">
						<div class="frame-nav" id="Hui-tabNav">
						<div class="row">
							<div class="col-xs-11">
								<div class="bs-example bs-example-tabs Hui-tabNav-wp" data-example-id="togglable-tabs">
									<ul id="frame-tab" class="nav nav-tabs" role="tablist">
										<li class="active" >
											<a href="#home" data-toggle="tab" data-href="home">
												<span>网站信息</span>
												
												<em></em>
											</a>
										</li>
										
									</ul>
								</div>
							</div>
							<div class="col-xs-1 nav-more">
								<a class="frame-nav-left button-prev buttonnav" href="javascript:void(0);" id="js-tabNav-prev">
									<i class="icon icon-angle-left" aria-hidden="true"></i>
								</a>
								<a class="frame-nav-right button-next buttonnav" href="javascript:void(0);" id="js-tabNav-next">
									<i class="icon icon-angle-right" aria-hidden="true"></i>
								</a>
							</div>
						</div>
					</div>
					</div>
					
					<div class="index-page-content">
					
					<!--页面主体-->
						<div id="iframe_box" class="tab-content" >
							
								<div class="tab-pane active show_iframe" id="home" aria-labelledby="home" >
									<div class="loading" style="display: none;"></div>
									<iframe  width="" height="" frameborder="0" class="boxiframe" src="./welcome.jsp"></iframe>
								</div>
							
						</div>
					</div>
					</div>
				</div>
			</div>
<script type="text/javascript">
$(document).ready(function(){ 
$.post('<%=basepath %>/admin/getmenu.action',{}, function(data){
		var json = data; 
		if (json.length>0){
    		$(json).each(function(index,item){
    			var navlist=$(".nav-list");
    			var li=$("<li>").addClass("");
    			var a=$("<a>");
    			if(item.menulink){
    				a.attr("_href",item.menulink);
    				a.attr("href","javascript:void(0);");
    				a.attr("id",'nav_'+item.id);
    			}else{
    				a.attr("href","javascript:void(0);");
    			}
    			if(item.cssclass){
    				a.append($("<i class=\""+item.cssclass+"\"></i>"));
    			}else{
    				a.append($("<i class=\"icon-desktop\"></i>"));
    			}
    			a.append($("<i class=\"\"></i>"));
    			a.append($("<span class=\"menu-text\">"+item.menuname+"</span>"));
    			var ul=$("<ul>").addClass("submenu");
    			var cjson = item.childSysmenu; 
    			if (cjson.length>0){
    				a.addClass("dropdown-toggle");
    				a.append($("<b class=\"arrow icon-angle-down icon-angle-right\"></b>"));
    				li.append(a);
    	    		$(cjson).each(function(cindex,citem){
    	    			var cli=$("<li>").addClass("");
    	    			var ca=$("<a>").attr("_href",citem.menulink).attr('id','nav_'+citem.id).attr("href","javascript:void(0);").append($("<i class=\"icon-double-angle-right\"></i>")).append(citem.menuname);
    	    			cli.append(ca);
    	    			ul.append(cli);
    	    		 });
    	    		li.append(ul);
    			}else{
    				li.append(a);
    			}
    			navlist.append(li);
    		 });
    	}
		
});	
});	

$("#updatepwd").click(function(){
	creatIframe("<%=basepath%>/admin/sysuser/toupdatePsw.action","密码修改","updatepwdnav");
});

</script>
<%@include file="../common/admin_footer.jsp"%>

