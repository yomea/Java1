//延迟加载
!function(a,b,c,d){var e=a(b);a.fn.lazyload=function(f){function g(){var b=0;i.each(function(){var c=a(this);if(!j.skip_invisible||c.is(":visible"))if(a.abovethetop(this,j)||a.leftofbegin(this,j));else if(a.belowthefold(this,j)||a.rightoffold(this,j)){if(++b>j.failure_limit)return!1}else c.trigger("appear"),b=0})}var h,i=this,j={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:b,data_attribute:"original",skip_invisible:!0,appear:null,load:null,placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};return f&&(d!==f.failurelimit&&(f.failure_limit=f.failurelimit,delete f.failurelimit),d!==f.effectspeed&&(f.effect_speed=f.effectspeed,delete f.effectspeed),a.extend(j,f)),h=j.container===d||j.container===b?e:a(j.container),0===j.event.indexOf("scroll")&&h.bind(j.event,function(){return g()}),this.each(function(){var b=this,c=a(b);b.loaded=!1,(c.attr("src")===d||c.attr("src")===!1)&&c.is("img")&&c.attr("src",j.placeholder),c.one("appear",function(){if(!this.loaded){if(j.appear){var d=i.length;j.appear.call(b,d,j)}a("<img />").bind("load",function(){var d=c.attr("data-"+j.data_attribute);c.hide(),c.is("img")?c.attr("src",d):c.css("background-image","url('"+d+"')"),c[j.effect](j.effect_speed),b.loaded=!0;var e=a.grep(i,function(a){return!a.loaded});if(i=a(e),j.load){var f=i.length;j.load.call(b,f,j)}}).attr("src",c.attr("data-"+j.data_attribute))}}),0!==j.event.indexOf("scroll")&&c.bind(j.event,function(){b.loaded||c.trigger("appear")})}),e.bind("resize",function(){g()}),/(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion)&&e.bind("pageshow",function(b){b.originalEvent&&b.originalEvent.persisted&&i.each(function(){a(this).trigger("appear")})}),a(c).ready(function(){g()}),this},a.belowthefold=function(c,f){var g;return g=f.container===d||f.container===b?(b.innerHeight?b.innerHeight:e.height())+e.scrollTop():a(f.container).offset().top+a(f.container).height(),g<=a(c).offset().top-f.threshold},a.rightoffold=function(c,f){var g;return g=f.container===d||f.container===b?e.width()+e.scrollLeft():a(f.container).offset().left+a(f.container).width(),g<=a(c).offset().left-f.threshold},a.abovethetop=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollTop():a(f.container).offset().top,g>=a(c).offset().top+f.threshold+a(c).height()},a.leftofbegin=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollLeft():a(f.container).offset().left,g>=a(c).offset().left+f.threshold+a(c).width()},a.inviewport=function(b,c){return!(a.rightoffold(b,c)||a.leftofbegin(b,c)||a.belowthefold(b,c)||a.abovethetop(b,c))},a.extend(a.expr[":"],{"below-the-fold":function(b){return a.belowthefold(b,{threshold:0})},"above-the-top":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-screen":function(b){return a.rightoffold(b,{threshold:0})},"left-of-screen":function(b){return!a.rightoffold(b,{threshold:0})},"in-viewport":function(b){return a.inviewport(b,{threshold:0})},"above-the-fold":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-fold":function(b){return a.rightoffold(b,{threshold:0})},"left-of-fold":function(b){return!a.rightoffold(b,{threshold:0})}})}(jQuery,window,document);
$(function () {
    //图片延时加载
    $(".lazyload img,#container img").lazyload({
        placeholder: "http://images.shopin.net/s/images/s.gif",
		threshold:200,
        failurelimit: 10,
        effect: "fadeIn"
    });
    //主导航
    var liPara = [];
    var liW = [];
    $(".navBar ul li").each(function (n, alt) {
        liW.push($(this).children("a").width());
        liPara.push($(this).children("a").position().left);
        $(this).children("a").attr({ "alt": liPara[n], "date-width": liW[n] });
    });
    var liLeft = $(".focus").attr("alt");
    var dateWidth = $(".focus").attr("date-width");
    var localLeft = 0;
    $("#tomy").css({
        'left': liLeft + "px",
        'width': dateWidth
    });
    $('.navBar ul li').hover(function () {
        $("#tomy").stop().animate({ left: $(this).children().attr("alt"), width: $(this).children().attr("date-width") });
    }, function () {
        $("#tomy").stop().animate({ left: liLeft, width: dateWidth });
    });
    $(".navBar ul li a").click(function () {
        liLeft = $(this).attr("alt");
        dateWidth = $(this).attr("date-width");
    });
    //顶部二级菜单
    $('.j_topMenu').hover(function(){
    	$(this).addClass('menuHover');
    },function(){
    	$(this).removeClass('menuHover')
    })
    //品类
	$(".jCate").find(".cateItem").each(function() {
        var $this = $(this);
		$this.hoverDelay({
            outDuring: 300,
            hoverEvent: function(){
                $this.children(".catehd").addClass("focus");
				$this.siblings().children(".catehd").removeClass("focus");
				$this.children(".cateBox").show();
				$this.siblings().children(".cateBox").hide();
            },
            outEvent: function(){
                $this.children(".catehd").removeClass("focus");
				$(".jCate").find(".cateBox").slideUp();
            }
        });
    });
    //添加当前状态
	$(".addOn li").live('mousemove',function(){
		$(this).addClass("on").siblings().removeClass("on");
	});
	$(".addOn li").live('mouseout',function(){
		 $(this).removeClass("on");
	});
    //关注我们
    $(".tWeixin").hover(function () {
        $(".tCode").show();
    }, function () {
        $(".tCode").hide();
    });
	//限制购物车显示数量
	var proNum = $(".cartAmount").text();
	if(proNum>99){
		$(".cartAmount").text("99+");
	}
    //显示购物车
    $("#jCart").hover(function () {
        $(this).children("dd").fadeIn();
    }, function () {
        $(this).children("dd").hide();
    });
	//闪购
	$(".jSale").find("li").each(function() {
        $(this).hover(function(){
			$(this).addClass("on").siblings().removeClass("on");
			$(this).find(".saleText,.saleTextBg").animate({bottom:0});
		},function(){
			$(this).removeClass("on");
			$(this).find(".saleText,.saleTextBg").stop().animate({bottom:-30});
		});
    });
	//回顶部
	$(".mytools").goToTop();
	$(window).bind('scroll resize',function(){
		$(".mytools").goToTop({});
	});

	//浮动导航
	$(".jSmart").smartFloat();
    //清除输入框默认值
    $("input:text").focus(function () {
        var inputTxt = $(this).val();
        $("#search").css('backgroundColor', '#f20')
        if (inputTxt == this.defaultValue) {
            $(this).val("").css('color', '#000');
        }
    });
    $("input:text").blur(function () {
        var inputTxt = $(this).val();
        $("#search").css('backgroundColor', '')
        if (inputTxt == "") {
            $(this).val(this.defaultValue).css('color', '#868686');
        }
    });
	//公告滚动函数
	if($('#g_box li')[0] && $('#g_box li').length > 1){
	    $('.gonggao').show()
	    var tId = setInterval(gg_roll,5000)
	}else if($('#g_box li')[0]){
	    $('.gonggao').show()
	}
});
function gg_roll(){
    $('#g_box').animate({'top':'-20px'},600,function(){$('#g_box').css('top','0');move_GG_li()});
}
function move_GG_li(){
    var first = $('#g_box li').first();
    var last  = $('#g_box li').last();
    var firstHtml = first.html();
    first.remove();
    $('#g_box').append("<li>" + firstHtml + "</li>");
}
//回顶部
var goToTopTime;
$.fn.goToTop=function(options){
	var opts = $.extend({},$.fn.goToTop.def,options);
	var $window=$(window);
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body'); // opera fix
	//$(this).hide();
	var $this=$(this);
	clearTimeout(goToTopTime);
	goToTopTime=setTimeout(function(){
		var controlLeft;
		if ($window.width() > opts.pageHeightJg * 2 + opts.pageWidth) {
			controlLeft = ($window.width() - opts.pageWidth) / 2 + opts.pageWidth + opts.pageWidthJg;
		}else{
			controlLeft = $window.width()- opts.pageWidthJg-$this.width();
		}
		var cssfixedsupport=$.browser.msie && parseFloat($.browser.version) < 7;//判断是否ie6
		var controlTop=$window.height() - $this.height()-opts.pageHeightJg;
		controlTop=cssfixedsupport ? $window.scrollTop() + controlTop : controlTop;
		var shouldvisible=( $window.scrollTop() >= opts.startline )? true : false;

		if (shouldvisible){
			$this.stop().show();
		}else{
			$this.stop().hide();
		}

		$this.css({
			position: cssfixedsupport ? 'absolute' : 'fixed',
			top: controlTop,
			left: controlLeft
		});
	},30);
	$(".backToTop").click(function(event){
		$body.stop().animate( { scrollTop: $(opts.targetObg).offset().top}, opts.duration);
		$(this).blur();
		event.preventDefault();
		event.stopPropagation();
	});
};
$.fn.goToTop.def={
	pageWidth:1200,
	pageWidthJg:2,
	pageHeightJg:50,
	startline:50,
	duration:200,
	targetObg:"body"
};
//鼠标延迟执行方法
$.fn.hoverDelay = function(options){
	var defaults = {
		hoverDuring: 300,
		outDuring: 300,
		hoverEvent: function(){
			$.noop();
		},
		outEvent: function(){
			$.noop();
		}
	};
	var sets = $.extend(defaults,options || {});
	var hoverTimer, outTimer, that = this;
	return $(this).each(function(){
		$(this).hover(function(){
			clearTimeout(outTimer);
			hoverTimer = setTimeout(function(){sets.hoverEvent.apply(that)}, sets.hoverDuring);
		},function(){
			clearTimeout(hoverTimer);
			outTimer = setTimeout(function(){sets.outEvent.apply(that)}, sets.outDuring);
		});
	});
}
//浮动层固定位置
$.fn.smartFloat = function () {
    var position = function (element) {
        var top = element.position().top, pos = element.css("position");
        $(window).scroll(function () {
            var scrolls = $(this).scrollTop();
            if (scrolls > top) {
                if (window.XMLHttpRequest) {
                    element.css({
                        position: "fixed",
                        top: 0
                    });
                } else {
                    element.css({
                        top: scrolls
                    });
                }
            } else {
                element.css({
                    position: pos,
                    top: top
                });
            }
        });
    };
    return $(this).each(function () {
        position($(this));
    });
};
//倒计时
function countDown() {
	$(".countDown").each(function(){
		var showday = $(this).attr("showday");//用来判断是否显示天数的变量
		var endtime = new Date($(this).attr("endtime")).getTime();//取结束日期(毫秒值)
		var nowtime = new Date().getTime();        //今天的日期(毫秒值)
		var youtime = endtime - nowtime;//还有多久(毫秒值)
		var seconds = youtime / 1000 - 14*3600;
		var minutes = Math.floor(seconds / 60);
		var hours = Math.floor(minutes / 60);
		var days = Math.floor(hours / 24);
		var CDay = days;
		var CHour = hours % 24;
		var CMinute = minutes % 60;
		var CSecond = Math.floor(seconds % 60);//"%"是取余运算，可以理解为60进一后取余数，然后只要余数。
		if (endtime <= nowtime) {
			$(this).html("本次活动已结束！")//如果结束日期小于当前日期就提示过期啦
		} else {
			if ($(this).attr("showday") == "no") {
				$(this).html("<span>" + CHour + "</span>小时<span>" + CMinute + "</span>分<span>" + CSecond + "</span>秒");//输出没有天数的数据
			} else {
				$(this).html("<span>" + days + "</span>天<span>" + CHour + "</span>小时<span>" + CMinute + "</span>分<span>" + CSecond + "</span>秒");//输出有天数的数据
			}
		}
	});
	setTimeout("countDown()", 1000);
};
//搜索
$(function(){
	//键盘事件
	$("#searchKey").keydown(function(event){
		//向下键
		if(event.keyCode == 40){
			//当前选中项
			var nowli = $("#select li.on");
			//如果已存在选中，则向下，否则，选中第一个
			if(nowli.length>0){
				nextli=nowli.next("li")
				//如果不是最后选项，向下个li移动，否则跳到第一个li
				if(nextli.length>0){
					$("li.on").removeClass("on");
					nextli.addClass("on");
				}
				else{
					$("li.on").removeClass("on");
					nowli.parent().find("li:first").addClass("on");
				}
			}
			else{
				$("#select").find("li:first").addClass("on");
			}
		}
		else if(event.keyCode == 38){
			//向上键
			var nowli = $("#select li.on");
			//如果已存在选中，则向上，否则，选中第一个
			if(nowli.length>0){
				prevli=nowli.prev("li")
				//如果不是最后选项，向下个li移动，否则跳到第一个li
				if(prevli.length>0){
					$("li.on").removeClass("on");
					prevli.addClass("on");
				}
				else{
					$("li.on").removeClass("on");
					nowli.parent().find("li:last").addClass("on");
				}
			}
			else{
				$("#select").find("li:first").addClass("on");
			}
		}
		else if(event.keyCode == 13){
			//按键是回车，则确定返回并隐藏选框
			var nowli=$("#select li.on");
			if(nowli.length == 1){
				var searchText = nowli.children(".searchItem").text();
				$(this).val(searchText);
				goSearch();
			}
			hideSearch();
			return false;
		}
		return true;
	});
	//鼠标点击选定
	$("#select li").live("click", function(){
		var searchText = $(this).children(".searchItem").text();
		$("#searchKey").val(searchText);
		goSearch();
		hideSearch();
	});
});
//显示下拉层
// $("#searchKey").live('propertychange input', function () {
//    showSearch();
// });
//搜索
function goSearch(){
	var keyword = $("#searchKey").val();
	if (keyword == "请输入搜索内容！") {
		$("#searchKey").focus();
		return;
	}
	if (keyword != null && keyword != "" && keyword != "请输入搜索内容！") {
		alert("搜索！");
	}
};
$(document).click(function(event) {
	hideSearch()
});

function showSearch(){
	$(".searchList").show();
}
function hideSearch(){
	$(".searchList").hide();
}
