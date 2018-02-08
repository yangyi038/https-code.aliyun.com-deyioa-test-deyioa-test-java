<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/admin_head.jsp"%>
<html>
<head>
<%
String hiddenid=request.getParameter("hiddenid");
String id=request.getParameter("id");
String path=request.getParameter("path");
String value=request.getParameter("value");
if(path==null||path.equals("")){
	path="admin";
}
String level=request.getParameter("level");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="<%=basepath%>/css/tree.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" media="screen"
          href="<%=basepath%>/css/jqgrid/jquery-ui-1.8.2.custom.css"/>
              <link href="<%=basepath%>/css/loginStyle.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript" src="<%=basepath%>/js/tree/jquery.tree.js"></script>
      <script type="text/javascript" src="<%=basepath%>/js/modalDialog.js"></script>
             
</head>
<body style="margin:0px;padding:0px;height:100%">

 <div style="border: #c3daf9 1px solid; width:160px; height:98%; background:#fff;overflow-x: hidden; overflow-y: auto; float:left;text-align:left;">
 		<div class="depIco">
 		<ul>
 			<li class="rest" onclick="parent.document.getElementById('<%=hiddenid%>').value='';parent.document.getElementById('<%=id%>').value=''">重置</li>
 			<li class="close" onclick="parent.hiddenDep('<%=id%>_depDiv')">关闭</li>
 		</ul>
 		</div>
        <div id="tree">            
        </div>        
</div>
    
</body>
 
    <script type="text/javascript">
         var userAgent = window.navigator.userAgent.toLowerCase();
        $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
        $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent); 
        $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
        function load() {        
            var o = { showcheck: true,url: "<%=basepath%>/<%=path%>/tree_loadCityTree.action",onnodeclick:function(tree, item){
            	 var s=tree;
            	 var s_value=(s.value).split("|");
            	 if(s.parentid!=null){
            		 var ptext=$("#tree").parentnode(s.parentid).text;
            		 var ppid=$("#tree").parentnode(s.parentid).parentid;
            		 if(ppid!=null){
            			 ptext=$("#tree").parentnode(ppid).text+ptext+s.text;
            		 }else{
            			 ptext=ptext+s.text;
            		 }
            	 }else{
            		 ptext=s.text;
            	 }
            	 
            	 
            	 if(<%=level%>!=s_value[1]&&'<%=level%>'!='null'){
            		 alert("该节点不可选！");
            	 }else{
            	 parent.document.getElementById("<%=hiddenid%>").value=s.id;
            	 parent.document.getElementById("<%=id%>").value=ptext;
            	 parent.hiddenDep("<%=id%>_depDiv");
            	 }
            }
   
            };                 
            $("#tree").treeview(o);
            

        } 
        
        if( $.browser.msie6)
        {
            load();
        }
        else{
            $(document).ready(load);
        }

        <%if(value!=null&&!value.equals("")){%>
        $(document).ready(function(){ 
        var id="<%=id%>";	
        $.post("<%=basepath%>/<%=path%>/tree_loadCityMc.action", {value: <%=value%>}, function(data){
    		if (data) {	
    			 $("#"+id,window.parent.document).val(data);
    			 if($("#"+id,window.parent.document).is('span')){
    				 $("#"+id,window.parent.document).text(data);
    			 }
    			 
    		} 
    	});	
        });	
        
        <%}%>
    </script>
</html>