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
	var getNav = function(href){
		DirectRemote.getNavigation(href, function(data){
			var str = "";
			//遍历导航对象，获取导航数据
			for(var j = 0; j < data.subItems.length; j ++){
				var topItem = data.subItems[j];
				//菜单主项目
				if(topItem.folder){
					str += "<li class='nav-header'>" + topItem.linkName + "</li>";
				}else{
					str += "<li><a href='" + topItem.linkUrl + "'>" + topItem.linkName + "</a></li>";
				}
				//具体菜单项
				for(var i = 0; i < topItem.subItems.length; i ++){
					var subItem = topItem.subItems[i];
					str += "<li><a href='" + subItem.linkUrl + "'>" + subItem.linkName + "</a></li>";
				}
			}
			$("#ajax_left_nav").html(str);
			bindUrlControl("#ajax_left_nav");
		});
	};
	$("#ajax_main_nav a").live("click", function(e){
		e.preventDefault();
		getNav($(this).attr("href"));
	});
	DirectRemote.getNavigation("top", function(data){
		var str = "";
		//遍历导航对象，获取导航数据
		for(var j = 0; j < data.subItems.length; j ++){
			var topItem = data.subItems[j];
			//菜单主项目
			str += "<li><a href='" + topItem.linkUrl + "'>" + topItem.linkName + "</a></li>";
		}
		$("#ajax_main_nav").html(str);
		getNav(data.subItems[0].linkUrl);
	});
	/****************************************************************
	 * 			安全退出
	 * ***************************************************************/
	$(".logout").click(function(e){
		e.preventDefault();
		if(confirm("Are you sure you want to logout？") == false){
			return ;
		}
		$.get($.orionis.url("account/logout"),{}, function(data){
			$.orionis.alert_message(data.info);
			if(data.status == 1){
				window.location.href = $.orionis.url("account/login");
			}
		},"json");
	});
});
