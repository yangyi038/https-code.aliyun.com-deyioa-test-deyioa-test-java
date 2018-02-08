<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	//取得当前Web应用的名称
	String basepath = request.getContextPath();
%>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>报错了</title>
		<!-- Bootstrap -->
		<link href="<%=basepath%>/admin/css/bootstrap.min.css" rel="stylesheet">
				<link rel="stylesheet" href="<%=basepath%>/admin/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basepath%>/admin/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=basepath%>/admin/css/frame.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	</head>
<body>
		<div class="frame-container">
			<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												404
											</span>
											没有找到页面
										</h1>

										<hr />
										<h3 class="lighter smaller">很抱歉,并未找到您所要的页面</h3>

										<div>
											<form class="form-search">
<!-- 												<span class="input-icon align-middle"> 
													<i class="icon-search"></i>

													<input type="text" style="padding: 5px 6px 5px 24px;" class="search-query" placeholder="Give it a search..." />
												</span>
												<button class="btn btn-sm" onclick="return false;">立即查找</button>-->
											</form>

											<div class="space"></div>
											<h4 class="smaller">尝试下列方法:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="icon-hand-right blue"></i>
													复核拼写错误的url
												</li>

												<!-- <li>
													<i class="icon-hand-right blue"></i>
													阅读常见问题解答
												</li>-->

												<li>
													<i class="icon-hand-right blue"></i>
													联系管理员
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<!--<div class="center">
											<a href="#" class="btn btn-grey">
												<i class="icon-arrow-left"></i>
												返回
											</a>

											<a href="#" class="btn btn-primary">
												<i class="icon-dashboard"></i>
												Dashboard
											</a>
										</div>-->
									</div>
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div>
		</div><!--frame-container over-->
	</body>
</html>