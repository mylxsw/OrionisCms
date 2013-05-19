package name.orionis.cms.core.rbac.form;

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

    @NotNull
    @Size(max = 40)
    private String permissionName;

    @NotNull
    @Size(max = 40)
    private String controller;

    @NotNull
    @Size(max = 40)
    private String method;

    @NotNull
    @Size(max = 255)
    private String url;

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
