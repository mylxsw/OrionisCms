<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>登录 &middot; Orionis CMS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="<c:url value="/resources/jquery-ui/css/custom-theme/jquery-ui-1.10.0.custom.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"/>
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.min.css" />"/>
	<link rel="stylesheet" href="<c:url value="/resources/static/css/core.css" />"/>
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <script type="text/javascript">
	var basePath = "<%=basePath%>";
	</script>
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="account/login" method="post" ajax="true" after="login_success">
        <h2 class="form-signin-heading">登录</h2>
        <input type="text" name="username" class="input-block-level" placeholder="用户名" value="godgod" />
        <input type="password" name="password" class="input-block-level" placeholder="密码" value="godgod" />
        <input class="btn btn-large btn-primary" type="submit" value="登录" />
      </form>
    </div> <!-- /container -->
<div id="dialog"></div>
<div id="queue"></div>
<script src="<c:url value="/resources/static/js/jquery-1.8.3.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/static/js/jquery.form.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/jquery-ui/js/jquery-ui-1.10.3.custom.js" />" type="text/javascript"></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js" />'></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/DirectRemote.js" />'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js" />'></script>
<script src="<c:url value="/resources/static/js/jquery-cms-ext.js"/>" type="text/javascript" ></script>
  <script type="text/javascript">
  	$(function(){
  		$.orionis.reloadJsEvent();
  	});
  	function login_success(data){
  		$.orionis.alert_message(data.info);
  		if(data.status== 1){
  			window.location.href=$.orionis.url("index");
  		}
  	}
  </script>
  </body>
</html>