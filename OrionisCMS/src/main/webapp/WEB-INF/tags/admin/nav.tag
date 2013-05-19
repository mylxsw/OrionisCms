<%@tag import="name.orionis.cms.utils.NavUtils"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="nav_data" required="true" type="name.orionis.cms.core.rbac.dto.NavItem"  %>
<ul class="u-menu u-menu-gp u-menu-show">
	<%
		String nav = NavUtils.genNavManager(nav_data);
		out.println(nav);
	%>
    
</ul>