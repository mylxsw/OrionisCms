<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<div style="width:700px">
<form action="rbac/role/add" method="post" id="add_role_form" ajax="true" after="add_role_form_after">
    <admin:input name="roleName" label="Role Name" id="rolename"></admin:input>
    <admin:buttonPanel>
    	<label class="checkbox">
    		<input type="checkbox" value="1" name="status" checked="checked"><span class="metro-checkbox">是否可用</span>
   		</label>
    	<admin:button value="Add" type="submit"></admin:button>
    </admin:buttonPanel>
</form>
</div>
<script>
	$.orionis.reloadJsEvent();
	function add_role_form_after(data){
		if(data.status ==  1){
			$.orionis.alert_message(data.info);
			$.orionis.updateMain("rbac/role/list");
			$.orionis.dialog_dismiss();
		}else{
			$.orionis.alert_message(data.errors);
		}
	}
</script>