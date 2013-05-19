<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="type" required="false" description="类型" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<button type='<c:out value="${type }" default="button" />'  name="<c:out value="${name }" />" class="btn <c:out value="${clazz }" default="" />"  <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach>>  <c:out value="${value }" /></button>