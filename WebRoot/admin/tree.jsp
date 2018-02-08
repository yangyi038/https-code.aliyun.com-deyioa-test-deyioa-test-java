<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@include file="../../../common/admin_head.jsp"%>
 <link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/js/jqgrid/css/ui.jqgrid.css"/>
 <link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/tree.css"/>
    <script src="<%=basepath%>/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="<%=basepath%>/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>	
   <script type="text/javascript" src="<%=basepath%>/js/gridLayout.js"></script>	
     <script type="text/javascript" src="<%=basepath%>/admin/js/tree.js"></script>
		
		
		<div class="frame-container">
			<div class="page-content" >
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<div class="tree-content">
							<ul id="tree-ul">
								<li class="tree-menu">
									<div class="tree-node-el">
										<i class="glyphicon glyphicon-plus glyphicon-minus clicki"></i>
										<input type="checkbox">
										<i class="glyphicon glyphicon-folder-open"></i>
										<span class="open broch">一级列表</span>
									</div>
									<ul class="tree-node-menu open">
										<li class="tree-node">
											<div class="tree-node-el">
												<i class="glyphicon glyphicon-plus clicki"></i>
												<input type="checkbox">
												<i class="glyphicon glyphicon-folder-close"></i>
												<span class="open broch">二级列表</span>
											</div>
											<ul class="tree-node-menu">
												<li class="tree-node">
													<div class="tree-node-el">
														<i class="glyphicon glyphicon-minus clicki"></i>
														<input type="checkbox">
														<i class="glyphicon glyphicon-briefcase"></i>
														<span class="open broch">二级列表</span>
													</div>
												</li>
											</ul>
										</li>
										<li class="tree-node">
											<div class="tree-node-el">
												<i class="glyphicon glyphicon-minus clicki"></i>
												<input type="checkbox">
												<i class="glyphicon glyphicon-briefcase"></i>
												<span class="open broch">二级列表</span>
											</div>
										</li>
										<li class="tree-node">asdasd</li>
										<li class="tree-node">asdasd</li>
										<li class="tree-node">asdasd</li>
									</ul>
								</li>
								<li class="tree-menu">
									<div class="tree-node-el">
										<i class="glyphicon glyphicon-plus glyphicon-minus clicki"></i>
										<input type="checkbox">
										<i class="glyphicon glyphicon-folder-open"></i>
										<span class="open broch">一级列表</span>
									</div>
								</li>
								<li class="tree-menu">
									<div class="tree-node-el">
										<i class="glyphicon glyphicon-plus glyphicon-minus clicki"></i>
										<input type="checkbox">
										<i class="glyphicon glyphicon-folder-open"></i>
										<span class="open broch">一级列表</span>
									</div>
								</li>
								<li class="tree-menu">
									<div class="tree-node-el">
										<i class="glyphicon glyphicon-plus glyphicon-minus clicki"></i>
										<input type="checkbox">
										<i class="glyphicon glyphicon-folder-open"></i>
										<span class="open broch">一级列表</span>
									</div>
								</li>
								
							</ul>
						</div>
					</div>
					<div class="col-xs-12 col-sm-8">
						<div class="tree-content">
							<div class="modal-real-body inputbox">
								<div class="admin-s-body">
									<span>一级列表</span>
									<div class="admin-s">
										<input type="text" name="rolename" value="" id="" placeholder="">
									</div>
								</div>
								<div class="admin-s-body">
									<span>一级列表</span>
									<div class="admin-s">
										<input type="text" name="rolename" value="" id="" placeholder="">
									</div>
								</div>
								<div class="admin-s-body">
									<span>一级列表</span>
									<div class="admin-s">
										<input type="text" name="rolename" value="" id="" placeholder="">
									</div>
								</div>
								<div class="admin-s-body">
									<span>一级列表</span>
									<div class="admin-s">
										<input type="text" name="rolename" value="" id="" placeholder="">
									</div>
								</div>
								<div class="admin-s-body">
									<span>一级列表</span>
									<div class="admin-s">
										<input type="text" name="rolename" value="" id="" placeholder="">
									</div>
								</div>
								<div class="admin-s-body">
									<span>一级列表</span>
									<div class="admin-s">
										<input type="text" name="rolename" value="" id="" placeholder="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


<%@include file="../../../common/admin_footer.jsp"%>
