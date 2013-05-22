<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="label" required="false"  %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="tip" required="false" description="提示"%>
<%@ attribute name="theme" required="false" description="主题" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<%@ attribute name="value" %>
<%@ attribute name="after" required="false" fragment="true" description="文本框后置内容，如验证码等" %>
<div class="control-group">
    <label class="control-label"><c:out value="${label }" default="" escapeXml="false" /></label>
    <div class="controls">
        <input type='text' name="<c:out value="${name }" />" value='<%
        	if(value != null){
        		if(value.endsWith(".0")){
        			out.print(value.substring(0, value.length() - 2));
        		}else{
        			out.print(value);
        		}
        	}
        %>' class="u-ipt datetimepicker <c:out value="${clazz }" default="" />" <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach>/>
        <jsp:invoke fragment="after"></jsp:invoke>
        <span><c:out value="${tip }" default="" escapeXml="false" /></span>
    </div>
</div>
