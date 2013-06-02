<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="blogroll/category/add" method="post" 
		id="add_blogroll_category_form" ajax="true" 
		after="add_blogroll_category_form_after">
		<admin:input name="categoryName" label="分类名称" id="categoryName"></admin:input>
		<admin:input name="listOrder" label="排序" id="listOrder"  value="1"></admin:input>
		<admin:buttonPanel>
			<admin:button value="添加" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="blogroll/category/list" functionName="add_blogroll_category_form_after" />
</admin:script>