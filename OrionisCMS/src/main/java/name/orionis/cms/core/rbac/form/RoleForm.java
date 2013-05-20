package name.orionis.cms.core.rbac.form;

import javax.validation.constraints.Size;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.Status;
/**
 * Role Form
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
public class RoleForm extends Form<RbacRole> {
	
	@Size(min = 2, max = 20)
    private String roleName;
	
	private int status = 0;
	
	@Override
	public RbacRole toEntity() {
		RbacRole role = new RbacRole();
		role.setRoleName(roleName);
		role.setStatus(status == 1 ? Status.ENABLED : Status.DISABLED);
		return role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
