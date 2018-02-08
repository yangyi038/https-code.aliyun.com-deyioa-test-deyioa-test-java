<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>


<!-- by lqf at 20161021 -->
<div class="yz-frame-head">
	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		状态: 
		<button class="btn btn-info btn-xs" onclick="searchLine()" id="button1" value="不限" style="width:60px;height:30px; background:green;color:red">不限</button>
		<button class="btn btn-info btn-xs" onclick="searchUnline()" id="button2" value="未上线" style="width:60px;height:30px;">未上线</button>
		<button class="btn btn-info btn-xs" onclick="searchOnlined()" id="button3" value="已上线" style="width:60px;height:30px;">已上线</button>
		<button class="btn btn-info btn-xs" onclick="searchUnlined()" id="button4" value="已下线" style="width:60px;height:30px;">已下线</button>
		<div style="float:right; margin-right:200px">
			<button class="btn btn-info btn-xs" onclick="setBack()"   style="width:60px;height:32px;">返回</button>
		</div>
	</div>
	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		模式: 
		<button class="btn btn-info btn-xs" onclick="model()" id="button5" value="不限" style="width:60px;height:30px; background:green;color:red">不限</button>
		<button class="btn btn-info btn-xs" onclick="normalModel()" id="button6" value="正常" style="width:60px;height:30px;">正常</button>
		<button class="btn btn-info btn-xs" onclick="testModel()" id="button7" value="测试" style="width:60px;height:30px;">测试</button>
		<div style="float:right; margin-right:200px">
			<input name="order_sn" id="name" style="height:32px;width:260px" placeholder="可根据名称进行模糊查询"/>
			<button class="btn btn-info btn-xs" onclick="search()"   style="width:60px;height:32px;">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	
	<!-- end row -->
</div>
