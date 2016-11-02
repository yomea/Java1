$(function(){
	$(".tabBd .item:not(:first)").hide();
	function tabs(tabTit,on,tabCon){
		$(tabCon).each(function(){
			$(this).children().eq(0).show();
		});
		$(tabTit).each(function(){
			$(this).children().eq(0).addClass(on);
		});
		$(tabTit).children().mousemove(function(){
			$(this).addClass(on).siblings().removeClass(on);
			var index = $(tabTit).children().index(this);
			$(tabCon).children().eq(index).show().siblings().hide().end();
			$(tabCon).find('img').each(function () {
				var original = $(this).attr("data-original");
				if (original) {
					$(this).attr('src', original).removeAttr('data-original');
				}
			});
		});
	}
	tabs(".tabHd","cur",".tabBd");
});