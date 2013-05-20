<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<div style="width:700px">
<form action="rbac/menus/modify" method="post" id="add_menu_form" ajax="true" after="edit_menu_form_after">
	<legend>Menu Edit <a href="#" id="callbackInfo" >Fill Back</a></legend>
	<admin:input name="menuName" label="Menu Name" id="menuName" value="${menu.menuName }"></admin:input>
	<input type="hidden" name="id" value="${menu.id }" />
	<input type="hidden" name="roldId" value="${role_id }" />
	<admin:input name="url" label="Url" id="url" value="${menu.url }"></admin:input>
	<admin:select name="parentId" label="Parent" data="${menus }" id="parent" selected="${menu.parentId}"></admin:select>
    <admin:buttonPanel>
   		<button type="submit" class="btn" name="login" value="login">Save</button>
    </admin:buttonPanel>
  <script>
    $(function(){
		var handler = null;
		$("#callbackInfo").click(function(e){
			e.preventDefault();
			handler = window.open($.orionis.url("rbac/menus/select"), "_menu");
		});
    });
    function fillInfo(menu_name, url){
    	$("#menuName").val(menu_name);
    	$("#url").val(url);
    }
    
    function edit_menu_form_after(data){
    	$.orionis.alert_message(data.info);
    	if(data.status == 1){
    		$.orionis.updateMain("rbac/menus/list?id=${role_id}");
    		$.orionis.dialog_dismiss();
    	}
    }
  </script>
</form>
</div>
