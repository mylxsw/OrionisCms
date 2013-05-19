<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<ul class="breadcrumb">
  <li><a href="#"><i class="icon-home"></i> 主页</a> <span class="divider">/</span></li>
  <li><a href="#">用户</a> <span class="divider">/</span></li>
  <li class="active">角色管理</li>
</ul>
<div class="row-fluid o-module o-module-big">
	<h4>角色管理</h4>
	<div>
		<button id="addRole" class="btn btn-primary"  style="margin-left: 20px;">添加角色</button>
		<admin:button value="更新权限" id="updatePermission"></admin:button>
		<ec:table 
				items="roles"
				var="ul"
				styleClass="table table-hover table-striped"
				form="false">
				<ec:row>
					<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
					<ec:column property="roleName"  title="角色名"/>
					<ec:column property="status" title="状态"></ec:column>
					<ec:column title="操作" alias="action"  filterable="false" sortable="false">
						<admin:button clazz="menus_list btn-info" value="菜单" role-id="${ul.id }"></admin:button>
						<admin:button clazz="permission_list btn-success" value="权限" role-id="${ul.id }"></admin:button>
						<admin:button clazz="role_delete btn-danger" role-id="${ul.id }" value="删除"></admin:button>
					</ec:column>
				</ec:row>
			</ec:table>
	</div>
</div>

<script>
$(function(){
	$("#addRole").click(function(){
		$.get($.orionis.url("rbac/role/add"), {}, function(data){
			$.orionis.dialog(data);	
		});
	});
	$(".permission_list").click(function(){
		window.open(basePath + "admin/rbac/permission_role_add.html?role_id=" + $(this).attr("role-id"));
	});
	$(".menus_list").click(function(){
		window.open(basePath + "admin/rbac/menus.html?role_id=" + $(this).attr("role-id"));
	});
	$(".role_delete").click(function(){
		if(confirm("确认删除?") == false){
			return false;
		}
		$.get(basePath + "admin/rbac/role_delete.html", {role_id: $(this).attr("role-id")}, function(data){
			if(data.status == 1){
				reloadMain();
			}else if(data.status == 0){
				alert(data.info);
			}else{
				alert("操作失败!");
			}
		}, "json");
	});
	$("#updatePermission").click(function(){
		if(confirm("该操作将会更新所有的权限信息，确定继续？") == false){
			return false;
		}
		$.get(basePath + "admin/rbac/update_permissions.html", {}, function(data){
			alert(data.info);
		},"json");
		
	});
});
</script>


