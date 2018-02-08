<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>


<!-- by lqf at 20161021 -->
<div class="yz-frame-head">
	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		激活状态: 
		<button class="btn btn-info btn-xs" onclick="activateStatus()" id="button1" value="不限" style="width:60px;height:30px; background:green;color:red">不限</button>&nbsp;
		<button class="btn btn-info btn-xs" onclick="setActivate()" id="button2" value="激活" style="width:60px;height:30px;">激活</button>&nbsp;
		<button class="btn btn-info btn-xs" onclick="setStop()" id="button3" value="停止" style="width:60px;height:30px;">停止</button>
		<div style="float:right;margin-right:200px;">
			<button class="btn btn-info btn-xs" onclick="setBack()"  style="height:32px;width:100px">返回直播频道</button>
		</div>
		
	</div><br/>
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		URL状态: 
		<button class="btn btn-info btn-xs" onclick="lineState()" id="button4" value="不限" style="width:60px;height:30px; background:green;color:red">不限</button>&nbsp;
		<button class="btn btn-info btn-xs" onclick="effective()" id="button5" value="有效" style="width:60px;height:30px;">有效</button>&nbsp;
		<button class="btn btn-info btn-xs" onclick="unEffective()" id="button6" value="无效" style="width:60px;height:30px;">无效</button>&nbsp;
		<button class="btn btn-info btn-xs" onclick="scrap()" id="button7" value="废弃" style="width:60px;height:30px;">废弃</button>
		
	</div>
	
	
	<div class="row" style="margin-left: 0px; margin-top: 10px">
		<e:yzbutton id="add_physical_channel" name="添加" cssClass="btn btn-success" />
	</div>
	
	<!-- end row -->
</div>
