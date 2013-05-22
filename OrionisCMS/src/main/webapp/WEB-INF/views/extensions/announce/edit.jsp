<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="announce/edit" method="post" 
		id="edit_announce_form" ajax="true" 
		after="edit_announce_form_after">
		<admin:input_hidden name="id" value="${announce.id }"></admin:input_hidden>
		<admin:input name="announceName" label="Announce Name" value="${announce.announceName }"></admin:input>
		
		<admin:buttonPanel>
			<admin:button value="Save" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="announce/list" functionName="edit_announce_form_after" />
</admin:script>