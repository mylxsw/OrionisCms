<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="Home" first="true"></admin:crumb_item>
	<admin:crumb_item name="Advertisement"></admin:crumb_item>
</admin:crumb>
<admin:module title="Advertisement">
	<admin:button value="Advertisement Add" clazz="btn-primary btn-top" id="addAdvertisement"  ></admin:button>
	<ec:table items="advertisements" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="title"  title="Title"/>
			<ec:column property="publisher"  title="Creator"/>
			<ec:column property="updator"  title="Updator"/>
			<ec:column property="hits"  title="Hits"/>
			<ec:column title="Operation" alias="action"  filterable="false" sortable="false">
				<admin:button value="Edit" advertisement-id="${ul.id }" clazz="edit_advertisement"></admin:button>
				<admin:button value="Delete" advertisement-id="${ul.id }" clazz="btn-danger delete_advertisement"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addAdvertisement" url="advertisement/add" title="Advertisement Add"></admin:js_dialog>
	<admin:js_dialog selector=".edit_advertisement" url="advertisement/edit" title="Advertisement Edit" params="id:$(this).attr('advertisement-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_advertisement" url="advertisement/delete" message="Are you sure you want to delete?" updateUrl="advertisement/list" params="id:$(this).attr('advertisement-id')" />
</admin:script>