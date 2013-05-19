<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="label" required="true"  %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<div class="control-group" id="group-${id }">
   <label class="control-label" for="${id }">${label }</label>
   <div class="controls">
	 <textarea style="width:484px; height:94px"  id="${id }" name="${name }" placeholder="${label }"  <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach> >${value }</textarea>
   </div>
</div>
