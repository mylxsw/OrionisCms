<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ attribute name="selector" required="true"  %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="params" required="false" %>
<%@ attribute name="title"  required="true"%>
$("${selector }").click(function(){
	$.get($.orionis.url("${url }"), {${params }}, function(data){
		$.orionis.dialog(data, "${title }");
	});
});