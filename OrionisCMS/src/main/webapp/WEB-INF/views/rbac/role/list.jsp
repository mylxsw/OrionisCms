<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<ul class="breadcrumb">
  <li><a href="#"><i class="icon-home"></i> 主面板</a> <span class="divider">/</span></li>
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
						<admin:button clazz="role_edit" value="编辑" role-id="${ul.id }"></admin:button>
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
			$.orionis.dialog(data, "添加角色");
		});
	});
	$(".permission_list").click(function(){
		$.orionis.updateMain("rbac/permission/add?id=" + $(this).attr("role-id"));
	});
	$(".menus_list").click(function(){
		$.orionis.updateMain("rbac/menus/list?id=" + $(this).attr("role-id"));
	});
	$(".role_edit").click(function(){
		$.get($.orionis.url("rbac/role/edit"), {id: $(this).attr("role-id")}, function(data){
			$.orionis.dialog(data, "编辑角色");
		});
	});
	$(".role_delete").click(function(){
		if(confirm("您确定要删除该角色吗?") == false){
			return false;
		}
		$.get($.orionis.url("rbac/role/delete"), {id: $(this).attr("role-id")}, function(data){
			if(data.status == 1){
				$.orionis.updateMain("rbac/role/list");
			}else if(data.status == 0){
				$.orionis.alert_message(data.info);
			}else{
				$.orionis.alert_message("操作失败!");
			}
		}, "json");
	});
	$("#updatePermission").click(function(){
		if(confirm("您确定要更新所有权限吗?!!") == false){
			return false;
		}
		$.get($.orionis.url("rbac/permission/update"), {}, function(data){
			$.orionis.alert_message(data.info);
		},"json");
		
	});
});
</script>


