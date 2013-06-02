<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="blogroll/edit" method="post" 
		id="edit_blogroll_form" ajax="true" 
		after="edit_blogroll_form_after">
		<admin:input_hidden name="id" value="${blogroll.id }"></admin:input_hidden>
		<admin:input name="linkName" label="链接名成" value="${blogroll.linkName }"></admin:input>
		<admin:input name="linkUrl" label="链接地址" value="${blogroll.linkUrl }"></admin:input>
		<admin:select name="categoryId" label="所属类别" data="${category }" selected="${blogroll.blogRollCategory.id }"></admin:select>
		<admin:textarea name="introduce" label="简介" value="${blogroll.introduce }" ></admin:textarea>
		<admin:upload name="logo" label="Logo" id="logo"  privateAccess="false"></admin:upload>
		<admin:input name="listOrder" label="排序" value="${blogroll.listOrder }" ></admin:input>
		<admin:buttonPanel>
			<admin:button value="保存" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="blogroll/list" functionName="edit_blogroll_form_after" />
</admin:script>