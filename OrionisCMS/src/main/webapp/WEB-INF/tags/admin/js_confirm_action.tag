<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ attribute name="selector" required="true"  %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="params" required="false" %>
<%@ attribute name="message"  required="true"%>
<%@ attribute name="updateUrl" required="true" %>
$("${selector }").click(function(){
	if(confirm("${message }") == false){
		return false;
	}
	$.get($.orionis.url("${url }"), {${params }}, function(data){
		$.orionis.alert_message(data.info);
		if(data.status == 1){
			$.orionis.updateMain("${updateUrl }");
		}
	}, "json");
});