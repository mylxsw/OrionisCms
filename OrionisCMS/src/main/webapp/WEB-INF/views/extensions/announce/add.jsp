<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="announce/add" method="post" 
		id="add_announce_form" ajax="true" 
		after="add_announce_form_after">
		<admin:input name="title" label="Title"></admin:input>
		<admin:datetime name="publishTime" label="Publish Time"></admin:datetime>
		<admin:datetime name="startTime" label="Start Time"></admin:datetime>
		<admin:datetime name="endTime" label="End Time"></admin:datetime>
		<admin:textarea name="content" label="Content"></admin:textarea>		
		<admin:buttonPanel>
			<admin:button value="Add" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="announce/list" functionName="add_announce_form_after" />
</admin:script>