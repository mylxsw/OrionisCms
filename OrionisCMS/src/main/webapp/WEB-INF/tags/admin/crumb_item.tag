<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="name" required="true" %>
<%@attribute name="url" required="false" %>
<%@attribute name="first" type="java.lang.Boolean" %>
<c:choose>
	<c:when test="${first }">
		<li><c:if test="${url != null }"><a href="${url }"  class="parse"></c:if> <i class="icon-home"></i> <c:out value="${name }" /><c:if test="${url != null }"></a></c:if></li>
	</c:when>
	<c:otherwise>
		<li><span class="divider">/</span><c:if test="${url!= null  }"><a href="${url }" class="parse"></c:if><c:out value="${name }" /><c:if test="${url != null }"></a></c:if></li>
	</c:otherwise>
</c:choose>
