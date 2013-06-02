<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="公告"></admin:crumb_item>
</admin:crumb>
<admin:module title="公告">
	<admin:button value="添加公告" clazz="btn-primary btn-top" id="addAnnounce"  ></admin:button>
	<ec:table items="announces" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="title"  title="标题"/>
			<ec:column property="publishTime"  title="发布时间"/>
			<ec:column property="startTime"  title="生效时间"/>
			<ec:column property="endTime"  title="过期时间"/>
			<ec:column title="操作" alias="action"  filterable="false" sortable="false">
				<admin:button value="编辑" announce-id="${ul.id }" clazz="edit_announce"></admin:button>
				<admin:button value="删除" announce-id="${ul.id }" clazz="btn-danger delete_announce"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addAnnounce" url="announce/add" title="Announce Add"></admin:js_dialog>
	<admin:js_dialog selector=".edit_announce" url="announce/edit" title="Announce Edit" params="id:$(this).attr('announce-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_announce" url="announce/delete" message="您确定要删除该公告吗?" updateUrl="announce/list" params="id:$(this).attr('announce-id')" />
</admin:script>