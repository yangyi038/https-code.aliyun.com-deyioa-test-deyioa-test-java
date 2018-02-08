if (!("ace" in window)) {
	window.ace = {}
}
jQuery(function(a) {
	window.ace.click_event = a.fn.tap ? "tap" : "click"
});
jQuery(function(a) {
	ace.handle_side_menu(jQuery);
	ace.enable_search_ahead(jQuery);
	ace.general_things(jQuery);
	ace.widget_boxes(jQuery);
	ace.widget_reload_handler(jQuery)
});
ace.handle_side_menu = function(a) {
	a("#menu-toggler").on(ace.click_event, function() {
		a("#sidebar").toggleClass("display");
		a(this).toggleClass("display");
		return false
	});
	var c = a("#sidebar").hasClass("menu-min");
	a("#sidebar-collapse").on(ace.click_event, function() {
		c = a("#sidebar").hasClass("menu-min");
		ace.settings.sidebar_collapsed(!c)
	});
	var b = "ontouchend" in document;
	a(".nav-list").on(ace.click_event, function(h) {
		var g = a(h.target).closest("a");
		if (!g || g.length == 0) {
			return
		}
		c = a("#sidebar").hasClass("menu-min");
		if (!g.hasClass("dropdown-toggle")) {
			if (c && ace.click_event == "tap" && g.get(0).parentNode.parentNode == this) {
				var i = g.find(".menu-text").get(0);
				if (h.target != i && !a.contains(i, h.target)) {
					return false
				}
			}
			return
		}
		var f = g.next().get(0);
		if (!a(f).is(":visible")) {
			var d = a(f.parentNode).closest("ul");
			if (c && d.hasClass("nav-list")) {
				return
			}
			d.find("> .open > .submenu").each(function() {
				if (this != f && !a(this.parentNode).hasClass("active")) {
					a(this).slideUp(200).parent().removeClass("open");	
					$(this).parent().children('a.dropdown-toggle').children('b').addClass('icon-angle-down')
				}
			})
		} else {}
		if (c && a(f.parentNode.parentNode).hasClass("nav-list")) {
			return false
		}
		a(f).slideToggle(200).parent().toggleClass("open").children('a.dropdown-toggle').children('b').toggleClass('icon-angle-down');
		return false
	})
};
ace.general_things = function(a) {
	a('.ace-nav [class*="icon-animated-"]').closest("a").on("click", function() {
		var d = a(this).find('[class*="icon-animated-"]').eq(0);
		var c = d.attr("class").match(/icon\-animated\-([\d\w]+)/);
		d.removeClass(c[0]);
		a(this).off("click")
	});
	a(".nav-list .badge[title],.nav-list .label[title]").tooltip({
		placement: "right"
	});
	a("#ace-settings-btn").on(ace.click_event, function() {
		a(this).toggleClass("open");
		a("#ace-settings-box").toggleClass("open")
	});
	a("#ace-settings-navbar").on("click", function() {
		ace.settings.navbar_fixed(this.checked)
	}).each(function() {
		this.checked = ace.settings.is("navbar", "fixed")
	});
	a("#ace-settings-sidebar").on("click", function() {
		ace.settings.sidebar_fixed(this.checked)
	}).each(function() {
		this.checked = ace.settings.is("sidebar", "fixed")
	});
	a("#ace-settings-breadcrumbs").on("click", function() {
		ace.settings.breadcrumbs_fixed(this.checked)
	}).each(function() {
		this.checked = ace.settings.is("breadcrumbs", "fixed")
	});
	a("#ace-settings-add-container").on("click", function() {
		ace.settings.main_container_fixed(this.checked)
	}).each(function() {
		this.checked = ace.settings.is("main-container", "fixed")
	});
	a("#ace-settings-rtl").removeAttr("checked").on("click", function() {
		ace.switch_direction(jQuery)
	});
	a("#btn-scroll-up").on(ace.click_event, function() {
		var c = Math.min(400, Math.max(100, parseInt(a("html").scrollTop() / 3)));
		a("html,body").animate({
			scrollTop: 0
		}, c);
		return false
	});
	try {
		a("#skin-colorpicker").ace_colorpicker()
	} catch (b) {}
	a("#skin-colorpicker").on("change", function() {
		var d = a(this).find("option:selected").data("skin");
		var c = a(document.body);
		c.removeClass("skin-1 skin-2 skin-3");
		if (d != "default") {
			c.addClass(d)
		}
		if (d == "skin-1") {
			a(".ace-nav > li.grey").addClass("dark")
		} else {
			a(".ace-nav > li.grey").removeClass("dark")
		}
		if (d == "skin-2") {
			a(".ace-nav > li").addClass("no-border margin-1");
			a(".ace-nav > li:not(:last-child)").addClass("light-pink").find('> a > [class*="icon-"]').addClass("pink").end().eq(0).find(".badge").addClass("badge-warning")
		} else {
			a(".ace-nav > li").removeClass("no-border margin-1");
			a(".ace-nav > li:not(:last-child)").removeClass("light-pink").find('> a > [class*="icon-"]').removeClass("pink").end().eq(0).find(".badge").removeClass("badge-warning")
		}
		if (d == "skin-3") {
			a(".ace-nav > li.grey").addClass("red").find(".badge").addClass("badge-yellow")
		} else {
			a(".ace-nav > li.grey").removeClass("red").find(".badge").removeClass("badge-yellow")
		}
	})
};
ace.widget_boxes = function(a) {
	a(document).on("hide.bs.collapse show.bs.collapse", function(c) {
		var b = c.target.getAttribute("id");
		a('[href*="#' + b + '"]').find('[class*="icon-"]').each(function() {
			var e = a(this);
			var d;
			var f = null;
			var g = null;
			if ((f = e.attr("data-icon-show"))) {
				g = e.attr("data-icon-hide")
			} else {
				if (d = e.attr("class").match(/icon\-(.*)\-(up|down)/)) {
					f = "icon-" + d[1] + "-down";
					g = "icon-" + d[1] + "-up"
				}
			}
			if (f) {
				if (c.type == "show") {
					e.removeClass(f).addClass(g)
				} else {
					e.removeClass(g).addClass(f)
				}
				return false
			}
		})
	});
	a(document).on("click.ace.widget", "[data-action]", function(o) {
		o.preventDefault();
		var n = a(this);
		var p = n.data("action");
		var b = n.closest(".widget-box");
		if (b.hasClass("ui-sortable-helper")) {
			return
		}
		if (p == "collapse") {
			var j = b.hasClass("collapsed") ? "show" : "hide";
			var f = j == "show" ? "shown" : "hidden";
			var c;
			b.trigger(c = a.Event(j + ".ace.widget"));
			if (c.isDefaultPrevented()) {
				return
			}
			var g = b.find(".widget-body");
			var m = n.find("[class*=icon-]").eq(0);
			var h = m.attr("class").match(/icon\-(.*)\-(up|down)/);
			var d = "icon-" + h[1] + "-down";
			var i = "icon-" + h[1] + "-up";
			var l = g.find(".widget-body-inner");
			if (l.length == 0) {
				g = g.wrapInner('<div class="widget-body-inner"></div>').find(":first-child").eq(0)
			} else {
				g = l.eq(0)
			}
			var e = 300;
			var k = 200;
			if (j == "show") {
				if (m) {
					m.addClass(i).removeClass(d)
				}
				b.removeClass("collapsed");
				g.slideUp(0, function() {
					g.slideDown(e, function() {
						b.trigger(c = a.Event(f + ".ace.widget"))
					})
				})
			} else {
				if (m) {
					m.addClass(d).removeClass(i)
				}
				g.slideUp(k, function() {
					b.addClass("collapsed");
					b.trigger(c = a.Event(f + ".ace.widget"))
				})
			}
		} else {
			if (p == "close") {
				var c;
				b.trigger(c = a.Event("close.ace.widget"));
				if (c.isDefaultPrevented()) {
					return
				}
				var r = parseInt(n.data("close-speed")) || 300;
				b.hide(r, function() {
					b.trigger(c = a.Event("closed.ace.widget"));
					b.parent().remove();
					b.remove();
				})
			} else {
				if (p == "reload") {
					var c;
					b.trigger(c = a.Event("reload.ace.widget"));
					if (c.isDefaultPrevented()) {
						return
					}
					n.blur();
					var q = false;
					if (b.css("position") == "static") {
						q = true;
						b.addClass("position-relative")
					}
					b.append('<div class="widget-box-overlay"><i class="icon-spinner icon-spin icon-2x white"></i></div>');
					b.one("reloaded.ace.widget", function() {
						b.find(".widget-box-overlay").remove();
						if (q) {
							b.removeClass("position-relative")
						}
					})
				} else {
					if (p == "settings") {
						var c = a.Event("settings.ace.widget");
						b.trigger(c)
					}
				}
			}
		}
	})
};

ace.widget_reload_handler = function(a) {
	a(document).on("reload.ace.widget", ".widget-box", function(b) {
		var c = a(this);
		setTimeout(function() {
			c.trigger("reloaded.ace.widget")
		}, parseInt(Math.random() * 1000 + 1000))
	})
};
ace.enable_search_ahead = function(a) {
	ace.variable_US_STATES = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"];
	try {
		a("#nav-search-input").typeahead({
			source: ace.variable_US_STATES,
			updater: function(c) {
				a("#nav-search-input").focus();
				return c
			}
		})
	} catch (b) {}
};
ace.switch_direction = function(d) {
	var c = d(document.body);
	c.toggleClass("rtl").find(".dropdown-menu:not(.datepicker-dropdown,.colorpicker)").toggleClass("pull-right").end().find(".pull-right:not(.dropdown-menu,blockquote,.profile-skills .pull-right)").removeClass("pull-right").addClass("tmp-rtl-pull-right").end().find(".pull-left:not(.dropdown-submenu,.profile-skills .pull-left)").removeClass("pull-left").addClass("pull-right").end().find(".tmp-rtl-pull-right").removeClass("tmp-rtl-pull-right").addClass("pull-left").end().find(".chosen-container").toggleClass("chosen-rtl").end();

	function a(h, g) {
		c.find("." + h).removeClass(h).addClass("tmp-rtl-" + h).end().find("." + g).removeClass(g).addClass(h).end().find(".tmp-rtl-" + h).removeClass("tmp-rtl-" + h).addClass(g)
	}

	function b(h, g, i) {
		i.each(function() {
			var k = d(this);
			var j = k.css(g);
			k.css(g, k.css(h));
			k.css(h, j)
		})
	}
	a("align-left", "align-right");
	a("no-padding-left", "no-padding-right");
	a("arrowed", "arrowed-right");
	a("arrowed-in", "arrowed-in-right");
	a("messagebar-item-left", "messagebar-item-right");
	var e = d("#piechart-placeholder");
	if (e.size() > 0) {
		var f = d(document.body).hasClass("rtl") ? "nw" : "ne";
		e.data("draw").call(e.get(0), e, e.data("chart"), f)
	}
};

//选项卡
$('#frame-tab li').click(function(e) {
	e.preventDefault();
	$('#frame-content').tab('show');
});

/*获取选项卡总长度*/
function tabNavallwidth() {
	var taballwidth = 0,
		$tabNav = $("#frame-tab"),
		$tabNavWp = $(".Hui-tabNav-wp"),
		$tabNavitem = $("#frame-tab li"),
		$tabNavmore = $(".nav-more");
	$tabNavmore.css('display', 'none');
	if (!$tabNav[0]) {
		return
	}
	$tabNavitem.each(function(index, element) {
		taballwidth += Number(parseFloat($(this).width() + 60));
	});
	$tabNav.width(taballwidth + 15);
	var w = $tabNavWp.width();
	if (taballwidth + 100 > w) {
		$tabNavmore.show();
	} else {
		$tabNavmore.hide();
		$tabNav.css({
			left: 0
		})
	}
}
$(function() {
	$("#sidebar").on("click", ".nav-list a", function() {
		if ($(this).attr('_href')) {
			var bStop = false;
			var bStopIndex = 0;
			var _href = $(this).attr('_href');
			var navId = $(this).attr('id');
			var navNum = navId.split('_')[1];
			var _titleName = $(this).text();
			var topWindow = $(window.parent.document);
			var show_navLi = topWindow.find("#frame-tab li");
			show_navLi.each(function() {
				if ($(this).find('a').attr("id") == 'tab_'+navNum) {
					bStop = true;
					bStopIndex = show_navLi.index($(this));
					return false;
				}
			});
			if (!bStop) {
				creatIframe(_href, _titleName,navNum);
				min_titleList();
			} else {
				show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
				var iframe_box = topWindow.find("#iframe_box");
				iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("id", 'frame_'+navNum);
			}
		}
	});

	function min_titleList() {
		var topWindow = $(window.parent.document);
		var show_nav = topWindow.find("#frame-tab");
		var aLi = show_nav.find("li");
	};

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


	var num = 0;
	var oUl = $("#frame-tab");
	var hide_nav = $("#Hui-tabNav");
	$(document).on("click", "#frame-tab li", function() {
		var bStopIndex = $(this).index();
		var iframe_box = $("#iframe_box");
		$("#frame-tab li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
	});
	$(document).on("click", "#frame-tab li a i", function() {
		var iframe_box = $("#iframe_box");
		var aCloseIndex = $(this).parents().parents("li").index();
		$(this).parent().parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
		num == 0 ? num = 0 : num--;
		iframe_box.find(".show_iframe").hide().eq(aCloseIndex - 1).show();
		tabNavallwidth();
	});
	$(document).on("dblclick", "#frame-tab li", function() {
		var aCloseIndex = $(this).index();
		var iframe_box = $("#iframe_box");

		if (aCloseIndex > 0) {
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
			num == 0 ? num = 0 : num--;
			$("#frame-tab li").removeClass("active").eq(aCloseIndex - 1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex - 1).show();
			tabNavallwidth();
		} else {
			return false;
		}
	});
	tabNavallwidth();

	$('#js-tabNav-next').click(function() {
		num == oUl.find('li').length - 1 ? num = oUl.find('li').length - 1 : num++;
		toNavPos();
	});
	$('#js-tabNav-prev').click(function() {
		num == 0 ? num = 0 : num--;
		toNavPos();
	});

	function toNavPos() {
		oUl.stop().animate({
			'left': -num * 100
		}, 100);
	}
});

$(document).on("click.ace.widget", "[data-dock]", function(a) {
	var a = $(this);
	var p = $(this).data("dock");
	
	if(p == "resett"){
		
		alert('此功能暂未开放')
	}else{
		if(p == "opthis"){
			/*if(a.hasClass('opsuccess')){
				a.html('关闭选课');
				a.removeClass('opsuccess').removeClass('btn-default').addClass('btn-danger').addClass('clothis');
			}else{
				a.html('开启选课');
				a.removeClass('clothis').removeClass('btn-danger').addClass('btn-default').addClass('opsuccess');
			}*/
		}else{
			// 重置选课,关联两个按钮
			/*if(p == "mark"){
				var open = a.parent().parent().next().children().children('.opentime').children('.rgside');
				var close = a.parent().parent().next().children().children('.overtime').children('.rgside');
				var kq = a.parent().parent().next().children().children('.classtime').children('.rgside');
				open.html('<input value=\'1231231\' class=\'dockinput\'>');
			}*/
		}
	}
});

	//view model
       			$(function(){
       				$('.hoverlink').each(function(){
       					if (!$(this).is(':animated')){
       						$(this).mouseover(function(){
       							$(this).children().show();
       						});
       						$(this).mouseout(function(){
       							$(this).children().hide();
       						});
       					};    					       					
       				});
       			});
 
       			   			
       			$(function(){
       	        	$('.nav-list-hock').on('click','a',function(){
       	        		var g = $(this).next();
       	        		var f = $(this).parent();
       	        		var liList = $('.nav-list-hock>ul>li');
       	        		if(g.length == 0 ){
       	        			var uList = $('.nav-list-hock .submenu');
       	        			uList.children('li').removeClass('hock-active');
       	        			$(this).parent('li').addClass('hock-active');
       	        		}else{
       	        			if(f.hasClass('open')){
       	        				g.slideUp(300);
       	        				$(this).children('b').removeClass('icon-angle-down').addClass('icon-angle-right');
       	        				f.removeClass('open');
       	        				$('.nav-list-hock li').removeClass('hock-active');
       	        			}else{
       	        				var botUl = $(this).parent('li').parent('ul');
       	        				$(this).children('b').removeClass('icon-angle-right').addClass('icon-angle-down');
       	        				g.slideDown(300);
       	        				liList.removeClass('open').slideUp();
       	        				$('.nav-list-hock li').removeClass('hock-active');
       	        				f.addClass('open');
       	        			}
       	        		};
       	        	});
       	        	$('.nav-list-lb').on('click','a',function(){
       	        		var g = $(this).next();
       	        		var f = $(this).parent();
       	        			var uList = $('.nav-list-hock .submenu');
       	        			$('.nav-list-lb').children('li').removeClass('hock-active');
       	        			$(this).parent('li').addClass('hock-active');
       	        	});
       	        });
    			      			
       			
function getContentSize() {
	map_width = document.body.clientWidth; //获取body宽度
	map_height = document.documentElement.clientHeight; //获取页面可见高度
	//alert(map_width);
	//alert(map_height);
	$('.tab-content').css('padding', "0");
	var but_iframe = $('.boxiframe');
	but_iframe.css({
		'border': '0',
		'width': '100%',
		'height':'100%',
		//'min-width': '800px'
		'height':map_height - 86,
		//'padding':'8px 0 0 10px'
	});
	$('#sidebar').height(map_height-50);
	$('.main-container-inner').height(map_height-50);
}
window.onload = getContentSize
window.onresize = getContentSize;
setInterval('getContentSize()', 10);



$(function(){
	var searchBtn = $('.searchbox').children('.addRock');
	searchBtn.on('click',function(){
		$(this).children('i').toggleClass('icon-angle-down');
		var searchBox = $(this).parent('.searchbox');
		searchBox.toggleClass('overSearch');
	});
})

