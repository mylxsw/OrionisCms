$(function(){
	/**
	 * 顶部菜单单击事件
	 * 将会通过Dwr的直接远程调用获取所有的左侧栏菜单项，
	 * 并组装后替换掉左侧栏菜单项
	 */
	if($("#ajax_main_nav").length){
		var ajax_main_nav_cur = "";//用于防止多次重复点击
		$("#ajax_main_nav a").click(function(e){
			//禁止链接默认事件
			e.preventDefault();
			
			//防止多次重复点击
			if(ajax_main_nav_cur == $(this).attr("link-name")){
				return true;
			}
			ajax_main_nav_cur = $(this).attr("link-name");
			
			//当前对象高亮
			$("#ajax_main_nav li").removeClass("active");
			$(this).parent("li").addClass("active");
			
			//远程调用方法，获取所有的导航对象
			AdminNav.getLeftNav($(this).attr("link-name"), function(data){
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
				
				//alert(dwr.util.toDescriptiveString(topItem, 2));
				//填充到左侧栏
				$("#ajax_left_nav").html(str);
				//为左侧栏菜单增加链接控制
				bindUrlControl("#ajax_left_nav");
			});
		});
	}
	
	if($(".logout").length){
		$(".logout").click(function(e){
			e.preventDefault();
			var href= $(this).attr("href");
			$.get(href, {}, function(data){
				alert("成功退出");
				if(data.status == 1){
					window.location.href=basePath + "admin/login.html";
				}
			},"json");
		});
	}
	
	/**
	 * 为左侧栏菜单项绑定单击事件，绑定后，所有链接的目标通过ajax
	 * 方式调用，显示结果到主面板中
	 */
	function bindUrlControl(selector){
		$(selector + " a").bind("click",function(e){
			e.preventDefault();
			
			//将当前项设为高亮
			$( selector + " li").removeClass("active");
			$(this).parent("li").addClass("active");
			
			$.get(basePath + $(this).attr("href") + ".html", {}, function(data){
				$("#ajax_main").html(data);
			});
		});
	}
});
