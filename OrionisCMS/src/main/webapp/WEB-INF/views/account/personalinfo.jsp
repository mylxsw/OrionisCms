<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="修改个人信息" ></admin:crumb_item>
</admin:crumb>
<admin:module title="修改个人信息" >
	<admin:form action="account/personalinfo" method="post" 
		id="personalInfo" after="modifyUserInfo" ajax="true"  style="margin:20px;">
		<admin:input name="userName" label="用户名" enabled="false" value="${user.userName }"></admin:input>
		<admin:input name="email" label="邮箱" id="email" value="${user.email }"></admin:input>
		<admin:buttonPanel>
			<admin:button value="保存" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:module>
<admin:script>
	<admin:js_form_ajax_afterEvent updateUrl="account/personalinfo" functionName="modifyUserInfo" />
</admin:script>