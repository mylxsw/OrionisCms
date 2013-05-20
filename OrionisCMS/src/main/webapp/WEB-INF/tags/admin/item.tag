<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="label" required="false"  %>
<%@ attribute name="clazz" required="false" %>
<div class="control-group ${clazz }">
    <label class="control-label">${label }</label>
    <div class="controls">
       	<jsp:doBody></jsp:doBody>
        <span></span>
    </div>
</div>