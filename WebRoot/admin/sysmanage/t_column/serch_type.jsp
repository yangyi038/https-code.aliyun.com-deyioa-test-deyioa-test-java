<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>


<!-- by lqf at 20161021 -->
<div class="yz-frame-head">

<div class="row" style="margin-left: 0px; margin-top: 10px">
		上线状态: 
		<button class="btn btn-info btn-xs" onclick="searchLine()" id="button1" value="不限" style="width:60px;height:30px; background:green;color:red">不限</button>
		<button class="btn btn-info btn-xs" onclick="searchUnline()" id="button2" value="未上线" style="width:60px;height:30px;">未上线</button>
		<button class="btn btn-info btn-xs" onclick="searchOnlined()" id="button3" value="已上线" style="width:60px;height:30px;">已上线</button>
		<button class="btn btn-info btn-xs" onclick="searchUnlined()" id="button4" value="已下线" style="width:60px;height:30px;">已下线</button>
		<button class="btn btn-info btn-xs" onclick="setBack()"   style="float: right; margin-right: 200px;width:60px;height:32px;">返回</button>
	</div>
	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		审核状态: 
		<button class="btn btn-info btn-xs" onclick="check()" id="button5" value="不限" style="width:60px;height:30px; background:green;color:red">不限</button>
		<button class="btn btn-info btn-xs" onclick="checked()" id="button6" value="已审核" style="width:60px;height:30px;">已审核</button>
		<button class="btn btn-info btn-xs" onclick="unCheck()" id="button7" value="未审核" style="width:60px;height:30px;">未审核</button>
	</div>
	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		
		内容类型:
		<select  name="type" id="column_type" placeholder="" style="width:190px;"/>
					<option >直播节目</option>
					<option >图片</option>
					<option >字幕</option>
					<option >app应用</option>
					<option >图文信息</option>
 		 		</select>
		<button class="btn btn-info btn-xs" onclick="searchType()"   style="width:60px;height:32px;">搜索</button> 
		<input name="order_sn" id="name" style="margin-left:22cm;height:32px;width:260px" placeholder="可根据内容对象进行模糊查询"/>
		<button class="btn btn-info btn-xs" onclick="search()"   style="width:60px;height:32px;">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	
	
	
	<!-- end row -->
</div>
