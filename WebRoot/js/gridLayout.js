 function gridLayout(gridid){
	 //alert(getViewSizeWithoutScrollbar()['width']);
	 var isparent=$('#frame-tab', parent.document).length > 0;
	 var qtwidth=0;
	 jQuery("body").find("div").each(function(i){
		 if(jQuery(this).attr('class')=='maillist-left'){
			 qtwidth=qtwidth+jQuery(this).width()+10;
		 }
	 });
	/* jQuery("#"+gridid).jqGrid("setGridWidth",getViewSizeWithoutScrollbar(isparent)['width']-10-qtwidth); */
	 var qtheigth=jQuery(".yz-frame-head").height();
	 var qwidth=jQuery(".yz-frame-head").width();
	 var browser=getBrowserName();
	
//	 if(browser!='IE'&&getBrowserName()!='Others'){
//		 qtheigth+=8;
//	 }
	
	 if(isparent){
		 //var bodyHei = $(parent.window).height()-50-34-qtheigth-42-55-8;
		 var bodyHei =$(parent.window).height()-50-34-qtheigth-10-42-55-10;
		 if(!jQuery("#"+gridid).getGridParam("autowidth")){
			 bodyHei=bodyHei-18;
		 }
		 jQuery("#"+gridid).jqGrid("setGridHeight",bodyHei);
	 }else{
		 var headHei =  $('.yz-frame-head').height();
		 var bodyHei = $(window).height()- headHei - 10;
		 jQuery("#"+gridid).jqGrid("setGridHeight",bodyHei); 
		
		/* jQuery("#"+gridid).jqGrid("setGridHeight",getViewSizeWithoutScrollbar(isparent)['height']-bodyHei-40);*/
	 }
	
	
	 
 }

 function getViewSizeWithoutScrollbar(isparent) {// 不包含滚动条
	if (isparent) {
		return {
			width : $(window.parent).width(),
			/*height : $(window.parent).height()*/
			
		}
	} else {
		return {
			width : $(window).width(),
			/*height : $(window).height()*/
			
		}
	}
}
	 var ua = navigator.userAgent.toLowerCase();
	 function check(r){
	   return r.test(ua);
	 }
$(document).ready(function(){
	 var refreshBtn = $('.ui-icon-refresh');
	 refreshBtn.addClass('icon-refresh blue');
});