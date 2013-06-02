<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="category/edit" method="post" 
		id="edit_category_form" ajax="true" 
		after="edit_category_form_after">
		<admin:input_hidden name="id" value="${category.id }"></admin:input_hidden>
		<admin:input name="cate_name" label="分类名称" value="${category.cate_name }"></admin:input>
		
		<admin:buttonPanel>
			<admin:button value="保存" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="category/list" functionName="edit_category_form_after" />
</admin:script>