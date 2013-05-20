<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<div style="width:700px">
<form action="rbac/user/add" method="post" id="add_user_form" ajax="true" after="add_user_form_after">
	<admin:input name="userName" id="username" label="Username"></admin:input>
    <admin:input name="password" type="password" label="Password" id="password"></admin:input>
    <admin:input name="email" label="Email" id="email"></admin:input>
    <admin:select name="roleId" label="Role" data="${roles }" id="roleid"></admin:select>
    <admin:buttonPanel>
    	<label class="checkbox">
    		<input type="checkbox" value="1" name="status" checked="checked"><span class="metro-checkbox">Enabled</span>
   		</label>
   		<button type="submit" class="btn" name="login" value="login">Add</button>
    </admin:buttonPanel>
</form>
<script>
    $.orionis.reloadJsEvent();
    function add_user_form_after(data){
		if(data.status ==  1){
			$.orionis.alert_message(data.info);
			$.orionis.updateMain("rbac/user/list");
			$.orionis.dialog_dismiss();
		}else{
			$.orionis.alert_message(data.errors);
		}
    }
  </script>
</div>
