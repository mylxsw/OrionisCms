<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<ul class="breadcrumb">
  <li><a href="#"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
  <li class="active">Role Management</li>
</ul>
<div class="row-fluid o-module o-module-big">
	<h4>Role Management</h4>
	<div>
		<button id="addRole" class="btn btn-primary"  style="margin-left: 20px;">Role Add</button>
		<admin:button value="Permission Update" id="updatePermission"></admin:button>
		<ec:table 
				items="roles"
				var="ul"
				styleClass="table table-hover table-striped"
				form="false">
				<ec:row>
					<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
					<ec:column property="roleName"  title="Role Name"/>
					<ec:column property="status" title="Status"></ec:column>
					<ec:column title="Operation" alias="action"  filterable="false" sortable="false">
						<admin:button clazz="menus_list btn-info" value="Menus" role-id="${ul.id }"></admin:button>
						<admin:button clazz="permission_list btn-success" value="Permissions" role-id="${ul.id }"></admin:button>
						<admin:button clazz="role_delete btn-danger" role-id="${ul.id }" value="Delete"></admin:button>
					</ec:column>
				</ec:row>
			</ec:table>
	</div>
</div>

<script>
$(function(){
	$("#addRole").click(function(){
		$.get($.orionis.url("rbac/role/add"), {}, function(data){
			$.orionis.dialog(data, "Role Add");
		});
	});
	$(".permission_list").click(function(){
		$.orionis.updateMain("rbac/permission/add?id=" + $(this).attr("role-id"));
	});
	$(".menus_list").click(function(){
		$.orionis.updateMain("rbac/menus/list?id=" + $(this).attr("role-id"));
	});
	$(".role_delete").click(function(){
		if(confirm("Are you sure you want to delete?") == false){
			return false;
		}
		$.get($.orionis.url("rbac/role/delete"), {id: $(this).attr("role-id")}, function(data){
			if(data.status == 1){
				$.orionis.updateMain("rbac/role/list");
			}else if(data.status == 0){
				$.orionis.alert_message(data.info);
			}else{
				$.orionis.alert_message("Operation Failed!");
			}
		}, "json");
	});
	$("#updatePermission").click(function(){
		if(confirm("Are you sure you want to update all of the permissions?!!") == false){
			return false;
		}
		$.get($.orionis.url("rbac/permission/update"), {}, function(data){
			$.orionis.alert_message(data.info);
		},"json");
		
	});
});
</script>


