<%@page import="name.orionis.helper.reflection.ActionMethodInfo"%>
<%@page import="name.orionis.helper.reflection.ControllerClassInfo"%>
<%@page import="name.orionis.cms.core.rbac.model.RbacPermission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="admin" %>
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
.group{
	margin-right:10px;
}
</style>
<admin:crumb>
	<admin:crumb_item name="主面板" first="true"></admin:crumb_item>
	<admin:crumb_item name="角色管理" url="javascript:$.orionis.updateMain('rbac/role/list');"></admin:crumb_item>
	<admin:crumb_item name="权限列表"></admin:crumb_item>
</admin:crumb>

<%
		Map<String, RbacPermission> permission_list = (Map<String, RbacPermission>) request.getAttribute("permissions");
		StringBuilder sb = new StringBuilder();
    	List<ControllerClassInfo> result = (List<ControllerClassInfo>)request.getAttribute("tree");
    	for(ControllerClassInfo c : result){
    		sb.append("<ul><span style='border:1px solid black; background:rgb(9, 159, 230);color:white;font-weight:bold; margin:10px; padding:10px;display:block;'>" + c.getClassName() + " <span style='color:rgb(226, 59, 36)'>[ " + c.getRemark() + " ]</span></span> ");
			
			List<ActionMethodInfo> methods = c.getMethods();
			for(ActionMethodInfo m : methods){
				sb.append("<li style=\"margin-left:40px;width:800px;\">" 
					+ "<span class='label group '>"
					+ m.getGroup()
					+ " </span>"
					+ m.getMethodName() + "<span style='color:red'> [ ");
					
				String[] urls = m.getUrl();
				for(int i=0; i < urls.length; i ++){
					sb.append("<a target='_blank' href='" 
						+  (urls[i].indexOf("/") == 0 ? urls[i].substring(1) : urls[i]) + "'>" + urls[i] 
						+ "</a> ");
				}
				
				sb.append(" ] </span>" );
				
				if(permission_list.containsKey(c.getClassName() + "." + m.getMethodName())){
					sb.append("<span class='right_btn_cancel' "
						 + " permission-id='" 
						 + permission_list.get(c.getClassName() + "." + m.getMethodName()).getId() 
						 + "' "
					 	 + ">取消</span>");
				}else{
					sb.append("<span class='right_btn' " 
						 + " permission-controller='" + c.getClassName() 
						 + "' permission-method='" + m.getMethodName()
						 + "' permission-url='" + (m.getUrl())[0]
						 + "' permission-label='" + m.getRemark()
						 + "' "
						 + ">授权</span>");
				}
				
				sb.append(" <span style='color:gray;display:inline-block; float:right'>[ " + m.getRemark() + " ]</span> "
					+ "</li>");
			}
			
			sb.append("</ul>");
    	}
    	
    	request.setAttribute("_result_", sb.toString());
    %>
<admin:module title="权限列表">
	<span class="label label-success">[ ${role.roleName } ]</span>
	${_result_ }
</admin:module>
<script>
	$(function(){
		$(".right_btn_cancel").parent("li").addClass("permission_selected");
	$(".right_btn").click(function(){
		$.post($.orionis.url("rbac/permission/add") , 
			{	roleId:"${id}", 
				permissionName: $(this).attr("permission-label"),
				controller:$(this).attr("permission-controller"),
				method:$(this).attr("permission-method"), 
				url:$(this).attr("permission-url")
			}, 
			function(data){
				if(data.status==1){
					$.orionis.updateMain("rbac/permission/add?id=${id}");
				}
			}, "json"
		);
	});
	$(".right_btn_cancel").click(function(){
		$.get($.orionis.url("rbac/permission/delete") , 
			{	roleId:"${id}",
				id:$(this).attr("permission-id")
			}, 
			function(data){
				if(data.status==1){
					$.orionis.updateMain("rbac/permission/add?id=${id}");
				}
			}, "json"
		);
	});
	var label_pre = "";
	var label_color = "";
	var badge = ['badge-success','badge-warning','badge-important','badge-info','badge-inverse',''];
	$(".group").each(function(){
		var val = $(this).text();
		if(val != label_pre){
			label_pre = val;
			$(this).parent("li").css("margin-top", "20px");
			label_color = randomVal(badge);
		}
		$(this).addClass(label_color);
	});
	});
	/**
 * 从数组中随机取出一个值
 * @param arr
 * @returns
 */
function randomVal(arr){
  arr.sort(function(){
    return Math.random() - 0.5;
  });
  return arr[0];
}
</script>
