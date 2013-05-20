<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="true" description="Title" %>
<div class="row-fluid o-module o-module-big">
	<h4>${title }</h4>
	<div>
		<jsp:doBody></jsp:doBody>
	</div>
</div>