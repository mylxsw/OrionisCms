<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="广告"></admin:crumb_item>
</admin:crumb>
<admin:module title="广告">
	<admin:button value="广告投放" clazz="btn-primary btn-top" id="addAdvertisement"  ></admin:button>
	<ec:table items="advertisements" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="title"  title="标题"/>
			<ec:column property="publisher"  title="创建者"/>
			<ec:column property="updator"  title="更新者"/>
			<ec:column property="hits"  title="点击率"/>
			<ec:column title="操作" alias="action"  filterable="false" sortable="false">
				<admin:button value="编辑" advertisement-id="${ul.id }" clazz="edit_advertisement"></admin:button>
				<admin:button value="删除" advertisement-id="${ul.id }" clazz="btn-danger delete_advertisement"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addAdvertisement" url="advertisement/add" title="广告投放"></admin:js_dialog>
	<admin:js_dialog selector=".edit_advertisement" url="advertisement/edit" title="编辑广告" params="id:$(this).attr('advertisement-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_advertisement" url="advertisement/delete" message="您确定要删除该广告?" updateUrl="advertisement/list" params="id:$(this).attr('advertisement-id')" />
</admin:script>