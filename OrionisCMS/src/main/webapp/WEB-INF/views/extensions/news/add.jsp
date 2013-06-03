<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="文章管理" url="news/list"></admin:crumb_item>
	<admin:crumb_item name="文章发布"></admin:crumb_item>
</admin:crumb>
<admin:module title="文章发布" >
	<admin:form action="news/add" method="post" 
		id="add_news_form" ajax="true" 
		after="add_news_form_after" before="before" style="margin:20px;">
		<admin:input name="title" label="文章标题" style="width:400px;"></admin:input>
		<admin:input name="username" label="作者"></admin:input>
		<admin:datetime name="create_time" label="发布时间"></admin:datetime>
		<admin:select name="category" label="文章分类" data="${cates }"></admin:select>
		<admin:textarea name="description" label="内容摘要"></admin:textarea>
		<admin:textarea name="content" label="文章内容" id="content" rich="true"></admin:textarea>
		<admin:input name="list_order" label="排序"></admin:input>
		<admin:buttonPanel>
			<admin:button value="添加" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:module>
<admin:script>
	function before(){
		 window.editor.sync();
	}
	<admin:js_reloadJsEvent />
	<admin:js_form_ajax_afterEvent updateUrl="news/list" functionName="add_news_form_after" />
</admin:script>