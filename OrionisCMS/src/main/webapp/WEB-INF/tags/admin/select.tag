<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="label" required="true"  %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<%@ attribute name="data" required="true" description="数据map" type="java.util.Map" %>
<%@ attribute name="selected" required="false" description="默认key" %>
<div class="control-group" id="group-${id }">
   <label class="control-label" for="${id }">${label }</label>
   <div class="controls">
     <select id="${id }" name="${name }"  class="<c:out value="${clazz }" default="" />" <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach> >
		<c:forEach items="${data }" var="d">
			<option value="${d.key }" <c:if test="${selected == d.key }">selected</c:if>  >${d.value }</option>
		</c:forEach>
	</select>
   </div>
</div>
