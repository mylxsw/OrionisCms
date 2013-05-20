<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="Home" first="true"></admin:crumb_item>
	<admin:crumb_item name="Blogroll"></admin:crumb_item>
</admin:crumb>
<admin:module title="Blogroll">
	<admin:button value="Blogroll Add" clazz="btn-primary btn-top" id="addBlogroll"  ></admin:button>
	<admin:button value="Blogroll Category"  parse="true" href="blogroll/category/list" id="blogrollCategory"  ></admin:button>
	<ec:table items="users" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="userName"  title="User Name"/>
			<ec:column property="rbacRole.roleName" title="Role" />
			<ec:column property="email" title="Email" />
			<ec:column property="status" title="Status"></ec:column>
			<ec:column title="Operation" alias="action"  filterable="false" sortable="false">
				<admin:button value="Edit" blogroll-id="${ul.id }" clazz="edit_blogroll"></admin:button>
				<admin:button value="Delete" blogroll-id="${ul.id }" clazz="btn-danger delete_blogroll"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addBlogroll" url="blogroll/add" title="Blogroll Add"></admin:js_dialog>
	<admin:js_dialog selector=".edit_blogroll" url="blogroll/edit" title="Blogroll Edit" params="id:$(this).attr('blogroll-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_blogroll" url="blogroll/delete" message="Are you sure you want to delete?" updateUrl="blogroll/list" params="id:$(this).attr('blogroll-id')" />
</admin:script>