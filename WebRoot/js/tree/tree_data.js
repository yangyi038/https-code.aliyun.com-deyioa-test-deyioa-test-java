
$(document).ready(function(){
	$.post(basepath+"/admin/role/loadTree.action", {privilege: document.getElementById("menuid").value}, function(data){
		if (data) {	
			load(data);  
		} 
	});	
});
//treedata = [createNode()];
