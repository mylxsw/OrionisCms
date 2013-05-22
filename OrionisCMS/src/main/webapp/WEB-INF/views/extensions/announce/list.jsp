<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="Home" first="true"></admin:crumb_item>
	<admin:crumb_item name="Announce"></admin:crumb_item>
</admin:crumb>
<admin:module title="Announce">
	<admin:button value="Announce Add" clazz="btn-primary btn-top" id="addAnnounce"  ></admin:button>
	<ec:table items="announce" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="announceName"  title="Announce Name"/>
			
			<ec:column title="Operation" alias="action"  filterable="false" sortable="false">
				<admin:button value="Edit" blogroll-id="${ul.id }" clazz="edit_announce"></admin:button>
				<admin:button value="Delete" announce-id="${ul.id }" clazz="btn-danger delete_announce"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addAnnounce" url="announce/add" title="Announce Add"></admin:js_dialog>
	<admin:js_dialog selector=".edit_announce" url="announce/edit" title="Announce Edit" params="id:$(this).attr('announce-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_announce" url="announce/delete" message="Are you sure you want to delete?" updateUrl="announce/list" params="id:$(this).attr('announce-id')" />
</admin:script>