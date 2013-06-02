<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="友情链接"></admin:crumb_item>
</admin:crumb>
<admin:module title="友情链接">
	<admin:button value="添加" clazz="btn-primary btn-top" id="addBlogroll"  ></admin:button>
	<admin:button value="链接分类"  parse="true" href="blogroll/category/list" id="blogrollCategory"  ></admin:button>
	<ec:table items="blogrolls" var="ul" styleClass="table table-hover table-striped" form="false">
		<ec:row>
			<ec:column property="id" title="ID"  filterable="false" sortable="false"/>
			<ec:column property="linkName"  title="链接名称"/>
			<ec:column property="linkUrl" title="链接地址" />
			<ec:column property="blogRollCategory.categoryName" title="所属分类"></ec:column>
			<ec:column title="Logo" property="logo">
				<admin:image imageName="${ul.logo }" baseFolder="uploads" style="height:30px;"></admin:image>
			</ec:column>
			<ec:column property="listOrder" title="排序"></ec:column>
			<ec:column title="操作" alias="action"  filterable="false" sortable="false">
				<admin:button value="编辑" blogroll-id="${ul.id }" clazz="edit_blogroll"></admin:button>
				<admin:button value="删除" blogroll-id="${ul.id }" clazz="btn-danger delete_blogroll"></admin:button>
			</ec:column>
		</ec:row>
	</ec:table>
</admin:module>
<admin:script>
	<admin:js_dialog selector="#addBlogroll" url="blogroll/add" title="添加链接"></admin:js_dialog>
	<admin:js_dialog selector=".edit_blogroll" url="blogroll/edit" title="编辑链接" params="id:$(this).attr('blogroll-id')"></admin:js_dialog>
	<admin:js_confirm_action selector=".delete_blogroll" url="blogroll/delete" message="您确定要删除该链接?" updateUrl="blogroll/list" params="id:$(this).attr('blogroll-id')" />
</admin:script>