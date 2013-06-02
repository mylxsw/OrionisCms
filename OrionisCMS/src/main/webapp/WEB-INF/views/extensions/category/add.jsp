<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:dialog_panel>
	<admin:form action="category/add" method="post" 
		id="add_category_form" ajax="true" 
		after="add_category_form_after">
		<admin:input name="cate_name" label="分类名称"></admin:input>

		<admin:buttonPanel>
			<admin:button value="添加" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:dialog_panel>
<admin:script>
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="category/list" functionName="add_category_form_after" />
</admin:script>