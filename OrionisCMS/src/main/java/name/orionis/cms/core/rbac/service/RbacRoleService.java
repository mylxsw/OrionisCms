package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.rbac.model.RbacRole;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.core.rbac.model.RbacRole.class })
public interface RbacRoleService {
	/**
	 * Update Role
	 * @param role
	 * @return
	 */
	public RbacRole updateRbacRole(RbacRole role);
}
