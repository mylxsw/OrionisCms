<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="selector" required="true"  %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="params" required="false" %>
$("${selector }").click(function(){
	$.orionis.updateMain("${url }"<c:if test="${params != null }">, ${params }</c:if>);
});