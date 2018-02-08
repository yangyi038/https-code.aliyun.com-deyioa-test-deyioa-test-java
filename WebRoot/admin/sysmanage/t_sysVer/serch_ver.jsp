<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>

<!-- by lqf at 20161021 -->
<div class="yz-frame-head">

	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		<e:yzbutton id="add_t_sysVer" name="添加" cssClass="btn btn-success" />
		<div style="float: right; margin-right: 200px;">
			<input name="order_sn" id="search_content"
				style="height: 32px; width: 250px" placeholder="可输入参数进行模糊查询" />
			<button class="btn btn-info btn-xs" onclick="search()"
				style="width: 60px; height: 32px;">搜索</button>
		</div>
	</div>

	<!-- end row -->
</div>
