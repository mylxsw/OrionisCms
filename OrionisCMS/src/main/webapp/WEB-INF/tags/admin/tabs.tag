<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="ajax" fragment="true"  %>
<%@ attribute name="body" fragment="true" %>
<div class="tabs">
  <ul>
    <c:forEach items="${dyna_attr }" var="attr">
    	<li><a href="#tab_${attr.key }">${attr.value }</a></li>
    </c:forEach>
    <jsp:invoke fragment="ajax"></jsp:invoke>
  </ul>
  <div class="clear"></div>
  <jsp:invoke fragment="body"></jsp:invoke>
</div>
