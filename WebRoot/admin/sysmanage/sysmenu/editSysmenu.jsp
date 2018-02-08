<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/jqGrid.tld" prefix="tgrid" %>
<%@ taglib prefix="e" uri="/yz"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../common/admin_head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basepath%>/admin/css/frame.css"/>
 <script language="javascript" type="text/javascript" src="<%=basepath%>/js/My97DatePicker/WdatePicker.js"></script>
<div class="modal-real-body">
    <form action="<%=basepath%>/admin/department/updateDepartment.action" enctype="multipart/form-data" method="post">
	<input type="hidden" name="id" value="${department.id}"/>
      	<div class="admin-s-body">
       		<span>组织机构名称</span>
       		<div class="admin-s"><input type="text" name="depname" id="depname" placeholder="输入名称" value="${department.depname}"/></input></div>
       	</div>
       	<div class="admin-s-body">
       		<span>组织机构编号</span>
       		<div class="admin-s"><input type="text" name="depcode" id="depcode" placeholder="输入编号"  value="${department.depcode}"/></input></div>
       	</div>
       		<div class="admin-s-body">
       		<span>组织机构地址</span>
       		<div class="admin-s"><input type="text" name="depaddress" id="depaddress" placeholder="输入地址" value="${department.depaddress}"/></input></div>
       	</div>
       	<div class="admin-s-body">
       		<span>上级组织机构</span>
       		<div class="admin-s"><input type="text" name="parentDepartment.depname" readonly="true" value="${department.parentDepartment.depname}"/><input name="depparent" type="hidden" value="${department.depparent}"/></div>
       	</div>
       	<div class="admin-s-body">
       		<span>联系电话</span>
       		<div class="admin-s">
       		<input type="text" name="telephone" value="${department.telephone}"/>
       		</div>
       	</div>
       	<div class="admin-s-body">
       		<span>联系人</span>
       		<div class="admin-s">
       		<input type="text" name="liaisonper" value="${department.liaisonper}"/>
       		</div>
       	</div>
       	<div class="admin-s-body">
       		<span>创建年份</span>
       		<div class="admin-s">
       		<input name="depyear" class="number" style="Wdate" onclick="WdatePicker({skin:'default',dateFmt:'yyyy'})" value="${department.depyear}"/>
       		</div>
       	</div>
       	<div class="admin-s-body block">
       		<span class="top">组织机构简称</span>
       		<div class="admin-s">
       		<textarea name="contracted" rows="3" style="width:100%">${department.contracted}</textarea>
       		</div>
       	</div>
       	<div class="admin-s-body block">
       		<span class="top">备注</span>
       		<div class="admin-s">
       		<input type="text" name="remark" rows="3" value="${department.remark}"/>
       		</div>
       	</div>
       	
			        	<div class="real-body-foot">
			        		<button type="submit" class="btn btn-primary" onclick="return checkdep();">确定</button>
			        	</div>
    </form>
			        	
		        	</div>
<c:if test="${meg!=null}">
	<e:msgdialog basepath="<%=basepath%>" callback="window.parent.closeDialogm('department_edit_d');window.parent.rs('${department.depparent}');">
		${meg}
	</e:msgdialog>
</c:if>
<script type="text/javascript">
function checkdep(){
	var info="";
	var depname=$("#depname").val();
	if(depname==""){
		info+="组织机构名称不能为空！";
	}
	var depparent=$("input[name=depparent]").val();
	if(depparent==""){
		info+="上级组织机构不能为空！";
	}
	if(info!=""){
		modalDialogAlert(info);
		return false;
	}
	
	return true;
}
</script>
<%@include file="../../../common/admin_footer.jsp"%>