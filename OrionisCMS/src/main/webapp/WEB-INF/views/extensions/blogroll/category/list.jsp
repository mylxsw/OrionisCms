<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="Home" first="true"></admin:crumb_item>
	<admin:crumb_item name="Blogroll" url="blogroll/list"></admin:crumb_item>
	<admin:crumb_item name="Blogroll Category" ></admin:crumb_item>
</admin:crumb>
<admin:module title="Blogroll Category">
	<admin:button value="Add" clazz="btn-primary btn-top" id="addCategory"  ></admin:button>
	<ec:table 
		items="category" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="categoryName"  title="Category Name"/>
			<ec:column property="listOrder" title="list Order" />
			<ec:column title="Operation" alias="action"  filterable="false" sortable="false">
				<admin:button value="Edit" category-id="${ul.id }" clazz="edit_category"  ></admin:button>
				<admin:button value="Delete" category-id="${ul.id }" clazz="btn-danger delete_category"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addCategory" url="blogroll/category/add" title="Category Add"></admin:js_dialog>
	<admin:js_dialog selector=".edit_category" url="blogroll/category/edit" title="Category Edit" params="id:$(this).attr('category-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_category" url="blogroll/category/delete" message="Are you sure you want to delete?" updateUrl="blogroll/category/list" params="id:$(this).attr('category-id')"></admin:js_confirm_action>
</admin:script>