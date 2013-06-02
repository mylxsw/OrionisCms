<%@page import="name.orionis.cms.extensions.content.model.Category"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="文章分类"></admin:crumb_item>
</admin:crumb>
<admin:module title="文章分类">
	<admin:button value="添加分类" clazz="btn-primary btn-top" id="addCategory"  ></admin:button>
	<ec:table items="categorys" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="cate_name"  title="分类名称"/>
			<ec:column title="文章数量" alias="文章数量">${ul.contents.size() }</ec:column>
			<ec:column title="操作" alias="action"  filterable="false" sortable="false">
				<admin:button value="编辑" category-id="${ul.id }" clazz="edit_category"></admin:button>
				<admin:button value="删除" category-id="${ul.id }" clazz="btn-danger delete_category"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addCategory" url="category/add" title="Category Add"></admin:js_dialog>
	<admin:js_dialog selector=".edit_category" url="category/edit" title="Category Edit" params="id:$(this).attr('category-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_category" url="category/delete" message="您确定要删除吗?" updateUrl="category/list" params="id:$(this).attr('category-id')" />
</admin:script>