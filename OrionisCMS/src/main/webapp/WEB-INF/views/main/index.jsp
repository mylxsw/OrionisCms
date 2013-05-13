<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>Dashboard</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.min.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/static/css/core.css" />"/>
</head>
<body>
<div class="container-fluid container-global">
<div class="navbar navbar-inverse ">
  <div class="navbar-inner">
    <div class="container-fluid">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="#">CMS后台管理</a>
      <div class="nav-collapse collapse">
        <p class="navbar-text pull-right">
          Logged in as <a href="#" class="navbar-link">Username</a>
        </p>
        <ul class="nav">
          <li class="active"><a href="index.html">我的面板</a></li>
          <li><a href="setting.html">设置</a></li>
          <li><a href="module.html">模块</a></li>
          <li><a href="#contact">内容</a></li>
          <li><a href="#contact">用户</a></li>
          <li><a href="#contact">界面</a></li>
          <li><a href="#contact">扩展</a></li>
          <li><a href="#contact">应用</a></li>
        </ul>
        <form class="navbar-search pull-left" action="">
          <input type="text" class="search-query span2" placeholder="Search">
        </form>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row-fluid">

    <div class="span2 left_nav" >
      <div class="well sidebar-nav">
        <ul class="nav nav-list">
          <li class="nav-header">个人设置</li>
          <li class="active"><a href="#">修改个人信息</a></li>
          <li><a href="#">修改密码</a></li>
          <li class="nav-header">内容管理</li>
          <li><a href="#">管理内容</a></li>
          <li><a href="#">附件管理</a></li>
          <li><a href="#">专题</a></li>
          <li><a href="#">评论管理</a></li>
          <li class="nav-header">缓存管理</li>
          <li><a href="#">生成首页</a></li>
          <li><a href="#">清理缓存</a></li>
          <li><a href="#">更新栏目</a></li>
        </ul>
      </div><!--/.well -->
    </div><!--/span-->
    
    <div class="span10 main">
		<ul class="breadcrumb">
		  <li><a href="#"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
		  <li><a href="#">Library</a> <span class="divider">/</span></li>
		  <li class="active">Data</li>
		</ul>
     	<div class="row-fluid">
			<div class="span6 o-module">
				<h4>我的个人信息</h4>
				<div class="span12">
					<p>你好，管理员cms</p>
					<p>所属角色：超级管理员</p>
					<hr class="hr"/>
					<p>上次登录时间：2013-12-11</p>
				</div>				
			</div>
			<div class="span6 o-module">
				<h4>安全提示</h4>
				<div class="span12">
					<div style="color:#ff0000;">
					※ 强烈建议您将phpcms目录设置为644（linux/unix）或只读（NT）<br>
					※ 强烈建议您网站上线后，建议关闭 DEBUG （前台SQL错误提示）<br>
					<hr class="hr" />
					※ 您的模版允许后台在线编辑，为避免安全问题，建议您设为不允许在线修改<br>
					</div>
				</div>				
			</div>
     	</div>

     	<div class="row-fluid">
			<div class="span4 o-module">
				<h4>我的个人信息</h4>
				<div class="span12">
					<p>你好，管理员cms</p>
					<p>所属角色：超级管理员</p>
					<hr class="hr"/>
					<p>上次登录时间：2013-12-11</p>
				</div>				
			</div>
			<div class="span4 o-module">
				<h4>安全提示</h4>
				<div class="span12">
					<div style="color:#ff0000;">
					※ 强烈建议您将phpcms目录设置为644（linux/unix）或只读（NT）<br>
					※ 强烈建议您网站上线后，建议关闭 DEBUG （前台SQL错误提示）<br>
					<hr class="hr" />
					※ 您的模版允许后台在线编辑，为避免安全问题，建议您设为不允许在线修改<br>
					</div>
				</div>				
			</div>
			<div class="span4 o-module">
				<h4>安全提示</h4>
				<div class="span12">
					<div style="color:#ff0000;">
					※ 强烈建议您将phpcms目录设置为644（linux/unix）或只读（NT）<br>
					※ 强烈建议您网站上线后，建议关闭 DEBUG （前台SQL错误提示）<br>
					<hr class="hr" />
					※ 您的模版允许后台在线编辑，为避免安全问题，建议您设为不允许在线修改<br>
					</div>
				</div>				
			</div>
     	</div>
     
    </div><!--/span-->
  </div><!--/row-->

  <hr>
</div><!--/.fluid-container-->
<footer class="footer">
	<div class="copyright">&copy; orionis.name 2013</div>
</footer>
</div>
<script src="<c:url value="/resources/static/js/jquery-1.8.3.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/static/js/core.js"/>" type="text/javascript" ></script>
</body>
</html>