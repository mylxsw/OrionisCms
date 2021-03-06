<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<div style="width:700px">
<form action="rbac/menus/add" method="post" id="add_menu_form" ajax="true" after="add_menu_form_after">
	<legend>添加菜单 <a href="#" id="callbackInfo" >回填</a></legend>
	<admin:input name="menuName" label="菜单名" id="menuName"></admin:input>
	<input type="hidden" name="roldId" value="${role_id }" />
	<admin:input name="url" label="菜单链接" id="url"></admin:input>
	<admin:select name="parentId" label="上级菜单" data="${menus }" id="parent" selected="0"></admin:select>
    <admin:buttonPanel>
   		<button type="submit" class="btn" name="login" value="login">添加</button>
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
    
    function add_menu_form_after(data){
    	$.orionis.alert_message(data.info);
    	if(data.status == 1){
    		$.orionis.updateMain("rbac/menus/list?id=${role_id}");
    		$.orionis.dialog_dismiss();
    	}
    }
  </script>
</form>
</div>
