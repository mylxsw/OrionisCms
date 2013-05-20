<%@tag pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="id" required="true"  %>
<div id="tab_${id }">
	<jsp:doBody></jsp:doBody>
</div>