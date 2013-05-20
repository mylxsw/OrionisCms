package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.rbac.model.RbacRole;


public class RbacRoleServiceImpl implements RbacRoleService {
	@Override
	public RbacRole updateRbacRole(RbacRole role){
		RbacRole rbacRole = RbacRole.findRbacRole(role.getId());
		rbacRole.setRoleName(role.getRoleName());
		rbacRole.setStatus(role.getStatus());
		return rbacRole.merge();
	}
}
