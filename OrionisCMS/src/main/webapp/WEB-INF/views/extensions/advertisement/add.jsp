<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="advertisement/add" method="post" 
		id="add_advertisement_form" ajax="true" 
		after="add_advertisement_form_after">
		<admin:input name="title" label="标题"></admin:input>
		<admin:textarea name="content" label="内容"></admin:textarea>
		<admin:buttonPanel>
			<admin:button value="添加" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="advertisement/list" functionName="add_advertisement_form_after" />
</admin:script>