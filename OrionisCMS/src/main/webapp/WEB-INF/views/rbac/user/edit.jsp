<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<div style="width:700px">
<form action="rbac/user/edit" method="post" id="edit_user_form" ajax="true" after="edit_user_form_after">
	<input type="hidden" name="id" value="${user.id }" />
	<admin:input name="userName" id="username" label="Username" value="${user.userName }"></admin:input>
    <admin:input name="password" type="password" label="Password" id="password" value="--------" ></admin:input>
    <admin:input name="email" label="Email" id="email" value="${user.email }"></admin:input>
    <admin:select name="roleId" label="Role" data="${roles }" id="roleid" selected="${user.rbacRole.id }"></admin:select>
    <admin:buttonPanel>
    	<label class="checkbox">
    		<input type="checkbox" value="1" name="status" checked="checked"><span class="metro-checkbox">是否可用</span>
   		</label>
   		<button type="submit" class="btn" name="login" value="login">Save</button>
    </admin:buttonPanel>
</form>
<script>
    $.orionis.reloadJsEvent();
    $("input[name='status']").attr("checked", ${user.status == "ENABLED" ? 'true' : 'false'});
    function edit_user_form_after(data){
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
