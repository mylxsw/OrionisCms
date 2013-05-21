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
	<ec:table items="blogrolls" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="linkName"  title="Link Name"/>
			<ec:column property="linkUrl" title="Link URL" />
			<ec:column property="blogRollCategory.categoryName" title="Category"></ec:column>
			<ec:column title="Logo" property="logo">
				<admin:image imageName="${ul.logo }" baseFolder="uploads" style="height:30px;"></admin:image>
			</ec:column>
			<ec:column property="listOrder" title="List Order"></ec:column>
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