<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="announce/edit" method="post" 
		id="edit_announce_form" ajax="true" 
		after="edit_announce_form_after">
		<admin:input_hidden name="id" value="${announce.id }"></admin:input_hidden>
		<admin:input name="title" label="Title" value="${announce.title }"></admin:input>
		<admin:datetime name="publishTime" label="Publish Time" value="${announce.publishTime }"></admin:datetime>
		<admin:datetime name="startTime" label="Start Time" value="${announce.startTime }"></admin:datetime>
		<admin:datetime name="endTime" label="End Time" value="${announce.endTime }"></admin:datetime>
		<admin:textarea name="content" label="Content" value="${announce.content }"></admin:textarea>
		<admin:buttonPanel>
			<admin:button value="Save" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="announce/list" functionName="edit_announce_form_after" />
</admin:script>