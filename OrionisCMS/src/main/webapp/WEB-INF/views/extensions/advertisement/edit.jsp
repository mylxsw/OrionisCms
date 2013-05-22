<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="advertisement/edit" method="post" 
		id="edit_advertisement_form" ajax="true" 
		after="edit_advertisement_form_after">
		<admin:input_hidden name="id" value="${advertisement.id }"></admin:input_hidden>
		<admin:input name="title" label="Title" value="${advertisement.title }"></admin:input>
		<admin:textarea name="content" label="Content" value="${advertisement.content }"></admin:textarea>
		<admin:buttonPanel>
			<admin:button value="Save" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="advertisement/list" functionName="edit_advertisement_form_after" />
</admin:script>