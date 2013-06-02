<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="友情链接" url="blogroll/list"></admin:crumb_item>
	<admin:crumb_item name="友情链接分类" ></admin:crumb_item>
</admin:crumb>
<admin:module title="友情链接分类">
	<admin:button value="添加" clazz="btn-primary btn-top" id="addCategory"  ></admin:button>
	<ec:table 
		items="category" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="categoryName"  title="分类名称"/>
			<ec:column property="listOrder" title="排序" />
			<ec:column title="操作" alias="action"  filterable="false" sortable="false">
				<admin:button value="编辑" category-id="${ul.id }" clazz="edit_category"  ></admin:button>
				<admin:button value="删除" category-id="${ul.id }" clazz="btn-danger delete_category"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addCategory" url="blogroll/category/add" title="添加分类"></admin:js_dialog>
	<admin:js_dialog selector=".edit_category" url="blogroll/category/edit" title="编辑分类" params="id:$(this).attr('category-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_category" url="blogroll/category/delete" message="您确定要删除该分类吗?" updateUrl="blogroll/category/list" params="id:$(this).attr('category-id')"></admin:js_confirm_action>
</admin:script>