<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="label" required="true"  %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="tip" required="false" description="提示"%>
<%@ attribute name="id"  %>
<%@ attribute name="type" required="false"  description="类型" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<div class="control-group" id="group-${id }">
   <label class="control-label" for="${id }">${label }</label>
   <div class="controls">
     <input type="<c:out value="${type }" default="text" />" id="${id }" name="${name }" placeholder="${label }" <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach> >
   </div>
</div>