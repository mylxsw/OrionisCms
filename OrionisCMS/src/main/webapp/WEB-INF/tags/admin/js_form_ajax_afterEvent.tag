<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="functionName" required="true" %>
<%@ attribute name="updateUrl" required="true" %>
<%@ attribute name="errorField" required="false" %>
function ${functionName}(data){
	if(data.status ==  1){
		$.orionis.alert_message(data.info);
		$.orionis.updateMain("${updateUrl}");
		$.orionis.dialog_dismiss();
	}else{
		<c:if test="${errorField != null }"> ${errorField } </c:if>
		<c:choose>
			<c:when test="${errorField != null }">
				$.orionis.alert_message(data.${errorField });
			</c:when>
			<c:otherwise>
				if(data.errors != undefined){
					$.orionis.alert_message(data.errors);
				}else{
					$.orionis.alert_message(data.info);
				}
			</c:otherwise>
		</c:choose>
	}
}