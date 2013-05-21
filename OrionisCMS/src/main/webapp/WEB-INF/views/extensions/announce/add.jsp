<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="announce/add" method="post" 
		id="add_announce_form" ajax="true" 
		after="add_announce_form_after">
		<admin:input name="title" label="Title"></admin:input>
		<admin:input name="" label="Link Url"></admin:input>
		<admin:select name="categoryId" label="Category" data="${category }"></admin:select>
		<admin:textarea name="introduce" label="Introduce" ></admin:textarea>
		<admin:upload name="logo" label="Logo" id="logo"  privateAccess="false"></admin:upload>
		<admin:input name="listOrder" label="List Order" value="1"></admin:input>
		<admin:buttonPanel>
			<admin:button value="Add" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="blogroll/list" functionName="add_blogroll_form_after" />
</admin:script>