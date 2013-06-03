/**********************************************************************************
 * 扩展jQuery，提供系统相关的一些函数
 * ********************************************************************************
 */
jQuery.orionis = {
	/**
	 * 判断变量是否为空
	 */
	isEmpty:function(str){// 判断变量是否为空
		return str == undefined || str == "";
	},
	/**
	 * 组装合适的url地址
	 */
	url:function(url){// 组装合适的url
		if(url.indexOf("http://") == 0 
				|| url.indexOf("https://") == 0 
				|| url.indexOf("ftp://") == 0 ){
			return url;
		}
		
		if(url.indexOf("/") == 0){
			return basePath + "cms/" + url.substring(1);
		}
		return basePath + "cms/" + url;
	},
	/**
	 * 获取元素的属性值，可选默认值
	 */
	getAttr:function(ref, attr, defaultVal){ // 获取属性值
		return $.orionis.isEmpty(ref.attr(attr)) ? defaultVal : ref.attr(attr);
	},
	/**
	 * 动态载入javascript
	 * 问题：该如何判断js已经加载完毕？
	 */
	loadJs:function(url){//动态加载js
		//判断js是否加载过
		var isLoaded = false;
		$("script").each(function(){
			var cur = $(this).attr("src");
			if(cur == url){
				isLoaded = true;
				return false;
			}
		});
		
		if(isLoaded){
			return true;
		}
		//执行加载
		var script = document.createElement('script');  
		script.src = url;  
		script.type = 'text/javascript';  
		script.charset = 'utf-8';  
		var head = document.getElementsByTagName('body')[0];  
		head.appendChild(script);
	},
	/**
	 * 更新主面板内容
	 * @param url
	 */
	updateMain:function(url){
		$.get($.orionis.url(url), {}, function(data){
			$("#ajax_main").html(data);
			$.orionis.reloadJsEvent();
		});
	},
	reloadJsEvent:function(){
		/**********************************************************************************
		 * 表单AJAX提交
		 * 表单属性: 
		 * 		before:		表单提交之前的处理动作
		 * 		after:		表单提交之后的处理动作，会覆盖success/failed/defaultAction/reload
		 * 		success:	表单提交之后，返回data.status = 1的处理动作, 覆盖defaultAction/reload
		 * 		failed:		表单提交后，返回data.status = 0 的处理动作，覆盖defaultAction/reload
		 * 		ajax:		是否开启ajax提价方式，默认是false，如果为false，则其它所有条件均无效
		 * 		reload:		是否在返回status=1 的情况下自动刷新页面，需要defaultAction=true
		 * 		defaultAction:	是否采用默认的动作，弹窗提示消息，默认为false
		 * ********************************************************************************
		 */
		if($("form[ajax='true']").length){
			$("form[ajax='true']").off("submit").on("submit", function(e){
				e.preventDefault();
				var options = {
						dataType: 'json'	
				};
				
				options.url = $.orionis.url($(this).attr("action"));
				// 表单提交之前的处理，一般是表单字段校验
				// function_name(formData, form, options)
				if( !$.orionis.isEmpty($(this).attr("before"))){
					options.beforeSubmit = eval($(this).attr("before"));
				}
				
				// 表单提交之后的处理，将会覆盖success和failed处理函数
				if( !$.orionis.isEmpty($(this).attr("after"))){
					options.success =  eval($(this).attr("after"));
				}else{ // 表单提交之后，根据返回的status值进行处理函数的分发
					var form = $(this);
					options.success = function(data){
						// status == 1 处理成功
						if( !$.orionis.isEmpty(form.attr("success")) && data.status == 1){
							eval(form.attr("success"))(data);
							return true;
						}
						
						// status == 0 处理失败
						if( !$.orionis.isEmpty(form.attr("failed")) && data.status == 0){
							eval(form.attr("failed"))(data);
							return true;
						}
						
						// 默认处理方式
						if(form.attr("default") == 'true'){
							$.orionis.alert_message(data.info);
							if(data.status == 1 && form.attr("reload") == 'true'){
								window.location.reload(true);
							}
						}
					};
				}
				
				$(this).ajaxSubmit(options);
			});
		}
		
		/**********************************************************************************
		 * 为按钮增加href点击事件
		 * ********************************************************************************
		 */
		if($("button[parse='true']").length){
			$("button[parse='true']").click(function(e){
				e.preventDefault();
				var href = $(this).attr("href");
				var params = $(this).attr("params");
				
				$.orionis.updateMain(href + (params != "" ? "?" + params : ""));
			});
		}
		/**********************************************************************************
		 * 解析超链接为正确的格式
		 * 需要解析的超链接增加parse的class属性即可
		 * ********************************************************************************
		 */
		if($("a.parse").length){
			$("a.parse").click(function(e){
				e.preventDefault();
				$.orionis.updateMain($(this).attr("href"));
			});
			$("form.parse").each(function(){
				$(this).attr("action", $.orionis.url($(this).attr("action")));
			});
			//$("a.parse").click(function(e){
				//e.preventDefault();
				//window.location.href = $.orionis.url($(this).attr("href"));
			//});
		}
		/**********************************************************************************
		 * 日期选择器
		 * ********************************************************************************
		 */
		if($('input.datepicker').length){
			$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
			$('input.datepicker').datepicker({ 
						dateFormat: "yy-mm-dd", 
						appendText: " (年-月-日)",
						changeMonth: true});
		}
		if($('input.datetimepicker').length){
			$('input.datetimepicker').datetimepicker({ 
				timeFormat: 'HH:mm:ss',
				stepHour: 2,
				stepMinute: 10,
				stepSecond: 10});
		}
		/**********************************************************************************
		 * Tab页, 支持ajax
		 * ********************************************************************************
		 */
		if($('div.tabs').length){
			$('div.tabs').tabs({
			      beforeLoad: function( event, ui ) {
			          ui.jqXHR.error(function() {
			            ui.panel.html(
			              "<img src='" + basePath + "resources/orionis/images/load.gif' />" );
			          });}});
		}
		/**********************************************************************************
		 * 验证码刷新
		 *********************************************************************************
		 */
		if($('img.captcha').length){
			$('img.captcha').attr("src", basePath + "cms/captcha-image?sid=" + Math.random()).tooltip({
				show: null,
			      position: {
			        my: "left top",
			        at: "right center"
			      },
			      open: function( event, ui ) {
			        ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
			      }
			});
			$('img.captcha').click(function(){
				$(this).attr("src", basePath + "cms/captcha-image?sid=" + Math.random());
			});
		}
	},
	/**
	 * 对话框提示消息
	 */
	alert_message:function(message){//Alert提示消息
		var dialog = $("#message");
		dialog.html(message);
		dialog.dialog({
			modal: true,
			width:400,
			height:190,
			buttons: {
		        Ok: function() {
		          $( this ).dialog( "close" );
		        }
		      }
		});
	},
	dialog_dismiss:function(){
		$("#dialog").dialog("close");
	},
	/**
	 * 弹出对话框
	 * @param html
	 */
	dialog:function(html, title){
		var dialog = $("#dialog");
		dialog.html(html);
		dialog.dialog({
			modal: true,
			width:800,
			height:400,
			title: title
		});
	},
	/**
	 * 为元素绑定上传控件
	 * 
	 * 字段要求：
	 * 	1. 绑定的元素应该为input 类型为file
	 *  2. 绑定的input标签上增加以下两个属性：
	 *  	a) field-bind 用于绑定一个隐含的input输入框，绑定服务器返回的文件名token
	 *  	b) multiple="true"
	 *  3. input元素后面紧跟一个<div class="_filename"></div>
	 * 
	 *  三个额外表单参数：
	 *  	1. JSESSIONID 用于辨别用户，弥补flash的漏洞
	 *  	2. PRIVATE_ACCESS 区分文件上传地址是公开位置还是私有位置
	 *  	3. PRIVATE_KEY 加密密钥
	 *  
	 */
	upload_bind: function(id, private_key, privateAccess){//上传文件绑定
		// 绑定的上传结果字段
		var field = $("#" + id).attr("field-bind");
		privateAccess = (privateAccess == undefined ? "true" :privateAccess);
		$("#" + id).uploadify({
			'method'		:	'POST',
			'fileObjName' : 'upload_file',
			'formData'     : {
				'JSESSIONID': $("input[name='_jsessionId_']").val(),
				'PRIVATE_ACCESS': privateAccess,
				'PRIVATE_KEY': private_key,
			},
			'swf'      : basePath + 'resources/jquery-uploadify/uploadify.swf',
			'uploader' : $.orionis.url("file/upload"),
			'uploadLimit' : 5, 
			'fileSizeLimit': '4MB',
			'queueID':'queue',
			'height': 26,
			'buttonText':"选择文件",
			'removeCompleted':true,
			'fileTypeExts' : '*.gif; *.jpg; *.png',
			'onUploadSuccess' : function(file, data, response) {
				if(response){
					var result = eval("(" + data + ")");
					if(result.status == 1){
						$("input[name='" + field + "']").val(result.info);
						$("#" + id).siblings("._filename").html(file.name);
						$.orionis.alert_message("上传成功!");
					}else{
						$.orionis.alert_message(result.info);
					}
					
	            }
	        }
		});
	}
};