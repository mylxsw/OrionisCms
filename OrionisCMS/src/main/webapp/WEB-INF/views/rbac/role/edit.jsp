<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<div style="width:700px">
<form action="rbac/role/edit" method="post" id="edit_role_form" ajax="true" after="edit_role_form_after">
    <admin:input name="roleName" label="Role Name" id="rolename" value="${role.roleName }"></admin:input>
    <input type="hidden" name="id" value="${role_id }"	 />
    <admin:buttonPanel>
    	<label class="checkbox">
    		<input type="checkbox" value="1" name="status" checked="checked"><span class="metro-checkbox">是否可用</span>
   		</label>
    	<admin:button value="Save" type="submit"></admin:button>
    </admin:buttonPanel>
</form>
</div>
<script>
	$.orionis.reloadJsEvent();
	$("input[name='status']").attr("checked", ${role.status == "ENABLED" ? 'true' : 'false'});
	function edit_role_form_after(data){
		if(data.status ==  1){
			$.orionis.alert_message(data.info);
			$.orionis.updateMain("rbac/role/list");
			$.orionis.dialog_dismiss();
		}else{
			$.orionis.alert_message(data.errors);
		}
	}
</script>