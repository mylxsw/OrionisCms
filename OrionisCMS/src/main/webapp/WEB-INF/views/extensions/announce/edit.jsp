<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="announce/edit" method="post" 
		id="edit_announce_form" ajax="true" 
		after="edit_announce_form_after">
		<admin:input_hidden name="id" value="${announce.id }"></admin:input_hidden>
		<admin:input name="title" label="标题" value="${announce.title }"></admin:input>
		<admin:datetime name="publishTime" label="发布时间" value="${announce.publishTime }"></admin:datetime>
		<admin:datetime name="startTime" label="生效时间" value="${announce.startTime }"></admin:datetime>
		<admin:datetime name="endTime" label="过期时间" value="${announce.endTime }"></admin:datetime>
		<admin:textarea name="content" label="公告内容" value="${announce.content }"></admin:textarea>
		<admin:buttonPanel>
			<admin:button value="保存" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="announce/list" functionName="edit_announce_form_after" />
</admin:script>