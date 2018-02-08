$(function(){
	var bodyH =$(window).height();
	$('.tree-content').height(bodyH - 40);
	var oBtn = $('.tree-node-el');
	oBtn.each(function(){
		var a = $(this);
		var g = $(this).next();
		if(g.hasClass('tree-node-menu')){
			a.children('.clicki').on('click',function(){
				$(this).toggleClass('glyphicon-minus');
				g.slideToggle(300);
			});
			var c = a.children('input');
			c.on('click',function(){
				var g = $(this).parent().next();
				if($(this).is(':checked')){
					g.children().children().children('input').attr('checked',true);
				}else{
					g.children().children().children('input').attr('checked',false);
				}
			});
		}else{
			a.children('.clicki').addClass('none');
		}
		
	});
});