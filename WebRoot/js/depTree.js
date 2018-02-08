function showDep(id){
$("#"+id).show();

}
function hiddenDep(id){
	$("#"+id).hide();

	}
//      Add by dyl at 2010-09-10  关于工种框的输入控制  
function noPermitInput(e){     
      var evt = window.event || e ;   
        if(isIE()){   
           evt.returnValue=false; //ie 禁止键盘输入   
      }else{   
           evt.preventDefault(); //fire fox 禁止键盘输入   
       }      
}   
function isIE() {   
   if (window.navigator.userAgent.toLowerCase().indexOf("msie") >= 1)   
        return true;   
    else   
        return false;   
}  
//      end by dyl at 2010-09-10 
$(document).ready(function() {
$("#input_tree").focus(function(){
	$(this).next("div").show();
	});
});