<%@tag pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>
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
<link rel="stylesheet" href="<%=basePath %>resources/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=basePath %>resources/bootstrap/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/jquery-ui/css/custom-theme/jquery-ui-1.10.0.custom.css"/>
<link type="text/css" href="<%=basePath %>resources/assets/css/font-awesome.min.css"  />
<link rel="stylesheet" href="<%=basePath %>resources/static/css/core.css" />
<jsp:doBody></jsp:doBody>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
</head>
<body>
<div class="container-fluid container-global">
<div class="container-fluid">
  <div class="row-fluid">