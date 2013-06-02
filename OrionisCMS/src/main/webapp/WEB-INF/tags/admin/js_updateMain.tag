<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ attribute name="selector" required="true"  %>
<%@ attribute name="url" required="true" %>
$("${selector }").click(function(){
	$.orionis.updateMain("${url }");
});