<%@tag import="name.orionis.cms.core.rbac.web.AccountController"%>
<%@tag import="name.orionis.cms.core.rbac.authentication.UserInfo"%>
<%@ tag pageEncoding="UTF-8" dynamic-attributes="dynamic_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="ajax" type="java.lang.Boolean" description="是否ajax方式提交， 默认false" %>
<%@ attribute name="action" description="提交的动作地址" required="true" %>
<%@ attribute name="method" description="提交方式, 默认POST"  %>
<%@ attribute name="before" description="ajax提交之前的回调函数" %>
<%@ attribute name="after" description="ajax提交之后的回调函数" %>
<%@ attribute name="success" description="ajax返回结果为success的回调函数" %>
<%@ attribute name="failed" description="ajax提交返回结果为failed的回调函数" %>
<%@ attribute name="id"  %>
<%@ attribute name="defaultAction" description="是否采用默认的动作" type="java.lang.Boolean" %>
<%@ attribute name="reload" description="返回成功状态后自动刷新页面" type="java.lang.Boolean" %>
	<form action="${action }" method="<c:out value="${method }" default="POST" />" 
		<c:if test="${ajax != null }"> ajax="${ajax }" </c:if>
		<c:if test="${before != null }"> before="${before}" </c:if>
		<c:if test="${after != null }"> after="${after }" </c:if>
		<c:if test="${success != null }"> success="${success }" </c:if>
		<c:if test="${id != null }"> id="${id }" </c:if>
		<c:if test="${failed != null }"> failed="${failed }" </c:if>
		<c:if test="${defaultAction != null }"> default="${defaultAction }" </c:if>
		<c:if test="${reload != null }"> reload="${reload }" </c:if>
	 <c:forEach var="attr" items="${dynamic_attr }">	${attr.key }="${attr.value }" </c:forEach>>
		<input type="hidden" name="_jsessionId_" value="<%=request.getSession().getId() %>" />
		<%
			UserInfo user_  = null;
			try{
				user_ = (UserInfo)request.getSession().getAttribute(AccountController.ACCOUNT_INFO);
			}catch(Exception e){}
			String _token_ = user_ == null ? "" : user_.getToken();
		 %>
		<input type="hidden" name="_token_" value="<%=_token_ %>" />
		<fieldset>
			<jsp:doBody></jsp:doBody>
		</fieldset>
	</form>
