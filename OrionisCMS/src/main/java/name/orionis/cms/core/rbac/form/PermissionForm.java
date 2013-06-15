package name.orionis.cms.core.rbac.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.core.rbac.model.RbacPermission;
import name.orionis.cms.core.rbac.model.RbacRole;
/**
 * Permission Form
 * @author orionis
 * @2013-5-18
 * Site : http://blog.orionis.name
 *
 */
public class PermissionForm extends Form<RbacPermission> {

    @NotNull(message="权限名称不能为空!")
    @Size(max = 40, message="权限名不能大于40字符!")
    private String permissionName;

    @NotNull(message="控制器名不能为空!")
    @Size(max = 220, message="控制器名不能大于220字符!")
    private String controller;

    @NotNull(message="方法名不能为空!")
    @Size(max = 220, message="方法名不能大于220字符!")
    private String method;

    @NotNull(message="URL地址不能为空!")
    @Size(max = 255, message="url不能大于255字符!")
    private String url;

    @Min(message="角色ID不能为空!", value = 0)
    private long roleId;
    
    
	@Override
	public RbacPermission toEntity() {
		RbacPermission permission = new RbacPermission();
		permission.setController(controller);
		permission.setMethod(method);
		permission.setPermissionName(permissionName);
		permission.setUrl(url);
		permission.setRbacRole(RbacRole.findRbacRole(roleId));
		
		return permission;
	}


	public String getPermissionName() {
		return permissionName;
	}


	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}


	public String getController() {
		return controller;
	}


	public void setController(String controller) {
		this.controller = controller;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public long getRoleId() {
		return roleId;
	}


	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
}
