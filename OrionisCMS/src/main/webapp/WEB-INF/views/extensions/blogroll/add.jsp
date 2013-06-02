<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="blogroll/add" method="post" 
		id="add_blogroll_form" ajax="true" 
		after="add_blogroll_form_after">
		<admin:input name="linkName" label="链接名称"></admin:input>
		<admin:input name="linkUrl" label="链接地址"></admin:input>
		<admin:select name="categoryId" label="所属分类" data="${category }"></admin:select>
		<admin:textarea name="introduce" label="简介" ></admin:textarea>
		<admin:upload name="logo" label="Logo" id="logo"  privateAccess="false"></admin:upload>
		<admin:input name="listOrder" label="排序" value="1"></admin:input>
		<admin:buttonPanel>
			<admin:button value="提交" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="blogroll/list" functionName="add_blogroll_form_after" />
</admin:script>