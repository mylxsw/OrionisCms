<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="type" required="false" description="类型" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<%@ attribute name="parse"  %>
<%@ attribute name="href" %>
<%@ attribute name="params" %>
<button type='<c:out value="${type }" default="button" />'   
	<c:if test="${parse != null }"> parse="${parse }" </c:if>
	<c:if test="${href != null }"> href="${href }" </c:if>
	<c:if test="${params != null }"> params="${params }" </c:if>
	name="<c:out value="${name }" />" class="btn <c:out value="${clazz }" default="" />"  <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach>>  ${value }</button>