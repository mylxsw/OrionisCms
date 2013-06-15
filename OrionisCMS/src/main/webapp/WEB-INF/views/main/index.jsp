<%@page import="java.util.List"%>
<%@page import="name.orionis.cms.core.rbac.dto.NavItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>Orionis CMS</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<link rel="stylesheet" href="<c:url value="/resources/orionis-admin/vendor/easy-ui/themes/bootstrap/easyui.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/orionis-admin/vendor/bootstrap-jquery-ui/css/bootstrap.min.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/orionis-admin/vendor/bootstrap-jquery-ui/css/bootstrap-responsive.min.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/jquery-ui/css/custom-theme/jquery-ui-1.10.0.custom.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/orionis-admin/css/easy-main.css" />"/>
<!--[if IE 7]>
<link rel="stylesheet" href="<c:url value="/resources/assets/css/font-awesome-ie7.min.css" />">
<![endif]-->
<!--[if lt IE 9]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/custom-theme/jquery.ui.1.10.0.ie.css" />" />
<![endif]-->

<link type="text/css" href="<c:url value="resources/assets/css/font-awesome.min.css" />" />

<link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/assets/ico/apple-touch-icon-144-precomposed.png" />" >
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value="/resources/assets/ico/apple-touch-icon-114-precomposed.png" />">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/resources/assets/ico/apple-touch-icon-72-precomposed.png" />">
<link rel="apple-touch-icon-precomposed" href="<c:url value="/resources/assets/ico/apple-touch-icon-57-precomposed.png" />">
<link rel="shortcut icon" href="<c:url value="/resources/assets/ico/favicon.png" />">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var CKEDITOR_BASEPATH = basePath + "resources/ckeditor/";
</script>
</head>
<body  class="easyui-layout">
<div class="navbar navbar-inverse navbar-fixed-top" data-options="region:'north'" >
  <div class="navbar-inner">
    <div class="container-fluid">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="#">Orionis CMS</a>
      <div class="nav-collapse collapse">
        <p class="navbar-text pull-right">
           <a href="#" class="navbar-link logout" >退出</a>
        </p>
        <ul class="nav" id="ajax_main_nav"></ul>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>
<div data-options="region:'west', split: true" title="侧栏菜单" id="sidebar">
	<div class="well sidebar-nav">
       <ul class="nav nav-list" id="ajax_left_nav">
       </ul>
     </div>
</div>
<div data-options="region:'center'" id="ajax_main">
	<ul class="breadcrumb">
		<li>
			<a href="#"><i class="icon-home icon-white"></i> 主页</a><span class="divider">/</span>
		</li>
		<li>
			<a href="#">类库</a><span class="divider">/</span>
		</li>
		<li class="active">
			数据
		</li>
	</ul>
	<div class="body">
		<div class="row-fluid ">
			<div class="span12">
				<div class="widget">
					<div class="widget-title">
						<div class="icon">
							<i class="icon-th-list"></i>
						</div>
						<h4>管理内容</h4>
						<a href="#" class="minimize"><i class="icon-chevron-up"></i></a>
					</div>
					<div class="widget-content">
						<div>欢迎使用Orionis CMS 二次开发平台</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="window"></div>
<div id="dialog"></div>
<div id="message"></div>
<div id="queue"></div>
<script src="<c:url value="/resources/static/js/jquery-1.8.3.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/static/js/jquery.form.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/jquery-ui/js/jquery-ui-1.10.3.custom.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/jquery-ui/js/jquery.ui.datepicker-zh-CN.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/jquery-ui/js/jquery-ui-timepicker-addon.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/jquery-uploadify/jquery.uploadify.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/easyloader.js" />"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/plugins/jquery.resizable.js" />"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/plugins/jquery.parser.js" />"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/plugins/jquery.draggable.js" />"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/plugins/jquery.droppable.js" />"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/plugins/jquery.layout.js" />"></script>
<script src="<c:url value="/resources/orionis-admin/vendor/easy-ui/plugins/jquery.panel.js" />"></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js" />'></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/DirectRemote.js" />'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/ueditor/editor_config.js" />' ></script>
<script type="text/javascript" src='<c:url value="/resources/ueditor/editor_all_min.js" />' ></script>
<script src="<c:url value="/resources/static/js/jquery-cms-ext.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/static/js/core.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/orionis-admin/js/easy-ui-main.js" />"></script>
</body>
</html>