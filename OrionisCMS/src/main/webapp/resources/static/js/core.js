$(function(){
	/****************************************************
	 *    导航菜单
	 ****************************************************/
	var bindUrlControl = function(selector){
		$(selector + " a").bind("click",function(e){
			e.preventDefault();
			
			//将当前项设为高亮
			$( selector + " li").removeClass("active");
			$(this).parent("li").addClass("active");
			
			$.orionis.updateMain($(this).attr("href"));
		});
	};
	DirectRemote.getNavigation("top", function(data){
		var str = "";
		//遍历导航对象，获取导航数据
		for(var j = 0; j < data.subItems.length; j ++){
			var topItem = data.subItems[j];
			//菜单主项目
			str += "<li><a href='" + topItem.linkUrl + "'>" + topItem.linkName + "</a></li>";
		}
		$("#ajax_main_nav").html(str);
	});
	$("#ajax_main_nav a").live("click", function(e){
		e.preventDefault();
		DirectRemote.getNavigation($(this).attr("href"), function(data){
			var str = "";
			//遍历导航对象，获取导航数据
			for(var j = 0; j < data.subItems.length; j ++){
				var topItem = data.subItems[j];
				//菜单主项目
				str += "<li class='nav-header'>" + topItem.linkName + "</li>";
				//具体菜单项
				for(var i = 0; i < topItem.subItems.length; i ++){
					var subItem = topItem.subItems[i];
					str += "<li><a href='" + subItem.linkUrl + "'>" + subItem.linkName + "</a></li>";
				}
			}
			$("#ajax_left_nav").html(str);
			bindUrlControl("#ajax_left_nav");
		});
	});
});
