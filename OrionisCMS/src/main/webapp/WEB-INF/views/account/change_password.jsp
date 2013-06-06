<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="修改密码" ></admin:crumb_item>
</admin:crumb>
<admin:module title="修改密码" >
	<admin:form action="account/changePassword" method="post" 
		id="personalInfo" before="checkPwd" after="modifyUserInfo" ajax="true"  style="margin:20px;">
		<admin:input name="password" label="新密码 " type="password"></admin:input>
		<admin:input name="password_confirm" label="密码确认" type="password"></admin:input>
		<admin:buttonPanel>
			<admin:button value="保存" type="submit"></admin:button>
		</admin:buttonPanel>
	</admin:form>
</admin:module>
<admin:script>
	function checkPwd(){
		var pwd = $("input[name='password']").val();
		var pwd_c = $("input[name='password_confirm']").val();
		
		if(pwd != pwd_c){
			$.orionis.alert_message("两次输入的密码不一致!");
			return false;
		}
		return true;
	}
	<admin:js_form_ajax_afterEvent updateUrl="account/changePassword" functionName="modifyUserInfo" />
</admin:script>