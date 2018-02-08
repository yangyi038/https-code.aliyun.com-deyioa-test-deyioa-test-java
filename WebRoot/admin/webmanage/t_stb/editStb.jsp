<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@ taglib prefix="e" uri="/yz"%>
<%@ taglib prefix="fns" uri="/WEB-INF/fns.tld"%>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>/admin/css/frame.css" />
<script type="text/javascript"
	src="<%=basepath%>/js/tree/jquery.tree.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/tree/tree_data.js"></script>
<link href="<%=basepath%>/js/tree/tree.css" rel="stylesheet"
	type="text/css" />
<script  type="text/javascript"
	src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>

<div class="modal-real-body">
	<form action="<%=basepath%>/admin/t_stb/updateStbById.action"
		enctype="multipart/form-data" method="post">
		<input id="sid" type="hidden" name="sid" value="${t_stb.sid}" />

		<div>
			<span style="color: red;"><strong>配置：</strong></span>
		</div>
		<table>
			<tr>
				<th><span style="color:red;">*</span><strong>机顶盒账号：</strong></th>
				<td><input type="text" name="stbnum" value="${t_stb.stbnum}" required="required" disabled="true"/></td>
				<th><span style="color:red;">*</span><strong>所属机顶盒组：</strong></th>
				<td><select name='stbgroup' id='stbgroup' required="required" >
						<option value='' selected>-请选择-</option>
						<c:forEach items="${stbgrouplist}" var="v">
							<option value="${v.sid}"
								<c:if test="${v.sid==t_stb.stbgroup}">selected="selected"</c:if>>${v.groupname}</option>
						</c:forEach>
				</select></td>
				<th><span style="color:red;">*</span><strong>机顶盒类型：</strong></th>
				<td>
					<%-- <select name="stbtype" id="stbtype" required="required">
							<option value='' selected>-请选择-</option>
							<c:forEach
								items="${fns:getCodeMap(pageContext.request,'terminaltype')}"
								var='item'>
								<option value='${item.key}'>${item.value}</option>
							</c:forEach>
					</select> --%>
					<select  name="stbtypeStr" id="stbtypeStr"  value="${t_stb.stbtypeStr}">
				    		<option <c:if test="${t_stb.stbtype=='1'}">selected="selected"</c:if>>IPTV</option>
				    		<option <c:if test="${t_stb.stbtype=='2'}">selected="selected"</c:if>>iPhone</option>
		 		 			<option <c:if test="${t_stb.stbtype=='3'}">selected="selected"</c:if>>iPad</option>
		  					<option <c:if test="${t_stb.stbtype=='4'}">selected="selected"</c:if>>AndroidMobil</option>
		  					<option <c:if test="${t_stb.stbtype=='5'}">selected="selected"</c:if>>AndroidStb</option>
		  					<option <c:if test="${t_stb.stbtype=='6'}">selected="selected"</c:if>>AndroidPad</option>
		  					<option <c:if test="${t_stb.stbtype=='7'}">selected="selected"</c:if>>PC</option>
		  					<option <c:if test="${t_stb.stbtype=='8'}">selected="selected"</c:if>>eCosSTB</option>
		  					<option <c:if test="${t_stb.stbtype=='9'}">selected="selected"</c:if>>LinuxSTB</option>
			   		</select>
				</td>
			</tr>
			<tr>
				<th><span style="color:red;">*</span><strong>密码：</strong></th>
				<td><input type="password" name="pwd" value="${t_stb.pwd}" required="required" /></td>
				<th><span style="color:red;">*</span><strong>确认密码：</strong></th>
				<td><input type="password" name="pwdsure" value="${t_stb.pwdsure}" required="required" /></td>
			</tr>
			<tr>
				<th><strong>EPG栏目根节点：</strong></th><!--<span style="color:red;">*</span>  -->
				<td><select name='epgroot' id='epgroot'  ><!-- required="required" -->
						<option value='' selected>-请选择-</option>
						<c:forEach items="${columnlist}" var="v">
							<option value="${v.id}"
								<c:if test="${v.id==t_stb.epgroot}">selected="selected"</c:if>>${v.name}</option>
						</c:forEach>
				</select></td>
				<th><strong>EPG栏目定制根节点：</strong></th>
				<td><select name='epgprivate' id='epgprivate'>
						<option value='' selected>-请选择-</option>
						<c:forEach items="${columnlist}" var="v">
							<option value="${v.id}"
								<c:if test="${v.id==t_stb.epgprivate}">selected="selected"</c:if>>${v.name}</option>
						</c:forEach>
				</select></td>
				<th><strong>手机栏目根节点：</strong></th>
				<td><select name='telroot' id='telroot'>
						<option value='' selected>-请选择-</option>
						<c:forEach items="${columnlist}" var="v">
							<option value="${v.id}"
								<c:if test="${v.id==t_stb.telroot}">selected="selected"</c:if>>${v.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th><strong> 账户类型：</strong></th>
				<td>
				<select  name="accounttypeStr" id="accounttypeStr"  value="${t_stb.accounttypeStr}">
			    		<option <c:if test="${t_stb.accounttype=='1'}">selected="selected"</c:if>>普通账户</option>
			    		<option <c:if test="${t_stb.accounttype=='2'}">selected="selected"</c:if>>网关设备</option>
	 		 			<option <c:if test="${t_stb.accounttype=='3'}">selected="selected"</c:if>>小Q设备</option>
		   		</select>
				</td>
				<th><strong>支付类型：</strong></th>
				<td>
				<select  name="paytypeStr" id="paytypeStr"  value="${t_stb.paytypeStr}">
			    		<option <c:if test="${t_stb.paytype=='1'}">selected="selected"</c:if>>后付款</option>
			    		<option <c:if test="${t_stb.paytype=='2'}">selected="selected"</c:if>>先付款</option>
	 		 			<option <c:if test="${t_stb.paytype=='3'}">selected="selected"</c:if>>外部代收款</option>
		   		</select>
				</td>
			</tr>
			<tr>
				<th><strong>余额：</strong></th>
				<td><input type="text" name="balance" value="${t_stb.balance}"
					class="znumber" /></td>
				<th><strong>押金：</strong></th>
				<td><input type="text" name="deposit" value="${t_stb.deposit}"
					class="znumber" /></td>
			</tr>
			<tr>
				<th><strong>机顶盒状态：</strong></th>
				<td>
				<select  name="stbstatusStr" id="stbstatusStr"  value="${t_stb.stbstatusStr}">
			    		<option <c:if test="${t_stb.stbstatus=='1'}">selected="selected"</c:if>>未激活</option>
			    		<option <c:if test="${t_stb.stbstatus=='2'}">selected="selected"</c:if>>已激活</option>
	 		 			<option <c:if test="${t_stb.stbstatus=='3'}">selected="selected"</c:if>>用户停机</option>
	  					<option <c:if test="${t_stb.stbstatus=='4'}">selected="selected"</c:if>>欠费停机</option>
	  					<option <c:if test="${t_stb.stbstatus=='5'}">selected="selected"</c:if>>回收</option>
		   		</select>
				</td>
				<%-- <th><strong>到期时间: </strong></th>
				<td><input id='validdate' name='validdate' cssClass='Wdate'
					value='<fmt:formatDate value="${t_stb.validdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
					onclick="WdatePicker({
		    		skin:'whyGreen',
		    		startDate:'%y-%M-%d %H:00:00',
		    		dateFmt:'yyyy-MM-dd HH:mm:ss',
		    		quickSel:['<fmt:formatDate value="${t_stb.validdate}"  pattern="yyyy-MM-dd HH:mm:ss"/>','%y-%M-01','%y-%M-%ld']
		    		})" /></td> --%>
			</tr>
		</table>
		<script>
			$('#validdate').val(
					$.formatDateTime('yy-mm-dd hh:ii:ss', new Date()));
		</script>
		<br>

		<div>
			<span style="color: red;"><strong>固件：</strong></span>
		</div>
		<table>
			<tr>
				<th><strong>机顶盒ID：</strong></th>
				<td><input type="text" name="stbid" value="${t_stb.stbid}" /></td>
				<th><strong>安装地址：</strong></th>
				<td><input type="text" name="installaddress"
					value="${t_stb.installaddress}" /></td>
			</tr>
			<tr>
				<th><span style="color:red;">*</span><strong>MAC地址：</strong></th>
				<td><input type="text" name="mac" value="${t_stb.mac}" required="required"/></td>
				<th><strong>apk软件版本：</strong></th>
				<td><input type="text" name="apkversion"
					value="${t_stb.apkversion}" /></td>
			</tr>
			<tr>
				<th><strong>ROM版本:</strong></th>
				<td><input type="text" name="romversion"
					value="${t_stb.romversion}" /></td>
				<th><strong>ROM固件版本：</strong></th>
				<td><input type="text" name="romfirmware"
					value="${t_stb.romfirmware}" /></td>
			</tr>
			<tr>
				<th><strong>app类型：</strong></th>
				<td><input type="text" name="apptype" value="${t_stb.apptype}" /></td>
			</tr>
		</table>

		<div class="real-body-foot">
			<button type="submit" class="btn btn-primary">确定</button>
		</div>
	</form>

</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>"
		callback="window.parent.closeDialogm('edit_t_stb_m');window.parent.refreshGrid('t_stblist');">
							${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
	
</script>
<%@include file="../../../common/admin_footer.jsp"%>
