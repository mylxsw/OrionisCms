<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="name.orionis.helper.reflection.ActionMethodInfo"%>
<%@page import="name.orionis.helper.reflection.ControllerClassInfo"%>
<%@page import="name.orionis.cms.core.rbac.model.RbacPermission"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<admin:header>
<style type="text/css">
li {
}
li:hover{
	background:#CCC;
}
.right_btn,.right_btn_cancel{
	margin-left:20px;
	cursor:pointer;
	float:right;
}
.right_btn_cancel{
	color:red;
}
.permission_selected{
	background: rgb(233, 233, 233);
}
</style>
</admin:header>
    <div class="span12 main" id="ajax_main">
		<div>
			<%
				Map<String, RbacPermission> permission_list = (Map<String, RbacPermission>) request.getAttribute("permissions");
				
		    	List<ControllerClassInfo> result = (List<ControllerClassInfo>)request.getAttribute("tree");
		    	for(ControllerClassInfo c : result){
		    		out.write("<ul><span style='border:1px solid black; background:rgb(9, 159, 230);color:white;font-weight:bold; margin:10px; padding:10px;display:block;'>" + c.getClassName() + " <span style='color:rgb(226, 59, 36)'>[ " + c.getRemark() + " ]</span></span><br/> ");
					
					List<ActionMethodInfo> methods = c.getMethods();
					for(ActionMethodInfo m : methods){
						out.write("<li style=\"margin-left:40px;width:800px;\">" + m.getMethodName() + "<span style='color:red'> [ ");
							
						String[] urls = m.getUrl();
						for(int i=0; i < urls.length; i ++){
							out.write("<a target='_blank' href='" 
								+ basePath + (urls[i].indexOf("/") == 0 ? urls[i].substring(1) : urls[i]) + ".html'>" + urls[i] 
								+ "</a> ");
						}
						
						out.write(" ] </span>" );
						
							out.write("<span class='right_btn' " 
								 + " permission-controller='" + c.getClassName() 
								 + "' permission-method='" + m.getMethodName()
								 + "' permission-url='" + (m.getUrl())[0]
								 + "' permission-label='" + m.getRemark()
								 + "' "
								 + ">Select</span>");
						
						out.write(" <span style='color:gray;display:inline-block; float:right'>[ " + m.getRemark() + " ]</span> "
							+ "</li>");
					}
					
					out.write("</ul>");
		    	}
		    %>
		</div>
    </div><!--/span-->
 <admin:footer>
 	<script>
 		$(function(){
 			$(".right_btn").click(function(){
 				window.opener.fillInfo($(this).attr("permission-label"), $(this).attr("permission-url"));
 				window.close();
 			});
 		});
 	</script>
 </admin:footer>