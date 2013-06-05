<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="文章管理"></admin:crumb_item>
</admin:crumb>
<admin:module title="文章管理">
	<admin:button value="发表文章" clazz="btn-primary btn-top" id="addNew"  ></admin:button>
	<ec:table items="news" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="title"  title="标题"/>
			<ec:column property="category.cate_name" title="分类"></ec:column>
			<ec:column property="create_time" title="发表时间"></ec:column>
			<ec:column property="list_order" title="排序"></ec:column>
			<ec:column title="操作" alias="action"  filterable="false" sortable="false">
				<admin:button value="编辑" news-id="${ul.id }" clazz="edit_news"></admin:button>
				<admin:button value="删除" news-id="${ul.id }" clazz="btn-danger delete_news"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_updateMain selector="#addNew" url="news/add"></admin:js_updateMain>
	<admin:js_updateMain selector=".edit_news" url="news/edit" params="{id:$(this).attr('news-id')}"></admin:js_updateMain>
	<admin:js_confirm_action selector=".delete_news" url="news/delete" message="您确定要删除吗?" updateUrl="news/list" params="id:$(this).attr('news-id')" />
</admin:script>