;(function($){
	//给页面装载CSS样式
	var css = '<style type="text/css">#skygqOverlay{position:fixed;z-index:2000;left:0;top:0;width:100%;height:100%;background:black;}.wrap_out{background:#fff;position:fixed;z-index:2001;font-family:"microsoft yahei"}.wrap_bar{width:100%;height:41px;line-height:40px;background:#f7f7f7 url(http://images.shopin.net/s/images/dialog.png) repeat-x 0 0;}.wrap_title span{position:relative;margin-left:10px;cursor:text;font-weight:600;font-size:14px}.wrap_body{display:inline-block;border-top:1px solid #ddd;background:white;}.wrap_close{width:35px;height:30px;margin:-34px 0 0 10px;zoom:1;text-decoration:none;text-indent:-99em;overflow:hidden;cursor:pointer;float:right;border-bottom:none;background:url(http://images.shopin.net/s/images/dialog.png) no-repeat 0 -183px}.wrap_close:hover{background:url(http://images.shopin.net/s/images/dialog.png) no-repeat -45px -183px}.submit_btn{display:inline-block;*display:inline;*zoom:1;width:80px;height:35px;line-height:34px;cursor:pointer;overflow:visible;text-align:center;font-size:12px;font-weight:600;background:url(http://images.shopin.net/s/images/dialog.png) no-repeat 0 -41px;border:none;color:#fff}.submit_btn:hover{background:url(http://images.shopin.net/s/images/dialog.png) no-repeat 0 -111px}.cancel_btn{display:inline-block;*display:inline;*zoom:1;width:80px;height:35px;line-height:34px;margin-left:5px;cursor:pointer;overflow:visible;text-align:center;font-size:12px;font-weight:600;background:url(http://images.shopin.net/s/images/dialog.png) no-repeat 0 -76px;border:none;color:#666}.cancel_btn:hover{background:url(http://images.shopin.net/s/images/dialog.png) no-repeat 0 -146px;color:#000}</style>';
	$("head").append(css);
	var ie6 = ($.browser.msie && $.browser.version < 7);
	var timeout;
	$.fn.skygqbox = function(options){
		if (!this.length) {	return this;}
		var s = $.extend({}, $.fn.skygqbox.Default, options || {});
		return this.each(function(){
			$.skygqbox($(this),s)
		});
	};

	$.skygqbox = function(elements,s){
		if ($(elements).length == 0){
			elements = $("<span></span>").append(elements);
		}
		var s = $.extend({}, $.fn.skygqbox.Default, s || {});
		if ($("#skygqOverlay").length > 0){
			return;
		}
		//弹框的显示初始化
		var WRAP = '<div id="skygqOverlay"></div><div class="wrap_out" id="wrapOut"><div class="wrap_in" id="wrapIn"><div id="wrapBar" class="wrap_bar"  onselectstart="return false;"><div class="wrap_title"><span>'+s.title+'</span></div><a href="javascript:void(0);" class="wrap_close" id="wrapClose">'+s.shut+'</a></div><div class="wrap_body" id="wrapBody"></div></div></div>';
		$("body").append(WRAP);
		if (typeof (timeout) != "undefined"){
			clearTimeout(timeout);
		}

		//一些元素对象
		$.o = {
			s: s,
			ele: elements,
			bg: $("#skygqOverlay"),
			out: $("#wrapOut"),
			bar: $("#wrapBar"),
			clo: $("#wrapClose"),
			bd: $("#wrapBody")
		};
		elements.show();
		$.o.bd.append(elements);
		//尺寸
		$.skygqbox.setSize();
		//定位
		$.skygqbox.setPosition();
		$.o.clo.click(function(){
			$.skygqbox.hide();
		});

		if(s.autoClose > 0){
			timeout = setTimeout($.skygqbox.hide, s.autoClose);
		}
	};
	$.skygqbox.getSize = function(o){
		//获取任意元素的高宽
		var w_h = {};
		$('<div id="wrapClone" style="position:absolute;left:-6000px;"></div>').appendTo("body").append(o.clone());
		w_h.w = $("#wrapClone").width();
		w_h.h = $("#wrapClone").height();
		$("#wrapClone").remove();
		return w_h;
	};
	$.skygqbox.setSize = function(){
		if(!$.o.bd.size() || !$.o.ele.size() || !$.o.bd.size()){
			return;
		}
		//主体内容的尺寸
		var bd_w = parseInt($.o.s.width, 10), bd_h = parseInt($.o.s.height, 10);
		if(!bd_w || bd_w <= 0 ){
			var x_size = $.skygqbox.getSize($.o.ele), w = $(window).width();
			//宽度自动
			bd_w = x_size.w;
			if(bd_w < 50){
				bd_w = 120;
			}else if(bd_w > w){
				bd_w = w - 120;
			}
		}
		$.o.bd.css("width", bd_w);
		$.o.out.css("width", bd_w+2);
		if(bd_h > 0){
			$.o.bd.css("height", bd_h);
		}
		return $.o.bd;
	};
	$.skygqbox.setPosition = function(){
		if(!$.o.bg.size() || !$.o.ele.size() || !$.o.out.size()){
			return;
		}
		var w = $(window).width(),
		h = $(window).height(),
		ph = $("body").height();
		if(ph < h){
			ph = h;
		}
		$.o.bg.css("opacity", $.o.s.opacity);
		if (ie6){
			$.o.bg.css({
				position:"absolute",
				width:w,
				height:ph
			});
			$.o.out.css("position","absolute");
		}
		//主体内容的位置
		//获取当前主体元素的尺寸
		var xh = $.o.out.outerHeight(), xw = $.o.out.outerWidth();

		//弹出层定位：
		switch($.o.s.position){
			case "middle":
				$.o.out.css({
					"top":"50%",
					"left":"50%",
					"marginLeft": '-' + parseInt((xw / 2),10) + 'px',
					"width": xw + 'px',zIndex:$.o.s.index
				});
				if ( !($.browser.msie && $.browser.version < 7)) { // 兼容IE6
					$.o.out.css({marginTop: '-' + parseInt((xh / 2),10) + 'px'});
				}
				break;
			case "left_top":
				$.o.out.css({
					"top":0,
					"left":0,
					"width": xw + 'px',zIndex:$.o.s.index
				});
				break;
			case "left_middle":
				$.o.out.css({
					"top":"50%",
					"left":0,
					"width": xw + 'px',zIndex:$.o.s.index
				});
				if ( !($.browser.msie && $.browser.version < 7)) { // 兼容IE6
					$.o.out.css({marginTop: '-' + parseInt((xh / 2),10) + 'px'});
				}
				break;
			case "left_bottom":
				$.o.out.css({
					"bottom":0,
					"left":0,
					"width": xw + 'px',zIndex:$.o.s.index
				});
				break;
			case "top_middle":
				$.o.out.css({
					"top":"0",
					"left":"50%",
					"marginLeft": '-' + parseInt((xw / 2),10) + 'px',
					"width": xw + 'px',zIndex:$.o.s.index
				});
				break;
			case "right_top":
				$.o.out.css({
					"top":0,
					"right":0,
					"width": xw + 'px',zIndex:$.o.s.index
				});
				break;
			case "right_middle":
				$.o.out.css({
					"top":"50%",
					"right":0,
					"width": xw + 'px',zIndex:$.o.s.index
				});
				if ( !($.browser.msie && $.browser.version < 7)) { // 兼容IE6
					$.o.out.css({marginTop: '-' + parseInt((xh / 2),10) + 'px'});
				}
				break;
			case "right_bottom":
				$.o.out.css({
					"bottom":0,
					"right":0,
					"width": xw + 'px',zIndex:$.o.s.index
				});
				break;
			case "bottom_middle":
				$.o.out.css({
					"bottom":"0",
					"left":"50%",
					"marginLeft": '-' + parseInt((xw / 2),10) + 'px',
					"width": xw + 'px',zIndex:$.o.s.index
				});
				break;
			default:
				$.o.out.css({
					"top":"50%",
					"left":"50%",
					"marginLeft": '-' + parseInt((xw / 2),10) + 'px',
					"width": xw + 'px',zIndex:$.o.s.index
				});
				if ( !($.browser.msie && $.browser.version < 7)) { // 兼容IE6
					$.o.out.css({marginTop: '-' + parseInt((xh / 2),10) + 'px'});
				}
				break;
		}
		return $.o.out;
	};
	$.skygqbox.hide = function(){
		if($.o.ele && $.o.out.size() && $.o.bg.size()){
			$.o.ele.clone(true).appendTo($("body")).hide();
			$.o.out.fadeOut("fast", function(){
				$(this).remove();
			});
			$.o.bg.fadeOut("fast", function(){
				$(this).remove();
			});
		}
		return false;
	};
	$.fn.skygqbox.Default = {
		title		: "温馨提示",
		shut		: "关闭",
		index		: 2000,
		opacity		: 0.5,
		width		: "auto",
		height		: "auto",
		autoClose	: 0,//弹出层等待多长时间自动关闭(单位：毫秒) 0或者负数不触发自动关闭
		position	: "middle"
	};
})(jQuery);