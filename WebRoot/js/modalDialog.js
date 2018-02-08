function modalDialogWait(id,content) {
		if(typeof(id)=="undefined"||id==null||id==''){
			id = now.getFullYear()+""+now.getMonth()+""+now.getDay()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
		}
		var w="";
		var h="";
		var body = $("body");
		var waitlength = $('.fixcontent').length;
		var waitmodal = $("<div class=\"waitmodal\" id=\""+id+"\">");
		var fixcontent = $("<div class=\"fixcontent\">");
		var waitimg = $("<img src=\"img/load.gif\" width=\"100\">");
		body.append(waitmodal);
		waitmodal.append(waitimg,fixcontent);
}

function modalDialogContent(id,title,w,h,content,fn) {
	if(typeof(id)=="undefined"||id==null||id==''){
		id = now.getFullYear()+""+now.getMonth()+""+now.getDay()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	}
	var option_box = $('#'+id).children('alert-body');
	if(!w){
		w=250;
	}
	if(!h){
		h=150;
	}
	var body = $("body");
	var alertmodal = $("<div class=\"alertmodel\"  style=\"width:"+w+"px;height:"+h+"px\" id=\""+id+"\">");
	var fixcontent = $("<div class=\"fixcontent\">");
	var alerthead = $("<div class=\"alert-title\">"+title+"<i class=\"right icon icon-remove red alertremove\"></i></div>");
	var alertbody = $("<div class=\"alert-body\">"+content+"</div>");
	var alertfoot = $("<div class=\"alert-foot\">");
	var alertbutton_p=$("<div class=\"alert-button\">");
	var alertbutton=$("<button class=\"btn btn-primary\">确定</button>").on('click', fn);
	alertbutton_p.append(alertbutton);
	alertfoot.append(alertbutton_p);
	body.append(alertmodal);
	alertmodal.append( alerthead , alertbody , alertfoot ,fixcontent );
	$(alertmodal).height(parseInt(h-66));
	$(alertmodal).css({
		'margin-top':- (h-66/2),
		'margin-left':- (w/2)
	});
	$('.alertremove').on('click',function(){
		closeDialog(id)
	});
}
function modalDialogconfim(id,content,buttons) {
	if(typeof(id)=="undefined"||id==null||id==''){
	  id = now.getFullYear()+""+now.getMonth()+""+now.getDay()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	}
	var option_box = $('#'+id).children('alert-body');
	var title="提示";
	w=250;
	h=150;
	var body = $("body");
	var alertmodal = $("<div class=\"alertmodel\"  style=\"width:"+w+"px;height:"+h+"px\" id=\""+id+"\">");
	var fixcontent = $("<div class=\"fixcontent\">");
	var alerthead = $("<div class=\"alert-title\">"+title+"<i class=\"right icon icon-remove red alertremove\"></i></div>");
	var alertbody = $("<div class=\"alert-body\">"+content+"</div>");
	var alertfoot = $("<div class=\"alert-foot\">");
	var alertbutton_p=$("<div class=\"alert-button\">");
	
	$(buttons).each(function(index,item){
		var alertbutton=$("<button class=\"btn btn-primary\">"+item.name+"</button>").on('click', item.fn);
		alertbutton_p.append(alertbutton);
	});
	alertfoot.append(alertbutton_p);
	body.append(alertmodal);
	alertmodal.append( alerthead , alertbody , alertfoot ,fixcontent );
	var modalHei = $('.alert-body').height();
	$(alertmodal).css({
		/*'margin-top': -(alertmodal.height()),*/
		'margin-left':- (w/2),
		'height':modalHei + 125,
		'margin-top': -(modalHei + 117)/2
	});
	$('.alertremove').on('click',function(){
		closeDialog(id)
	});
}
function modalDialogAlert(content) {
	if(typeof(id)=="undefined"||id==null||id==''){
		id="waitmodal";
	}
	var now=new Date(); 
	var id = now.getFullYear()+""+now.getMonth()+""+now.getDay()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	var w = 280;
	var body = $("body");
	var alertmodal = $("<div class=\"alertmodel\"  style=\"width:"+w+"px\" id=\""+id+"\">");
	var fixcontent = $("<div class=\"fixcontent\">");
	var alerthead = $("<div class=\"alert-title\">提示信息<i class=\"right icon icon-remove red alertremove\"></i></div>");
	var alertbody = $("<div class=\"alert-body\">"+content+"</div>");
	var alertfoot = $("<div class=\"alert-foot\"><div class=\"alert-button\"><button class=\"btn btn-primary alertremove\">确定</button>");
	body.append(alertmodal);
	
	alertmodal.append( alerthead , alertbody , alertfoot ,fixcontent );
	var modalHei = $('.alert-body').height();
	$(alertmodal).css({
		/*'margin-top': -(alertmodal.height()),*/
		'margin-left':- (w/2),
		'height':modalHei + 117,
		'margin-top': -(modalHei + 117)/2
	});
	$('.alertremove').on('click',function(){
		closeDialog(id)
	});
}
function closeDialog(id) {
	$('#'+id).remove();
}
function closeDialogm(id) {
	$('#'+id).modal('hide');
}
function refreshGrid(id) {
	jQuery("#"+id).trigger("reloadGrid");
	
}
function creatIframe(href, titleName, navNum) {
	var topWindow = $(window.parent.document);
	var show_nav = topWindow.find('#frame-tab');
	show_nav.find('li').removeClass("active");
	var iframe_box = topWindow.find('#iframe_box');
	//show_nav.append('<li class="active"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
	/*show_nav.append('<li class="active"><a href="#' + href + '" data-href="' + href + '" id="tab_'+navNum+'" data-toggle="tab"><span>' + titleName + '</span><i></i><em></em></a></li>');
	*/
	show_nav.append('<li class="active"><a href="#' + href + '"  id="tab_'+navNum+'" ><span>' + titleName + '</span><i></i><em></em></a></li>');
	
	tabNavallwidth();
	var iframeBox = iframe_box.find('.show_iframe');
	iframeBox.hide();
	iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src=' + href + ' id="frame_'+navNum+'" class="boxiframe"></iframe></div>');
	var showBox = iframe_box.find('.show_iframe:visible');
	showBox.find('iframe').attr("src", href).load(function() {
		showBox.find('.loading').hide();
	});
}
/**
 * 
 * @param options.id 弹出框的id
 * @param options.iframeurl 加载iframe的页面
 * @param options.url 加载第三方的页面的html
 * @param options.title 弹出框的标题
 */
(function(jQuery) {     
	jQuery.fn.yzIframeDialog = function(options) { 
		if(typeof(options.id)=="undefined"||options.id==null||options.id==''){
			options.id="myModal";
		}
		var w="";
		if(options.width){
			w="style=\"width:"+options.width+"\";";
		}else{
			w="style=\"width:"+($(window).width()-100)+"px\";";
		}
		var h="";
		if(options.height){
			h="style=\"height:"+options.height+"\";";
		}else{
			h="style=\"height:"+($(window).height()-150)+"px\";";
		}
		var body=$("body");
		var modaldiv=$("<div class=\"modal fade bs-example-modal-lg\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myLargeModalLabel\" id=\""+options.id+"\">");
		var documentdiv=$("<div class=\"modal-dialog modal-lg\" role=\"document\" "+w+">");
		var contentdiv=$("<div class=\"modal-content\">");
		var headerdiv=$("<div class=\"modal-header\">");
		var headerbutton=$("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>");
		var headertitle=$("<h4 class=\"modal-title\" id=\""+options.id+"Label\">"+options.title+"</h4>");
		headerdiv.append(headerbutton).append(headertitle);
		contentdiv.append(headerdiv);
		var bodydiv=$("<div class=\"modal-body\" "+h+">");
		var iframe;
		if(typeof(options.iframeurl)!="undefined"&&options.iframeurl!=null&&options.iframeurl!=''){
		    iframe=jQuery('<iframe id="'+options.id+'ifr" frameborder="no" width="100%"/>');
			bodydiv.append(iframe);
		}else{
			bodydiv.append(options.bodycontent);
		}
		contentdiv.append(bodydiv);
		if(typeof(options.iframeurl)=="undefined"||options.iframeurl==null||options.iframeurl==''){
		var footerdiv=$("<div class=\"modal-footer\">");
		var footerbutton=$(" <div type=\"button\" class=\"btn btn-primary\" >确定</div>").on('click', options.dosubmit);
				footerdiv.append(footerbutton);
				contentdiv.append(footerdiv);
		}
		documentdiv.append(contentdiv);
		modaldiv.append(documentdiv);
		body.append(modaldiv);
		if(typeof(options.iframeurl)!="undefined"&&options.iframeurl!=null&&options.iframeurl!=''){
		iframe.attr('src',options.iframeurl);
		}
		$('#'+options.id).on('hidden.bs.modal', function (e) {
			$('#'+options.id).remove();
		});
	};   
})(jQuery);    
