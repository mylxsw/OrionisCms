<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="blogroll/category/edit" method="post" 
		id="edit_blogroll_category_form" ajax="true" 
		after="edit_blogroll_category_form_after">
		<admin:input_hidden name="id" value="${category.id }"></admin:input_hidden>
		<admin:input name="categoryName" label="Category Name" id="categoryName" value="${category.categoryName }"></admin:input>
		<admin:input name="listOrder" label="List Order" id="listOrder" value="${category.listOrder }"></admin:input>
		<admin:buttonPanel>
			<admin:button value="Save" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="blogroll/category/list" functionName="edit_blogroll_category_form_after" />
</admin:script>