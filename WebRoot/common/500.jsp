<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
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
		<title>报错了</title>

		<!-- Bootstrap -->

		<link href="<%=basepath%>/admin/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=basepath%>/admin/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basepath%>/admin/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=basepath%>/admin/css/frame.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<script src="<%=basepath%>/admin/js/ace-extra.min.js"></script>
		<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	</head>

	<body>

		<div class="frame-container">
			<div class="page-content">
						<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-random"></i>
												500
											</span>
											应用程序出错
										</h1>

										<hr />
										<h3 class="lighter smaller">
											但是我们正在抓紧时间修复
											<i class="icon-wrench icon-animated-wrench bigger-125"></i>
											!!!
										</h3>

										<div class="space"></div>

										<div>
											<h4 class="lighter smaller">同时请尝试以下操作之一:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<!--<li>
													<i class="icon-hand-right blue"></i>
													阅读常见问题解答
												</li>-->

												<li>
													<i class="icon-hand-right blue"></i>
													给我们更多关于这个特定的错误发生时的信息
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<!--<div class="center">
											<a href="#" class="btn btn-grey">
												<i class="icon-arrow-left"></i>
												Go Back
											</a>

											<a href="#" class="btn btn-primary">
												<i class="icon-dashboard"></i>
												Dashboard
											</a>
										</div>-->
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
		</div><!--frame-container over-->

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<!--<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>-->
		<script src="js/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/side.js"></script>
	</body>

</html>