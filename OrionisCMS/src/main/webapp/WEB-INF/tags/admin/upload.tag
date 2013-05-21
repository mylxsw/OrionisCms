<%@tag import="org.apache.commons.codec.digest.DigestUtils"%>
<%@tag import="name.orionis.cms.core.rbac.authentication.UserInfo"%>
<%@tag import="name.orionis.cms.core.rbac.web.AccountController"%>
<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="label" required="true"  %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="tip" required="false" description="提示"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="privateAccess" type="java.lang.Boolean" required="true"%>
<%@ attribute name="clazz" required="false" description="CSS" %>
<div class="control-group" id="group-${id }">
   <label class="control-label" for="${id }">${label }</label>
   <div class="controls">
   	 <input type="hidden" name="${name }"  />
     <input multiple="true" field-bind="${name }" type="file" id="${id }" name="${name }_upload"  <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach> >
   	 <div class="_filename"></div>
   </div>
</div>
<script>
<%
UserInfo user_  = null;
try{
	user_ = (UserInfo)request.getSession().getAttribute(AccountController.ACCOUNT_INFO);
}catch(Exception e){}
String _token_ = user_ == null ? "" : user_.getToken();
String private_key = DigestUtils.md5Hex(_token_ + "{" + (privateAccess ? "true" : "false") + "}");
%>
	$.orionis.upload_bind("${id}", "<%=private_key %>" , ${privateAccess});
</script>
