<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<style type="text/css">
#menu_preview a.del ,a.edit{
}
#menu_preview .control{
	float:right;
	display:inline-block;
}
#menu_preview li>span{
	width: 100%;
	display: inline-block;
}
#menu_preview li>span:hover{
	background:#CCC;
}
</style>
<ul class="breadcrumb">
  <li><a href="#"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
  <li ><a href="javascript:$.orionis.updateMain('rbac/role/list');">Role Management</a><span class="divider">/</span></li>
  <li class="active">Permission List</li>
</ul>
		<div class="row-fluid o-module o-module-big">
    	<admin:button value="Menu Add" id="addMenu" style="margin-left: 9px; margin-top: 10px;"></admin:button>
		<div style="margin-top:20px; border:1px solid #CCC; padding:10px;" id="menu_preview">
			<admin:nav nav_data="${menus }"></admin:nav>
		</div>
		</div>
 	<script>
 		$(function(){
 			$("#addMenu").click(function(){
 				$.get($.orionis.url("rbac/menus/add"), {id: "${role_id}"}, function(data){
 					$.orionis.dialog(data, "Menu Add");
 					$.orionis.reloadJsEvent();
 				});
 			});
 			$("span[status='invalid']").css("background", "rgb(245, 210, 136)");
 			$(".edit").click(function(e){
 				e.preventDefault();
 				var menu_id = $(this).parent("span").attr("mid");
 				$.get($.orionis.url("rbac/menus/modify"), {id:menu_id, roldId:${role_id}}, function(data){
 					$.orionis.dialog(data, "Menu Edit");
 					$.orionis.reloadJsEvent();
 				});
 			});
 			$(".del").click(function(e){
 				e.preventDefault();
 				if(confirm("Are you sure you want to delete? This will also delete all submenus!!!") == false){
 					return false;
 				}
 				var curid = $(this).parent("span").attr("mid");
 				$.get($.orionis.url("rbac/menus/delete"), {id:curid}, function(data){
 					$.orionis.alert_message(data.info);
 					if(data.status ==  1){
 						$.orionis.updateMain("rbac/menus/list?id=${role_id}");
 					}
 				}, "json");
 			});
 		});
 	</script>
